package com.mrbysco.captcha.client.screen;

import com.mrbysco.captcha.network.payload.CompletedCaptcha;
import net.minecraft.client.gui.GuiGraphics;
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
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		this.message.renderCentered(guiGraphics, this.width / 2, messageY);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return false;
	}

	public int getMaxCompletionTime() {
		return (maxCompletionTime * 20);
	}

	public void completeCaptcha() {
		this.minecraft.setScreen(null);
		this.minecraft.getConnection().send(new CompletedCaptcha(code));
	}

	public void resetCaptcha() {
		this.timeWaited = 0;
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}