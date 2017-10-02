package com.edgecraftsmp.imjustisabella.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import com.edgecraftsmp.imjustisabella.Main;
import com.edgecraftsmp.imjustisabella.Data.playerStatistics;
import com.edgecraftsmp.imjustisabella.Scoreboard.boardView;

public class Quit {

	public Quit(Main plugin)
	{
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		playerStatistics stats = new playerStatistics(event.getPlayer());
		
		stats.removeData();
		
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			boardView scoreboard = new boardView(p);
			
			scoreboard.displayBoard();
		}
	}
}
