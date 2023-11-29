package com.mrbysco.captcha.network;

import com.mrbysco.captcha.Captcha;
import com.mrbysco.captcha.network.message.CompletedCaptchaMessage;
import com.mrbysco.captcha.network.message.RequireCaptchaMessage;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.simple.SimpleChannel;

public class NetworkHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Captcha.MOD_ID, "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);

	private static int id = 0;

	public static void init() {
		CHANNEL.registerMessage(id++, RequireCaptchaMessage.class, RequireCaptchaMessage::encode, RequireCaptchaMessage::decode, RequireCaptchaMessage::handle);
		CHANNEL.registerMessage(id++, CompletedCaptchaMessage.class, CompletedCaptchaMessage::encode, CompletedCaptchaMessage::decode, CompletedCaptchaMessage::handle);
	}
}