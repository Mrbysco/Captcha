package com.mrbysco.captcha.network;

import com.mrbysco.captcha.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.List;

public record RequireCaptcha(String captchaName, String code, int maxCompletionTime,
                             List<? extends String> configuredWords) implements CustomPacketPayload {
	public static final StreamCodec<FriendlyByteBuf, RequireCaptcha> CODEC = CustomPacketPayload.codec(
			RequireCaptcha::write,
			RequireCaptcha::new);
	public static final Type<RequireCaptcha> ID = new Type<>(Constants.REQUIRE_CAPTCHA);

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
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}
}
