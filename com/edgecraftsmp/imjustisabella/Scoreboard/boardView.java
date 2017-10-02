package com.edgecraftsmp.imjustisabella.Scoreboard;

import org.bukkit.Bukkit;
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
		
		for(int i = 0; i < Main.scoreboard.getInt("Rows"); i++)
		{
			addLine();
		}
		
		this.player.setScoreboard(this.board);
	}
	
	private int Lines = 16;
	private int Blanks = 0;
	
	
	private void addLine()
	{
		String line = Main.scoreboard.getString(Integer.toString(Lines));
		
		if(line.equalsIgnoreCase(""))
		{
			this.Blanks++;
			line = "";
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
	
	public String replacement(String line)
	{
		playerStatistics stats = new playerStatistics(this.player);
		
		String message = line;
		
		message = message.replace("<Kills>", Integer.toString(stats.getKills()));
		message = message.replace("<Deaths>", Integer.toString(stats.getDeaths()));
		message = message.replace("<KDR>", Double.toString(stats.getKDR()));
		message = message.replace("<onlineCount>", Integer.toString(Bukkit.getServer().getOnlinePlayers().size()));
		
		message = message.replace("&1", "§1");
		message = message.replace("&2", "§2");
		message = message.replace("&3", "§3");
		message = message.replace("&4", "§4");
		message = message.replace("&5", "§5");
		message = message.replace("&6", "§6");
		message = message.replace("&7", "§7");
		message = message.replace("&8", "§8");
		message = message.replace("&9", "§9");
		message = message.replace("&0", "§0");
		
		message = message.replace("&a", "§a");
		message = message.replace("&b", "§b");
		message = message.replace("&c", "§c");
		message = message.replace("&d", "§d");
		message = message.replace("&e", "§e");
		message = message.replace("&f", "§f");

	
		message = message.replace("&k", "§k");
		message = message.replace("&l", "§l");
		message = message.replace("&m", "§m");
		message = message.replace("&n", "§n");
		message = message.replace("&o", "§o");
		message = message.replace("&r", "§r");
		
		return message;
	}
}
