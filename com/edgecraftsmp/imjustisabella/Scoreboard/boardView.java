package com.edgecraftsmp.imjustisabella.Scoreboard;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.edgecraftsmp.imjustisabella.Main;
import com.edgecraftsmp.imjustisabella.Data.playerStatistics;

public class boardView {

	private Player player;
	
	public boardView(Player player)
	{
		this.player = player;
	}
	
	private ScoreboardManager manager = Bukkit.getScoreboardManager();
	private Scoreboard board = manager.getNewScoreboard();
	private Objective objective = board.registerNewObjective("Scoreboard", "dummy");
	
	public void displayBoard()
	{
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		

		String line = Main.scoreboard.getString("Name");

		String replaced = replacement(line);
		
		objective.setDisplayName(replaced);
		
		Lines = 16;
		Blanks = 0;
		
		List<String> list = Main.scoreboard.getStringList("Rows");
		
		this.Internal = -1;
		for(int i = 0; i < list.size(); i++)
		{
			this.Internal++;
			addLine();
		}
		
		this.player.setScoreboard(this.board);
	}
	
	public void hideBoard()
	{
		this.player.setScoreboard(this.board);
	}
	
	private int Lines = 16;
	private int Blanks = 0;
	private int Internal = -1;
	
	private void addLine()
	{
		if(this.Lines >= 1)
		{
			List<String> list = Main.scoreboard.getStringList("Rows");
			
			String line = list.get(this.Internal);
			
			if(line.equalsIgnoreCase(""))
			{
				this.Blanks++;
				for(int i = 0; i < this.Blanks; i++)
				{
					line = line + "&a";
				}
			}
			
			String replaced = replacement(line);
			
			Score new_row = this.objective.getScore(replaced);
			new_row.setScore(this.Lines);
			
			this.Lines--;
		}
	}
	
	public String replacement(String line)
	{
		playerStatistics stats = new playerStatistics(this.player);
		
		String message = line;
		
		message = message.replace("<Kills>", Integer.toString(stats.getKills()));
		message = message.replace("<Deaths>", Integer.toString(stats.getDeaths()));
		message = message.replace("<KDR>", Double.toString(stats.getKDR()));
		message = message.replace("<onlineCount>", Integer.toString(Bukkit.getServer().getOnlinePlayers().size()));
		message = message.replace("<playerName>", this.player.getName().toString());
		message = ChatColor.translateAlternateColorCodes('&', message);
		
		return message;
	}
}
