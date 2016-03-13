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
		if (checkForPlugin("PermissionsEx", "ru.tehkode.permissions.bukkit.PermissionsEx")) {
			permissions = new PermissionsExPlugin();
		} else {
			permissions = new NoPlugin();
		}

		getLogger().info("Hooked into: " + permissions.getName());
		getServer().getPluginManager().registerEvents(new ChatTransformer(permissions), this);
	}

	/**
	 * Checks if a certain JavaPlugin is available and enabled by bukkit
	 * 
	 * @param name
	 *            JavaPlugin name
	 * @param namespace
	 *            The whole main namespace of the JavaPlugin (Using
	 *            {@link Class} would crash if the plugin is not installed due
	 *            to linking of the class at runtime)
	 * @return
	 */
	private boolean checkForPlugin(String name, String namespace) {
		Plugin plugin = getServer().getPluginManager().getPlugin(name);
		return plugin != null && plugin.isEnabled() && plugin.getClass().getName().equals(namespace);
	}

}
