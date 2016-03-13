package me.blaetterwahn.VendacciChat.PluginWrapper;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public final class PermissionsExPlugin implements IPermissionsPlugin {

	@Override
	public String getPrefix(Player p) {
		PermissionUser user = PermissionsEx.getUser(p);
		if (user == null) {
			return "";
		}
		return user.getPrefix();
	}

	@Override
	public String getSuffix(Player p) {
		PermissionUser user = PermissionsEx.getUser(p);
		if (user == null) {
			return "";
		}
		return user.getSuffix();
	}

	@Override
	public String getName() {
		return PermissionsEx.getPlugin().getName();
	}

}
