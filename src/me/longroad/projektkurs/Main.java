/**
 *  @author longroad
 *	@version 1.0
 *	Created on 14.02.2018.
 */
package me.longroad.projektkurs;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.longroad.projektkurs.commands.StartPointCommand;
import me.longroad.projektkurs.listener.InteractListener;
import me.longroad.projektkurs.listener.MoveListener;

public class Main extends JavaPlugin{

	private static Main instance;
	
	private InteractListener interactListener;
	
	@Override
	public void onEnable() {
		instance = this;
		
		this.getCommand("startpoint").setExecutor(new StartPointCommand());
		
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
