package com.mrbysco.captcha.util;

import com.mrbysco.captcha.config.CaptchaConfig;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CaptchaManager {
	private static final Map<UUID, Long> captchaMap = new HashMap<>();
	private static final Map<UUID, String> activeMap = new HashMap<>();

	public static boolean completedCaptchaRecently(UUID uuid) {
		if(captchaMap.containsKey(uuid)) {
			long lastCompleted = captchaMap.get(uuid);
			long currentTime = System.currentTimeMillis();

			return (currentTime - lastCompleted) < (CaptchaConfig.COMMON.captchaCooldown.get() * 1000);
		}

		return false;
	}

	public static String getActiveCode(UUID uuid) {
		return activeMap.get(uuid);
	}

	public static String applyRandomCode(UUID uuid) {
		String code = generateRandomCode();
		activeMap.put(uuid, code);
		return code;
	}

	public static String generateRandomCode() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public static void setCompletedRecently(UUID uuid, String code) {
		if(activeMap.containsKey(uuid)) {
			if(activeMap.get(uuid).equals(code)) {
				activeMap.remove(uuid);
				captchaMap.put(uuid, System.currentTimeMillis());
			}
		}
	}

	public static void forgetUser(UUID uuid) {
		activeMap.remove(uuid);
		captchaMap.remove(uuid);
	}
}