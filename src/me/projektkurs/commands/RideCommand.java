/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 22.03.2018.
 */
package me.projektkurs.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.projektkurs.Main;

public class RideCommand implements CommandExecutor {

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
				Main.getInstance().getInteractListener().listenRedstone(p);
				p.sendMessage(ChatColor.GREEN + "Rechtsklicke den Block!");
			}
		}

		return true;
	}

}
