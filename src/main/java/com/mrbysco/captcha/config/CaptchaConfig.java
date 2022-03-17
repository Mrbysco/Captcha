package com.mrbysco.captcha.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

public class CaptchaConfig {
	public static class Common {
		public final IntValue captchaTime;
		public final IntValue captchaCooldown;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("General");

			captchaTime = builder
					.comment("Defines the amount of time in seconds in which the captcha should be solved")
					.defineInRange("captchaTime", 15, 1, Integer.MAX_VALUE);

			captchaCooldown = builder
					.comment("Defines the cooldown between captcha requests in seconds")
					.defineInRange("captchaCooldown", 1800, 30, Integer.MAX_VALUE);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}