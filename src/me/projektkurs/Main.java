/**
 *  @author Lennart
 *	@version 1.0
 *	Created on 14.02.2018.
 */
package me.projektkurs;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.projektkurs.commands.EndPointCommand;
import me.projektkurs.commands.GamemodeCommand;
import me.projektkurs.commands.StartPointCommand;
import me.projektkurs.listener.InteractListener;
import me.projektkurs.listener.MoveListener;

public class Main extends JavaPlugin{

	private static Main instance;
	
	private InteractListener interactListener;
	
	@Override
	public void onEnable() {
		instance = this;
		
		this.getCommand("startpoint").setExecutor(new StartPointCommand());
		this.getCommand("endpoint").setExecutor(new EndPointCommand());
		this.getCommand("gm").setExecutor(new GamemodeCommand());
		
		Bukkit.getPluginManager().registerEvents(new MoveListener(), this);
		Bukkit.getPluginManager().registerEvents(interactListener = new InteractListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
	public static Main getInstance() {
		return instance;
	}
	
	public InteractListener getInteractListener() {
		return interactListener;
	}
}
