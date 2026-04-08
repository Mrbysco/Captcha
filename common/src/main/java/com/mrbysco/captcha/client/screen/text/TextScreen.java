package com.mrbysco.captcha.client.screen.text;

import com.mojang.blaze3d.platform.NativeImage;
import com.mrbysco.captcha.Constants;
import com.mrbysco.captcha.client.screen.CaptchaScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.TextAlignment;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TextScreen extends CaptchaScreen {
	private static final Identifier TEXTURE = Constants.modLoc("text");
	private static final Random random = new Random();
	private EditBox answerBox;
	private String currentWord;
	private DynamicTexture scrambledImage;
	protected MultiLineLabel note = MultiLineLabel.EMPTY;
	private final List<? extends String> configuredWords;

	public TextScreen(String code, int maxCompletionTime, List<? extends String> configuredWords) {
		super(Component.translatable("captcha.text.screen"), code, maxCompletionTime);
		this.configuredWords = configuredWords;
		this.changeText();
		this.messageY = 170;
	}

	/**
	 * This method generates a random Minecraft related word.
	 *
	 * @return String
	 */
	private String randomWord() {
		return configuredWords.get(random.nextInt(configuredWords.size()));
	}

	private void changeText() {
		this.currentWord = randomWord();

		try {
			scrambledImage = RenderUtil.generateCaptchaTexture(this.currentWord, 200, 40);

			if (scrambledImage != null) {
				Minecraft mc = Minecraft.getInstance();
				mc.getTextureManager().register(Constants.modLoc("text"), scrambledImage);
			}
		} catch (IOException ignored) {

		}
	}

	@Override
	protected void init() {
		super.init();

		this.message = MultiLineLabel.create(this.font, Component.translatable("captcha.text.screen"));

		this.answerBox = new EditBox(this.font, 76, 190, 120, 20, Component.empty());
		this.answerBox.setMaxLength(16);
		this.answerBox.setX(this.width / 2 - this.answerBox.getWidth() / 2);
		this.addWidget(this.answerBox);

		this.note = MultiLineLabel.create(this.font, Component.translatable("captcha.text.note").withStyle(ChatFormatting.RED));
	}

	@Override
	public void tick() {
		super.tick();

		if (this.timeWaited > getMaxCompletionTime()) {
			resetCaptcha();
		}
	}

	public void checkAnswer() {
		if (answerBox.getValue().equals(this.currentWord)) {
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
		this.changeText();
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (super.keyPressed(event)) {
			return true;
		} else if (event.key() == GLFW.GLFW_KEY_ENTER || event.key() == GLFW.GLFW_KEY_KP_ENTER) {
			this.checkAnswer();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractRenderState(guiGraphics, mouseX, mouseY, partialTicks);

		this.answerBox.extractRenderState(guiGraphics, mouseX, mouseY, partialTicks);

		this.note.visitLines(TextAlignment.CENTER, this.width / 2, 220, 10, guiGraphics.textRenderer());

		if (scrambledImage != null) {
			NativeImage nativeImage = scrambledImage.getPixels();
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.width / 2 - (nativeImage.getWidth() / 2), (80 + nativeImage.getHeight()),
					0, 0, nativeImage.getWidth(), nativeImage.getHeight(), nativeImage.getWidth(), nativeImage.getHeight());
		}
	}
}