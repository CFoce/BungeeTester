package com.akaiha.tester.listeners;

import com.akaiha.tester.Tester;
import com.akaiha.tester.util.TestData;

import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Listeners implements Listener {
	
	private TestData data;
	
	private long chatTime = 0L;
	private long loginTime = 0L;
	private long permissionCheckTime = 0L;
	private long playerDisconnectTime = 0L;
	private long playerHandshakeTime = 0L;
	private long pluginMessageTime = 0L;
	private long postLoginTime = 0L;
	private long preLoginTime = 0L;
	private long proxyPingTime = 0L;
	private long proxyReloadTime = 0L;
	private long serverConnectedTime = 0L;
	private long serverConnectTime = 0L;
	private long serverDisconnectTime = 0L;
	private long serverKickTime = 0L;
	private long serverSwitchTime = 0L;
	private long tabCompleteTime = 0L;
	private long tabCompleteResponseTime = 0L;
	private long targetedTime = 0L;
	
	public Listeners(Tester plugin) {
		this.data = plugin.data;
	}

	@EventHandler(priority = Byte.MIN_VALUE)
	public void startChatEvent(ChatEvent e) {
		this.chatTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endChatEvent(ChatEvent e) {
		long time = System.nanoTime() - chatTime;
		data.addTime(time, data.chatCount, data.chatTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startLoginEvent(LoginEvent e) {
		this.loginTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endLoginEvent(LoginEvent e) {
		long time = System.nanoTime() - loginTime;
		data.addTime(time, data.loginCount, data.loginTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startPermissionCheckEvent(PermissionCheckEvent e) {
		this.permissionCheckTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endPermissionCheckEvent(PermissionCheckEvent e) {
		long time = System.nanoTime() - permissionCheckTime;
		data.addTime(time, data.permissionCheckCount, data.permissionCheckTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startPlayerDisconnectEvent(PlayerDisconnectEvent e) {
		this.playerDisconnectTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endPlayerDisconnectEvent(PlayerDisconnectEvent e) {
		long time = System.nanoTime() - playerDisconnectTime;
		data.addTime(time, data.playerDisconnectCount, data.playerDisconnectTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startPlayerHandshakeEvent(PlayerHandshakeEvent e) {
		this.playerHandshakeTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endPlayerHandshakeEvent(PlayerHandshakeEvent e) {
		long time = System.nanoTime() - playerHandshakeTime;
		data.addTime(time, data.playerHandshakeCount, data.playerHandshakeTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startPluginMessageEvent(PluginMessageEvent e) {
		this.pluginMessageTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endPluginMessageEvent(PluginMessageEvent e) {
		long time = System.nanoTime() - pluginMessageTime;
		data.addTime(time, data.pluginMessageCount, data.pluginMessageTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startPostLoginEvent(PostLoginEvent e) {
		this.postLoginTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endPostLoginEvent(PostLoginEvent e) {
		long time = System.nanoTime() - postLoginTime;
		data.addTime(time, data.postLoginCount, data.postLoginTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startPreLoginEvent(PreLoginEvent e) {
		this.preLoginTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endPreLoginEvent(PreLoginEvent e) {
		long time = System.nanoTime() - preLoginTime;
		data.addTime(time, data.preLoginCount, data.preLoginTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startProxyPingEvent(ProxyPingEvent e) {
		this.proxyPingTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endProxyPingEvent(ProxyPingEvent e) {
		long time = System.nanoTime() - proxyPingTime;
		data.addTime(time, data.proxyPingCount, data.proxyPingTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startProxyReloadEvent(ProxyReloadEvent e) {
		this.proxyReloadTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endProxyReloadEvent(ProxyReloadEvent e) {
		long time = System.nanoTime() - proxyReloadTime;
		data.addTime(time, data.proxyReloadCount, data.proxyReloadTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startServerConnectedEvent(ServerConnectedEvent e) {
		this.serverConnectedTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endServerConnectedEvent(ServerConnectedEvent e) {
		long time = System.nanoTime() - serverConnectedTime;
		data.addTime(time, data.serverConnectedCount, data.serverConnectedTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startServerConnectEvent(ServerConnectEvent e) {
		this.serverConnectTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endServerConnectEvent(ServerConnectEvent e) {
		long time = System.nanoTime() - serverConnectTime;
		data.addTime(time, data.serverConnectCount, data.serverConnectTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startServerDisconnectEvent(ServerDisconnectEvent e) {
		this.serverDisconnectTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endServerDisconnectEvent(ServerDisconnectEvent e) {
		long time = System.nanoTime() - serverDisconnectTime;
		data.addTime(time, data.serverDisconnectCount, data.serverDisconnectTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startServerKickEvent(ServerKickEvent e) {
		this.serverKickTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endServerKickEvent(ServerKickEvent e) {
		long time = System.nanoTime() - serverKickTime;
		data.addTime(time, data.serverKickCount, data.serverKickTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startServerSwitchEvent(ServerSwitchEvent e) {
		this.serverSwitchTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endServerSwitchEvent(ServerSwitchEvent e) {
		long time = System.nanoTime() - serverSwitchTime;
		data.addTime(time, data.serverSwitchCount, data.serverSwitchTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startTabCompleteEvent(TabCompleteEvent e) {
		this.tabCompleteTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endTabCompleteEvent(TabCompleteEvent e) {
		long time = System.nanoTime() - tabCompleteTime;
		data.addTime(time, data.tabCompleteCount, data.tabCompleteTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startTabCompleteResponseEvent(TabCompleteResponseEvent e) {
		this.tabCompleteResponseTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endTabCompleteResponseEvent(TabCompleteResponseEvent e) {
		long time = System.nanoTime() - tabCompleteResponseTime;
		data.addTime(time, data.tabCompleteResponseCount, data.tabCompleteResponseTimes);
	}
	
	@EventHandler(priority = Byte.MIN_VALUE)
	public void startTargetedEvent(TargetedEvent e) {
		this.targetedTime = System.nanoTime();
	}
	
	@EventHandler(priority = Byte.MAX_VALUE)
	public void endTargetedEvent(TargetedEvent e) {
		long time = System.nanoTime() - targetedTime;
		data.addTime(time, data.targetedCount, data.targetedTimes);
	}
}
