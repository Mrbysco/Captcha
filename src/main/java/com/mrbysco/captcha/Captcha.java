package com.mrbysco.captcha;

import com.mojang.logging.LogUtils;
import com.mrbysco.captcha.commands.CaptchaCommands;
import com.mrbysco.captcha.config.CaptchaConfig;
import com.mrbysco.captcha.handler.CaptchaHandler;
import com.mrbysco.captcha.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Captcha.MOD_ID)
public class Captcha {
	public static final String MOD_ID = "captcha";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Captcha() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CaptchaConfig.commonSpec);

		eventBus.addListener(this::setup);

		MinecraftForge.EVENT_BUS.addListener(this::onCommandRegister);
		MinecraftForge.EVENT_BUS.addListener(CaptchaHandler::onPlayerTick);
	}

	private void setup(final FMLCommonSetupEvent event) {
		NetworkHandler.init();
	}

	public void onCommandRegister(RegisterCommandsEvent event) {
		CaptchaCommands.initializeCommands(event.getDispatcher());
	}
}