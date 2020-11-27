package xenlan.services.grant.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xenlan.services.grant.util.ItemBuilder;
import xenlan.services.grant.util.chatUtil;
import xenlan.services.grant.xGrant;

import java.util.List;

public class GrantMenu {

	public static void openGUI(Player player) {
		int size = xGrant.instance.getConfig().getInt("Inventory.Settings.Size");
		String title = xGrant.instance.getConfig().getString("Inventory.Settings.Title");
		Inventory i = Bukkit.createInventory(null, size, chatUtil.chat(title));

		if (xGrant.instance.getConfig().getBoolean("Inventory.Auto-Fill.Enabled")) {
			for (int it = 0; it < size; it++) {
				String material = xGrant.instance.getConfig().getString("Inventory.Auto-Fill.Material");
				int data = xGrant.instance.getConfig().getInt("Inventory.Auto-Fill.Data");
				ItemStack itemStack = new ItemBuilder(Material.valueOf(material)).displayName(null).data((short) data).build();
				i.setItem(it, itemStack);
			}
		}

		for (String section : xGrant.instance.getConfig().getConfigurationSection("Inventory.Contents").getKeys(false)) {

			String name = xGrant.instance.getConfig().getString("Inventory.Contents." + section + ".Name");
			String material = xGrant.instance.getConfig().getString("Inventory.Contents." + section + ".Material");
			int data = xGrant.instance.getConfig().getInt("Inventory.Contents." + section + ".Data");
			int slot = xGrant.instance.getConfig().getInt("Inventory.Contents." + section + ".Slot");
			List<String> lore = xGrant.instance.getConfig().getStringList("Inventory.Contents." + section + ".Lore");

			ItemStack stack = new ItemBuilder(Material.valueOf(material)).data((short) data).displayName(chatUtil.chat(name)).setLore(chatUtil.colorLines(lore)).build();

			i.setItem(slot, stack);

		}

		player.openInventory(i);

	}

}
