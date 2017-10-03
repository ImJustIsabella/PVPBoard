package com.edgecraftsmp.imjustisabella.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.edgecraftsmp.imjustisabella.Main;
import com.edgecraftsmp.imjustisabella.Data.playerStatistics;
import com.edgecraftsmp.imjustisabella.Scoreboard.boardView;

public class Deaths implements Listener {

	public Deaths(Main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		playerStatistics data = new playerStatistics(event.getEntity());
		data.addDeath();
		
		boardView scoreboard = new boardView(event.getEntity());
		scoreboard.displayBoard();
	}
}
