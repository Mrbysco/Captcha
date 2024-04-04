package com.mrbysco.captcha.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.captcha.Captcha;
import net.minecraft.Util;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class WinRARScreen extends CaptchaScreen {
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Captcha.MOD_ID, "textures/gui/popup.png");
	protected int imageWidth = 256;
	protected int imageHeight = 166;
	protected int leftPos;
	protected int topPos;

	public WinRARScreen(String code, int maxCompletionTime) {
		super(new TranslatableComponent("captcha.winrar.screen"), code, maxCompletionTime);
	}

	@Override
	protected void init() {
		super.init();
		this.leftPos = (this.width - this.imageWidth) / 2;
		this.topPos = (this.height - this.imageHeight) / 2;

		this.message = MultiLineLabel.create(this.font, List.of(
				new TranslatableComponent("captcha.winrar.message1"),
				new TranslatableComponent("captcha.winrar.message2"),
				new TranslatableComponent("captcha.winrar.message3"),
				new TranslatableComponent("captcha.winrar.message4"),
				new TranslatableComponent("captcha.winrar.message5"),
				new TranslatableComponent("captcha.winrar.message6")
		));

		int yOffset = 24;
		this.addRenderableWidget(
				new Button(this.width / 2 + 40, topPos + yOffset, 80, 20,
						new TranslatableComponent("captcha.winrar.buy"), (button) -> {
					//Open WinRAR wikipedia page
					Util.getPlatform().openUri("https://en.wikipedia.org/wiki/WinRAR#License");
				})
		);
		yOffset += 22;
		this.addRenderableWidget(
				new Button(this.width / 2 + 40, topPos + yOffset, 80, 20,
						new TranslatableComponent("captcha.winrar.how"), (button) -> {
					//Open Rick Astley
					Util.getPlatform().openUri("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
				})
		);
		yOffset += 22;
		this.addRenderableWidget(
				new Button(this.width / 2 + 40, topPos + yOffset, 80, 20,
						new TranslatableComponent("captcha.winrar.close"), (button) -> {
					completeCaptcha();
				})
		);
		yOffset += 22;
		this.addRenderableWidget(
				new Button(this.width / 2 + 40, topPos + yOffset, 80, 20,
						new TranslatableComponent("captcha.winrar.help"), (button) -> {
					//Open Curseforge page
					Util.getPlatform().openUri("https://www.curseforge.com/minecraft/mc-mods/captcha");
				})
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
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		super.render(poseStack, mouseX, mouseY, partialTicks);

		this.hLine(poseStack, leftPos + 3, leftPos + this.imageWidth - 4, topPos + 18, 0xFFFFFFFF);
		this.font.draw(poseStack, this.title, leftPos + 8, topPos + 6, 4210752);
	}

	@Override
	public void renderBackground(PoseStack poseStack) {
		super.renderBackground(poseStack);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE_LOCATION);
		blit(poseStack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
	}

	@Override
	public void renderMessage(PoseStack poseStack) {
		this.hLine(poseStack, leftPos + 8, leftPos + 160, topPos + 30, 0xFFA0A0A0);
		this.vLine(poseStack, leftPos + 8, topPos + 30, topPos + 100, 0xFFA0A0A0);
		this.message.renderLeftAlignedNoShadow(poseStack, leftPos + 16, topPos + 36, 10, 0);
		this.vLine(poseStack, leftPos + 160, topPos + 30, topPos + 100, 0xFFA0A0A0);
		this.hLine(poseStack, leftPos + 8, leftPos + 160, topPos + 100, 0xFFA0A0A0);
	}
}