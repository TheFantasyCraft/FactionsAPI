package techcable.minecraft.factionsapi;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import lombok.*;

@Getter
public abstract class Factions {
    private Factions instance;
    
    public abstract FPlayer getFPlayer(OfflinePlayer player); 
    public abstract Faction getOwningFaction(Location location);

    public void registerInstance(Factions factions) {
	if (getInstance() == null) {
	    this.instance = factions;
	}
    }
    
    public abstract Faction[] getAllFactions();
    
    public abstract Faction getWarzone();
    public abstract Faction getSafezone();
}
