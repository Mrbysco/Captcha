package com.mrbysco.captcha.network.payload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record RequireCaptcha(String captchaName, String code, int maxCompletionTime,
							 List<? extends String> configuredWords) implements CustomPacketPayload {
	public static final ResourceLocation ID = new ResourceLocation("captcha", "require_captcha");

	public RequireCaptcha(final FriendlyByteBuf packetBuffer) {
		this(packetBuffer.readUtf(), packetBuffer.readUtf(), packetBuffer.readInt(), packetBuffer.readList(FriendlyByteBuf::readUtf));
	}

	public void write(final FriendlyByteBuf buf) {
		buf.writeUtf(captchaName);
		buf.writeUtf(code);
		buf.writeInt(maxCompletionTime);
		buf.writeCollection(this.configuredWords, FriendlyByteBuf::writeUtf);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}
}