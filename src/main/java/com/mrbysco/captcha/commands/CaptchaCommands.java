package com.mrbysco.captcha.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mrbysco.captcha.Captcha;
import com.mrbysco.captcha.client.CaptchaEnum;
import com.mrbysco.captcha.config.CaptchaConfig;
import com.mrbysco.captcha.network.payload.RequireCaptcha;
import com.mrbysco.captcha.util.CaptchaManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CaptchaCommands {
	public static void initializeCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
		final LiteralArgumentBuilder<CommandSourceStack> root = Commands.literal(Captcha.MOD_ID);
		root.requires((sourceStack) -> sourceStack.hasPermission(2))
				.then(Commands.literal("forceCaptcha")
						.then(Commands.argument("player", EntityArgument.players())
								.then(Commands.argument("captchaName", StringArgumentType.word()).suggests((cs, builder) -> {
									List<String> captchaNames = new ArrayList<>();
									for (CaptchaEnum eCaptcha : CaptchaEnum.values()) {
										captchaNames.add(eCaptcha.getCaptchaName());
									}
									return SharedSuggestionProvider.suggest(captchaNames, builder);
								}).executes(CaptchaCommands::forceCaptcha))));
		dispatcher.register(root);
	}

	private static int forceCaptcha(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		final String captchaName = StringArgumentType.getString(ctx, "captchaName");
		for (ServerPlayer player : EntityArgument.getPlayers(ctx, "player")) {
			UUID uuid = player.getUUID();
			CaptchaManager.forgetUser(uuid);
			String code = CaptchaManager.applyRandomCode(uuid);
			player.connection.send(new RequireCaptcha(captchaName, code, CaptchaConfig.COMMON.captchaTime.get(),
					CaptchaConfig.COMMON.textCaptchaWords.get()));
		}

		return 0;
	}
}
