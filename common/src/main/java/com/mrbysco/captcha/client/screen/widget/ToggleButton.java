package com.mrbysco.captcha.client.screen.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3x2fStack;

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
	public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
//		RenderSystem.enableDepthTest();
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, this.resourceLocation, this.getX(), this.getY(), (float) this.xTexStart, (float) this.yTexStart, this.width, this.height, this.textureWidth, this.textureHeight);
		if (clicked) {
			Matrix3x2fStack poseStack = guiGraphics.pose();
			poseStack.pushMatrix();
			poseStack.translate(this.getX() - 1, this.getY() - 1);
			guiGraphics.fill(0, 0, 34, 34, 0x1AFFFF00);
			poseStack.popMatrix();
		}
	}
}
