package com.edgecraftsmp.imjustisabella.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class playerStatistics {

	private Player player;
	
	public static HashMap<Player, Integer> _playerKills = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> _playerDeaths = new HashMap<Player, Integer>();
		
	public playerStatistics(Player player)
	{
		this.player = player;
	}
	
	public int grabData(String data)
	{
		File f = new File("plugins/PVPBoard/players/" + this.player.getUniqueId().toString() + ".yml");
		if(f.exists())
		{
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			return yml.getInt(data);
		}
		else
		{
			setupYML();
			return 0;
		}
	}
	public void setupYML()
	{
		File f = new File("plugins/PVPBoard/players/" + this.player.getUniqueId().toString() + ".yml");
		if(!f.exists())
		{
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			yml.set("Deaths", 0);
			yml.set("Kills", 0);
			try {
				yml.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setupData()
	{
		_playerKills.put(this.player, grabData("Kills"));
		_playerDeaths.put(this.player, grabData("Deaths"));
	}
	
	public void removeData()
	{
		_playerKills.remove(this.player);
		_playerDeaths.remove(this.player);
	}
	
	public void addKill()
	{
		int newValue = _playerKills.get(this.player) + 1;
		_playerKills.put(this.player, newValue);
		
		File f = new File("plugins/PVPBoard/players/" + this.player.getUniqueId().toString() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set("Kills", newValue);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addDeath()
	{
		int newValue = _playerDeaths.get(this.player) + 1;
		_playerDeaths.put(this.player, newValue);
		
		File f = new File("plugins/PVPBoard/players/" + this.player.getUniqueId().toString() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set("Deaths", newValue);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getKills()
	{
		return _playerKills.get(this.player);
	}
	
	public int getDeaths()
	{
		return _playerDeaths.get(this.player);
	}
	
	public double getKDR()
	{
		boolean isCompleted = false;
		
		double val = 0.0;
		
		if(_playerKills.get(this.player) > 0)
		{
			if(_playerDeaths.get(this.player) > 0)
			{
				val = _playerKills.get(this.player) / _playerDeaths.get(this.player);
				isCompleted = true;
			}
		}
		
		if(isCompleted == false)
		{
			if(_playerKills.get(this.player) > 0)
			{
				val = 1.0;
			}
			else
			{
				val = 0.0;
			}
		}
		
		return val;
	}
}
