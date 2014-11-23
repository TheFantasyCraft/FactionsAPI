package techcable.minecraft.factionsapi.compat.v2_0_x;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.massivecore.ps.PS;

import techcable.minecraft.factionsapi.Factions;
import techcable.minecraft.factionsapi.FPlayer;
import techcable.minecraft.factionsapi.util.EasyCache;
import techcable.minecraft.factionsapi.util.EasyCache.Loader;

public class MassiveFactions extends Factions {

	private EasyCache<OfflinePlayer, FPlayer> fPlayerCache = new EasyCache<>(new Loader<OfflinePlayer, FPlayer>() {

		public FPlayer load(OfflinePlayer player) {
			return new MassiveFPlayer(player, MassiveFactions.this);
		}
	});

	@Override
	public FPlayer getFPlayer(OfflinePlayer player) {
		if (player == null) return null;
		return fPlayerCache.get(player);
	}

	private EasyCache<Faction, MassiveFaction> massiveFactionCache = new EasyCache<>(new Loader<Faction, MassiveFaction>() {
		@Override
		public MassiveFaction load(Faction key) {
			return new MassiveFaction(MassiveFactions.this, key);
		}
	});

	public MassiveFaction getMassiveFaction(Faction realFaction) {
		if (realFaction == null) return null;
		return massiveFactionCache.get(realFaction);
	}

	@Override
	public MassiveFaction getOwningFaction(Location location) {
		if (location == null) return null;
		PS ps = PS.valueOf(location);
		Faction rawFaction = BoardColl.get().getFactionAt(ps);
		return getMassiveFaction(rawFaction);
	}

	@Override
	public MassiveFaction[] getAllFactions() {
		List<Faction> rawAll = Lists.newArrayList(FactionColl.get().getAll());
		MassiveFaction[] all = new MassiveFaction[rawAll.size()];
		for (int i = 0; i < rawAll.size(); i++) {
			all[i] = getMassiveFaction(rawAll.get(i));
		}
		return all;
	}

	@Override
	public MassiveFaction getWarzone() {
		return getMassiveFaction(FactionColl.get().getWarzone());
	}

	@Override
	public MassiveFaction getSafezone() {
		return getMassiveFaction(FactionColl.get().getSafezone());
	}

	@Override
	public MassiveFaction getNone() {
		return getMassiveFaction(FactionColl.get().getNone());
	}
}