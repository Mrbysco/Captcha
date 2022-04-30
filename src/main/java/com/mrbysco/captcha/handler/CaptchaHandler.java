package com.mrbysco.captcha.handler;

import com.mrbysco.captcha.client.CaptchaEnum;
import com.mrbysco.captcha.config.CaptchaConfig;
import com.mrbysco.captcha.network.NetworkHandler;
import com.mrbysco.captcha.network.message.RequireCaptchaMessage;
import com.mrbysco.captcha.util.CaptchaManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.Random;
import java.util.UUID;

public class CaptchaHandler {
	private static final Random random = new Random();

	public static void onPlayerTick(PlayerTickEvent event) {
		Player player = event.player;
		if (event.phase == Phase.END && event.side.isServer() && player != null && !player.isCreative() && !player.isSpectator()) {
			Level level = player.level;
			if (!player.isSpectator() && level.getGameTime() % 50 == 0 && level.random.nextInt(10) < 2) {
				UUID uuid = player.getUUID();
				if (!CaptchaManager.completedCaptchaRecently(uuid)) {
					String code = CaptchaManager.getActiveCode(uuid);
					if (code == null) {
						code = CaptchaManager.applyRandomCode(uuid);
					}
					if (code != null && !code.isEmpty()) {

						NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
								new RequireCaptchaMessage(CaptchaEnum.getRandom(random).getCaptchaName(), code, CaptchaConfig.COMMON.captchaTime.get()));
					}
				}
			}
		}
	}
}