/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 03.05.2018.
 */
package me.projektkurs.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PitchCommand implements CommandExecutor {

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

			if (args.length == 1) {
				try {
					float pitch = Float.parseFloat(args[0]);
					Location loc = p.getLocation();
					loc.setPitch(pitch);
					p.teleport(loc);
				} catch (NumberFormatException e) {
					p.sendMessage(ChatColor.RED + "Syntax: /pitch <Winkel>");
				}
			} else
				p.sendMessage(ChatColor.RED + "Syntax: /pitch <Winkel>");

		}

		return true;
	}

}
