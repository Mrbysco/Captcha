package com.mrbysco.captcha.network;

import com.mrbysco.captcha.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CompletedCaptcha(String code) implements CustomPacketPayload {
	public static final StreamCodec<FriendlyByteBuf, CompletedCaptcha> CODEC = CustomPacketPayload.codec(
			CompletedCaptcha::write,
			CompletedCaptcha::new);
	public static final Type<CompletedCaptcha> ID = new Type<>(Constants.COMPLETE_CAPTCHA);

	public CompletedCaptcha(final FriendlyByteBuf packetBuffer) {
		this(packetBuffer.readUtf());
	}

	public void write(FriendlyByteBuf buf) {
		buf.writeUtf(code);
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}
}
