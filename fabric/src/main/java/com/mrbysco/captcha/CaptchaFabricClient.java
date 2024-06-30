package com.mrbysco.captcha;

import com.mrbysco.captcha.network.RequireCaptcha;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class CaptchaFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(RequireCaptcha.ID, (payload, context) -> {
			context.client().execute(() -> {
				//Open Captcha Screen
				com.mrbysco.captcha.client.ScreenHandler.openCaptcha(payload.captchaName(), payload.code(),
						payload.maxCompletionTime(), payload.configuredWords());
			});
		});
	}
}
