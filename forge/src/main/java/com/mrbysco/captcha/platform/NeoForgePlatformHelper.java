package com.mrbysco.captcha.platform;

import com.mrbysco.captcha.config.CaptchaConfigNeoForge;
import com.mrbysco.captcha.network.CompletedCaptcha;
import com.mrbysco.captcha.network.RequireCaptcha;
import com.mrbysco.captcha.platform.services.IPlatformHelper;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class NeoForgePlatformHelper implements IPlatformHelper {
	
	@Override
	public int getCaptchaCooldown() {
		return CaptchaConfigNeoForge.COMMON.captchaCooldown.get();
	}

	@Override
	public int getGracePeriod() {
		return CaptchaConfigNeoForge.COMMON.gracePeriod.get();
	}

	@Override
	public int getAdditionMaxX() {
		return CaptchaConfigNeoForge.COMMON.additionMaxX.get();
	}

	@Override
	public int getAdditionMaxY() {
		return CaptchaConfigNeoForge.COMMON.additionMaxY.get();
	}

	@Override
	public int getSubtractionMaxX() {
		return CaptchaConfigNeoForge.COMMON.subtractionMaxX.get();
	}

	@Override
	public int getSubtractionMaxY() {
		return CaptchaConfigNeoForge.COMMON.subtractionMaxY.get();
	}

	@Override
	public int getMultiplicationMaxX() {
		return CaptchaConfigNeoForge.COMMON.multiplicationMaxX.get();
	}

	@Override
	public int getMultiplicationMaxY() {
		return CaptchaConfigNeoForge.COMMON.multiplicationMaxY.get();
	}

	@Override
	public int getDivisionMaxX() {
		return CaptchaConfigNeoForge.COMMON.divisionMaxX.get();
	}

	@Override
	public int getDivisionMaxY() {
		return CaptchaConfigNeoForge.COMMON.divisionMaxY.get();
	}

	@Override
	public void sendRequireCaptchaMessage(ServerPlayer serverPlayer, String captchaName, String code) {
		RequireCaptcha data = new RequireCaptcha(
				captchaName, code,
				CaptchaConfigNeoForge.COMMON.captchaTime.get(), CaptchaConfigNeoForge.COMMON.textCaptchaWords.get());

		serverPlayer.connection.send(data);
	}

	@Override
	public void sendCompletedCaptchaMessage(String code) {
		ClientPacketDistributor.sendToServer(new CompletedCaptcha(code));
	}
}
