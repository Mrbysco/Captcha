package com.mrbysco.captcha.network.message;

import com.mrbysco.captcha.network.RequireCaptchaData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkEvent.Context;

import java.io.Serial;
import java.util.List;
import java.util.function.Supplier;

public class RequireCaptchaMessage {
	private final RequireCaptchaData data;

	public RequireCaptchaMessage(RequireCaptchaData data) {
		this.data = data;
	}

	public void encode(FriendlyByteBuf buf) {
		data.encode(buf);
	}

	public static RequireCaptchaMessage decode(final FriendlyByteBuf packetBuffer) {
		return new RequireCaptchaMessage(RequireCaptchaData.decode(packetBuffer));
	}

	public void handle(Supplier<Context> context) {
		Context ctx = context.get();
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isClient()) {
				OpenCaptcha.open(this.data).run();
			}
		});
		ctx.setPacketHandled(true);
	}

	private static class OpenCaptcha {
		private static SafeRunnable open(RequireCaptchaData data) {
			return new SafeRunnable() {
				@Serial
				private static final long serialVersionUID = 1L;

				@Override
				public void run() {
					//Open Captcha Screen
					com.mrbysco.captcha.client.ScreenHandler.openCaptcha(data.captchaName(), data.code(),
							data.maxCompletionTime(), data.configuredWords());
				}
			};
		}
	}
}