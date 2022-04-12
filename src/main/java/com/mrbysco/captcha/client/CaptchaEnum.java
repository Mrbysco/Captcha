package com.mrbysco.captcha.client;

import javax.annotation.Nullable;
import java.util.Random;

public enum CaptchaEnum {
	ROBOT("robot", 0),
	MATH("math", 1);

	private final String name;
	private final Integer id;

	CaptchaEnum(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getCaptchaName() {
		return name;
	}

	public Integer getId() {
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
