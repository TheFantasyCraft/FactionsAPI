package techcable.minecraft.factionsapi;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import lombok.*;

@Getter
public abstract class FPlayer {
    private OfflinePlayer player;
    

    public FPlayer(OfflinePlayer player) {
	this.player = player;
    }
    
    public abstract boolean hasFaction();
    public abstract Faction getFaction();
    public abstract FRank getRank();
    
    public boolean isOutrank(FPlayer other) {
    	if (other.getRank() == null) return false;
    	if (!hasFaction()) return false;
    	if (!other.hasFaction()) return false;
    	if (!other.getFaction().equals(getFaction())) return false;
    	return getRank().isOutrank(other.getRank());
    }
}