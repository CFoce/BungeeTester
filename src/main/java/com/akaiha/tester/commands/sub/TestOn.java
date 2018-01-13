package com.akaiha.tester.commands.sub;

import com.akaiha.tester.Tester;
import com.akaiha.tester.commands.BasicCommand;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class TestOn implements BasicCommand {
	
	private Tester plugin;
	
	public TestOn(Tester plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(final CommandSender commandSender, final String[] args) {
		plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
            @Override
            public void run() {
            	String output;
            	if (!plugin.data.testing.get()) {
            		plugin.data.testing.set(true);
            		plugin.getProxy().getPluginManager().registerListener(plugin, plugin.listener);
            		output = "&aTesting Has Been Turned: &eON";
            	} else {
            		output = "&aTesting Is Already: &eON";
            	}
            	commandSender.sendMessage(
            			new TextComponent(
            					ChatColor.translateAlternateColorCodes('&', 
            							output)));
            }
		});
		return false;
	}
}