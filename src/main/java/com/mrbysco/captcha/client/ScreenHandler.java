package com.mrbysco.captcha.client;

import com.mrbysco.captcha.client.screen.CaptchaScreen;
import com.mrbysco.captcha.client.screen.MathScreen;
import com.mrbysco.captcha.client.screen.NotARobotScreen;
import net.minecraft.client.Minecraft;

import java.util.Random;

public class ScreenHandler {
	private static final Random random = new Random();

	public static void openCaptcha(String code, int maxCompletionTime) {
		Minecraft mc = Minecraft.getInstance();
		if(!(mc.screen instanceof CaptchaScreen)) {
			switch (random.nextInt(3)) {
				default -> mc.setScreen(new NotARobotScreen(code, maxCompletionTime));
				case 1 -> mc.setScreen(new MathScreen(code, maxCompletionTime));
			}
		}
	}
}