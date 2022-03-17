package com.mrbysco.captcha.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.captcha.client.screen.widget.NumberEditBox;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.List;

public class MathScreen extends CaptchaScreen {
	private NumberEditBox answerBox;
	private int valueX;
	private int valueY;
	private int answer;
	protected MultiLineLabel note = MultiLineLabel.EMPTY;

	public MathScreen(String code, int maxCompletionTime) {
		super(new TranslatableComponent("captcha.math.screen"), code, maxCompletionTime);
		this.changeQuestion();
	}

	private void changeQuestion() {
		this.valueX = (int)(Math.random() * 100);
		this.valueY = (int)(Math.random() * 100);
		this.answer = valueX + valueY;

		if(this.answer == 0){
			this.valueX = 9;
			this.valueY = 10;
			this.answer = 19;
		}
	}

	@Override
	protected void init() {
		super.init();

		this.message = MultiLineLabel.create(this.font, List.of(new TranslatableComponent("captcha.math.screen"),
				new TranslatableComponent("captcha.math.screen2"),
				TextComponent.EMPTY,
				new TranslatableComponent("captcha.math.question", valueX, valueY).withStyle(ChatFormatting.YELLOW)));
		int i = (this.message.getLineCount() + 1) * 9;

		this.answerBox = new NumberEditBox(this.font, 76 + i, 140, 60, 20, TextComponent.EMPTY);
		this.answerBox.setMaxLength(6);
		this.answerBox.setX(this.width / 2 - this.answerBox.getWidth() / 2);
		this.addWidget(this.answerBox);

		this.note = MultiLineLabel.create(this.font, List.of(new TranslatableComponent("captcha.math.note").withStyle(ChatFormatting.RED)));
	}

	@Override
	public void tick() {
		super.tick();

		if(this.timeWaited > getMaxCompletionTime()) {
			resetCaptcha();
		}
	}

	public void checkAnswer() {
		if(answerBox.getInt() == this.answer) {
			//Complete
			this.completeCaptcha();
		} else {
			resetCaptcha();
		}
	}

	@Override
	public void resetCaptcha() {
		super.resetCaptcha();
		this.answerBox.setValue("");
		this.changeQuestion();

		this.message = MultiLineLabel.create(this.font, List.of(new TranslatableComponent("captcha.math.screen"),
				new TranslatableComponent("captcha.math.screen2"),
				TextComponent.EMPTY,
				new TranslatableComponent("captcha.math.question", valueX, valueY).withStyle(ChatFormatting.YELLOW)));
		int i = (this.message.getLineCount() + 1) * 9;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (super.keyPressed(keyCode, scanCode, modifiers)) {
			return true;
		} else if (keyCode == 257) {
			this.checkAnswer();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		super.render(poseStack, mouseX, mouseY, partialTicks);

		this.answerBox.render(poseStack, mouseX, mouseY, partialTicks);

		this.note.renderCentered(poseStack, this.width / 2, 120);
	}
}