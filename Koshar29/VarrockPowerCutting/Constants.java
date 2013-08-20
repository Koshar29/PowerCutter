package Koshar29.VarrockPowerCutting;

import java.awt.Color;

import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Tile;

public class Constants {
	public static final Tile[] pathToTrees = { new Tile(3178, 3429, 0), new Tile(3172, 3425, 0), new Tile(3170, 3419, 0), new Tile(3168, 3413, 0), new Tile(3167, 3407, 0), new Tile(3167, 3401, 0) };
	public static final Area bank = new Area(new Tile(3182, 3432, 0), new Tile(3189, 3446, 0));
	public static final Area trees = new Area(new Tile(3173, 3421, 0), new Tile(3155, 3371, 0));
	public static final int[] TREE_IDS = { 38783, 38760, 38787, 38785, 38731 }, LOG_IDS = { 1511, 1521 }, CUTTING_ANIM = { 879, 21177 }, BONFIRE_IDS = { 70755, 70757 };
	public static final int BONFIRE_STANCE = 16701, CRAFTING_MENU = 1179;
	public static final Color foreColor = new Color(69, 138, 41, 100), backColor = new Color(38, 84, 14, 100);
}
