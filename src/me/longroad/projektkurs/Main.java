/**
 *  @author longroad
 *	@version 1.0
 *	Created on 14.02.2018.
 */
package me.longroad.projektkurs;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
	public static Main getInstance() {
		return instance;
	}
}
