package Koshar29.VarrockPowerCutting.Jobs;

import org.powerbot.script.methods.Hud;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Item;

import Koshar29.VarrockPowerCutting.Constants;
import Koshar29.VarrockPowerCutting.Node;
import Koshar29.VarrockPowerCutting.PowerCutter;

public class BurnLogs extends Node {

	public BurnLogs(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public void execute() {
		if (ctx.backpack.select().id(Constants.LOG_IDS).count() == 0) {
			PowerCutter.isCutting = true;
			return;
		}
		if (!ctx.hud.isVisible(Hud.Window.BACKPACK)) {
			ctx.hud.open(Hud.Window.BACKPACK);
		}

		if (!ctx.hud.isVisible(Hud.Window.BACKPACK)) {
			ctx.hud.view(Hud.Window.BACKPACK);
		}
		if (ctx.objects.select().id(Constants.BONFIRE_IDS).within(1).isEmpty()) {
			PowerCutter.console("Create Fire");
			if (ctx.groundItems.select().id(Constants.LOG_IDS).within(1).isEmpty()) {
				for (Item i : ctx.backpack.select().id(Constants.LOG_IDS).first()) {
					i.interact("Light");
					sleep(2000, 2400);
				}
			}
		} else {
			if (ctx.widgets.get(Constants.CRAFTING_MENU).isValid()) {
				ctx.widgets.get(Constants.CRAFTING_MENU, 39).click();
				sleep(1000, 1500);
			} else {
				if (PowerCutter.myPlayer.getStance() != Constants.BONFIRE_STANCE && !PowerCutter.myPlayer.isInMotion()) {
					PowerCutter.console("Burning Logs");
					for (Item i : ctx.backpack.select().id(Constants.LOG_IDS).first()) {
						i.interact("Craft");
						sleep(1000, 1200);
					}
				}
			}
		}
	}

	@Override
	public boolean validate() {
		return !PowerCutter.myPlayer.isInMotion() && !PowerCutter.isCutting && PowerCutter.myPlayer.getAnimation() == -1 && PowerCutter.myPlayer.getStance() != Constants.BONFIRE_STANCE && !PowerCutter.isCutting;
	}
}
