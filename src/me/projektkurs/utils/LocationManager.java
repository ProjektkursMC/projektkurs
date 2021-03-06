/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 17.02.2018.
 */
package me.projektkurs.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationManager {

	private static LocationManager instance;
	
	private Map<String, Location> startPoints, endPoints;
	
	private Location globalStart, globalEnd;
	
	public static LocationManager getInstance() {
		if(instance == null)
			instance = new LocationManager();
		return instance;
	}
	
	private LocationManager() {
		startPoints = new HashMap<>();
		endPoints = new HashMap<>();
	}
	
	public boolean hasStartPoint(Player p) {
		if(p == null)
			throw new IllegalArgumentException("Player cannot be null!");
		return startPoints.containsKey(p.getName());
	}
	
	public boolean hasEndPoint(Player p) {
		if(p == null)
			throw new IllegalArgumentException("Player cannot be null!");
		return endPoints.containsKey(p.getName());
	}
	
	public void setStartPoint(Player p, Location loc) {
		if(p == null)
			throw new IllegalArgumentException("Player cannot be null!");
		startPoints.put(p.getName(), loc);
	}
	
	public void setEndPoint(Player p, Location loc) {
		if(p == null)
			throw new IllegalArgumentException("Player cannot be null!");
		endPoints.put(p.getName(), loc);
	}
	
	public Location getStartPoint(Player p) {
		if(p == null)
			throw new IllegalArgumentException("Player cannot be null!");
		return startPoints.get(p.getName());
	}
	
	public Location getEndPoint(Player p) {
		if(p == null)
			throw new IllegalArgumentException("Player cannot be null!");
		return endPoints.get(p.getName());
	}
	
	public void setGlobalStart(Location loc) {
		globalStart = loc;
	}
	
	public void setGlobalEnd(Location loc) {
		globalEnd = loc;
	}
	
	public Location getGlobalStart() {
		return globalStart;
	}
	
	public Location getGlobalEnd() {
		return globalEnd;
	}
	
	public boolean hasGlobalStart() {
		if(globalStart == null)
			return false;
		return true;
	}
	
	public boolean hasGlobalEnd() {
		if(globalEnd == null)
			return false;
		return true;
	}
}
