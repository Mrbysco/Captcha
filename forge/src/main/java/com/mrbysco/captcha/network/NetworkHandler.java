package com.mrbysco.captcha.network;

import com.mrbysco.captcha.Constants;
import com.mrbysco.captcha.network.handler.ClientPayloadHandler;
import com.mrbysco.captcha.network.handler.ServerPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

public class NetworkHandler {
	public static void setupPackets(final RegisterPayloadHandlerEvent event) {
		final IPayloadRegistrar registrar = event.registrar(Constants.MOD_ID);
		registrar.play(RequireCaptcha.ID, RequireCaptcha::new, handler -> handler
				.client(ClientPayloadHandler.getInstance()::handleData));
		registrar.play(CompletedCaptcha.ID, CompletedCaptcha::new, handler -> handler
				.server(ServerPayloadHandler.getInstance()::handleData));
	}
}