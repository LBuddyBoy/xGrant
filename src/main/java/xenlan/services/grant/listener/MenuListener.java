package xenlan.services.grant.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xenlan.services.grant.util.ItemBuilder;
import xenlan.services.grant.util.chatUtil;
import xenlan.services.grant.xGrant;

import java.util.List;

public class MenuListener implements Listener {


	@EventHandler
	public void onInteractInventory(InventoryClickEvent event) {
		String title = xGrant.instance.getConfig().getString("Inventory.Settings.Title");
		if (event.getInventory().getTitle().equalsIgnoreCase(chatUtil.chat(title))) {
			for (String section : xGrant.instance.getConfig().getConfigurationSection("Inventory.Contents").getKeys(false)) {
				String name = xGrant.instance.getConfig().getString("Inventory.Contents." + section + ".Name");
				String display = xGrant.instance.getConfig().getString("Inventory.Contents." + section + ".Display");
				String material = xGrant.instance.getConfig().getString("Inventory.Contents." + section + ".Material");
				int data = xGrant.instance.getConfig().getInt("Inventory.Contents." + section + ".Data");
				int slot = xGrant.instance.getConfig().getInt("Inventory.Contents." + section + ".Slot");
				List<String> lore = xGrant.instance.getConfig().getStringList("Inventory.Contents." + section + ".Lore");

				ItemStack stack = new ItemBuilder(Material.valueOf(material)).data((short) data).displayName(chatUtil.chat(name)).setLore(chatUtil.colorLines(lore)).build();

				if (event.getCurrentItem() == null)
					return;

				if (stack.isSimilar(event.getCurrentItem())) {
					Player player = (Player) event.getWhoClicked();
					for (String message : xGrant.instance.getConfig().getStringList("Sender-Message")) {
						player.sendMessage(chatUtil.chat(message)
								.replaceAll("%rank%", chatUtil.chat(display))
										.replaceAll("%player%", xGrant.instance.playerStorage.get(player).getName()));
					}
					for (String message : xGrant.instance.getConfig().getStringList("Target-Message")) {
						xGrant.instance.playerStorage.get(player).sendMessage(chatUtil.chat(message)
								.replaceAll("%rank%", chatUtil.chat(display))
								.replaceAll("%player%", player.getName()));
					}
					String cmd = xGrant.instance.getConfig().getString("Inventory.Contents." + section + ".Command");
					Bukkit.dispatchCommand(player, cmd.replaceAll("%player%", xGrant.instance.playerStorage.get(player).getName()));
					xGrant.instance.playerStorage.remove(player);
					player.closeInventory();
				}

			}
		}

	}

}
