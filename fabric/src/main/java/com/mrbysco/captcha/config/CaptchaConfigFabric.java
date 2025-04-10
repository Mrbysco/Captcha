package com.mrbysco.captcha.config;

import com.mrbysco.captcha.Constants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.List;

@Config(name = Constants.MOD_ID)
public class CaptchaConfigFabric implements ConfigData {
	@CollapsibleObject
	public General general = new General();

	public static class General {
		@Comment("Defines the amount of ticks at the start of a world where no captcha's get requested [Default: 6000]")
		public int gracePeriod = 6000;
		@Comment("Defines the amount of time in seconds in which the captcha should be solved [Default: 15]")
		@ConfigEntry.BoundedDiscrete(min = 1, max = Integer.MAX_VALUE)
		public int captchaTime = 15;
		@Comment("Defines the cooldown between captcha requests in seconds [Default: 900 (15 minutes)]")
		@ConfigEntry.BoundedDiscrete(min = 30, max = Integer.MAX_VALUE)
		public int captchaCooldown = 900;
		@Comment("Disable automatic captcha cooldown [Default: false]")
		public boolean disableCooldown = false;
	}

	@CollapsibleObject
	public Math math = new Math();
	public static class Math {
		@Comment("Defines the max the first number can be in the addition problem [Default: 100]")
		public int additionMaxX = 100;
		@Comment("Defines the max the second number can be in the addition problem [Default: 100]")
		@ConfigEntry.BoundedDiscrete(min = 30, max = Integer.MAX_VALUE)
		public int additionMaxY = 100;
		@Comment("Defines the max the first number can be in the subtraction problem [Default: 100]")
		public int subtractionMaxX = 100;
		@Comment("Defines the max the second number can be in the subtraction problem [Default: 100]")
		@ConfigEntry.BoundedDiscrete(min = 30, max = Integer.MAX_VALUE)
		public int subtractionMaxY = 100;
		@Comment("Defines the max the first number can be in the multiplication problem [Default: 20]")
		public int multiplicationMaxX = 30;
		@Comment("Defines the max the second number can be in the multiplication problem [Default: 20]")
		@ConfigEntry.BoundedDiscrete(min = 20, max = Integer.MAX_VALUE)
		public int multiplicationMaxY = 20;
		@Comment("Defines the max the first number can be in the division problem [Default: 100]")
		@ConfigEntry.BoundedDiscrete(min = 1, max = Integer.MAX_VALUE)
		public int divisionMaxX = 100;
		@Comment("Defines the max the second number can be in the division problem [Default: 100]")
		@ConfigEntry.BoundedDiscrete(min = 30, max = Integer.MAX_VALUE)
		public int divisionMaxY = 100;
	}

	@CollapsibleObject
	public Text text = new Text();
	public static class Text {
		@Comment("List of words that can be used to generate a text captcha (16 character length max)")
		public List<String> textCaptchaWords = List.of("diamond", "iron", "gold", "emerald", "lapis", "coal", "redstone", "nether",
				"creeper", "skeleton", "spider", "zombie", "enderman", "endermite", "slime", "ghast",
				"pig", "cow", "sheep", "chicken", "rabbit", "mooshroom", "villager", "witch", "wither",
				"blaze", "guardian", "shulker", "ender dragon", "wither", "drowned", "husk", "stray",
				"phantom", "zoglin", "piglin", "panda", "bee", "cat", "fox", "ocelot");
	}
}
