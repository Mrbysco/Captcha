package com.mrbysco.captcha.client.screen;

import com.mrbysco.captcha.Captcha;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class WinRARScreen extends CaptchaScreen {
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Captcha.MOD_ID, "textures/gui/popup.png");
	protected int imageWidth = 256;
	protected int imageHeight = 166;
	protected int leftPos;
	protected int topPos;

	public WinRARScreen(String code, int maxCompletionTime) {
		super(Component.translatable("captcha.winrar.screen"), code, maxCompletionTime);
	}

	@Override
	protected void init() {
		super.init();
		this.leftPos = (this.width - this.imageWidth) / 2;
		this.topPos = (this.height - this.imageHeight) / 2;

		this.message = MultiLineLabel.create(this.font, List.of(
				Component.translatable("captcha.winrar.message1"),
				Component.translatable("captcha.winrar.message2"),
				Component.translatable("captcha.winrar.message3"),
				Component.translatable("captcha.winrar.message4"),
				Component.translatable("captcha.winrar.message5"),
				Component.translatable("captcha.winrar.message6")
		));

		int yOffset = 24;
		this.addRenderableWidget(
				Button.builder(Component.translatable("captcha.winrar.buy"), (button) -> {
					//Open WinRAR wikipedia page
					Util.getPlatform().openUri("https://en.wikipedia.org/wiki/WinRAR#License");
				}).bounds(this.width / 2 + 40, topPos + yOffset, 80, 20).build()
		);
		yOffset += 22;
		this.addRenderableWidget(
				Button.builder(Component.translatable("captcha.winrar.how"), (button) -> {
					//Open Rick Astley
					Util.getPlatform().openUri("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
				}).bounds(this.width / 2 + 40, topPos + yOffset, 80, 20).build()
		);
		yOffset += 22;
		this.addRenderableWidget(
				Button.builder(	Component.translatable("captcha.winrar.close"), (button) -> {
					completeCaptcha();
				}).bounds(this.width / 2 + 40, topPos + yOffset, 80, 20).build()
		);
		yOffset += 22;
		this.addRenderableWidget(
				Button.builder(	Component.translatable("captcha.winrar.help"), (button) -> {
					//Open Curseforge page
					Util.getPlatform().openUri("https://www.curseforge.com/minecraft/mc-mods/captcha");
				}).bounds(this.width / 2 + 40, topPos + yOffset, 80, 20).build()
		);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void resetCaptcha() {
		super.resetCaptcha();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);

		guiGraphics.hLine(leftPos + 3, leftPos + this.imageWidth - 4, topPos + 18, 0xFFFFFFFF);
		guiGraphics.drawString(this.font, this.title, leftPos + 8, topPos + 6, 4210752, false);
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		guiGraphics.blit(TEXTURE_LOCATION, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
	}

	@Override
	public void renderMessage(GuiGraphics guiGraphics) {
		guiGraphics.hLine(leftPos + 8, leftPos + 160, topPos + 30, 0xFFA0A0A0);
		guiGraphics.vLine(leftPos + 8, topPos + 30, topPos + 100, 0xFFA0A0A0);
		this.message.renderLeftAlignedNoShadow(guiGraphics, leftPos + 16, topPos + 36, 10, 0);
		guiGraphics.vLine(leftPos + 160, topPos + 30, topPos + 100, 0xFFA0A0A0);
		guiGraphics.hLine(leftPos + 8, leftPos + 160, topPos + 100, 0xFFA0A0A0);
	}
}