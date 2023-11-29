package com.mrbysco.captcha.network.message;

import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.neoforge.network.NetworkEvent.Context;

import java.util.List;

public class RequireCaptchaMessage {
	private final String captchaName;
	private final String code;
	private final int maxCompletionTime;
	private final List<? extends String> configuredWords;

	public RequireCaptchaMessage(String captchaName, String code, int maxCompletionTime, List<? extends String> configuredWords) {
		this.captchaName = captchaName;
		this.code = code;
		this.maxCompletionTime = maxCompletionTime;
		this.configuredWords = configuredWords;
	}

	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(captchaName);
		buf.writeUtf(code);
		buf.writeInt(maxCompletionTime);
		buf.writeCollection(this.configuredWords, FriendlyByteBuf::writeUtf);
	}

	public static RequireCaptchaMessage decode(final FriendlyByteBuf packetBuffer) {
		return new RequireCaptchaMessage(packetBuffer.readUtf(), packetBuffer.readUtf(), packetBuffer.readInt(), packetBuffer.readList(FriendlyByteBuf::readUtf));
	}

	public void handle(Context ctx) {
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isClient()) {
				//Open Captcha Screen
				com.mrbysco.captcha.client.ScreenHandler.openCaptcha(captchaName, code, maxCompletionTime, configuredWords);
			}
		});
		ctx.setPacketHandled(true);
	}
}