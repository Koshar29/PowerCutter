package Koshar29.VarrockPowerCutting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;

import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Player;

import Koshar29.VarrockPowerCutting.Jobs.BurnLogs;
import Koshar29.VarrockPowerCutting.Jobs.CutTrees;
import Koshar29.VarrockPowerCutting.Jobs.DumpInventory;
import Koshar29.VarrockPowerCutting.Jobs.UseBankBooth;
import Koshar29.VarrockPowerCutting.Jobs.WalkToTrees;

@Manifest(authors = { "koshar29" }, name = "Varrock Power Cutting " + PowerCutter.version, description = "Chops trees near varrock west and burns them using Bonfires.")
public class PowerCutter extends PollingScript implements PaintListener {
	public static final double version = 1.1;
	public static Player myPlayer;
	public static boolean isCutting = true, isWidgetLoaded = false;
	private final ArrayList<Node> nodes = new ArrayList<>();
	private int woodcutEXPstart = 0, firemakingEXPstart = 0;
	private long startTime = -1;

	public static void console(Object print) {
		System.out.println("[PowerCutter] " + print);
	}

	public PowerCutter() {
		Collections.addAll(nodes, new UseBankBooth(ctx), new DumpInventory(ctx), new WalkToTrees(ctx), new CutTrees(ctx), new BurnLogs(ctx));
		startTime = System.currentTimeMillis();
	}

	public void setup() {
		myPlayer = ctx.players.local();
		woodcutEXPstart = ctx.skills.getExperience(Skills.WOODCUTTING);
		firemakingEXPstart = ctx.skills.getExperience(Skills.FIREMAKING);
	}

	@Override
	public void start() {
		console("Starting Script...");
		if (ctx.game.isLoggedIn()) {
			setup();
		}
		super.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public int poll() {
		for (Node node : nodes) {
			if (node.validate()) {
				node.execute();
				return Random.nextInt(250, 375);
			}
		}
		return 0;
	}

	@Override
	public void repaint(Graphics g) {
		String title = "Varrock Power Cutter";
		// Fill Rectangles
		g.setColor(Constants.backColor);
		g.fillRect(0, 0, 250, 100);
		g.setColor(Constants.foreColor);
		g.fillRect(2, 2, 246, 96);
		// Draw Title
		Font titleFont = new Font("Arial", Font.BOLD, 15);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(titleFont);
		g2.setColor(Color.DARK_GRAY);
		g2.drawString(title, 125 - (g.getFontMetrics(titleFont).stringWidth(title) / 2), 16);
		g2.setColor(Color.WHITE);
		g2.drawString(title, 125 - (g.getFontMetrics(titleFont).stringWidth(title) / 2), 15);
		g2.dispose();
		// Draw Underline
		g.setColor(Constants.backColor);
		g.drawLine(0, 20, 249, 20);
		// g.setColor(Constants.highLight);
		g.drawLine(2, 21, 247, 21);
		// Draw Statistics
		Graphics2D g3 = (Graphics2D) g.create();
		g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g3.setColor(Color.DARK_GRAY);

		// Experience Gained
		String wcEXP = "Woodcutting EXP: " + (ctx.skills.getExperience(Skills.WOODCUTTING) - woodcutEXPstart);
		String fmEXP = "Firemaking EXP: " + (ctx.skills.getExperience(Skills.FIREMAKING) - firemakingEXPstart);
		String timeRan = "Script Run Time: " + calcTime(startTime);
		g3.drawString(wcEXP, 125 - (g3.getFontMetrics().stringWidth(wcEXP) / 2), 34);
		g3.drawString(fmEXP, 125 - (g3.getFontMetrics().stringWidth(fmEXP) / 2), 48);
		g3.drawString(timeRan, 125 - (g3.getFontMetrics().stringWidth(timeRan) / 2), 62);
		g3.setColor(Color.WHITE);
		g3.drawString(wcEXP, 125 - (g3.getFontMetrics().stringWidth(wcEXP) / 2), 33);
		g3.drawString(fmEXP, 125 - (g3.getFontMetrics().stringWidth(fmEXP) / 2), 47);
		g3.drawString(timeRan, 125 - (g3.getFontMetrics().stringWidth(timeRan) / 2), 61);

		g3.dispose();
		// Draw Borders
		g.setColor(new Color(38, 84, 14));
		g.drawRect(0, 0, 249, 99);
	}

	private String calcTime(long timeMillis) {
		long elapsedTime = System.currentTimeMillis() - timeMillis;
		elapsedTime = elapsedTime / 1000;
		String seconds = Integer.toString((int) (elapsedTime % 60));
		String minutes = Integer.toString((int) ((elapsedTime % 3600) / 60));
		String hours = Integer.toString((int) (elapsedTime / 3600));
		return check(hours) + ":" + check(minutes) + ":" + check(seconds);
	}

	private String check(String time) {
		if (time.length() < 2) {
			return "0" + time;
		}
		return "" + time;
	}
}
