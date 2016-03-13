package me.blaetterwahn.VendacciChat.PluginWrapper;

import org.bukkit.entity.Player;

public final class NoPlugin implements IPermissionsPlugin {

	@Override
	public String getName() {
		return "No plugin hooked up!";
	}

	@Override
	public String getPrefix(Player p) {
		return "";
	}

	@Override
	public String getSuffix(Player p) {
		return "";
	}

}
