/**
 *  @author longroad
 *	@version 1.0
 *	Created on 17.02.2018.
 */
package me.longroad.projektkurs.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.longroad.projektkurs.utils.LocationManager;

public class InteractListener implements Listener {

	public static enum PointType{
		START, END;
	}
	
	private Map<String, PointType> map;
	
	public InteractListener() {
		map = new HashMap<>();
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(map.containsKey(e.getPlayer().getName())) {
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(map.get(e.getPlayer().getName()) == PointType.START)
					LocationManager.getInstance().setStartPoint(e.getPlayer(), e.getClickedBlock().getLocation());
				else
					LocationManager.getInstance().setEndPoint(e.getPlayer(), e.getClickedBlock().getLocation());
				e.getPlayer().sendMessage(ChatColor.GREEN + "Location erfolgreich gesetzt!");
				map.remove(e.getPlayer().getName());
			}
		
		} else if(map.containsKey("global_" + e.getPlayer().getName())) {
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(map.get(e.getPlayer().getName()) == PointType.START)
					LocationManager.getInstance().setStartPoint(e.getPlayer(), e.getClickedBlock().getLocation());
				else
					LocationManager.getInstance().setEndPoint(e.getPlayer(), e.getClickedBlock().getLocation());
				e.getPlayer().sendMessage(ChatColor.GREEN + "Location erfolgreich gesetzt!");
				map.remove("global_" + e.getPlayer().getName());
			}
		}
	}
	
	public void listen(Player p, PointType type) {
		map.put(p.getName(), type);
	}
	
	public void listenGlobal(Player p, PointType type) {
		map.put("global", type);
	}
	
}
