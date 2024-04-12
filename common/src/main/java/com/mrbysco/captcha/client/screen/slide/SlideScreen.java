package com.mrbysco.captcha.client.screen.slide;

import com.mrbysco.captcha.client.screen.CaptchaScreen;
import com.mrbysco.captcha.client.screen.image.ImageEnum;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.OptionalInt;
import java.util.Random;

public class SlideScreen extends CaptchaScreen {
	private static final Random random = new Random();
	private ResourceLocation IMAGE = new ResourceLocation("captcha", "textures/gui/amethyst.png");

	protected int puzzleY, puzzleX = 16;
	protected double acceptedMin, acceptedMax;
	protected PuzzleSlider slider;
	protected int attemptCount = 0;

	public SlideScreen(String code, int maxCompletionTime) {
		super(Component.translatable("captcha.slide.screen"), code, maxCompletionTime);
		this.resetPuzzle();
	}

	private void resetPuzzle() {
		ImageEnum image = ImageEnum.getRandom(random, ImageEnum.SANDY, ImageEnum.VEHICLE);
		IMAGE = new ResourceLocation("captcha", "textures/gui/" + image.getImageName() + ".png");

		OptionalInt randomNumber = random.ints(32, (84 + 1)).findFirst();
		this.puzzleX = randomNumber.getAsInt();
		this.acceptedMin = this.puzzleX - 0.1D - 16;
		this.acceptedMax = this.puzzleX + 0.4D - 16;

		this.puzzleY = random.ints(32, 84 + 1).findFirst().getAsInt();

		if (this.slider != null)
			this.slider.setValue(0);
	}

	@Override
	protected void init() {
		super.init();

		this.addRenderableWidget(this.slider = new PuzzleSlider(this.width / 2 - 100, this.height / 2 + 100, 200, 20,
				Component.empty(), Component.empty(), 0, 84, 0, 0.1, 0, (slider) -> {
			double sliderValue = slider.getValue();
			if (sliderValue >= this.acceptedMin && sliderValue <= this.acceptedMax) {
				completeCaptcha();
			}

			// Fail-safe to prevent an error rendering the slider captcha impossible to complete
			this.attemptCount++;
			if (this.attemptCount >= 20) {
				completeCaptcha();
			}
		}));
	}

	@Override
	public void tick() {
		super.tick();

		if (this.timeWaited > getMaxCompletionTime()) {
			resetCaptcha();
		}
	}

	@Override
	public void resetCaptcha() {
		super.resetCaptcha();
		if (this.timeWaited > getMaxCompletionTime()) {
			this.resetPuzzle();
		}
	}

	@Override
	public int getMaxCompletionTime() {
		return super.getMaxCompletionTime();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);

		int pieceX = (this.width / 2 - 100) + (this.puzzleX * 2) - 32;
		int pieceY = (this.height / 2 - 100) + (this.puzzleY * 2) - 32;
		guiGraphics.fillGradient(pieceX, pieceY, pieceX + 32, pieceY + 32, 0xA6000000, 0xA6000000);

		int sliderX = (this.width / 2 - 100) + (int) (this.slider.getValue() * 2);
		guiGraphics.blit(IMAGE, sliderX, pieceY, this.puzzleX * 2 - 32, this.puzzleY * 2 - 32, 32, 32, 200, 200);
		guiGraphics.fillGradient(sliderX, pieceY, sliderX + 32, pieceY + 32, 0x1AFFFFFF, 0x1AFFFFFF);
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics) {
		super.renderBackground(guiGraphics);
		guiGraphics.blit(IMAGE, this.width / 2 - 100, this.height / 2 - 100, 0, 0, 200, 200, 200, 200);
	}
}