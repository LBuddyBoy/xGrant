package xenlan.services.grant.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xenlan.services.grant.util.chatUtil;
import xenlan.services.grant.xGrant;

public class ReloadCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player || !(sender instanceof Player)) {
			if (!sender.hasPermission("xgrant.command.grant")) {
				sender.sendMessage(chatUtil.chat("&cNo permission."));
				return true;
			}

			if (args.length == 0) {
				sender.sendMessage(chatUtil.chat("&c/xgrant reload"));
				return true;
			}

			if (args.length > 2) {
				return true;
			}

			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("reload")) {
					xGrant.instance.reloadConfig();
				} else {
					sender.sendMessage(chatUtil.chat("&c/xgrant reload"));
				}

			}
		}
		return false;
	}
}
