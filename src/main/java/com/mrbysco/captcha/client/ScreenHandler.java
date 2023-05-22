package com.mrbysco.captcha.client;

import com.mrbysco.captcha.client.screen.CaptchaScreen;
import com.mrbysco.captcha.client.screen.image.ImageScreen;
import com.mrbysco.captcha.client.screen.math.MathScreen;
import com.mrbysco.captcha.client.screen.NotARobotScreen;
import com.mrbysco.captcha.client.screen.text.TextScreen;
import net.minecraft.client.Minecraft;

import java.util.List;

public class ScreenHandler {
	public static void openCaptcha(String captchaName, String code, int maxCompletionTime, List<? extends String> configuredWords) {
		Minecraft mc = Minecraft.getInstance();
		if (!(mc.screen instanceof CaptchaScreen)) {
			setScreen(CaptchaEnum.getByName(captchaName), code, maxCompletionTime, configuredWords);
		}
	}

	public static void setScreen(CaptchaEnum captcha, String code, int maxCompletionTime, List<? extends String> configuredWords) {
		switch (captcha) {
			default -> Minecraft.getInstance().setScreen(new NotARobotScreen(code, maxCompletionTime));
			case MATH -> Minecraft.getInstance().setScreen(new MathScreen(code, maxCompletionTime));
			case TEXT -> Minecraft.getInstance().setScreen(new TextScreen(code, maxCompletionTime, configuredWords));
			case IMAGE -> Minecraft.getInstance().setScreen(new ImageScreen(code, maxCompletionTime));
		}
	}
}