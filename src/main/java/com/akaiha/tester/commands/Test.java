package com.akaiha.tester.commands;

import java.util.EnumMap;

import com.akaiha.tester.Tester;
import com.akaiha.tester.commands.sub.TestInfo;
import com.akaiha.tester.commands.sub.TestOff;
import com.akaiha.tester.commands.sub.TestOn;
import com.akaiha.tester.util.UtilEnum;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class Test extends Command {
	
	private Tester plugin;
	private EnumMap<TestEnum, BasicCommand> commands;
	
	public Test(Tester plugin) {
		super("test");
		this.plugin = plugin;
		this.commands = new EnumMap<TestEnum, BasicCommand>(TestEnum.class);
		load();
	}
	
	private void load() {
		commands.put(TestEnum.ON, new TestOn(plugin));
		commands.put(TestEnum.OFF, new TestOff(plugin));
		commands.put(TestEnum.INFO, new TestInfo(plugin));
	}

	@Override
	public void execute(final CommandSender sender, final String[] args) {
		plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
            @Override
            public void run() {
            	if (args.length > 0 && sender.hasPermission("tester") && UtilEnum.isInEnum(args[0].toUpperCase(), TestEnum.class)) {
            		TestEnum tEnum = TestEnum.valueOf(args[0].toUpperCase());
            		commands.get(tEnum).onCommand(sender, args);
        		}
            }
		});
	}
}
