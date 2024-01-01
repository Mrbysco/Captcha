package com.mrbysco.captcha.client.screen;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.network.chat.Component;

public class NotARobotScreen extends CaptchaScreen {
	private Checkbox notARobot;

	public NotARobotScreen(String code, int maxCompletionTime) {
		super(Component.translatable("captcha.notarobot.screen"), code, maxCompletionTime);
	}

	@Override
	protected void init() {
		super.init();

		this.message = MultiLineLabel.create(this.font, Component.translatable("captcha.notarobot.screen"), this.width - 50);
		int i = (this.message.getLineCount() + 1) * 9;

		Component checkBoxComponent = Component.translatable("captcha.notarobot.checkbox");
		this.notARobot = Checkbox.builder(checkBoxComponent, this.font)
				.pos(this.width / 2 - this.font.width(checkBoxComponent) - 20, 76 + i).build();
		this.addRenderableWidget(notARobot);
	}

	@Override
	public void tick() {
		super.tick();
		if (notARobot.selected()) {
			if (this.timeWaited < getMaxCompletionTime()) {
				//Complete
				this.completeCaptcha();
			} else {
				this.resetCaptcha();
			}
		}
	}

	@Override
	public void resetCaptcha() {
		super.resetCaptcha();
		this.notARobot.onPress();

		this.message = MultiLineLabel.create(this.font, Component.translatable("captcha.notarobot.failed").withStyle(ChatFormatting.RED), this.width - 50);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
	}
}