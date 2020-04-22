package com.caideli.springBoot.headfirst.command.undo;

public interface Command {
	public void execute();
	public void undo();
}
