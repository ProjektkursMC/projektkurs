/**
 *  @author longroad
 *	@version 1.0
 *	Created on 17.02.2018.
 */
package me.longroad.projektkurs.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationManager {

	private static LocationManager instance;
	
	private Map<String, Location> startPoints, endPoints;
	
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
		return startPoints.containsKey(p.getName());
	}
	
	public boolean hasEndPoint(Player p) {
		return endPoints.containsKey(p.getName());
	}
	
	public void setStartPoint(Player p, Location loc) {
		startPoints.put(p.getName(), loc);
	}
	
	public void setEndPoint(Player p, Location loc) {
		endPoints.put(p.getName(), loc);
	}
	
	public Location getStartPoint(Player p) {
		return startPoints.get(p.getName());
	}
	
	public Location getEndPoint(Player p) {
		return endPoints.get(p.getName());
	}
	
}
