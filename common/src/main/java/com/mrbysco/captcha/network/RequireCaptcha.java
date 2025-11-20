package com.mrbysco.captcha.network;

import com.mrbysco.captcha.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.List;

public record RequireCaptcha(String captchaName, String code, int maxCompletionTime,
                             List<String> configuredWords) implements CustomPacketPayload {
	public static final StreamCodec<FriendlyByteBuf, RequireCaptcha> CODEC = StreamCodec.composite(
			ByteBufCodecs.STRING_UTF8,
			RequireCaptcha::captchaName,
			ByteBufCodecs.STRING_UTF8,
			RequireCaptcha::code,
			ByteBufCodecs.INT,
			RequireCaptcha::maxCompletionTime,
			ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()),
			RequireCaptcha::configuredWords,
			RequireCaptcha::new);
	public static final Type<RequireCaptcha> ID = new Type<>(Constants.REQUIRE_CAPTCHA);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}
}
