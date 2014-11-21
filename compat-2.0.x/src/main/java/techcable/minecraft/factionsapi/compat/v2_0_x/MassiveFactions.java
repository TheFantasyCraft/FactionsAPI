package techcable.minecraft.factionsapi.compat.v2_0_x;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;

import techcable.minecraft.factionsapi.Factions;
import techcable.minecraft.factionsapi.FPlayer;
import techcable.minecraft.factionsapi.util.EasyCache;
import techcable.minecraft.factionsapi.util.EasyCache.Loader;

public class MassiveFactions extends Factions {
    
    private EasyCache<Player, FPlayer> fPlayerCache = new EasyCache<>(new Loader<Player, FPlayer>() {
	    
	    public FPlayer load(Player player) {
		return new MassiveFPlayer(player, MassiveFactions.this);
	    }
    });
    @Override
    public FPlayer getFPlayer(Player player) {
	if (player == null) return null;
	return fPlayerCache.get(player);
    }
    private EasyCache<Faction, MassiveFaction> massiveFactionCache = new EasyCache<>(new Loader<Faction, MassiveFaction>() {
	    @Override
	    private MassiveFaction load(Faction faction) {
		return new MassiveFaction(faction, MassiveFactions.this);
	    }
    });
    public MassiveFaction getMassiveFaction(Faction realFaction) {
	if (realFaction == null) return null;
	return massiveFactionCache.get(realFaction);
    }
    @Override
    public techcable.minecraft.factionsapi.Faction getOwningFaction(Location location) {
	if (location == null) return null;
	PS ps = PS.valueOf(location);
	Faction rawFaction = BoardColl.get().getFactionAt(ps);
	return getMassiveFaction(rawFaction);
    }
}