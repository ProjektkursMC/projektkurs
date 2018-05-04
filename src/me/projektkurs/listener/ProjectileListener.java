/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 03.05.2018.
 */
package me.projektkurs.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ProjectileListener implements Listener {

	private Map<Projectile, Node> map = new HashMap<>();
	private Map<String, Double[]> tests = new HashMap<>();

	private class Node {

		private String player;
		private Location pLoc;
		private Location loc;

		public Node(Player p, Location loc) {
			player = p.getName();
			this.pLoc = p.getLocation();
			this.loc = loc;
		}

		public Player getPlayer() {
			return Bukkit.getPlayer(player);
		}

		public Location getLocation() {
			return loc;
		}
		
		public Location getPlayerLocation() {
			return pLoc;
		}
	}

	@EventHandler
	public void onShoot(EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			Location loc = e.getEntity().getLocation();
			float pitch = p.getLocation().getPitch();

			if(e.getForce() != 1.0) {
				e.setCancelled(true);
				return;
			}
			
			map.put((Projectile) e.getProjectile(), new Node(p, loc));

			if (!tests.containsKey(p.getName()))
				p.sendMessage(ChatColor.GREEN + "Projektil mit " + pitch + " Grad abgeschossen!");
		}
	}

//	@EventHandler
//	public void onLaunch(ProjectileLaunchEvent e) {
//		if (e.getEntity().getShooter() instanceof Player) {
//			Player p = (Player) e.getEntity().getShooter();
//			Location loc = e.getEntity().getLocation();
//			float pitch = p.getLocation().getPitch();
//
//			map.put(e.getEntity(), new Node(p, loc));
//
//			if (!tests.containsKey(p.getName()))
//				p.sendMessage(ChatColor.GREEN + "Projektil mit " + pitch + " Grad abgeschossen!");
//		}
//	}

	@EventHandler
	public void onHit(ProjectileHitEvent e) {
		Location loc = map.get(e.getEntity()).getLocation();
		Player p = map.get(e.getEntity()).getPlayer();

		double dX = Math.abs(loc.getX() - e.getEntity().getLocation().getX());
		double dZ = Math.abs(loc.getZ() - e.getEntity().getLocation().getZ());
		double distance = Math.sqrt(dX * dX + dZ * dZ);

		if(distance == 0) {
			dX = loc.getX() - map.get(e.getEntity()).getPlayerLocation().getX();
			dZ = loc.getZ() - map.get(e.getEntity()).getPlayerLocation().getZ();
			System.out.println(loc.distance(map.get(e.getEntity()).getPlayerLocation()));
			distance = Math.sqrt(dX * dX + dZ * dZ);
		}
		map.remove(e.getEntity());
		
		if (tests.containsKey(((HumanEntity) e.getEntity().getShooter()).getName())) {
			p.sendMessage(ChatColor.GRAY + "" + distance);
			Double[] array = tests.get(((HumanEntity) e.getEntity().getShooter()).getName());

			int z = 0;
			for (int i = 0; i < array.length; i++) {
				double x = array[i];
				if (x == -1) {
					array[i] = distance;
					z = i;
					break;
				}
			}

			if (z == array.length - 1) {
				double k = 0;
				for (double y : array)
					k += y;
				k /= array.length;
				p.sendMessage(ChatColor.GREEN + "Die Durchschnittsweite ist " + k + " m");
				tests.remove(p.getName());
			}
		} else {
			p.sendMessage(ChatColor.GREEN + "Das Projektil ist " + distance + " m weit gefolgen!");
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (tests.containsKey(e.getPlayer().getName())) {
			if (e.getTo().getPitch() != e.getFrom().getPitch())
				e.setCancelled(true);
		}
	}

	public void test(Player p) {
		Double[] x = new Double[10];
		for (int i = 0; i < x.length; i++)
			x[i] = -1.0;
		tests.put(p.getName(), x);
	}
}
