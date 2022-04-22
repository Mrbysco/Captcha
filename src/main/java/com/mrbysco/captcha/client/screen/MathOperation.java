package com.mrbysco.captcha.client.screen;

import java.util.Random;

public enum MathOperation {
	ADDITION("+"),
	SUBTRACTION("-"),
	MULTIPLICATION("*"),
	DIVISION("/");

	private final String symbol;
	MathOperation(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getAnswer(double x, double y) {
		return switch(this) {
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
