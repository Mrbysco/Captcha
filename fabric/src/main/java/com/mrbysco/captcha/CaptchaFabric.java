package com.mrbysco.captcha;

import com.mrbysco.captcha.callback.PlayerTickCallback;
import com.mrbysco.captcha.commands.CaptchaCommands;
import com.mrbysco.captcha.config.CaptchaConfig;
import com.mrbysco.captcha.network.CompletedCaptcha;
import com.mrbysco.captcha.network.RequireCaptcha;
import com.mrbysco.captcha.util.CaptchaManager;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.InteractionResult;
import net.neoforged.fml.config.ModConfig;

public class CaptchaFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		ConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.COMMON, CaptchaConfig.commonSpec);

		CommonClass.init();

		CommandRegistrationCallback.EVENT.register((commandDispatcher, buildContext, selection) -> CaptchaCommands.initializeCommands(commandDispatcher));

		PlayerTickCallback.EVENT.register((player) -> {
			CommonClass.onPlayerTick(player);
			return InteractionResult.PASS;
		});

		PayloadTypeRegistry.clientboundPlay().register(RequireCaptcha.ID, RequireCaptcha.CODEC);
		PayloadTypeRegistry.serverboundPlay().register(CompletedCaptcha.ID, CompletedCaptcha.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(CompletedCaptcha.ID, (payload, context) -> {
			context.server().execute(() -> {
				//Complete Captcha
				CaptchaManager.setCompletedRecently(context.player().getUUID(), payload.code());
			});
		});
	}
}
