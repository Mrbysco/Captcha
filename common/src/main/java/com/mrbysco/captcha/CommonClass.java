package com.mrbysco.captcha;

import com.mrbysco.captcha.client.CaptchaEnum;
import com.mrbysco.captcha.platform.Services;
import com.mrbysco.captcha.util.CaptchaManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class CommonClass {

	public static void init() {

	}

	public static void onPlayerTick(Player player) {
		/*event.phase == Phase.END && event.side.isServer() && */
		if (player != null && !player.isCreative() && !player.isSpectator() && !Services.PLATFORM.cooldownDisabled()) {
			Level level = player.level();
			if (!player.isSpectator() && level.getGameTime() >= Services.PLATFORM.getGracePeriod() &&
					level.getGameTime() % 50 == 0 && level.random.nextInt(10) < 2) {
				UUID uuid = player.getUUID();
				if (!CaptchaManager.completedCaptchaRecently(uuid)) {
					String code = CaptchaManager.getActiveCode(uuid);
					if (code == null) {
						code = CaptchaManager.applyRandomCode(uuid);
					}
					if (code != null && !code.isEmpty()) {
						Services.PLATFORM.sendRequireCaptchaMessage((ServerPlayer) player,
								CaptchaEnum.getRandom(Constants.random).getCaptchaName(), code);
					}
				}
			}
		}
	}
}