package com.akaiha.tester.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.akaiha.tester.Tester;

public class TestData {

	private Tester plugin;
	public AtomicBoolean testing = new AtomicBoolean(false);
	
	public List<Long> chatTimes;
	public List<Long> loginTimes;
	public List<Long> permissionCheckTimes;
	public List<Long> playerDisconnectTimes;
	public List<Long> playerHandshakeTimes;
	public List<Long> pluginMessageTimes;
	public List<Long> postLoginTimes;
	public List<Long> preLoginTimes;
	public List<Long> proxyPingTimes;
	public List<Long> proxyReloadTimes;
	public List<Long> serverConnectedTimes;
	public List<Long> serverConnectTimes;
	public List<Long> serverDisconnectTimes;
	public List<Long> serverKickTimes;
	public List<Long> serverSwitchTimes;
	public List<Long> tabCompleteTimes;
	public List<Long> tabCompleteResponseTimes;
	public List<Long> targetedTimes;
	
	public AtomicInteger chatCount;
	public AtomicInteger loginCount;
	public AtomicInteger permissionCheckCount;
	public AtomicInteger playerDisconnectCount;
	public AtomicInteger playerHandshakeCount;
	public AtomicInteger pluginMessageCount;
	public AtomicInteger postLoginCount;
	public AtomicInteger preLoginCount;
	public AtomicInteger proxyPingCount;
	public AtomicInteger proxyReloadCount;
	public AtomicInteger serverConnectedCount;
	public AtomicInteger serverConnectCount;
	public AtomicInteger serverDisconnectCount;
	public AtomicInteger serverKickCount;
	public AtomicInteger serverSwitchCount;
	public AtomicInteger tabCompleteCount;
	public AtomicInteger tabCompleteResponseCount;
	public AtomicInteger targetedCount;
	
	public TestData(Tester plugin) {
		this.plugin = plugin;
		resetCount();
		resetTimes();
	}
	
	public String getTimes(String name, AtomicInteger count, List<Long> list) {
		int temp = count.get();
		if (temp > 0) {
			long all = 0L;
			long avg = 0L;
			
			synchronized (list) {
				Iterator<Long> iterator = list.iterator(); 
				while (iterator.hasNext())
			          all += iterator.next();
			}
			avg = all / temp;
			
			if (avg >= 1000000) {
				return (name + " count: " + temp + " avg: " + plugin.format.format(avg / 1000000) + " ms");
			} else {
				return (name + " count: " + temp + " avg: " + plugin.format.format(avg) + " ns");
			}
		} else {
			return (name + " No Data");
		}
	}
	
	public void addTime(final long time, AtomicInteger count, List<Long> list) {
		plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
            @Override
            public void run() {
            	if (count.get() >= 100) {
            		count.set(100);
            		list.remove(0);
            		list.add(time);
            	} else {
            		count.getAndIncrement();
            		list.add(time);
            	}
            }
		});
	}
	
	public void resetCount() {
		this.chatCount = new AtomicInteger(0);
		this.loginCount = new AtomicInteger(0);
		this.permissionCheckCount = new AtomicInteger(0);
		this.playerDisconnectCount = new AtomicInteger(0);
		this.playerHandshakeCount = new AtomicInteger(0);
		this.pluginMessageCount = new AtomicInteger(0);
		this.postLoginCount = new AtomicInteger(0);
		this.preLoginCount = new AtomicInteger(0);
		this.proxyPingCount = new AtomicInteger(0);
		this.proxyReloadCount = new AtomicInteger(0);
		this.serverConnectedCount = new AtomicInteger(0);
		this.serverConnectCount = new AtomicInteger(0);
		this.serverDisconnectCount = new AtomicInteger(0);
		this.serverKickCount = new AtomicInteger(0);
		this.serverSwitchCount = new AtomicInteger(0);
		this.tabCompleteCount = new AtomicInteger(0);
		this.tabCompleteResponseCount = new AtomicInteger(0);
		this.targetedCount = new AtomicInteger(0);
	}
	
	public void resetTimes() {
		this.chatTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.loginTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.permissionCheckTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.playerDisconnectTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.playerHandshakeTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.pluginMessageTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.postLoginTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.preLoginTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.proxyPingTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.proxyReloadTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.serverConnectedTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.serverConnectTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.serverDisconnectTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.serverKickTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.serverSwitchTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.tabCompleteTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.tabCompleteResponseTimes = Collections.synchronizedList(new ArrayList<Long>());
		this.targetedTimes = Collections.synchronizedList(new ArrayList<Long>());
	}
}
