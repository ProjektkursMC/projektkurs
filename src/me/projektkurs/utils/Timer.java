/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 17.02.2018.
 */
package me.projektkurs.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class Timer {

	private static Timer instance;
	
	private Map<String, Long> map;
	
	public static Timer getInstance() {
		if(instance == null)
			instance = new Timer();
		return instance;
	}
	
	private Timer() {
		map = new HashMap<>();
	}
	
	public void start(Player p) {
		map.put(p.getName(), System.currentTimeMillis());
	}
	
	public long stop(Player p) {
		long time = System.currentTimeMillis() - map.get(p.getName());
		return time;
	}
}
