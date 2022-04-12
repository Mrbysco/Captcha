package com.mrbysco.captcha.client;

import com.mrbysco.captcha.client.screen.CaptchaScreen;
import com.mrbysco.captcha.client.screen.MathScreen;
import com.mrbysco.captcha.client.screen.NotARobotScreen;
import net.minecraft.client.Minecraft;

public class ScreenHandler {
	public static void openCaptcha(String captchaName, String code, int maxCompletionTime) {
		Minecraft mc = Minecraft.getInstance();
		if (!(mc.screen instanceof CaptchaScreen)) {
			setScreen(CaptchaEnum.getByName(captchaName), code, maxCompletionTime);
		}
	}

	public static void setScreen(CaptchaEnum captcha, String code, int maxCompletionTime) {
		switch (captcha) {
			default -> Minecraft.getInstance().setScreen(new NotARobotScreen(code, maxCompletionTime));
			case MATH -> Minecraft.getInstance().setScreen(new MathScreen(code, maxCompletionTime));
		}
	}
}