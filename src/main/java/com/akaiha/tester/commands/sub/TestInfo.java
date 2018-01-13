package com.akaiha.tester.commands.sub;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.akaiha.tester.Tester;
import com.akaiha.tester.commands.BasicCommand;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

public class TestInfo implements BasicCommand {
	
	private Tester plugin;
	private ThreadMXBean bean;
	
	public TestInfo(Tester plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(final CommandSender commandSender, final String[] args) {
		plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
            @Override
            public void run() {
            	if (args.length > 0) {
            		bean = ManagementFactory.getThreadMXBean();
                	List<String> subArgs = new ArrayList<String>(Arrays.asList(args));
                	List<String> output = new ArrayList<String>();
                	
                	
                	if (subArgs.contains("-mem")) {
                		output.addAll(checkMemory(commandSender));
                	}
                	if (subArgs.contains("-thread")) {
                		checkThreads(commandSender);
                	}
                	if (subArgs.contains("-mx")) {
                		checkMX(commandSender);
                	}
                	if (subArgs.contains("-cpu")) {
                		if (bean.isCurrentThreadCpuTimeSupported()) {
                			checkCPUMX(commandSender);
                		} else {
                			commandSender.sendMessage(
                	    			new TextComponent(
                	    					ChatColor.translateAlternateColorCodes('&', "&aCPU Times Not Supported On The OS!")));
                		}
                	}
                	if (subArgs.contains("-timing")) {
                		if (plugin.data.testing.get()) {
                			checkTimings(commandSender);
                		} else {
                			commandSender.sendMessage(
                	    			new TextComponent(
                	    					ChatColor.translateAlternateColorCodes('&', "&aCPU Times Not Supported On The OS!")));
                		}
                	}
                	if (subArgs.contains("-system")) {
                		checkSystem(commandSender);
                	}
                	if (subArgs.contains("-bungee")) {
                		checkBungee(commandSender);
                	}
                	if (subArgs.contains("-save")) {
                		save(commandSender, output);
                	}
            	}
            }
		});
		return false;
	}
	
	private List<String> checkMemory(CommandSender commandSender) {
		List<String> list = new ArrayList<String>();
		double maxMemory = (double) plugin.runtime.maxMemory() / (double) (1024 * 1024);
    	double allocatedMemory = (double) plugin.runtime.totalMemory() / (double) (1024 * 1024);
    	double freeMemory = (double) plugin.runtime.freeMemory() / (double) (1024 * 1024);
    	
    	String str = "&aFree &aMemory: &e" + plugin.format.format(freeMemory) + "&a MB," +
				" &aAllocated &aMemory: &e" + plugin.format.format(allocatedMemory) + "&a MB," +
				" &aMax &aMemory: &e" + plugin.format.format(maxMemory) + "&a MB";
    	list.add(str);
    	
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', str)));
		return list;
	}
	
	private void checkThreads(CommandSender commandSender) {
		int mxThreads = bean.getThreadCount();
    	int threads = Thread.activeCount();
    	
    	commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', 
    							"&aActive &aThread &aCount: &e" + threads +
    							", &aMX &aThread &aCount: &e" + mxThreads
    							
    							)));
	}
	
	private void checkMX(CommandSender commandSender) {
		OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
		List<GarbageCollectorMXBean> gcBean = ManagementFactory.getGarbageCollectorMXBeans();
		double cpuLoad = osBean.getSystemLoadAverage();
		long gcCount = 0L;
		long gcAvg = 0L;
		
		for (GarbageCollectorMXBean tmp: gcBean) {
			gcCount += tmp.getCollectionCount();
			gcAvg += tmp.getCollectionTime();
		}
		gcAvg = gcAvg / gcBean.size();
		
		
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "CPU Load: " + cpuLoad)));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "Garbage Collector Info: Collections Count: " + gcCount +
    							" Collections AVG Time: " + gcAvg + " ms")));
	}
	
	private void checkCPUMX(CommandSender commandSender) {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
    	StringBuilder sb = new StringBuilder();
    	
    	for(Thread temp : threadSet) {
			sb.append("&aActive &aThread &aID: &e" + temp.getId() + " &aCPU &aTime: &e" + plugin.format.format(bean.getThreadCpuTime(temp.getId()) / 1000000) + " &ams ");
		}
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', sb.toString())));
	}
	
	private void checkTimings(CommandSender commandSender) {
		commandSender.sendMessage(
    			new TextComponent(
    					plugin.data.getTimes("ServerConnectEvent", 
    							plugin.data.serverConnectCount, 
    							plugin.data.serverConnectTimes)));
	}
	
	private void save(CommandSender commandSender, List<String> arr) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		
		if (!plugin.getDataFolder().exists()) {
    		plugin.getDataFolder().mkdir();
    	}
		
		File file = new File(plugin.getDataFolder() + File.separator + dateFormat.format(date) + ".txt");
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			for(String str: arr) {
				  writer.write(str);
				}
				writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aSaving To File: &e" + dateFormat.format(date) + ".txt" + "&a!")));
    					
	}
	
	private void checkSystem(CommandSender commandSender) {
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aOS Name: &e" + System.getProperty("os.name"))));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aOS Version: &e" + System.getProperty("os.version"))));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aOS Architecture: &e" + System.getProperty("os.arch"))));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aJava Version: &e" + System.getProperty("java.version"))));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aJava Vendor: &e" + System.getProperty("java.vendor"))));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aAvailable Processors: &e" + plugin.runtime.availableProcessors())));
	}
	
	private void checkBungee(CommandSender commandSender) {
		String name = plugin.getProxy().getName();
		String version = plugin.getProxy().getVersion();
		int onlineCount = plugin.getProxy().getOnlineCount();
		Map<String, ServerInfo> serverList = plugin.getProxy().getServers();
		Collection<Plugin> pluginList = plugin.getProxy().getPluginManager().getPlugins();
		Collection<String> channelList = plugin.getProxy().getChannels();
		
		String channels = "";
		String plugins = "";
		String servers = "";
		
		for (Map.Entry<String, ServerInfo> entry : serverList.entrySet()) {
			servers += entry.getKey() + " " + entry.getValue().getAddress().getAddress().getHostAddress() + ":" + entry.getValue().getAddress().getPort() + ", ";
		}
		for (Plugin tmp : pluginList) {
			plugins += tmp.getDescription().getName() + " " + tmp.getDescription().getVersion() + ", "; 
		}
		for (String tmp : channelList) {
			channels += tmp + ", ";
		}
		
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aName: &e" + name)));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aVersion: &e" + version)));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aPlayer Count: &e" + onlineCount)));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aServers: &e" + servers)));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aPlugins: &e" + plugins)));
		commandSender.sendMessage(
    			new TextComponent(
    					ChatColor.translateAlternateColorCodes('&', "&aChannels: &e" + channels)));
	}
}