package me.blaetterwahn.VendacciChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.blaetterwahn.VendacciChat.PluginWrapper.IPermissionsPlugin;

public final class ChatTransformer implements Listener {

	public static final String PLAYER = "@player";
	public static final String PREFIX = "@prefix";
	public static final String SUFFIX = "@suffix";
	public static final String MESSAGE = "@message";

	private final IPermissionsPlugin permissions;

	public ChatTransformer(IPermissionsPlugin permissions) {
		this.permissions = permissions;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(final PlayerJoinEvent e) {
		String msg = e.getPlayer().hasPlayedBefore() ? VendacciChat.PLAYER_JOIN : VendacciChat.PLAYER_FIRST_JOIN;
		e.setJoinMessage(replacePlaceholders(e.getPlayer(), msg));
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onQuit(final PlayerQuitEvent e) {
		e.setQuitMessage(replacePlaceholders(e.getPlayer(), VendacciChat.PLAYER_QUIT));
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerChat(final AsyncPlayerChatEvent event) {
		String format = VendacciChat.CHAT_FORMAT;

		// String formatting done by bukkit
		// First placeholder: Player
		// Second placeholder: Message
		format = format.replace(MESSAGE, "%2$s");
		format = replacePlaceholders(event.getPlayer(), format);
		event.setFormat(format);
	}

	private String replacePlaceholders(Player player, String format) {
		format = format.replace(PLAYER, player.getName());
		format = format.replace(PREFIX, permissions.getPrefix(player));
		format = format.replace(SUFFIX, permissions.getSuffix(player));
		format = ChatColor.translateAlternateColorCodes('&', format);
		return format;
	}

}
