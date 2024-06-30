package com.mrbysco.captcha;

import com.mrbysco.captcha.commands.CaptchaCommands;
import com.mrbysco.captcha.config.CaptchaConfigNeoForge;
import com.mrbysco.captcha.network.NetworkHandler;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.TickEvent;

@Mod(Constants.MOD_ID)
public class CaptchaNeoForge {

	public CaptchaNeoForge(IEventBus eventBus) {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CaptchaConfigNeoForge.commonSpec);

		CommonClass.init();

		eventBus.addListener(NetworkHandler::setupPackets);

		NeoForge.EVENT_BUS.addListener(this::onCommandRegister);
		NeoForge.EVENT_BUS.addListener(this::onPlayerTick);
	}

	private void onCommandRegister(RegisterCommandsEvent event) {
		CaptchaCommands.initializeCommands(event.getDispatcher());
	}

	private void onPlayerTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		if (event.phase == TickEvent.Phase.END && event.side.isServer() && player != null && !player.isCreative() && !player.isSpectator()) {
			CommonClass.onPlayerTick(player);
		}
	}
}