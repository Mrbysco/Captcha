package com.mrbysco.captcha.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.captcha.network.NetworkHandler;
import com.mrbysco.captcha.network.message.CompletedCaptchaMessage;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.PacketDistributor;

public class CaptchaScreen extends Screen {
	private final String code;
	protected int timeWaited;
	private final int maxCompletionTime;
	protected MultiLineLabel message = MultiLineLabel.EMPTY;

	protected CaptchaScreen(Component component, String code, int maxCompletionTime) {
		super(component);
		this.code = code;
		this.maxCompletionTime = maxCompletionTime;
	}

	@Override
	public void tick() {
		super.tick();
		this.timeWaited++;
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(poseStack);
		this.message.renderCentered(poseStack, this.width / 2, 70);
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return false;
	}

	public int getMaxCompletionTime() {
		return (maxCompletionTime * 20);
	}

	public void completeCaptcha() {
		this.minecraft.setScreen(null);
		NetworkHandler.CHANNEL.send(PacketDistributor.SERVER.noArg(), new CompletedCaptchaMessage(code));
	}

	public void resetCaptcha() {
		this.timeWaited = 0;
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}