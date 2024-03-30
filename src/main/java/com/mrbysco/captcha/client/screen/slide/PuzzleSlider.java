package com.mrbysco.captcha.client.screen.slide;

import net.minecraft.network.chat.Component;

public class PuzzleSlider extends NeoForgeSlider {
	protected final PuzzleSlider.OnDrop onDrop;

	public PuzzleSlider(int x, int y, int width, int height, Component prefix, Component suffix, double minValue, double maxValue, double currentValue, double stepSize, int precision, PuzzleSlider.OnDrop onDrop) {
		super(x, y, width, height, prefix, suffix, minValue, maxValue, currentValue, stepSize, precision, false);
		this.onDrop = onDrop;
	}

	@Override
	public void onRelease(double p_93609_, double p_93610_) {
		super.onRelease(p_93609_, p_93610_);
		this.onDrop.onDrop(this);
	}

	@Override
	protected void updateMessage() {
		this.setMessage(Component.translatable("captcha.slide.screen"));
	}

	public interface OnDrop {
		void onDrop(PuzzleSlider slider);
	}
}
