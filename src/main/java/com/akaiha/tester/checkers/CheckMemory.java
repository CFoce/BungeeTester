package com.akaiha.tester.checkers;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import com.akaiha.tester.Tester;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class CheckMemory implements Check {

	private Runtime runtime;
	private Format format;
	
	public CheckMemory(Tester plugin) {
		this.runtime = plugin.runtime;
		this.format = plugin.format;
	}
	
	@Override
	public List<String> check() {
		List<String> list = new ArrayList<String>();
		double maxMemory = (double) runtime.maxMemory() / (double) (1024 * 1024);
    	double allocatedMemory = (double) runtime.totalMemory() / (double) (1024 * 1024);
    	double freeMemory = (double) runtime.freeMemory() / (double) (1024 * 1024);
    	double usedMemory = allocatedMemory - freeMemory;
    	
    	String str = "&5Memory: &6" + format.format(usedMemory) + "&5/&6" + format.format(maxMemory) + " &5MB " +
				" &6(&6" + format.format(allocatedMemory) + "&5Allocated)";
    	list.add(str);
    	
		return list;
	}
}