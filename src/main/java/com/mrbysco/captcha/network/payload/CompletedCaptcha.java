package com.mrbysco.captcha.network.payload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CompletedCaptcha(String code) implements CustomPacketPayload {
	public static final ResourceLocation ID = new ResourceLocation("captcha", "completed_captcha");

	public CompletedCaptcha(final FriendlyByteBuf packetBuffer) {
		this(packetBuffer.readUtf());
	}

	@Override
	public void write(FriendlyByteBuf buf) {
		buf.writeUtf(code);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}
}