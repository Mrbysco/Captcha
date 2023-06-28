package com.mrbysco.captcha.client.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ToggleButton extends Button {

	private int xTexStart;
	private int yTexStart;
	private final int textureWidth;
	private final int textureHeight;
	private ResourceLocation resourceLocation;
	private boolean clicked;

	public ToggleButton(int x, int y, int width, int height, int xTexStart, int yTexStart, ResourceLocation resourceLocation, int textureWidth, int textureHeight, Button.OnPress onPress, Component component) {
		super(x, y, width, height, component, onPress, DEFAULT_NARRATION);
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		this.xTexStart = xTexStart;
		this.yTexStart = yTexStart;
		this.resourceLocation = resourceLocation;
		this.clicked = false;
	}

	public boolean isClicked() {
		return this.clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public void setPosition(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public void setResourceLocation(ResourceLocation resourceLocation) {
		this.resourceLocation = resourceLocation;
	}

	public void setXTexStart(int xTexStart) {
		this.xTexStart = xTexStart;
	}

	public void setYTexStart(int yTexStart) {
		this.yTexStart = yTexStart;
	}

	@Override
	public void renderWidget(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		this.renderTexture(poseStack, this.resourceLocation, this.getX(), this.getY(), this.xTexStart, this.yTexStart, 0, this.width, this.height, this.textureWidth, this.textureHeight);
	}

	@Override
	public void renderTexture(PoseStack poseStack, ResourceLocation resourceLocation, int p_268218_, int p_267959_, int p_268261_, int p_267978_, int p_267937_, int p_268330_, int p_268160_, int p_267985_, int p_268321_) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, this.resourceLocation);

		RenderSystem.enableDepthTest();
		blit(poseStack, this.getX(), this.getY(), (float) this.xTexStart, (float) this.yTexStart, this.width, this.height, this.textureWidth, this.textureHeight);
		if (clicked) {
			poseStack.pushPose();
			poseStack.translate(this.getX() - 1, this.getY() - 1, 0);
			fill(poseStack, 0, 0, 34, 34, 0x1AFFFF00);
			poseStack.popPose();
		}
	}
}
