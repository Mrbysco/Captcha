package com.mrbysco.captcha.network.handler;

import com.mrbysco.captcha.network.payload.RequireCaptcha;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class ClientPayloadHandler {
	private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

	public static ClientPayloadHandler getInstance() {
		return INSTANCE;
	}

	public void handleData(final RequireCaptcha data, final PlayPayloadContext context) {
		context.workHandler().submitAsync(() -> {
					//Open Captcha Screen
					com.mrbysco.captcha.client.ScreenHandler.openCaptcha(
							data.captchaName(), data.code(), data.maxCompletionTime(), data.configuredWords());
				})
				.exceptionally(e -> {
					// Handle exception
					context.packetHandler().disconnect(Component.translatable("captcha.networking.failed", e.getMessage()));
					return null;
				});
	}
}
