package com.mrbysco.captcha.network.handler;

import com.mrbysco.captcha.network.CompletedCaptcha;
import com.mrbysco.captcha.util.CaptchaManager;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {
	private static final ServerPayloadHandler INSTANCE = new ServerPayloadHandler();

	public static ServerPayloadHandler getInstance() {
		return INSTANCE;
	}

	public void handleData(final CompletedCaptcha requireCaptcha, final IPayloadContext context) {
		// Do something with the data, on the main thread
		context.enqueueWork(() -> {
					//Complete Captcha
					if (context.player() != null) {
						CaptchaManager.setCompletedRecently(context.player().getUUID(), requireCaptcha.code());
					}
				})
				.exceptionally(e -> {
					// Handle exception
					context.disconnect(Component.translatable("captcha.networking.failed", e.getMessage()));
					return null;
				});
	}
}
