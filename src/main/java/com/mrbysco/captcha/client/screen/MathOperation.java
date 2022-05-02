package com.mrbysco.captcha.client.screen;

import com.mrbysco.captcha.config.CaptchaConfig;

import java.util.Random;
import java.util.function.Supplier;

public enum MathOperation {
	ADDITION("+", CaptchaConfig.COMMON.additionMaxX::get, CaptchaConfig.COMMON.additionMaxY::get),
	SUBTRACTION("-", CaptchaConfig.COMMON.subtractionMaxX::get, CaptchaConfig.COMMON.additionMaxY::get),
	MULTIPLICATION("*", CaptchaConfig.COMMON.multiplicationMaxX::get, CaptchaConfig.COMMON.multiplicationMaxY::get),
	DIVISION("/", CaptchaConfig.COMMON.divisionMaxX::get, CaptchaConfig.COMMON.divisionMaxY::get);

	private final String symbol;
	private final Supplier<Integer> maxX;
	private final Supplier<Integer> maxY;

	MathOperation(String symbol, Supplier<Integer> maxX, Supplier<Integer> maxY) {
		this.symbol = symbol;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public String getSymbol() {
		return symbol;
	}

	public double generateX() {
		return (int)(Math.random() * maxX.get());
	}

	public double generateY() {
		return (int)(Math.random() * maxY.get());
	}

	public double getAnswer(double x, double y) {
		return switch (this) {
			case ADDITION -> x + y;
			case SUBTRACTION -> x - y;
			case MULTIPLICATION -> x * y;
			case DIVISION -> x / y;
			default -> 0;
		};
	}

	public static MathOperation getRandomOperation(Random random) {
		int pick = random.nextInt(MathOperation.values().length);
		return MathOperation.values()[pick];
	}
}
