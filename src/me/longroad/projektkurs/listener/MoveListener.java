/**
 *  @author longroad
 *	@version 1.0
 *	Created on 17.02.2018.
 */
package me.longroad.projektkurs.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.longroad.projektkurs.utils.LocationManager;

public class MoveListener implements Listener {

	private List<String> list = new ArrayList<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (LocationManager.getInstance().hasStartPoint(p) && LocationManager.getInstance().hasEndPoint(p)) {
			
		}
	}

}
