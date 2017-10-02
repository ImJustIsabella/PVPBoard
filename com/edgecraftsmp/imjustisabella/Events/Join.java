package com.edgecraftsmp.imjustisabella.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.edgecraftsmp.imjustisabella.Main;
import com.edgecraftsmp.imjustisabella.Data.playerStatistics;
import com.edgecraftsmp.imjustisabella.Scoreboard.boardView;

public class Join implements Listener {

	public Join(Main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		playerStatistics stats = new playerStatistics(event.getPlayer());
		
		stats.setupData();
		
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			boardView scoreboard = new boardView(p);
			
			scoreboard.displayBoard();
		}
	}
}
