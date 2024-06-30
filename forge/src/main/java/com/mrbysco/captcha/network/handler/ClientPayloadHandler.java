package com.mrbysco.captcha.network.handler;

import com.mrbysco.captcha.network.RequireCaptcha;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {
	private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

	public static ClientPayloadHandler getInstance() {
		return INSTANCE;
	}

	public void handleData(final RequireCaptcha data, final IPayloadContext context) {
		context.enqueueWork(() -> {
					//Open Captcha Screen
					com.mrbysco.captcha.client.ScreenHandler.openCaptcha(
							data.captchaName(), data.code(), data.maxCompletionTime(), data.configuredWords());
				})
				.exceptionally(e -> {
					// Handle exception
					context.disconnect(Component.translatable("captcha.networking.failed", e.getMessage()));
					return null;
				});
	}
}
