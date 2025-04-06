package com.mrbysco.captcha;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Constants {
	public static final String MOD_ID = "captcha";
	public static final String MOD_NAME = "Captcha";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static final ResourceLocation REQUIRE_CAPTCHA = ResourceLocation.fromNamespaceAndPath(MOD_ID, "require_captcha");
	public static final ResourceLocation COMPLETE_CAPTCHA = ResourceLocation.fromNamespaceAndPath(MOD_ID, "complete_captcha");

	public static final Random random = new Random();
}