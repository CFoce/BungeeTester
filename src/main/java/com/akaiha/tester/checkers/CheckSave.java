package com.akaiha.tester.checkers;

import java.util.List;

import com.akaiha.tester.Tester;

public class CheckSave implements Check {

	private Tester plugin;
	
	public CheckSave(Tester plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public List<String> check() {
		return null; 
	}
}
