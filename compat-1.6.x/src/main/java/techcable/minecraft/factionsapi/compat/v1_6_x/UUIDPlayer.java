package techcable.minecraft.factionsapi.compat.v1_6_x;

import org.bukkit.OfflinePlayer;

import techcable.minecraft.factionsapi.FRank;

import lombok.*;

@Getter
public class UUIDPlayer extends techcable.minecraft.factionsapi.FPlayer {
	private final UUIDFactions factions;
	public UUIDPlayer(OfflinePlayer player, UUIDFactions factions) {
		super(player);
		this.factions = factions;
	}

	@Override
	public UUIDFaction getFaction() {
		return null;
	}

	@Override
	public FRank getRank() {
		return null;
	}

}
