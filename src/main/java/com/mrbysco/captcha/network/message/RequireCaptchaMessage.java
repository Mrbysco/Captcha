package com.mrbysco.captcha.network.message;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkEvent.Context;

import java.io.Serial;
import java.util.List;
import java.util.function.Supplier;

public class RequireCaptchaMessage {
	private final String captchaName;
	private final String code;
	private final int maxCompletionTime;
	private final List<? extends String> configuredWords;

	public RequireCaptchaMessage(String captchaName, String code, int maxCompletionTime, List<? extends String> configuredWords) {
		this.captchaName = captchaName;
		this.code = code;
		this.maxCompletionTime = maxCompletionTime;
		this.configuredWords = configuredWords;
	}

	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(captchaName);
		buf.writeUtf(code);
		buf.writeInt(maxCompletionTime);
		buf.writeCollection(this.configuredWords, FriendlyByteBuf::writeUtf);
	}

	public static RequireCaptchaMessage decode(final FriendlyByteBuf packetBuffer) {
		return new RequireCaptchaMessage(packetBuffer.readUtf(), packetBuffer.readUtf(), packetBuffer.readInt(), packetBuffer.readList(FriendlyByteBuf::readUtf));
	}

	public void handle(Supplier<Context> context) {
		NetworkEvent.Context ctx = context.get();
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isClient()) {
				OpenCaptcha.open(this.captchaName, this.code, this.maxCompletionTime, this.configuredWords).run();
			}
		});
		ctx.setPacketHandled(true);
	}

	private static class OpenCaptcha {
		private static SafeRunnable open(String captchaName, String code, int maxCompletionTime, List<? extends String> configuredWords) {
			return new SafeRunnable() {
				@Serial
				private static final long serialVersionUID = 1L;

				@Override
				public void run() {
					//Open Captcha Screen
					com.mrbysco.captcha.client.ScreenHandler.openCaptcha(captchaName, code, maxCompletionTime, configuredWords);
				}
			};
		}
	}
}