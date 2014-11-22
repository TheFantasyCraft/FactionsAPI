package techcable.minecraft.factionsapi.compat.v2_0_x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;

import techcable.minecraft.factionsapi.FPlayer;

import lombok.*;

@Getter
public class MassiveFaction extends techcable.minecraft.factionsapi.Faction {
	private final Faction backing;
	
	@Override
	public MassiveFactions getFactions() {
		return (MassiveFactions) super.getFactions();
	}
	public MassiveFaction(MassiveFactions factions, Faction backing) {
		super(factions);
		this.backing = backing;
	}
	
	@Override
	public String getId() {
		return getBacking().getId();
	}

	public String getName() {
		return getBacking().getName();
	}

	public String getDescription() {
		return getBacking().getDescription();
	}

	public boolean isNone() {
		return getBacking().isNone();
	}

	public List<MassiveFaction> fromRawList(List<Faction> raw) {
		return Lists.transform(raw, new Function<Faction, MassiveFaction>() {
			@Override
			public MassiveFaction apply(Faction raw) {
				return MassiveFaction.this.getFactions().getMassiveFaction(raw);
			}
		});
	}
	/*
	public List<Faction> getAllWithRelation(Rel relation) {
		List<Faction> allWithRelation = Lists.newArrayList();
		for (Faction faction : FactionColl.get().getAll()) {
			if (getBacking().getRelationTo(faction).equals(relation)) {
				allWithRelation.add(faction);
			}
		}
		return allWithRelation;
	}
	*/
	@Override
	public FPlayer getOwner() {
		return fromMPlayer(getBacking().getLeader());
	}

	@Override
	public FPlayer[] getModerators() {
		return fromMPlayers(getBacking().getMPlayersWhereRole(Rel.OFFICER));
	}

	@Override
	public FPlayer[] getMembers() {
		return fromMPlayers(getBacking().getMPlayersWhereRole(Rel.MEMBER));
	}

	@Override
	public FPlayer[] getRecruits() {
		return fromMPlayers(getBacking().getMPlayersWhereRole(Rel.RECRUIT));
	}

	@Override
	public FPlayer[] getAllMembers() {
		return fromMPlayers(getBacking().getMPlayers());
	}

	public FPlayer fromMPlayer(MPlayer player) {
		return getFactions().getFPlayer(player.getPlayer());
	}

	public FPlayer[] fromMPlayers(List<MPlayer> player) {
		List<FPlayer> players = Lists.transform(player, new Function<MPlayer, FPlayer>() {

			@Override
			public FPlayer apply(MPlayer arg0) {
				return fromMPlayer(arg0);
			}

		});
		return players.toArray(new FPlayer[players.size()]);
	}

	@Override
	public boolean isAlly(techcable.minecraft.factionsapi.Faction rawother) {
		MassiveFaction other;
		if (rawother instanceof MassiveFaction) {
			other = (MassiveFaction) rawother;
		} else return false;
		return isAlly(other.getBacking()); 
	}

	@Override
	public boolean isEnemy(techcable.minecraft.factionsapi.Faction rawother) {
		MassiveFaction other;
		if (rawother instanceof MassiveFaction) {
			other = (MassiveFaction) rawother;
		} else return false;
		return isEnemy(other.getBacking()); 
	}
	
	public boolean isAlly(Faction other) {
		return getBacking().getRelationTo(other).equals(Rel.ALLY);
	}
	
	public boolean isEnemy(Faction other) {
		return getBacking().getRelationTo(other).equals(Rel.ENEMY);
	}
}