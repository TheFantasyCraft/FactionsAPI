package techcable.minecraft.factionsapi;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import lombok.*;

@Getter
public abstract class Factions {
    private Factions instance;
    
    public abstract FPlayer getFPlayer(Player player); 
    public abstract Faction getOwningFaction(Location location);

    public void registerInstance(Factions factions) {
	if (getInstance() == null) {
	    this.instance = factions;
	}
    }
}
