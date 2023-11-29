package com.mrbysco.captcha.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class CaptchaConfig {
	public static class Common {
		public final ModConfigSpec.IntValue gracePeriod;
		public final ModConfigSpec.IntValue captchaTime;
		public final ModConfigSpec.IntValue captchaCooldown;

		public final ModConfigSpec.IntValue additionMaxX;
		public final ModConfigSpec.IntValue additionMaxY;
		public final ModConfigSpec.IntValue subtractionMaxX;
		public final ModConfigSpec.IntValue subtractionMaxY;
		public final ModConfigSpec.IntValue multiplicationMaxX;
		public final ModConfigSpec.IntValue multiplicationMaxY;
		public final ModConfigSpec.IntValue divisionMaxX;
		public final ModConfigSpec.IntValue divisionMaxY;
		public final ModConfigSpec.ConfigValue<List<? extends String>> textCaptchaWords;

		Common(ModConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("General");

			gracePeriod = builder
					.comment("Defines the amount of ticks at the start of a world where no captcha's get requested [Default: 6000]")
					.defineInRange("gracePeriod", 6000, 0, Integer.MAX_VALUE);

			captchaTime = builder
					.comment("Defines the amount of time in seconds in which the captcha should be solved [Default: 15]")
					.defineInRange("captchaTime", 15, 1, Integer.MAX_VALUE);

			captchaCooldown = builder
					.comment("Defines the cooldown between captcha requests in seconds [Default: 900 (15 minutes)]")
					.defineInRange("captchaCooldown", 900, 30, Integer.MAX_VALUE);

			builder.pop();
			builder.comment("Math settings")
					.push("Math");

			additionMaxX = builder
					.comment("Defines the max the first number can be in the addition problem [Default: 100]")
					.defineInRange("additionMaxX", 100, 1, Integer.MAX_VALUE);

			additionMaxY = builder
					.comment("Defines the max the second number can be in the addition problem [Default: 100]")
					.defineInRange("additionMaxY", 100, 30, Integer.MAX_VALUE);

			subtractionMaxX = builder
					.comment("Defines the max the first number can be in the subtraction problem [Default: 100]")
					.defineInRange("subtractionMaxX", 100, 1, Integer.MAX_VALUE);

			subtractionMaxY = builder
					.comment("Defines the max the second number can be in the subtraction problem [Default: 100]")
					.defineInRange("subtractionMaxY", 100, 30, Integer.MAX_VALUE);

			multiplicationMaxX = builder
					.comment("Defines the max the first number can be in the multiplication problem [Default: 20]")
					.defineInRange("multiplicationMaxX", 20, 1, Integer.MAX_VALUE);

			multiplicationMaxY = builder
					.comment("Defines the max the second number can be in the multiplication problem [Default: 20]")
					.defineInRange("multiplicationMaxY", 20, 30, Integer.MAX_VALUE);

			divisionMaxX = builder
					.comment("Defines the max the first number can be in the division problem [Default: 100]")
					.defineInRange("divisionMaxX", 100, 1, Integer.MAX_VALUE);

			divisionMaxY = builder
					.comment("Defines the max the second number can be in the division problem [Default: 100]")
					.defineInRange("divisionMaxY", 100, 30, Integer.MAX_VALUE);

			builder.pop();
			builder.comment("Math settings")
					.push("Math");

			textCaptchaWords = builder
					.comment("List of words that can be used to generate a text captcha (16 character length max)")
					.defineList("textCaptchaWords", List.of("diamond", "iron", "gold", "emerald", "lapis", "coal", "redstone", "nether",
							"creeper", "skeleton", "spider", "zombie", "enderman", "endermite", "slime", "ghast",
							"pig", "cow", "sheep", "chicken", "rabbit", "mooshroom", "villager", "witch", "wither",
							"blaze", "guardian", "shulker", "ender dragon", "wither", "drowned", "husk", "stray",
							"phantom", "zoglin", "piglin", "panda", "bee", "cat", "fox", "ocelot"), s -> s instanceof String && !((String) s).isEmpty() && ((String) s).length() <= 16);

			builder.pop();
		}
	}

	public static final ModConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}