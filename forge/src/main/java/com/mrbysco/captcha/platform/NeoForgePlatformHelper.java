package com.mrbysco.captcha.platform;

import com.mrbysco.captcha.config.CaptchaConfig;
import com.mrbysco.captcha.network.CompletedCaptcha;
import com.mrbysco.captcha.network.RequireCaptcha;
import com.mrbysco.captcha.platform.services.IPlatformHelper;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class NeoForgePlatformHelper implements IPlatformHelper {

	@Override
	public void sendRequireCaptchaMessage(ServerPlayer serverPlayer, String captchaName, String code) {
		RequireCaptcha data = new RequireCaptcha(
				captchaName, code,
				CaptchaConfig.COMMON.captchaTime.get(),
				CaptchaConfig.COMMON.textCaptchaWords.get().stream().map(Object::toString).toList());

		serverPlayer.connection.send(data);
	}

	@Override
	public void sendCompletedCaptchaMessage(String code) {
		ClientPacketDistributor.sendToServer(new CompletedCaptcha(code));
	}
}
