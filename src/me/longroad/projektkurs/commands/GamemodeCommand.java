/**
 *  @author longroad
 *	@version 1.0
 *	Created on 18.02.2018.
 */
package me.longroad.projektkurs.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

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
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender.isOp()) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				String syntax = ChatColor.RED + "Syntax: /gm <value>";

				if (args.length == 1) {
					try {
						int value = Integer.parseInt(args[0]);
						p.setGameMode(GameMode.getByValue(value));
						p.sendMessage(ChatColor.GREEN + "Dein Gamemode wurde geupdated!");
					} catch (NumberFormatException e) {
						p.sendMessage(syntax);
					}
				} else
					p.sendMessage(syntax);
			}
		}

		return true;
	}

}
