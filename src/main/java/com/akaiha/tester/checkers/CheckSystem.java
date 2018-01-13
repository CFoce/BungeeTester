package com.akaiha.tester.checkers;

import java.util.List;

import com.akaiha.tester.Tester;

public class CheckSystem implements Check {

	private Tester plugin;
	
	public CheckSystem(Tester plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public List<String> check() {
		return null; 
	}
}
