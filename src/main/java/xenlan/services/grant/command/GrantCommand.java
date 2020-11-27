package xenlan.services.grant.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xenlan.services.grant.menu.GrantMenu;
import xenlan.services.grant.util.chatUtil;
import xenlan.services.grant.xGrant;

public class GrantCommand implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(chatUtil.chat("&cOnly players buddy."));
			return true;
		}

		if (!sender.hasPermission("xgrant.command.grant")) {
			sender.sendMessage(chatUtil.chat("&cNo permission."));
			return true;
		}

		if (args.length == 0) {
			sender.sendMessage(chatUtil.chat("&c/grant <player>"));
			return true;
		}

		if (args.length > 2) {
			return true;
		}

		if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(chatUtil.chat("&cTarget could not be found."));
				return true;
			}
			Player player = (Player) sender;
			xGrant.instance.playerStorage.put(player, target);
			GrantMenu.openGUI(player);

		}

		return false;
	}
}
