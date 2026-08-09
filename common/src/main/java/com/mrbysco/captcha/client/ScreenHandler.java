package com.mrbysco.captcha.client;

import com.mrbysco.captcha.client.screen.CaptchaScreen;
import com.mrbysco.captcha.client.screen.NotARobotScreen;
import com.mrbysco.captcha.client.screen.WinRARScreen;
import com.mrbysco.captcha.client.screen.image.ImageScreen;
import com.mrbysco.captcha.client.screen.math.MathScreen;
import com.mrbysco.captcha.client.screen.slide.SlideScreen;
import com.mrbysco.captcha.client.screen.text.TextScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.util.List;

public class ScreenHandler {
	public static void openCaptcha(String captchaName, String code, int maxCompletionTime, List<? extends String> configuredWords) {
		Minecraft mc = Minecraft.getInstance();
		if (!(mc.gui.screen() instanceof CaptchaScreen)) {
			setScreen(CaptchaEnum.getByName(captchaName), code, maxCompletionTime, configuredWords);
		}
	}

	public static void setScreen(CaptchaEnum captcha, String code, int maxCompletionTime, List<? extends String> configuredWords) {
		Gui gui = Minecraft.getInstance().gui;
		switch (captcha) {
			case MATH -> gui.setScreen(new MathScreen(code, maxCompletionTime));
			case TEXT -> gui.setScreen(new TextScreen(code, maxCompletionTime, configuredWords));
			case IMAGE -> gui.setScreen(new ImageScreen(code, maxCompletionTime));
			case SLIDE -> gui.setScreen(new SlideScreen(code, maxCompletionTime));
			case WINRAR -> gui.setScreen(new WinRARScreen(code, maxCompletionTime));
			default -> gui.setScreen(new NotARobotScreen(code, maxCompletionTime));
		}
	}
}