package com.edgecraftsmp.imjustisabella;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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
		
		setupYML();
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
			data.set("Rows", 16);
			data.set("Name", "&lPVP-Board");
			data.set("16", "Hi there!");
			data.set("15", "");
			data.set("14", "Welcome");
			data.set("13", "to");
			data.set("12", "PVP Board");
			data.set("11", "");
			data.set("10", "");
			data.set("9", "");
			data.set("8", "");
			data.set("7", "Online Players: <onlineCount>");
			data.set("6", "");
			data.set("5", "");
			data.set("4", "&aKills: &e<Kills>");
			data.set("3", "");
			data.set("2", "Deaths: &e<Deaths>");
			data.set("1", "KDR: &e<KDR>");
			try {
				data.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
