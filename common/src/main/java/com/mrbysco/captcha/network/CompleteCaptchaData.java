package com.mrbysco.captcha.network;

import net.minecraft.network.FriendlyByteBuf;

public record CompleteCaptchaData(String code) {
	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(code);
	}

	public static CompleteCaptchaData decode(final FriendlyByteBuf packetBuffer) {
		return new CompleteCaptchaData(packetBuffer.readUtf());
	}
}
