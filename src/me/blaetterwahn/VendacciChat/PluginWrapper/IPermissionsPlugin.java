package me.blaetterwahn.VendacciChat.PluginWrapper;

import org.bukkit.entity.Player;

public interface IPermissionsPlugin {

	public String getName();

	public String getPrefix(Player p);

	public String getSuffix(Player p);

}
