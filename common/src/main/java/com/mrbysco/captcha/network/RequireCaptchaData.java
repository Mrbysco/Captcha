package com.mrbysco.captcha.network;

import net.minecraft.network.FriendlyByteBuf;

import java.util.List;

public record RequireCaptchaData(String captchaName, String code, int maxCompletionTime,
                                 List<? extends String> configuredWords) {
	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(captchaName);
		buf.writeUtf(code);
		buf.writeInt(maxCompletionTime);
		buf.writeCollection(this.configuredWords, FriendlyByteBuf::writeUtf);
	}

	public static RequireCaptchaData decode(final FriendlyByteBuf packetBuffer) {
		return new RequireCaptchaData(packetBuffer.readUtf(), packetBuffer.readUtf(), packetBuffer.readInt(),
				packetBuffer.readList(FriendlyByteBuf::readUtf));
	}
}
