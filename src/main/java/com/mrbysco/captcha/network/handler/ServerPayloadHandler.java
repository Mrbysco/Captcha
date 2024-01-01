package com.mrbysco.captcha.network.handler;

import com.mrbysco.captcha.network.payload.CompletedCaptcha;
import com.mrbysco.captcha.util.CaptchaManager;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class ServerPayloadHandler {
	private static final ServerPayloadHandler INSTANCE = new ServerPayloadHandler();

	public static ServerPayloadHandler getInstance() {
		return INSTANCE;
	}

	public void handleData(final CompletedCaptcha requireCaptcha, final PlayPayloadContext context) {
		// Do something with the data, on the main thread
		context.workHandler().submitAsync(() -> {
					//Complete Captcha
					if (context.player().isPresent()) {
						CaptchaManager.setCompletedRecently(context.player().get().getUUID(), requireCaptcha.code());
					}
				})
				.exceptionally(e -> {
					// Handle exception
					context.packetHandler().disconnect(Component.translatable("captcha.networking.failed", e.getMessage()));
					return null;
				});
	}
}
