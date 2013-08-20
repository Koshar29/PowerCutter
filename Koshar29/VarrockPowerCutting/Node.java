package Koshar29.VarrockPowerCutting;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

public abstract class Node extends MethodProvider {

	public Node(final MethodContext ctx) {
		super(ctx);
	}

	public abstract void execute();

	public abstract boolean validate();

}