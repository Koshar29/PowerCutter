package Koshar29.VarrockPowerCutting.Jobs;

import org.powerbot.script.methods.MethodContext;

import Koshar29.VarrockPowerCutting.Constants;
import Koshar29.VarrockPowerCutting.Node;
import Koshar29.VarrockPowerCutting.PowerCutter;

public class WalkToTrees extends Node {

	public WalkToTrees(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public void execute() {
		PowerCutter.console("Walking To Trees");
		ctx.movement.findPath(Constants.trees.getCentralTile().randomize(4, 10)).traverse();

		sleep(1500, 2000);
	}

	@Override
	public boolean validate() {
		return !PowerCutter.myPlayer.isInMotion() && PowerCutter.myPlayer.getAnimation() == -1 && !Constants.trees.contains(PowerCutter.myPlayer.getLocation());
	}

}
