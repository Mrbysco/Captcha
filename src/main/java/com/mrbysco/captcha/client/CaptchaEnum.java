package com.mrbysco.captcha.client;

import org.jetbrains.annotations.Nullable;

import java.util.Random;

public enum CaptchaEnum {
	ROBOT("robot", 0),
	MATH("math", 1),
	TEXT("text", 2),
	IMAGE("image", 3);

	private final String name;
	private final int id;

	CaptchaEnum(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getCaptchaName() {
		return name;
	}

	public int getId() {
		return id;
	}

	@Nullable
	public static CaptchaEnum getByName(@Nullable String value) {
		for (CaptchaEnum captcha : values()) {
			if (captcha.name.equals(value)) {
				return captcha;
			}
		}
		return ROBOT;
	}

	public static CaptchaEnum getRandom(Random random) {
		int pick = random.nextInt(CaptchaEnum.values().length);
		return CaptchaEnum.values()[pick];
	}
}
