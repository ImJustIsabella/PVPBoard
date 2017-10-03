package com.edgecraftsmp.imjustisabella;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.edgecraftsmp.imjustisabella.Scoreboard.boardView;

public class CMD implements CommandExecutor {

	private static HashMap<Player, Boolean> _toggleStatus = new HashMap<Player, Boolean>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().toString().equalsIgnoreCase("reloadboard"))
		{
			if(sender.hasPermission("pvpboard.admin"))
			{
				Main.scoreboard = YamlConfiguration.loadConfiguration(new File("plugins/PVPBoard/scoreboard.yml"));
				sender.sendMessage(ChatColor.GREEN + "Scoreboard reloaded");
				for(Player p : Bukkit.getServer().getOnlinePlayers())
				{
					boardView scoreboard = new boardView(p);
					scoreboard.displayBoard();
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "Sorry! You require the permission pvpboard.admin");
			}
		}
		if(command.getName().toString().equalsIgnoreCase("pvpboard"))
		{
			sender.sendMessage(ChatColor.GREEN + "PVP-Board by ImJustIsabella");
			sender.sendMessage(ChatColor.GREEN + "https://dev.bukkit.org/projects/pvp-board");
		}
		if(command.getName().toString().equalsIgnoreCase("toggleboard"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				
				boardView scoreboard = new boardView(player);
				
				if(_toggleStatus.containsKey(player))
				{
					if(_toggleStatus.get(player) == true)
					{
						player.sendMessage(ChatColor.GREEN + "You have hidden your scoreboard!");
						_toggleStatus.put(player, false);
						scoreboard.hideBoard();
					}
					else
					{
						player.sendMessage(ChatColor.GREEN + "You have revealed your scoreboard!");
						_toggleStatus.put(player, true);
						scoreboard.displayBoard();
					}
				}
				else
				{
					player.sendMessage(ChatColor.GREEN + "You have hidden your scoreboard!");
					_toggleStatus.put(player, false);
					scoreboard.hideBoard();
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "This command can only be runned be a player");
			}
		}
		return false;
	}

	
}
