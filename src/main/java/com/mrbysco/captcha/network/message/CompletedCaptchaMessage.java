package com.mrbysco.captcha.network.message;

import com.mrbysco.captcha.util.CaptchaManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.network.NetworkEvent.Context;

import java.io.Serial;
import java.util.UUID;
import java.util.function.Supplier;

public class CompletedCaptchaMessage {
	private String code;

	public CompletedCaptchaMessage(String code){
		this.code = code;
	}

	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(code);
	}

	public static CompletedCaptchaMessage decode(final FriendlyByteBuf packetBuffer) {
		return new CompletedCaptchaMessage(packetBuffer.readUtf());
	}

	public void handle(Supplier<Context> context) {
		Context ctx = context.get();
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isServer() && ctx.getSender() != null) {
				CompleteCaptcha.complete(ctx.getSender().getUUID(), this.code).run();
			}
		});
		ctx.setPacketHandled(true);
	}

	private static class CompleteCaptcha {
		private static SafeRunnable complete(UUID uuid, String code) {
			return new SafeRunnable() {
				@Serial
				private static final long serialVersionUID = 1L;

				@Override
				public void run() {
					//Complete Captcha
					CaptchaManager.setCompletedRecently(uuid, code);
				}
			};
		}
	}
}