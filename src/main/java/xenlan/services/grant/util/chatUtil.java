package xenlan.services.grant.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class chatUtil {

	public static String chat(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static List<String> colorLines( List<String> lore) {
		List<String> color = new ArrayList<>();
		for ( String s : lore) {
			color.add(chat(s));
		}
		return color;
	}

}
