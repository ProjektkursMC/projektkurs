/**
 *  @author longroad
 *	@version 1.0
 *	Created on 17.02.2018.
 */
package me.longroad.projektkurs.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.longroad.projektkurs.utils.LocationManager;
import me.longroad.projektkurs.utils.Timer;
import net.md_5.bungee.api.ChatColor;

public class MoveListener implements Listener {

	private Map<String, Location> map = new HashMap<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (LocationManager.getInstance().hasStartPoint(p) && LocationManager.getInstance().hasEndPoint(p)) {
			if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation()
					.equals(LocationManager.getInstance().getStartPoint(p)) && !map.containsKey(p.getName())) {
				Timer.getInstance().start(p);
				p.sendMessage(ChatColor.GREEN + "Zeit wird genommen...");
				map.put(p.getName(), p.getLocation());

			} else if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation()
					.equals(LocationManager.getInstance().getEndPoint(p)) && map.containsKey(p.getName())) {
				long time = Timer.getInstance().stop(p);
				double seconds = time / 1000.0;
				double distance = p.getLocation().distance(map.get(p.getName()));

				double v = distance / seconds;

				p.sendMessage(ChatColor.GREEN + "Du hast " + time + "ms gebraucht! Werte:");
				p.sendMessage(ChatColor.GRAY + "  -  Sekunden: " + ChatColor.GREEN + seconds + "s");
				p.sendMessage(ChatColor.GRAY + "  -  Distanz: " + ChatColor.GREEN + distance + "m");
				p.sendMessage(ChatColor.GRAY + "  -  Weg/Zeit: " + ChatColor.GREEN + v + "m/s");
				map.remove(p.getName());
			}
		}
		
		if (LocationManager.getInstance().hasGlobalStart() && LocationManager.getInstance().hasGlobalEnd()) {
			if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation()
					.equals(LocationManager.getInstance().getGlobalStart()) && !map.containsKey(p.getName())) {
				Timer.getInstance().start(p);
				p.sendMessage(ChatColor.GREEN + "Zeit wird genommen...");
				map.put(p.getName(), p.getLocation());
				
			} else if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation()
					.equals(LocationManager.getInstance().getGlobalEnd()) && map.containsKey(p.getName())) {
				long time = Timer.getInstance().stop(p);
				double seconds = time / 1000.0;
				double distance = p.getLocation().distance(map.get(p.getName()));

				double v = distance / seconds;

				p.sendMessage(ChatColor.GREEN + "Du hast " + time + "ms gebraucht! Werte:");
				p.sendMessage(ChatColor.GRAY + "  -  Sekunden: " + ChatColor.GREEN + seconds + "s");
				p.sendMessage(ChatColor.GRAY + "  -  Distanz: " + ChatColor.GREEN + distance + "m");
				p.sendMessage(ChatColor.GRAY + "  -  Weg/Zeit: " + ChatColor.GREEN + v + "m/s");
				map.remove(p.getName());
				
			}
		}
	}
}