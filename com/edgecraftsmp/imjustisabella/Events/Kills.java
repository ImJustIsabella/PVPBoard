package com.edgecraftsmp.imjustisabella.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.edgecraftsmp.imjustisabella.Main;
import com.edgecraftsmp.imjustisabella.Data.playerStatistics;
import com.edgecraftsmp.imjustisabella.Scoreboard.boardView;

public class Kills {

	public Kills(Main plugin)
	{
		
	}

	@EventHandler
	public void onKill(EntityDamageByEntityEvent event)
	{
		if(event.getDamager() instanceof Player)
		{
			Player attacker = (Player) event.getDamager();
			if(event.getEntity() instanceof Player)
			{
				Player victim = (Player) event.getEntity();
				if(victim.isDead())
				{
					playerStatistics stats = new playerStatistics(attacker);
					boardView scoreboard = new boardView(attacker);
					stats.addKill();
					scoreboard.displayBoard();
				}
			}
		}
	}
}
