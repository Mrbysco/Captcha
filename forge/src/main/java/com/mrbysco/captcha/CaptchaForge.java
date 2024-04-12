package com.mrbysco.captcha;

import com.mrbysco.captcha.commands.CaptchaCommands;
import com.mrbysco.captcha.config.CaptchaConfigForge;
import com.mrbysco.captcha.network.NetworkHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class CaptchaForge {

	public CaptchaForge() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CaptchaConfigForge.commonSpec);

		CommonClass.init();

		eventBus.addListener(this::setup);

		MinecraftForge.EVENT_BUS.addListener(this::onCommandRegister);
		MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
	}

	private void setup(final FMLCommonSetupEvent event) {
		NetworkHandler.init();
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