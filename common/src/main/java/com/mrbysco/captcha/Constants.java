package com.mrbysco.captcha;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Constants {
	public static final String MOD_ID = "captcha";
	public static final String MOD_NAME = "Captcha";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static final Identifier REQUIRE_CAPTCHA = modLoc("require_captcha");
	public static final Identifier COMPLETE_CAPTCHA = modLoc("complete_captcha");

	public static final Random random = new Random();

	public static Identifier modLoc(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}