package com.mrbysco.captcha.platform;

import com.mrbysco.captcha.config.CaptchaConfig;
import com.mrbysco.captcha.network.CompletedCaptcha;
import com.mrbysco.captcha.network.RequireCaptcha;
import com.mrbysco.captcha.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

public class FabricPlatformHelper implements IPlatformHelper {

	@Override
	public void sendRequireCaptchaMessage(ServerPlayer serverPlayer, String captchaName, String code) {
		RequireCaptcha payload = new RequireCaptcha(
				captchaName, code,
				CaptchaConfig.COMMON.captchaTime.get(),
				CaptchaConfig.COMMON.textCaptchaWords.get().stream().map(Object::toString).toList());
		ServerPlayNetworking.send(serverPlayer, payload);
	}

	@Override
	public void sendCompletedCaptchaMessage(String code) {
		CompletedCaptcha payload = new CompletedCaptcha(code);
		ClientPlayNetworking.send(payload);
	}
}
