package com.akaiha.tester.commands;

import net.md_5.bungee.api.CommandSender;

public abstract interface BasicCommand {
	public abstract boolean onCommand(CommandSender commandSender, String[] args);
}
