package techcable.minecraft.factionsapi.compat.v1_6_x;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;

import techcable.minecraft.factionsapi.util.EasyCache;

import lombok.*;

@Getter
public class UUIDFactions extends techcable.minecraft.factionsapi.Factions {
	
	private EasyCache<OfflinePlayer, UUIDPlayer> playerCache = new EasyCache<>(new EasyCache.Loader<OfflinePlayer, UUIDPlayer>() {

		@Override
		public UUIDPlayer load(OfflinePlayer key) {
			return new UUIDPlayer(key, UUIDFactions.this);
		}
		
	});
	
	@Override
	public UUIDPlayer getFPlayer(OfflinePlayer player) {
		return playerCache.get(player);
	}
	
	private EasyCache<Faction, UUIDFaction> factionCache = new EasyCache<>(new EasyCache.Loader<Faction, UUIDFaction>() {

		@Override
		public UUIDFaction load(Faction key) {
			return new UUIDFaction(UUIDFactions.this, key);
		}
	});
	public UUIDFaction getUUIDFaction(Faction raw) {
		return factionCache.get(raw);
	}
	@Override
	public UUIDFaction getOwningFaction(Location location) {
		return getUUIDFaction(Board.getInstance().getFactionAt(new FLocation(location)));
	}

	@Override
	public UUIDFaction[] getAllFactions() {
		List<Faction> rawAll = Factions.getInstance().getAllFactions();
		UUIDFaction[] all = new UUIDFaction[rawAll.size()];
		for (int i = 0; i < rawAll.size(); i++) {
			all[i] = getUUIDFaction(rawAll.get(i));
		}
		return all;
	}

	@Override
	public UUIDFaction getWarzone() {
		return getUUIDFaction(Factions.getInstance().getWarZone());
	}

	@Override
	public UUIDFaction getSafezone() {
		return getUUIDFaction(Factions.getInstance().getSafeZone());
	}

}
