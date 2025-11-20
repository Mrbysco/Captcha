package com.mrbysco.captcha.network;

import com.mrbysco.captcha.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record CompletedCaptcha(String code) implements CustomPacketPayload {
	public static final StreamCodec<FriendlyByteBuf, CompletedCaptcha> CODEC = StreamCodec.composite(
			ByteBufCodecs.STRING_UTF8,
			CompletedCaptcha::code,
			CompletedCaptcha::new);
	public static final Type<CompletedCaptcha> ID = new Type<>(Constants.COMPLETE_CAPTCHA);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}
}
