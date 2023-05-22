package com.mrbysco.captcha.client.screen.image;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.captcha.client.screen.CaptchaScreen;
import com.mrbysco.captcha.client.screen.widget.ToggleButton;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ImageScreen extends CaptchaScreen {
	private static final Random random = new Random();
	private ResourceLocation IMAGE = new ResourceLocation("captcha", "textures/gui/warp.png");
	private int SQUARE_SIZE = 64; // The size of each square button
	private boolean[][] correctSquares = new boolean[4][4];
	private int tries = 0;

	private final ToggleButton[][] buttons = new ToggleButton[4][4];
	private Button button;
	protected MultiLineLabel error = MultiLineLabel.EMPTY;
	protected int errorY = 406;

	public ImageScreen(String code, int maxCompletionTime) {
		super(Component.translatable("captcha.image.screen"), code, maxCompletionTime);
	}

	@Override
	protected void init() {
		super.init();

		this.message = MultiLineLabel.create(this.font, List.of(
				Component.empty(),
				Component.translatable("captcha.image.screen"),
				Component.empty(),
				Component.translatable("captcha.image.none")
		));

		// Calculate the size and position of each square button
		updateImageSize();
		int startX = ((width - SQUARE_SIZE * 4) / 2) - 4;
		int startY = ((height - SQUARE_SIZE * 4) / 2) - 4;
		messageY = startY - (message.getLineCount() * font.lineHeight) - 12;

		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				int id = row + col;
				int x = (startX + col * SQUARE_SIZE) + (col * 4);
				int y = (startY + row * SQUARE_SIZE) + (row * 4);
				int textureX = col * SQUARE_SIZE;
				int textureY = row * SQUARE_SIZE;
				this.addRenderableWidget(buttons[row][col] =
						new ToggleButton(x, y, SQUARE_SIZE, SQUARE_SIZE, textureX, textureY,
								IMAGE, 128, 128, (button) -> {
							// Handle button click
							((ToggleButton) button).setClicked(!((ToggleButton) button).isClicked());
							updateButton();
						}, Component.literal("square " + id)));
			}
		}

		changeImage(false);

		this.errorY = startY + (SQUARE_SIZE * 4) + 16;
		this.addRenderableWidget(button =
				new Button(this.width / 2 - 60, startY + (SQUARE_SIZE * 4) + 36, 120, 20,
						Component.translatable("captcha.image.skip"), (button) -> checkSquares())
		);
	}

	private void updateImageSize() {
		SQUARE_SIZE = 32;
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		super.resize(minecraft, width, height);
		updateImageSize();
	}

	private void changeImage(boolean reset) {
		ImageEnum image = ImageEnum.getRandom(random);
		IMAGE = new ResourceLocation("captcha", "textures/gui/" + image.getImageName() + ".png");
		List<Map.Entry<String, boolean[][]>> entries = image.getDataMap().entrySet().stream().toList();
		Map.Entry<String, boolean[][]> entry = entries.get(random.nextInt(entries.size()));
		String object = entry.getKey();
		correctSquares = entry.getValue();
		if (buttons.length > 0) {
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					ToggleButton toggleButton = this.buttons[x][y];
					if (toggleButton != null) {
						toggleButton.setResourceLocation(IMAGE);
						toggleButton.setClicked(false);
					}
				}
			}
		}

		if (reset) {
			error = MultiLineLabel.create(this.font, List.of(
					Component.translatable("captcha.text.failed").withStyle(ChatFormatting.RED),
					Component.translatable("captcha.text.failed2").withStyle(ChatFormatting.RED)
			));
		}
		this.message = MultiLineLabel.create(this.font, List.of(
				Component.translatable("captcha.image.screen"),
				Component.empty(),
				Component.literal(object).withStyle(ChatFormatting.GOLD),
				Component.empty(),
				Component.translatable("captcha.image.none")
		));
	}

	/**
	 * Checks if all the correct squares are toggled
	 */
	private void checkSquares() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				ToggleButton toggleButton = this.buttons[x][y];
				if (toggleButton != null) {
					if (toggleButton.isClicked() != correctSquares[x][y]) {
						resetCaptcha();
						return;
					}
				}
			}
		}
		this.completeCaptcha();
	}

	private void updateButton() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				ToggleButton toggleButton = this.buttons[x][y];
				if (toggleButton != null) {
					if (toggleButton.isClicked()) {
						this.button.setMessage(Component.translatable("captcha.image.verify"));
						return;
					}
				}
			}
		}
		this.button.setMessage(Component.translatable("captcha.image.skip"));
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void resetCaptcha() {
		super.resetCaptcha();
		if (this.timeWaited > getMaxCompletionTime()) {
			this.changeImage(true);
			this.tries = 0;
			return;
		}
		if (tries > 1) {
			this.tries = 0;
			this.changeImage(true);
		} else {
			tries++;
		}
	}

	@Override
	public int getMaxCompletionTime() {
		return super.getMaxCompletionTime();
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		super.render(poseStack, mouseX, mouseY, partialTicks);
		this.error.renderCentered(poseStack, this.width / 2, this.errorY);
	}
}