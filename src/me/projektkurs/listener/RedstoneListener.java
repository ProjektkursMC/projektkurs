/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 22.03.2018.
 */
package me.projektkurs.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import me.projektkurs.utils.LocationManager;
import me.projektkurs.utils.Timer;

public class RedstoneListener implements Listener {

	private Map<Block, String> map;
	private Map<String, Location> locs;

	public RedstoneListener() {
		map = new HashMap<>();
		locs = new HashMap<>();
	}

	@EventHandler
	public void onRedstone(BlockRedstoneEvent e) {
		if (map.containsKey(e.getBlock()) && e.getNewCurrent() == 15) {
			Player p = null;

			for (Entry<Block, String> entry : map.entrySet()) {
				if (entry.getKey().equals(e.getBlock()))
					p = Bukkit.getPlayer(entry.getValue());
			}

			if (p != null) {
				if (LocationManager.getInstance().hasStartPoint(p)
						&& LocationManager.getInstance().getStartPoint(p).equals(e.getBlock().getLocation())) {
					Timer.getInstance().start(p);
					p.sendMessage(ChatColor.GREEN + "Zeit wird genommen...");
					locs.put(p.getName(), p.getLocation());

				} else if (LocationManager.getInstance().hasEndPoint(p)
						&& LocationManager.getInstance().getEndPoint(p).equals(e.getBlock().getLocation())) {
					long time = Timer.getInstance().stop(p);
					double seconds = time / 1000.0;
					double distance = p.getLocation().distance(locs.get(p.getName()));

					double v = distance / seconds;

					p.sendMessage(ChatColor.GREEN + "Du hast " + time + "ms gebraucht! Werte:");
					p.sendMessage(ChatColor.GRAY + "  -  Sekunden: " + ChatColor.GREEN + seconds + "s");
					p.sendMessage(ChatColor.GRAY + "  -  Distanz: " + ChatColor.GREEN + distance + "m");
					p.sendMessage(ChatColor.GRAY + "  -  Weg/Zeit: " + ChatColor.GREEN + v + "m/s");
					locs.remove(p.getName());
				}
			}
		}
	}

	public void listenOnPower(Player p, Block block) {
		map.put(block, p.getName());
	}

}
