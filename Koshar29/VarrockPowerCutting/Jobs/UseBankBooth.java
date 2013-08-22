package Koshar29.VarrockPowerCutting.Jobs;

import org.powerbot.script.methods.Bank;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;

import Koshar29.VarrockPowerCutting.Constants;
import Koshar29.VarrockPowerCutting.Node;
import Koshar29.VarrockPowerCutting.PowerCutter;

public class UseBankBooth extends Node {

	public UseBankBooth(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public void execute() {
		PowerCutter.console("Using Bank Booth");
		if (!ctx.objects.select().id(Bank.BANK_BOOTH_IDS).isEmpty()) {
			for (GameObject booth : ctx.objects.id(Bank.BANK_BOOTH_IDS).nearest().first()) {
				booth.interact("Bank");
				sleep(250, 600);
			}
		}
	}

	@Override
	public boolean validate() {
		return !PowerCutter.myPlayer.isInMotion() && PowerCutter.myPlayer.getAnimation() == -1 && Constants.bank.contains(PowerCutter.myPlayer.getLocation()) && ctx.backpack.select().count() > 0 && !ctx.bank.isOpen();
	}

}
