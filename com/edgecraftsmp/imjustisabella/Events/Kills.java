package com.edgecraftsmp.imjustisabella.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.edgecraftsmp.imjustisabella.Main;
import com.edgecraftsmp.imjustisabella.Data.playerStatistics;
import com.edgecraftsmp.imjustisabella.Scoreboard.boardView;

public class Kills {

	public Kills(Main plugin)
	{
		
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		Player attacker = event.getEntity().getKiller();
	
		playerStatistics stats = new playerStatistics(attacker);
		boardView scoreboard = new boardView(attacker);
		stats.addKill();
		scoreboard.displayBoard();
	}
}
