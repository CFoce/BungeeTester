package com.akaiha.tester.commands.sub;

import com.akaiha.tester.Tester;
import com.akaiha.tester.commands.BasicCommand;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class TestOff implements BasicCommand {
	
	private Tester plugin;
	
	public TestOff(Tester plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(final CommandSender commandSender, final String[] args) {
		plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
            @Override
            public void run() {
            	String output;
            	if (plugin.data.testing.get()) {
            		plugin.data.testing.set(false);
            		plugin.getProxy().getPluginManager().unregisterListener(plugin.listener);
            		plugin.data.resetCount();
            		plugin.data.resetTimes();
            		output = "&aTesting Has Been Turned: &eOFF";
            	} else {
            		output = "&aTesting Is Already: &eOFF";
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