package xenlan.services.grant;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xenlan.services.grant.command.GrantCommand;
import xenlan.services.grant.command.ReloadCommand;
import xenlan.services.grant.listener.MenuListener;
import xenlan.services.grant.util.chatUtil;

import java.util.HashMap;

public class xGrant extends JavaPlugin {

	public static xGrant instance;
	public HashMap<Player, Player> playerStorage;
	@Override
	public void onEnable() {
		instance = this;
		playerStorage = new HashMap<>();
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage(chatUtil.chat("&8&m-----------------"));
		Bukkit.getConsoleSender().sendMessage(chatUtil.chat("&bxGrant&f has been &aenabled"));
		Bukkit.getConsoleSender().sendMessage(chatUtil.chat("&fMade by &bLBuddyBoy&f for Xenlan Services."));
		Bukkit.getConsoleSender().sendMessage(chatUtil.chat("&8&m-----------------"));
		getCommand("grant").setExecutor(new GrantCommand());
		getCommand("xgrant").setExecutor(new ReloadCommand());
		Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
	}
}
