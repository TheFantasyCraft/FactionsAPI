package techcable.minecraft.factionsapi;

import org.bukkit.Bukkit;

import techcable.minecraft.factionsapi.compat.v1_6_x.UUIDFactions;
import techcable.minecraft.factionsapi.compat.v2_0_x.MassiveFactions;

import lombok.*;

@Getter
public class FactionsAPI {
	private static Factions instance;
	private static boolean supported = true;
	public static Factions getInstance() {
		if (!supported) return null;
		if (!isFactionsInstalled()) {
			supported = false;
		}
		if (instance == null) {
			if (is2_0_x()) {
				instance = new MassiveFactions();
			} else if (is1_6_x()) {
				instance = new UUIDFactions();
			} else {
				supported = false;
				return null;
			}
		}
		return instance;
	}
	
	public static boolean isFactionsInstalled() {
		return Bukkit.getPluginManager().isPluginEnabled("Factions");
	}
	
	public static boolean is2_0_x() {
		try {
			Class.forName("com.massivecraft.factions.entity.FactionColl");
			return true;
		} catch (ClassNotFoundException ex) {
			return false;
		}
	}
	
	public static boolean is1_6_x() {
		try {
			Class.forName("com.massivecraft.factions.FPlayer");
			return true;
		} catch (ClassNotFoundException ex) {
			return false;
		}
	}
}
