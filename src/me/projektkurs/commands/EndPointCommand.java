/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 17.02.2018.
 */
package me.projektkurs.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.projektkurs.Main;
import me.projektkurs.listener.InteractListener;

public class EndPointCommand implements CommandExecutor {

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
				if (args[0].equalsIgnoreCase("global")) {
					Main.getInstance().getInteractListener().listenGlobal(p, InteractListener.PointType.END);
					p.sendMessage(ChatColor.GREEN + "Rechtsklicke das Ziel!");
				}
			} else {
				Main.getInstance().getInteractListener().listen(p, InteractListener.PointType.END);
				p.sendMessage(ChatColor.GREEN + "Rechtsklicke das Ziel!");
			}
		}

		return true;
	}

}
