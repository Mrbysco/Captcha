package com.mrbysco.captcha.client.screen;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.DynamicTexture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class RenderUtil {
	private static final Random random = new Random();

	/**
	 * Generate a DynamicTexture that is a 200x40 image of the given string on a white background but the text is distorted similar to a text captcha.
	 * return DynamicTexture
	 */
	public static DynamicTexture generateCaptchaTexture(String str, int width, int height) throws IOException {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Arial", Font.BOLD, 24));

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int x = 20 + i * (width / str.length());
			int y = (40 * str.length()) + random.nextInt(10);
			graphics.drawChars(new char[]{ch}, 0, 1, x, y);

			AffineTransform shear = AffineTransform.getShearInstance(random.nextDouble() * 0.5D, 0);

			graphics.transform(shear);
		}

		graphics.drawString(str, 10, 25);

		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "png", arrayOutputStream);
		return new DynamicTexture(NativeImage.read(new ByteArrayInputStream(arrayOutputStream.toByteArray())));
	}
}
