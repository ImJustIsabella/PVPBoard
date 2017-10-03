package com.edgecraftsmp.imjustisabella;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.edgecraftsmp.imjustisabella.CMD;
import com.edgecraftsmp.imjustisabella.Events.Deaths;
import com.edgecraftsmp.imjustisabella.Events.Join;
import com.edgecraftsmp.imjustisabella.Events.Kills;
import com.edgecraftsmp.imjustisabella.Events.Quit;

public class Main extends JavaPlugin {

	public static Plugin plugin;
	
	public static YamlConfiguration scoreboard = YamlConfiguration.loadConfiguration(new File("plugins/PVPBoard/scoreboard.yml"));
	
	public void onEnable()
	{
		plugin = this;
		
		registerEvents();
		setupCommands();
		
		setupYML();
	}
	
	private void setupCommands()
	{
		this.getCommand("reloadboard").setExecutor(new CMD());
		this.getCommand("pvpboard").setExecutor(new CMD());
		this.getCommand("toggleboard").setExecutor(new CMD());
	}
	
	private void registerEvents()
	{
		new Deaths(this);
		new Join(this);
		new Kills(this);
		new Quit(this);
	}
	
	private void setupYML()
	{
		File f = new File("plugins/PVPBoard/scoreboard.yml");
		if(!f.exists())
		{
			YamlConfiguration data = YamlConfiguration.loadConfiguration(f);
			data.set("Name", "&lPVP-Board");
			
			List<String> strings = Arrays.asList("Hello <playerName>",
			"welcome to PVP-Board!",
			"",
			"This is an example",
			"of what can be done.",
			"You can do &1c&2o&3l&4o&5r&6s",
			"or use variables",
			"such as",
			"",
			"Kills: <Kills>",
			"Deaths: <Deaths>",
			"Online: <onlineCount>",
			"KDR: <KDR>");
			
			data.set("Rows", strings);
			try {
				data.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(f.exists())
		{
			boolean needsUpdating = false;
			for(int i = 0; i < 16; i++)
			{
				if(scoreboard.contains(Integer.toString(i)))
				{
					needsUpdating = true;
				}
			}
			if(needsUpdating == true)
			{
				f.delete();
				YamlConfiguration data = YamlConfiguration.loadConfiguration(f);
				data.set("Name", "&lPVP-Board");
				
				ArrayList<String> _lines = new ArrayList<String>();
				for(int i = 0; i < 16; i++)
				{
					if(scoreboard.contains(Integer.toString(i)))
					{
						_lines.add(scoreboard.getString(Integer.toString(i)));
					}
					else
					{
						_lines.add("");
					}
				}
					
				List<String> strings = Arrays.asList(_lines.get(0),
						_lines.get(1),
						_lines.get(2),
						_lines.get(3),
						_lines.get(4),
						_lines.get(5),
						_lines.get(6),
						_lines.get(7),
						_lines.get(8),
						_lines.get(9),
						_lines.get(10),
						_lines.get(11),
						_lines.get(12),
						_lines.get(13),
						_lines.get(14),
						_lines.get(15));
				
				data.set("Rows", strings);
				try {
					data.save(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
