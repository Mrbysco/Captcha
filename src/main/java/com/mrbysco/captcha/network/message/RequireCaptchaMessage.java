package com.mrbysco.captcha.network.message;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkEvent.Context;

import java.io.Serial;
import java.util.function.Supplier;

public class RequireCaptchaMessage {
	private final String captchaName;
	private final String code;
	private final int maxCompletionTime;

	public RequireCaptchaMessage(String captchaName, String code, int maxCompletionTime) {
		this.captchaName = captchaName;
		this.code = code;
		this.maxCompletionTime = maxCompletionTime;
	}

	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(captchaName);
		buf.writeUtf(code);
		buf.writeInt(maxCompletionTime);
	}

	public static RequireCaptchaMessage decode(final FriendlyByteBuf packetBuffer) {
		return new RequireCaptchaMessage(packetBuffer.readUtf(), packetBuffer.readUtf(), packetBuffer.readInt());
	}

	public void handle(Supplier<Context> context) {
		NetworkEvent.Context ctx = context.get();
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isClient()) {
				OpenCaptcha.open(this.captchaName, this.code, this.maxCompletionTime).run();
			}
		});
		ctx.setPacketHandled(true);
	}

	private static class OpenCaptcha {
		private static SafeRunnable open(String captchaName, String code, int maxCompletionTime) {
			return new SafeRunnable() {
				@Serial
				private static final long serialVersionUID = 1L;

				@Override
				public void run() {
					//Open Captcha Screen
					com.mrbysco.captcha.client.ScreenHandler.openCaptcha(captchaName, code, maxCompletionTime);
				}
			};
		}
	}
}