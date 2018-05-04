/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 19.03.2018.
 */
package me.projektkurs.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.projektkurs.Main;
import me.projektkurs.utils.LocationManager;
import net.md_5.bungee.api.ChatColor;

public class GravityCommand implements CommandExecutor {

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

			Location oldLoc = p.getLocation();
			Location newLoc = null;
			if (args.length == 1) {
				try {
					int height = Integer.parseInt(args[0]);
					newLoc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + height,
							p.getLocation().getZ());
				} catch (NumberFormatException e) {
					p.sendMessage(ChatColor.RED + "Syntax: /gravity <Höhe>");
				}
			} else
				newLoc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 600,
						p.getLocation().getZ());
			p.teleport(newLoc);

			LocationManager.getInstance().setEndPoint(p, oldLoc);
			LocationManager.getInstance().setStartPoint(p, newLoc);

			Main.getInstance().getMoveListener().gravity(p);
		} else
			sender.sendMessage(ChatColor.RED + "Du musst ein Spieler sein!");
		return true;
	}

}
