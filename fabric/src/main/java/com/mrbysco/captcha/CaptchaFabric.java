package com.mrbysco.captcha;

import com.mrbysco.captcha.callback.PlayerTickCallback;
import com.mrbysco.captcha.commands.CaptchaCommands;
import com.mrbysco.captcha.config.CaptchaConfigFabric;
import com.mrbysco.captcha.network.CompleteCaptchaData;
import com.mrbysco.captcha.util.CaptchaManager;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.InteractionResult;

public class CaptchaFabric implements ModInitializer {
	public static ConfigHolder<CaptchaConfigFabric> config;

	@Override
	public void onInitialize() {
		config = AutoConfig.register(CaptchaConfigFabric.class, Toml4jConfigSerializer::new);

		CommonClass.init();

		CommandRegistrationCallback.EVENT.register((commandDispatcher, buildContext, selection) -> {
			CaptchaCommands.initializeCommands(commandDispatcher);
		});

		PlayerTickCallback.EVENT.register((player) -> {
			CommonClass.onPlayerTick(player);
			return InteractionResult.PASS;
		});

		ServerPlayNetworking.registerGlobalReceiver(Constants.COMPLETE_CAPTCHA, (server, player, handler, buf, responseSender) -> {
			CompleteCaptchaData data = CompleteCaptchaData.decode(buf);

			server.execute(() -> {
				//Complete Captcha
				CaptchaManager.setCompletedRecently(player.getUUID(), data.code());
			});
		});
	}
}
