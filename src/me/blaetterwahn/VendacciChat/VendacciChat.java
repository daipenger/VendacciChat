package me.blaetterwahn.VendacciChat;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.blaetterwahn.VendacciChat.PluginWrapper.IPermissionsPlugin;
import me.blaetterwahn.VendacciChat.PluginWrapper.NoPlugin;
import me.blaetterwahn.VendacciChat.PluginWrapper.PermissionsExPlugin;

public final class VendacciChat extends JavaPlugin {

	public static final String CHAT_FORMAT;
	public static final String PLAYER_JOIN;
	public static final String PLAYER_QUIT;
	public static final String PLAYER_FIRST_JOIN;

	static {
		final String playerFormat = new StringBuilder(ChatTransformer.PREFIX).append(ChatTransformer.PLAYER).append(ChatTransformer.SUFFIX).toString();
		StringBuilder builder;

		builder = new StringBuilder();
		builder.append(playerFormat).append(": ").append(ChatTransformer.MESSAGE);
		CHAT_FORMAT = builder.toString();

		builder = new StringBuilder();
		builder.append("&2+&f ").append(playerFormat);
		PLAYER_JOIN = builder.toString();

		builder = new StringBuilder();
		builder.append("&c-&f ").append(playerFormat);
		PLAYER_QUIT = builder.toString();

		builder = new StringBuilder();
		builder.append("&6Herzlich willkommen ").append(playerFormat).append("!");
		PLAYER_FIRST_JOIN = builder.toString();
	}

	@Override
	public void onEnable() {
		final IPermissionsPlugin permissions;
		if (checkForPlugin("PermissionsEx")) {
			permissions = new PermissionsExPlugin();
		} else {
			permissions = new NoPlugin();
		}

		getLogger().info("Hooked into: " + permissions.getName());
		getServer().getPluginManager().registerEvents(new ChatTransformer(permissions), this);
	}

	private boolean checkForPlugin(String name) {
		Plugin plugin = getServer().getPluginManager().getPlugin(name);
		return plugin != null && plugin.isEnabled();
	}

}
