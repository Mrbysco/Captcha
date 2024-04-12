package com.mrbysco.captcha;

import com.mrbysco.captcha.network.RequireCaptchaData;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class CaptchaFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(Constants.REQUIRE_CAPTCHA, (client, handler, buf, responseSender) -> {
			RequireCaptchaData data = RequireCaptchaData.decode(buf);

			client.execute(() -> {
				//Open Captcha Screen
				com.mrbysco.captcha.client.ScreenHandler.openCaptcha(data.captchaName(), data.code(),
						data.maxCompletionTime(), data.configuredWords());
			});
		});
	}
}
