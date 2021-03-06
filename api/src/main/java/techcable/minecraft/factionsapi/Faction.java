package techcable.minecraft.factionsapi;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public abstract class Faction {
	
	@Getter
	private final Factions factions;

	public Faction(Factions factions) {
		this.factions = factions;
	}

	public abstract String getId();

	public abstract String getName();

	public abstract String getDescription();

	public boolean isSafezone() {
		return this.equals(getFactions().getSafezone());
	}

	public boolean isWarzone() {
		return this.equals(getFactions().getWarzone());
	}

	public boolean isNone() {
		return this.equals(getFactions().getNone());
	}

	public Faction[] getAllies() {
		List<Faction> allies = new ArrayList<>();
		for (Faction faction : getFactions().getAllFactions()) {
			if (isAlly(faction)) {
				allies.add(faction);
			}
		}
		return allies.toArray(new Faction[allies.size()]);
	}

	public Faction[] getEnemies() {
		List<Faction> enemies = new ArrayList<>();
		for (Faction faction : getFactions().getAllFactions()) {
			if (isEnemy(faction)) {
				enemies.add(faction);
			}
		}
		return enemies.toArray(new Faction[enemies.size()]);
	}

	public abstract boolean isAlly(Faction other);

	public abstract boolean isEnemy(Faction other);

	public boolean isAlly(FPlayer player) {
		return isAlly(player.getFaction());
	}

	public boolean isEnemy(FPlayer player) {
		return isEnemy(player.getFaction());
	}

	public boolean isMember(FPlayer player) {
		return player.getFaction().equals(this);
	}

	public abstract FPlayer getOwner();

	public abstract FPlayer[] getModerators();

	public abstract FPlayer[] getMembers();

	public abstract FPlayer[] getRecruits();

	public abstract FPlayer[] getAllMembers();

	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (other instanceof Faction) {
			Faction otherFaction = (Faction) other;
			if (otherFaction.getId().equals(getId())) return true;
			else return false;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}