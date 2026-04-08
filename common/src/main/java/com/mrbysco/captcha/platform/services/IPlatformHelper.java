package com.mrbysco.captcha.platform.services;

import net.minecraft.server.level.ServerPlayer;

public interface IPlatformHelper {

	/**
	 * Send Require Captcha Message to the client.
	 *
	 * @param serverPlayer      The player to send the message to.
	 * @param captchaName       The name of the captcha.
	 * @param code              The code for the captcha.
	 */
	void sendRequireCaptchaMessage(ServerPlayer serverPlayer, String captchaName, String code);

	/**
	 * Send Completed Captcha Message to the server.
	 *
	 * @param code The code for the captcha.
	 */
	void sendCompletedCaptchaMessage(String code);
}
