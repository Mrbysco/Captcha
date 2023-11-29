package com.mrbysco.captcha;

import com.mojang.logging.LogUtils;
import com.mrbysco.captcha.commands.CaptchaCommands;
import com.mrbysco.captcha.config.CaptchaConfig;
import com.mrbysco.captcha.handler.CaptchaHandler;
import com.mrbysco.captcha.network.NetworkHandler;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Captcha.MOD_ID)
public class Captcha {
	public static final String MOD_ID = "captcha";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Captcha() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CaptchaConfig.commonSpec);

		eventBus.addListener(this::setup);

		NeoForge.EVENT_BUS.addListener(this::onCommandRegister);
		NeoForge.EVENT_BUS.addListener(CaptchaHandler::onPlayerTick);
	}

	private void setup(final FMLCommonSetupEvent event) {
		NetworkHandler.init();
	}

	public void onCommandRegister(RegisterCommandsEvent event) {
		CaptchaCommands.initializeCommands(event.getDispatcher());
	}
}