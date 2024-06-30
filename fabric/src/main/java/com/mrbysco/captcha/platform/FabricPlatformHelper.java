package com.mrbysco.captcha.platform;

import com.mrbysco.captcha.Constants;
import com.mrbysco.captcha.config.CaptchaConfigFabric;
import com.mrbysco.captcha.network.CompletedCaptcha;
import com.mrbysco.captcha.network.RequireCaptcha;
import com.mrbysco.captcha.platform.services.IPlatformHelper;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class FabricPlatformHelper implements IPlatformHelper {

	@Override
	public int getCaptchaCooldown() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.general.captchaCooldown;
	}

	@Override
	public int getGracePeriod() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.general.gracePeriod;
	}

	@Override
	public int getAdditionMaxX() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.math.additionMaxX;
	}

	@Override
	public int getAdditionMaxY() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.math.additionMaxY;
	}

	@Override
	public int getSubtractionMaxX() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.math.subtractionMaxX;
	}

	@Override
	public int getSubtractionMaxY() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.math.subtractionMaxY;
	}

	@Override
	public int getMultiplicationMaxX() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.math.multiplicationMaxX;
	}

	@Override
	public int getMultiplicationMaxY() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.math.multiplicationMaxY;
	}

	@Override
	public int getDivisionMaxX() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.math.divisionMaxX;
	}

	@Override
	public int getDivisionMaxY() {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		return config.math.divisionMaxY;
	}

	@Override
	public void sendRequireCaptchaMessage(ServerPlayer serverPlayer, String captchaName, String code) {
		CaptchaConfigFabric config = AutoConfig.getConfigHolder(CaptchaConfigFabric.class).getConfig();
		RequireCaptcha data = new RequireCaptcha(
				captchaName, code,
				config.general.captchaTime, config.text.textCaptchaWords);

		FriendlyByteBuf buf = PacketByteBufs.create();
		data.write(buf);

		ServerPlayNetworking.send(serverPlayer, Constants.REQUIRE_CAPTCHA, buf);
	}

	@Override
	public void sendCompletedCaptchaMessage(String code) {
		CompletedCaptcha data = new CompletedCaptcha(code);

		FriendlyByteBuf buf = PacketByteBufs.create();
		data.write(buf);

		ClientPlayNetworking.send(Constants.COMPLETE_CAPTCHA, buf);
	}
}
