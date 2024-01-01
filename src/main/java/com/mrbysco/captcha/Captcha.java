package com.mrbysco.captcha;

import com.mojang.logging.LogUtils;
import com.mrbysco.captcha.commands.CaptchaCommands;
import com.mrbysco.captcha.config.CaptchaConfig;
import com.mrbysco.captcha.handler.CaptchaHandler;
import com.mrbysco.captcha.network.handler.ClientPayloadHandler;
import com.mrbysco.captcha.network.handler.ServerPayloadHandler;
import com.mrbysco.captcha.network.payload.CompletedCaptcha;
import com.mrbysco.captcha.network.payload.RequireCaptcha;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import org.slf4j.Logger;

@Mod(Captcha.MOD_ID)
public class Captcha {
	public static final String MOD_ID = "captcha";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Captcha(IEventBus eventBus) {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CaptchaConfig.commonSpec);

		eventBus.addListener(this::setupPackets);

		NeoForge.EVENT_BUS.addListener(this::onCommandRegister);
		NeoForge.EVENT_BUS.addListener(CaptchaHandler::onPlayerTick);
	}

	public void setupPackets(final RegisterPayloadHandlerEvent event) {
		final IPayloadRegistrar registrar = event.registrar(Captcha.MOD_ID);
		registrar.play(RequireCaptcha.ID, RequireCaptcha::new, handler -> handler
				.client(ClientPayloadHandler.getInstance()::handleData));
		registrar.play(CompletedCaptcha.ID, CompletedCaptcha::new, handler -> handler
				.server(ServerPayloadHandler.getInstance()::handleData));
	}

	public void onCommandRegister(RegisterCommandsEvent event) {
		CaptchaCommands.initializeCommands(event.getDispatcher());
	}
}