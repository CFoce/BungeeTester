package com.akaiha.tester;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.akaiha.tester.commands.Test;
import com.akaiha.tester.listeners.Listeners;
import com.akaiha.tester.util.TestData;

import net.md_5.bungee.api.plugin.Plugin;

public class Tester extends Plugin {

	private Test test;
	public Runtime runtime;
	public NumberFormat format;
	public TestData data;
	public Listeners listener;
	
	public void onEnable() {
		getLogger().info("Enabling!");
		getLogger().info("Starting in OFF configuration!");
		runtime = Runtime.getRuntime();
		format = new DecimalFormat("#0.0");
		data = new TestData(this);
		getProxy().getPluginManager().registerCommand(this, test = new Test(this));
		listener = new Listeners(this);
		getLogger().info("Enable Finished!");
	}

	public void onDisable() {
		getLogger().info("Disabling!");
		getProxy().getPluginManager().unregisterCommand(test);
		getLogger().info("Disable Finished!");
	}
}