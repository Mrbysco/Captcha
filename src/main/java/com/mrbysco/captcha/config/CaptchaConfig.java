package com.mrbysco.captcha.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

public class CaptchaConfig {
	public static class Common {
		public final IntValue captchaTime;
		public final IntValue captchaCooldown;

		public final IntValue additionMaxX;
		public final IntValue additionMaxY;
		public final IntValue subtractionMaxX;
		public final IntValue subtractionMaxY;
		public final IntValue multiplicationMaxX;
		public final IntValue multiplicationMaxY;
		public final IntValue divisionMaxX;
		public final IntValue divisionMaxY;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("General");

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