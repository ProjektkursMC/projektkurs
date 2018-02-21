/**
 *  @author longroad
 *	@version 1.0
 *	Created on 17.02.2018.
 */
package me.longroad.projektkurs.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.longroad.projektkurs.utils.LocationManager;
import me.longroad.projektkurs.utils.Timer;
import net.md_5.bungee.api.ChatColor;

public class MoveListener implements Listener {

	private List<String> list = new ArrayList<>();

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (LocationManager.getInstance().hasStartPoint(p) && LocationManager.getInstance().hasEndPoint(p)) {
			if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation()
					.equals(LocationManager.getInstance().getStartPoint(p)) && !list.contains(p.getName())) {
				Timer.getInstance().start(p);
				p.sendMessage(ChatColor.GREEN + "Zeit wird genommen...");
				list.add(p.getName());

			} else if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation()
					.equals(LocationManager.getInstance().getEndPoint(p)) && list.contains(p.getName())) {
				long time = Timer.getInstance().stop(p);
				double seconds = time / 1000.0;
				double distance = LocationManager.getInstance().getStartPoint(p)
						.distance(LocationManager.getInstance().getEndPoint(p));

				double v = distance / seconds;

				p.sendMessage(ChatColor.GREEN + "Du hast " + time + "ms gebraucht! Werte:");
				p.sendMessage(ChatColor.GRAY + "  -  Sekunden: " + ChatColor.GREEN + seconds + "s");
				p.sendMessage(ChatColor.GRAY + "  -  Distanz: " + ChatColor.GREEN + distance + "m");
				p.sendMessage(ChatColor.GRAY + "  -  Weg/Zeit: " + ChatColor.GREEN + v + "m/s");
				list.remove(p.getName());
			}
		}
		
		System.out.println(LocationManager.getInstance().hasGlobalStart());
		System.out.println(LocationManager.getInstance().hasGlobalEnd());
		if (LocationManager.getInstance().hasGlobalStart() && LocationManager.getInstance().hasGlobalEnd()) {
			System.out.println("triggered");
			if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation()
					.equals(LocationManager.getInstance().getGlobalStart()) && !list.contains(p.getName())) {
				Timer.getInstance().start(p);
				p.sendMessage(ChatColor.GREEN + "Zeit wird genommen...");
				list.add(p.getName());
				
			} else if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation()
					.equals(LocationManager.getInstance().getGlobalEnd()) && list.contains(p.getName())) {
				long time = Timer.getInstance().stop(p);
				double seconds = time / 1000.0;
				double distance = LocationManager.getInstance().getGlobalStart()
						.distance(LocationManager.getInstance().getGlobalEnd());

				double v = distance / seconds;

				p.sendMessage(ChatColor.GREEN + "Du hast " + time + "ms gebraucht! Werte:");
				p.sendMessage(ChatColor.GRAY + "  -  Sekunden: " + ChatColor.GREEN + seconds + "s");
				p.sendMessage(ChatColor.GRAY + "  -  Distanz: " + ChatColor.GREEN + distance + "m");
				p.sendMessage(ChatColor.GRAY + "  -  Weg/Zeit: " + ChatColor.GREEN + v + "m/s");
				list.remove(p.getName());
				
			}
		}
	}
}