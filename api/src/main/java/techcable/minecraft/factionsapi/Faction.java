BBpackage techcable.minecraft.factionsapi;

import java.util.ArrayList;
import java.util.List;

public abstract class Faction {
    public static final Faction WARZONE;
    public static final Faction SAFEZONE;
    public abstract String getId();
    public abstract String getName();
    public abstract String getDescription();
    public abstract boolean isSafezone();
    public abstract boolean isWarzone();
    public abstract boolean isNone();
    public abstract Faction[] getAllies();
    public abstract Faction[] getEnemies();
    public boolean isAlly(Faction other) {
	return isContainValue(getAllies(), value);
    }
    public boolean isAlly(FPlayer player) {
	return isAlly(player.getFaction());
    }
    public boolean isEnemy(Faction other) {
	return isContainValue(getEnemies(), value);
    }
    public boolean isEnemy(Faction player) {
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