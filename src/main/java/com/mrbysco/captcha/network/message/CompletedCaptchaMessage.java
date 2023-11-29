package com.mrbysco.captcha.network.message;

import com.mrbysco.captcha.util.CaptchaManager;
import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.neoforge.network.NetworkEvent.Context;

public class CompletedCaptchaMessage {
	private final String code;

	public CompletedCaptchaMessage(String code) {
		this.code = code;
	}

	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(code);
	}

	public static CompletedCaptchaMessage decode(final FriendlyByteBuf packetBuffer) {
		return new CompletedCaptchaMessage(packetBuffer.readUtf());
	}

	public void handle(Context ctx) {
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isServer() && ctx.getSender() != null) {
				//Complete Captcha
				CaptchaManager.setCompletedRecently(ctx.getSender().getUUID(), code);
			}
		});
		ctx.setPacketHandled(true);
	}
}