package techcable.minecraft.factionsapi;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public abstract class Faction {
    public static Faction WARZONE;
    public static Faction SAFEZONE;
    
    @Getter
    private final Factions factions;
    public Faction(Factions factions) {
    	if (WARZONE == null) {
    		Faction.WARZONE = factions.getWarzone();
    	}
    	if (SAFEZONE == null) {
    		Faction.SAFEZONE = factions.getSafezone();
    	}
    	this.factions = factions;
    }
    public abstract String getId();
    public abstract String getName();
    public abstract String getDescription();
    public abstract boolean isSafezone();
    public abstract boolean isWarzone();
    public abstract boolean isNone();
    public abstract Faction[] getAllies();
    public abstract Faction[] getEnemies();
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

    public static <T> boolean isContainValue(T[] array, T value) {
	for (T item : array) {
	    if (item.equals(value)) return true;
	}
	return false;
    }
}