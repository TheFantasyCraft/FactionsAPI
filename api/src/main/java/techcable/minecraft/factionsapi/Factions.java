package techcable.minecraft.factionsapi;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import lombok.*;

public abstract class Factions {
	
    public abstract FPlayer getFPlayer(OfflinePlayer player); 
    public abstract Faction getOwningFaction(Location location);
    
    public abstract Faction[] getAllFactions();
    
    public abstract Faction getWarzone();
    public abstract Faction getSafezone();
    public abstract Faction getNone();
}
