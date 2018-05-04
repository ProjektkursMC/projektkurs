/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 02.04.2018.
 */
package me.projektkurs.commands;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import me.projektkurs.Main;
import me.projektkurs.utils.Timer;

public class GravityBlockCommand implements CommandExecutor {

	/**
	 * @param sender
	 * @param command
	 * @param label
	 * @param args
	 * @return
	 * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender,
	 *      org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 * @since 1.0
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (args.length == 0) {
				FallingBlock block = p.getWorld().spawnFallingBlock(
						p.getLocation().getBlock().getRelative(this.getFace(p)).getLocation().add(0, 20, 0), new MaterialData(Material.SAND));
				this.gravityTest(p, block);
				block.setDropItem(true);

			} else {
				Material material = Material.valueOf(args[0]);
				if (material == null)
					p.sendMessage(ChatColor.RED + "Der Block konnte nicht gefunden werden!");
				else {
					FallingBlock block = p.getWorld().spawnFallingBlock(
							p.getLocation().getBlock().getRelative(this.getFace(p)).getLocation().add(0, 20, 0), new MaterialData(Material.SAND));
					this.gravityTest(p, block);
					block.setDropItem(true);
				}
			}

		}

		return true;
	}

	public BlockFace getFace(Player p) {
		float yaw = p.getLocation().getYaw();

		HashMap<BlockFace, Float> degreeMap = new HashMap<>();
		HashMap<BlockFace, Float> diffMap = new HashMap<>();

		degreeMap.put(BlockFace.SOUTH, (float) 0);
		degreeMap.put(BlockFace.WEST, (float) 90);
		degreeMap.put(BlockFace.NORTH, (float) 180);
		degreeMap.put(BlockFace.EAST, (float) 270);

		for (Entry<BlockFace, Float> entry : degreeMap.entrySet()) {
			float diff = Math.abs(yaw - entry.getValue());
			diffMap.put(entry.getKey(), diff);
		}

		float smallest = -1;
		BlockFace face = null;
		for (Entry<BlockFace, Float> entry : diffMap.entrySet()) {
			float diff = entry.getValue();
			if (smallest == -1 || diff < smallest) {
				smallest = diff;
				face = entry.getKey();
			}
		}
		return face;
	}

	public void gravityTest(Player p, FallingBlock block) {
		final Location start = block.getLocation();
		Timer.getInstance().start(p);
		
		run(p, start, 1, block);
		run(p, start, 2, block);
		run(p, start, 3, block);
//		run(p, start, 5, block);
//		run(p, start, 10, block);
	}
	
	public void run(Player p, Location start, int secondsToWait, FallingBlock block) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {
				double sec = Timer.getInstance().round(p) / 1000.0;
				double distance = start.distance(block.getLocation());
				
				double v1 = distance / sec;

				double gravity = (2 * distance) / (sec * sec);
				double v2 = gravity * sec;

				p.sendMessage(ChatColor.GREEN + "Nach " + sec + " Sekunden: ");
				p.sendMessage(ChatColor.GRAY + "  -  Sekunden: " + ChatColor.GREEN + sec + "s");
				p.sendMessage(ChatColor.GRAY + "  -  Distanz: " + ChatColor.GREEN + distance + "m");
				p.sendMessage(ChatColor.GRAY + "  -  Gleichmäßig: " + ChatColor.GREEN + v1 + "m/s");
				p.sendMessage(ChatColor.GRAY + "  -  Beschleunigt: " + ChatColor.GREEN + v2 + "m/s");
				p.sendMessage("");
			}}, secondsToWait * 20);
	}

}
