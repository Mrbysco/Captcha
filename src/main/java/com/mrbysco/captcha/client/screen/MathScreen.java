package com.mrbysco.captcha.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.captcha.client.screen.widget.NumberEditBox;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Random;

public class MathScreen extends CaptchaScreen {
	private static final Random random = new Random();
	private NumberEditBox answerBox;
	private MathOperation operation;
	private int valueX;
	private int valueY;
	private int answer;
	protected MultiLineLabel note = MultiLineLabel.EMPTY;

	public MathScreen(String code, int maxCompletionTime) {
		super(new TranslatableComponent("captcha.math.screen"), code, maxCompletionTime);
		this.changeQuestion();
	}

	private void changeQuestion() {
		this.operation = MathOperation.getRandomOperation(random);
		double valueX = this.operation.generateX();
		double valueY = this.operation.generateY();
		double answer = this.operation.getAnswer(valueX, valueY);

		if (this.operation == MathOperation.DIVISION) {
			int tries = 0;
			while ((valueX < valueY || valueX % valueY != 0) && tries < 20) {
				valueX = 1 + (int) this.operation.generateX();
				valueY = 1 + (int) this.operation.generateY();
			}
			answer = valueX / valueY;
		}

		int tries = 0;
		while (answer == 0 && tries < 5) {
			valueX = this.operation.generateX();
			valueY = this.operation.generateY();
			answer = this.operation.getAnswer(valueX, valueY);
			tries++;
		}

		this.valueX = (int) valueX;
		this.valueY = (int) valueY;
		this.answer = (int) answer;

		if (this.answer == 0) {
			this.operation = MathOperation.ADDITION;
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
				new TranslatableComponent("captcha.math.question", valueX, operation.getSymbol(), valueY).withStyle(ChatFormatting.YELLOW)));
		int i = (this.message.getLineCount() + 1) * 9;

		this.answerBox = new NumberEditBox(this.font, 76 + i, 140, 60, 20, TextComponent.EMPTY);
		this.answerBox.setMaxLength(8);
		this.answerBox.setX(this.width / 2 - this.answerBox.getWidth() / 2);
		this.addWidget(this.answerBox);

		this.note = MultiLineLabel.create(this.font, List.of(new TranslatableComponent("captcha.math.note").withStyle(ChatFormatting.RED)));
	}

	@Override
	public void tick() {
		super.tick();

		if (this.timeWaited > getMaxCompletionTime()) {
			resetCaptcha();
		}
	}

	public void checkAnswer() {
		if (answerBox.getInt() == this.answer) {
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
				new TranslatableComponent("captcha.math.question", valueX, operation.getSymbol(), valueY).withStyle(ChatFormatting.YELLOW)));
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (super.keyPressed(keyCode, scanCode, modifiers)) {
			return true;
		} else if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
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