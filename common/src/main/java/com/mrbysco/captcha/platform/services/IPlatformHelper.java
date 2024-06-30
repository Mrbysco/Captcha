package com.mrbysco.captcha.platform.services;

import net.minecraft.server.level.ServerPlayer;

public interface IPlatformHelper {

	/**
	 * Get the Captcha Cooldown from the config.
	 *
	 * @return The cooldown in seconds.
	 */
	int getCaptchaCooldown();

	/**
	 * Get the Grace Period from the config.
	 *
	 * @return The grace period in ticks.
	 */
	int getGracePeriod();

	/**
	 * Get the max X value for the addition operation.
	 *
	 * @return The max X value.
	 */
	int getAdditionMaxX();

	/**
	 * Get the max Y value for the addition operation.
	 *
	 * @return The max Y value.
	 */
	int getAdditionMaxY();

	/**
	 * Get the max X value for the subtraction operation.
	 *
	 * @return The max X value.
	 */
	int getSubtractionMaxX();

	/**
	 * Get the max Y value for the subtraction operation.
	 *
	 * @return The max Y value.
	 */
	int getSubtractionMaxY();

	/**
	 * Get the max X value for the multiplication operation.
	 *
	 * @return The max X value.
	 */
	int getMultiplicationMaxX();

	/**
	 * Get the max Y value for the multiplication operation.
	 *
	 * @return The max Y value.
	 */
	int getMultiplicationMaxY();

	/**
	 * Get the max X value for the division operation.
	 *
	 * @return The max X value.
	 */
	int getDivisionMaxX();

	/**
	 * Get the max Y value for the division operation.
	 *
	 * @return The max Y value.
	 */
	int getDivisionMaxY();

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
