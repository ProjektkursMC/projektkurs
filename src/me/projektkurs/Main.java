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
import me.projektkurs.commands.GravityBlockCommand;
import me.projektkurs.commands.GravityCommand;
import me.projektkurs.commands.PitchCommand;
import me.projektkurs.commands.ProjectileTestCommand;
import me.projektkurs.commands.RideCommand;
import me.projektkurs.commands.StartPointCommand;
import me.projektkurs.listener.InteractListener;
import me.projektkurs.listener.MoveListener;
import me.projektkurs.listener.ProjectileListener;
import me.projektkurs.listener.RedstoneListener;

public class Main extends JavaPlugin{

	private static Main instance;
	
	private InteractListener interactListener;
	private MoveListener moveListener;
	private RedstoneListener redstoneListener;
	private ProjectileListener projectileListener;
	
	@Override
	public void onEnable() {
		instance = this;
		
		this.getCommand("startpoint").setExecutor(new StartPointCommand());
		this.getCommand("endpoint").setExecutor(new EndPointCommand());
		this.getCommand("gm").setExecutor(new GamemodeCommand());
		this.getCommand("gravity").setExecutor(new GravityCommand());
		this.getCommand("gravityblock").setExecutor(new GravityBlockCommand());
		this.getCommand("ride").setExecutor(new RideCommand());
		this.getCommand("pitch").setExecutor(new PitchCommand());
		this.getCommand("test").setExecutor(new ProjectileTestCommand());

		
		Bukkit.getPluginManager().registerEvents(moveListener = new MoveListener(), this);
		Bukkit.getPluginManager().registerEvents(interactListener = new InteractListener(), this);
		Bukkit.getPluginManager().registerEvents(redstoneListener = new RedstoneListener(), this);
		Bukkit.getPluginManager().registerEvents(projectileListener = new ProjectileListener(), this);
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
	
	public MoveListener getMoveListener() {
		return moveListener;
	}
	
	public RedstoneListener getRedstoneListener() {
		return redstoneListener;
	}
	
	public ProjectileListener getProjectileListener() {
		return projectileListener;
	}
}
