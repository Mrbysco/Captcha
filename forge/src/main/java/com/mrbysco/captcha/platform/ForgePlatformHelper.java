package com.mrbysco.captcha.platform;

import com.mrbysco.captcha.Constants;
import com.mrbysco.captcha.client.CaptchaEnum;
import com.mrbysco.captcha.config.CaptchaConfigForge;
import com.mrbysco.captcha.network.CompleteCaptchaData;
import com.mrbysco.captcha.network.NetworkHandler;
import com.mrbysco.captcha.network.RequireCaptchaData;
import com.mrbysco.captcha.network.message.CompletedCaptchaMessage;
import com.mrbysco.captcha.network.message.RequireCaptchaMessage;
import com.mrbysco.captcha.platform.services.IPlatformHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;

public class ForgePlatformHelper implements IPlatformHelper {


	@Override
	public int getCaptchaCooldown() {
		return CaptchaConfigForge.COMMON.captchaCooldown.get();
	}

	@Override
	public int getGracePeriod() {
		return CaptchaConfigForge.COMMON.gracePeriod.get();
	}

	@Override
	public int getCaptchaTime() {
		return CaptchaConfigForge.COMMON.captchaTime.get();
	}

	@Override
	public List<? extends String> getTextCaptchaWords() {
		return CaptchaConfigForge.COMMON.textCaptchaWords.get();
	}

	@Override
	public int getAdditionMaxX() {
		return CaptchaConfigForge.COMMON.additionMaxX.get();
	}

	@Override
	public int getAdditionMaxY() {
		return CaptchaConfigForge.COMMON.additionMaxY.get();
	}

	@Override
	public int getSubtractionMaxX() {
		return CaptchaConfigForge.COMMON.subtractionMaxX.get();
	}

	@Override
	public int getSubtractionMaxY() {
		return CaptchaConfigForge.COMMON.subtractionMaxY.get();
	}

	@Override
	public int getMultiplicationMaxX() {
		return CaptchaConfigForge.COMMON.multiplicationMaxX.get();
	}

	@Override
	public int getMultiplicationMaxY() {
		return CaptchaConfigForge.COMMON.multiplicationMaxY.get();
	}

	@Override
	public int getDivisionMaxX() {
		return CaptchaConfigForge.COMMON.divisionMaxX.get();
	}

	@Override
	public int getDivisionMaxY() {
		return CaptchaConfigForge.COMMON.divisionMaxY.get();
	}

	@Override
	public void sendRequireCaptchaMessage(ServerPlayer serverPlayer, String captchaName, String code) {
		RequireCaptchaData data = new RequireCaptchaData(
				captchaName, code,
				CaptchaConfigForge.COMMON.captchaTime.get(), CaptchaConfigForge.COMMON.textCaptchaWords.get());

		NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer),
				new RequireCaptchaMessage(data));
	}

	@Override
	public void sendCompletedCaptchaMessage(String code) {
		NetworkHandler.CHANNEL.send(PacketDistributor.SERVER.noArg(), new CompletedCaptchaMessage(new CompleteCaptchaData(code)));
	}
}
