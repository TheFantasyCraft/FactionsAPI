package techcable.minecraft.factionsapi.compat.v1_6_x;

import org.bukkit.OfflinePlayer;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

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
		if (!hasFaction()) return getFactions().getNone();
		return getFactions().getUUIDFaction(getBacking().getFaction());
	}

	@Override
	public FRank getRank() {
		if (!hasFaction()) return null;
		switch (getBacking().getRole()) {
		case ADMIN : return FRank.OWNER;
		case MODERATOR : return FRank.MODERATOR;
		case NORMAL : return FRank.MEMBER;
		default : return FRank.MEMBER; //Worst case scenario get less permissions then should have
		}
	}
	
	public FPlayer getBacking() {
		return FPlayers.getInstance().getByOfflinePlayer(getPlayer());
	}

	@Override
	public boolean hasFaction() {
		if (getBacking() == null) return false;
		else return getBacking().hasFaction();
	}
}
