package com.mrbysco.captcha.client.screen;

import com.mrbysco.captcha.platform.Services;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.TextAlignment;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CaptchaScreen extends Screen {
	private final String code;
	protected int timeWaited;
	protected int messageY = 70;
	private final int maxCompletionTime;
	protected MultiLineLabel message = MultiLineLabel.EMPTY;

	protected CaptchaScreen(Component component, String code, int maxCompletionTime) {
		super(component);
		this.code = code;
		this.maxCompletionTime = maxCompletionTime;
	}

	@Override
	public void tick() {
		super.tick();
		this.timeWaited++;
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.extractBackground(guiGraphics, mouseX, mouseY, partialTick);
		this.extractMessage(guiGraphics);
	}

	public void extractMessage(GuiGraphicsExtractor guiGraphics) {
		this.message.visitLines(TextAlignment.CENTER, this.width / 2, messageY, 10, guiGraphics.textRenderer());
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return false;
	}

	public int getMaxCompletionTime() {
		return (maxCompletionTime * 20);
	}

	public void completeCaptcha() {
		this.minecraft.gui.setScreen(null);
		Services.PLATFORM.sendCompletedCaptchaMessage(code);
	}

	public void resetCaptcha() {
		this.timeWaited = 0;
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}