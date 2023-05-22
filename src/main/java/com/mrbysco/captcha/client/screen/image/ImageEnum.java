package com.mrbysco.captcha.client.screen.image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum ImageEnum {
	AMETHYST("amethyst", generateAmethyst()),
	BEE("bee", generateBees()),
	END("end", generateEnd()),
	ICY("icy", generateIcy()),
	PLAINS("plains", generatePlains()),
	PORTAL("portal", generatePortal()),
	SANDY("sandy", generateSandy()),
	VEHICLE("vehicle", generateVehicle());

	private final String imageName;
	private final Map<String, boolean[][]> dataMap;

	ImageEnum(String imageName, Map<String, boolean[][]> dataMap) {
		this.imageName = imageName;
		this.dataMap = dataMap;
	}

	public String getImageName() {
		return imageName;
	}

	public Map<String, boolean[][]> getDataMap() {
		return dataMap;
	}

	private static Map<String, boolean[][]> generateAmethyst() {
		Map<String, boolean[][]> dataMap = new HashMap<>();
		boolean[][] budding = {
				{false, false, false, false},
				{true, false, false, true},
				{false, true, true, false},
				{true, true, false, false}
		};
		dataMap.put("Budding Amethyst", budding);
		boolean[][] cluster = {
				{false, false, false, false},
				{false, false, false, false},
				{true, true, false, false},
				{true, true, false, false}
		};
		dataMap.put("Amethyst Cluster", cluster);
		return dataMap;
	}

	private static Map<String, boolean[][]> generateBees() {
		Map<String, boolean[][]> dataMap = new HashMap<>();
		boolean[][] bees = {
				{false, false, false, false},
				{false, false, true, true},
				{false, false, false, false},
				{true, false, false, false}
		};
		dataMap.put("Bees", bees);
		boolean[][] hives = {
				{false, false, false, false},
				{false, false, false, true},
				{false, true, false, false},
				{false, true, false, false}
		};
		dataMap.put("Beehives", hives);
		boolean[][] flowers = {
				{true, false, false, false},
				{true, true, false, false},
				{true, false, false, false},
				{true, false, false, false}
		};
		dataMap.put("Flowers", flowers);
		boolean[][] leaves = {
				{true, true, true, true},
				{true, true, true, true},
				{true, true, true, true},
				{false, false, false, false}
		};
		dataMap.put("Leaves", leaves);
		boolean[][] birch = {
				{false, true, false, false},
				{false, true, false, false},
				{false, false, false, false},
				{false, false, false, false}
		};
		dataMap.put("Birch Trees", birch);
		return dataMap;
	}

	private static Map<String, boolean[][]> generateEnd() {
		Map<String, boolean[][]> dataMap = new HashMap<>();
		boolean[][] city = {
				{true, true, true, true},
				{true, true, true, false},
				{true, true, true, true},
				{false, false, true, true}
		};
		dataMap.put("End City", city);
		boolean[][] chorus = {
				{false, false, false, false},
				{false, true, true, true},
				{true, true, true, false},
				{true, true, false, false}
		};
		dataMap.put("Chorus plants", chorus);
		boolean[][] banner = {
				{true, false, false, false},
				{false, false, false, false},
				{false, false, true, false},
				{false, false, false, false}
		};
		dataMap.put("Banners", banner);
		return dataMap;
	}

	private static Map<String, boolean[][]> generateIcy() {
		Map<String, boolean[][]> dataMap = new HashMap<>();
		boolean[][] ice = {
				{false, false, false, false},
				{false, false, true, true},
				{false, true, true, true},
				{true, true, true, true}
		};
		dataMap.put("Ice", ice);
		boolean[][] animals = {
				{false, false, false, false},
				{false, false, false, false},
				{false, false, false, false},
				{false, false, false, false}
		};
		dataMap.put("Animals", animals);
		return dataMap;
	}

	private static Map<String, boolean[][]> generatePlains() {
		Map<String, boolean[][]> dataMap = new HashMap<>();
		boolean[][] pumpkins = {
				{false, false, false, false},
				{true, true, false, false},
				{true, true, false, false},
				{false, false, false, false}
		};
		dataMap.put("Pumpkins", pumpkins);
		boolean[][] horse = {
				{false, false, false, false},
				{false, false, false, true},
				{false, false, true, false},
				{false, false, false, false}
		};
		dataMap.put("Horses", horse);
		boolean[][] flowers = {
				{true, true, true, false},
				{false, true, true, false},
				{false, false, false, false},
				{false, false, false, false}
		};
		dataMap.put("Flowers", flowers);
		return dataMap;
	}

	private static Map<String, boolean[][]> generatePortal() {
		Map<String, boolean[][]> dataMap = new HashMap<>();
		boolean[][] magma = {
				{false, false, false, false},
				{false, true, true, false},
				{true, true, true, true},
				{true, false, false, false}
		};
		dataMap.put("Magma", magma);
		boolean[][] obsidian = {
				{false, false, false, false},
				{false, true, true, false},
				{false, true, true, false},
				{false, false, false, false}
		};
		dataMap.put("Obsidian", obsidian);
		boolean[][] water = {
				{true, true, true, true},
				{true, true, true, true},
				{true, false, false, true},
				{true, false, true, true}
		};
		dataMap.put("Water", water);
		return dataMap;
	}

	private static Map<String, boolean[][]> generateSandy() {
		Map<String, boolean[][]> dataMap = new HashMap<>();
		boolean[][] water = {
				{true, false, false, false},
				{true, false, false, true},
				{false, false, false, false},
				{false, false, true, true}
		};
		dataMap.put("Water", water);
		boolean[][] lava = {
				{false, false, false, false},
				{false, true, false, false},
				{false, false, false, false},
				{false, false, false, false}
		};
		dataMap.put("Lava", lava);
		boolean[][] snow = {
				{false, true, false, false},
				{false, false, false, false},
				{false, false, true, false},
				{false, false, false, false}
		};
		dataMap.put("Snow", snow);
		boolean[][] empty = {
				{false, false, false, false},
				{false, false, true, false},
				{true, false, false, false},
				{true, true, false, false}
		};
		dataMap.put("Empty Cauldrons", empty);
		boolean[][] turtle = {
				{false, false, false, true},
				{false, false, false, false},
				{false, false, false, false},
				{false, false, false, false}
		};
		dataMap.put("Turtles", turtle);
		return dataMap;
	}

	private static Map<String, boolean[][]> generateVehicle() {
		Map<String, boolean[][]> dataMap = new HashMap<>();
		boolean[][] chest = {
				{true, false, false, false},
				{true, false, false, false},
				{false, false, true, false},
				{false, false, false, false}
		};
		dataMap.put("Chests", chest);
		boolean[][] boats = {
				{true, true, false, false},
				{false, false, false, false},
				{false, false, false, true},
				{false, false, true, false}
		};
		dataMap.put("Boats", boats);
		boolean[][] carts = {
				{false, false, false, false},
				{true, false, false, false},
				{false, false, true, false},
				{false, false, false, false}
		};
		dataMap.put("Minecarts", carts);
		boolean[][] leaves = {
				{false, false, false, false},
				{false, true, false, true},
				{false, true, false, true},
				{false, true, false, false}
		};
		dataMap.put("Leaves", leaves);
		boolean[][] water = {
				{true, false, true, true},
				{false, false, false, true},
				{true, false, true, false},
				{true, true, true, true}
		};
		dataMap.put("Water", water);
		boolean[][] regular = {
				{false, true, false, false},
				{false, false, false, false},
				{false, false, false, true},
				{false, false, true, false}
		};
		dataMap.put("Regular Boats", regular);
		return dataMap;
	}

	public static ImageEnum getRandom(Random random) {
		int pick = random.nextInt(ImageEnum.values().length);
		return ImageEnum.values()[pick];
	}


//	private static Map<String, boolean[][]> generateExample() {
//		Map<String, boolean[][]> dataMap = new HashMap<>();
//		boolean[][] example = {
//				{false, false, false, false},
//				{false, false, false, false},
//				{false, false, false, false},
//				{false, false, false, false}
//		};
//		dataMap.put("Example", example);
//		return dataMap;
//	}
}