package com.mrbysco.captcha;

import com.mrbysco.captcha.commands.CaptchaCommands;
import com.mrbysco.captcha.config.CaptchaConfigNeoForge;
import com.mrbysco.captcha.network.NetworkHandler;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@Mod(Constants.MOD_ID)
public class CaptchaNeoForge {

	public CaptchaNeoForge(IEventBus eventBus, ModContainer container, Dist dist) {
		container.registerConfig(ModConfig.Type.COMMON, CaptchaConfigNeoForge.commonSpec);

		CommonClass.init();

		eventBus.addListener(NetworkHandler::setupPackets);

		NeoForge.EVENT_BUS.addListener(this::onCommandRegister);
		NeoForge.EVENT_BUS.addListener(this::onPlayerTick);

		if (dist.isClient()) {
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}
	}

	private void onCommandRegister(RegisterCommandsEvent event) {
		CaptchaCommands.initializeCommands(event.getDispatcher());
	}

	private void onPlayerTick(PlayerTickEvent.Post event) {
		if (event.getEntity() instanceof ServerPlayer player && !player.isCreative() && !player.isSpectator()) {
			CommonClass.onPlayerTick(player);
		}
	}
}