package techcable.minecraft.factionsapi.compat.v1_6_x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.struct.Role;

import techcable.minecraft.factionsapi.Factions;

import lombok.*;

@Getter
public class UUIDFaction extends techcable.minecraft.factionsapi.Faction {
	private final Faction backing;
	public UUIDFactions getFactions() {
		return (UUIDFactions) super.getFactions();
	}
	public UUIDFaction(UUIDFactions factions, Faction backing) {
		super(factions);
		this.backing = backing;
	}

	@Override
	public String getId() {
		return getBacking().getId();
	}

	@Override
	public String getName() {
		return getBacking().getTag();
	}

	@Override
	public String getDescription() {
		return getBacking().getDescription();
	}

	@Override
	public boolean isNone() {
		return getBacking().isNone();
	}

	@Override
	public boolean isAlly(techcable.minecraft.factionsapi.Faction rawother) {
		if (rawother instanceof UUIDFaction) {
			UUIDFaction other = (UUIDFaction) rawother;
			return isAlly(other.getBacking());
		} else return false;
	}
	
	public boolean isAlly(Faction other) {
		return getBacking().getRelationTo(other).isAlly();
	}
	@Override
	public boolean isEnemy(techcable.minecraft.factionsapi.Faction rawother) {
		if (rawother instanceof UUIDFaction) {
			UUIDFaction other = (UUIDFaction) rawother;
			return isEnemy(other.getBacking());
		} else return false;
	}
	
	public boolean isEnemy(Faction other) {
		return getBacking().getRelationTo(other).isEnemy();
	}
	@Override
	public UUIDPlayer getOwner() {
		return getUUIDPlayer(getBacking().getFPlayerAdmin());
	}

	@Override
	public UUIDPlayer[] getModerators() {
		List<UUIDPlayer> uuidPlayers = toUUIDPlayers(getBacking().getFPlayersWhereRole(Role.MODERATOR));
		return uuidPlayers.toArray(new UUIDPlayer[uuidPlayers.size()]);
	}

	@Override
	public UUIDPlayer[] getMembers() {
		List<UUIDPlayer> uuidPlayers = toUUIDPlayers(getBacking().getFPlayersWhereRole(Role.NORMAL));
		return uuidPlayers.toArray(new UUIDPlayer[uuidPlayers.size()]);
	}
	public static final UUIDPlayer[] EMPTY_PLAYER_ARRAY = new UUIDPlayer[0];
	@Override
	public UUIDPlayer[] getRecruits() {
		return EMPTY_PLAYER_ARRAY;
	}

	@Override
	public UUIDPlayer[] getAllMembers() {
		List<UUIDPlayer> uuidPlayers = toUUIDPlayers(getBacking().getFPlayers());
		return uuidPlayers.toArray(new UUIDPlayer[uuidPlayers.size()]);
	}
	
	public UUIDPlayer getUUIDPlayer(FPlayer fplayer) {
		return getFactions().getFPlayer(fplayer.getPlayer());
	}
	
	public List<UUIDPlayer> toUUIDPlayers(Collection<FPlayer> fplayers) {
		List<UUIDPlayer> uuidPlayers = new ArrayList<>();
		for (FPlayer fplayer : fplayers) {
			uuidPlayers.add(getUUIDPlayer(fplayer));
		}
		return uuidPlayers;
	}
}
