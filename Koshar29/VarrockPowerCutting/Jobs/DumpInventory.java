package Koshar29.VarrockPowerCutting.Jobs;

import org.powerbot.script.methods.MethodContext;

import Koshar29.VarrockPowerCutting.Constants;
import Koshar29.VarrockPowerCutting.Node;
import Koshar29.VarrockPowerCutting.PowerCutter;

public class DumpInventory extends Node {

	public DumpInventory(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public void execute() {
		PowerCutter.console("Depositing Inventory");
		ctx.bank.depositInventory();
		sleep(300, 500);
		ctx.bank.close();
	}

	@Override
	public boolean validate() {
		return !PowerCutter.myPlayer.isInMotion() && PowerCutter.myPlayer.getAnimation() == -1 && Constants.bank.contains(PowerCutter.myPlayer.getLocation()) && ctx.backpack.select().count() > 0 && ctx.bank.isOpen();
	}

}
