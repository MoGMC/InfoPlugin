package moe.hilaryoi.mc.InfoPlugin;

import moe.hilaryoi.mc.ComponentUtil;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class InfoPluginPlugin extends JavaPlugin {

	HashMap<String, BaseComponent[]> messages;

	@Override
	public void onEnable () {

		saveDefaultConfig ();

		parseConfig ();


	}

	void parseConfig () {

		FileConfiguration config = getConfig ();

		messages = new HashMap<> ();

		for (String key : config.getKeys (false)) {

			messages.put (key, ComponentUtil.parseMarkup (config.getString (key)));

		}

	}

	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {

		if (command.getName ().equalsIgnoreCase ("reloadinfo")) {

			reloadConfig ();
			parseConfig ();
			sender.sendMessage ("Reloaded config.");

		}

		for (String key : messages.keySet ()) {

			if (command.getName ().equalsIgnoreCase (key)) {

				sender.spigot ().sendMessage (messages.get (key));
				return true;

			}

		}

		return false;

	}
}
