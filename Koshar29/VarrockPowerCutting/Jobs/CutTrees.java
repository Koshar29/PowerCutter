package Koshar29.VarrockPowerCutting.Jobs;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.GameObject;

import Koshar29.VarrockPowerCutting.Constants;
import Koshar29.VarrockPowerCutting.Node;
import Koshar29.VarrockPowerCutting.PowerCutter;

public class CutTrees extends Node {

	public CutTrees(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public void execute() {
		PowerCutter.console("Cutting Tree");
		if (ctx.backpack.select().count() == 28) {
			PowerCutter.isCutting = false;
			return;
		}
		if (ctx.skills.getLevel(Skills.WOODCUTTING) < 15) {
			for (GameObject o : ctx.objects.select().id(Constants.TREE_IDS).nearest().first()) {
				ctx.camera.turnTo(o.getLocation().randomize(2, 4));
				o.click();
			}
		} else {
			for (GameObject o : ctx.objects.select().id(Constants.ALL_TREE_IDS).nearest().first()) {
				ctx.camera.turnTo(o.getLocation().randomize(2, 4));
				o.click();
			}
		}
		sleep(2000, 3000);
	}

	@Override
	public boolean validate() {
		return !PowerCutter.myPlayer.isInMotion() && PowerCutter.myPlayer.getAnimation() == -1 && Constants.trees.contains(PowerCutter.myPlayer.getLocation()) && PowerCutter.isCutting;
	}

}
