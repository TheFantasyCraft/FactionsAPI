package techcable.minecraft.factionsapi.compat.v2_0_x;

import org.bukkit.OfflinePlayer;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.entity.MPlayerColl;

import techcable.minecraft.factionsapi.FPlayer;
import techcable.minecraft.factionsapi.FRank;

import lombok.*;

@Getter
public class MassiveFPlayer extends FPlayer {
	public MassiveFPlayer(OfflinePlayer backing, MassiveFactions factions) {
		super(backing);
		this.factions = factions;
	}
	private final MassiveFactions factions;
	@Override
	public MassiveFaction getFaction() {
		if (!hasFaction()) return getFactions().getNone();
		return toMassiveFaction(getMPlayer().getFaction());
	}

	@Override
	public FRank getRank() {
		if (!hasFaction()) return null;
		Rel rel = getMPlayer().getRole();
		switch (rel) {
			case LEADER : return FRank.OWNER;
			case OFFICER : return FRank.MODERATOR;
			case MEMBER : return FRank.MEMBER;
			case RECRUIT : return FRank.RECRUIT;
			default : return FRank.RECRUIT; //Worst case scenario they get less permissions then they should have
		}
	}
	
	public MPlayer getMPlayer() {
		return MPlayer.get(getPlayer());
	}
	
	public MassiveFaction toMassiveFaction(Faction raw) {
		return getFactions().getMassiveFaction(raw);
	}

	@Override
	public boolean hasFaction() {
		if (getMPlayer() == null) return false;
		return getMPlayer().hasFaction();
	}
}
