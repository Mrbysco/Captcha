package com.mrbysco.captcha.network;

import com.mrbysco.captcha.Constants;
import com.mrbysco.captcha.network.handler.ClientPayloadHandler;
import com.mrbysco.captcha.network.handler.ServerPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NetworkHandler {
	public static void setupPackets(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar(Constants.MOD_ID);
		registrar.playToClient(RequireCaptcha.ID, RequireCaptcha.CODEC, ClientPayloadHandler.getInstance()::handleData);
		registrar.playToServer(CompletedCaptcha.ID, CompletedCaptcha.CODEC, ServerPayloadHandler.getInstance()::handleData);
	}
}