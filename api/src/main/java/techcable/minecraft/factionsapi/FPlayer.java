Bpackage techcable.minecraft.factionsapi;

import org.bukkit.entity.Player;

import lombok.*;

@Getter
public abstract class FPlayer {
    private Player player;
    

    public FPlayer(Player player) {
	this.player = player;
    }

    public abstract Faction getFaction();
    public abstract FRank getRank();
    
    public boolean isOutrank(FPlayer other) {
	return getRank().isOutrank(other.getRank());
    }
}