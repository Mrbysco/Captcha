package com.mrbysco.captcha.client.screen;

import java.util.Random;

public enum MathOperation {
	ADDITION("+", 100, 100),
	SUBTRACTION("-", 100, 100),
	MULTIPLICATION("*", 20, 20),
	DIVISION("/", 100, 100);

	private final String symbol;
	private final int maxX;
	private final int maxY;

	MathOperation(String symbol, int maxX, int maxY) {
		this.symbol = symbol;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public String getSymbol() {
		return symbol;
	}

	public double generateX() {
		return Math.random() * maxX;
	}

	public double generateY() {
		return Math.random() * maxY;
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
