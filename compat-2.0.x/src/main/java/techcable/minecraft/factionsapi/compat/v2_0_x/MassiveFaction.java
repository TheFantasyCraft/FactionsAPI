package techcable.minecraft.factionsapi.compat.v2_0_x;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.FactionsColl;
import com.massivecraft.factions.Rel;

import lombok.*;

@RequiredArgsConstructor
@Getter
public class MassiveFaction extends techcable.minecraft.factionsapi.Faction {
    private final Faction backing;
    private final MassiveFactions factions;
    
    @Override
    public String getId() {
	return getBacking().getId();
    }
    
    public String getName() {
	return getBacking().getName();
    }
    public String getDescription() {
	return getBacking().getDescription();
    }
    public boolean isSafezone() {
	return getId().equals(Factions.ID_SAFEZONE);
    }
    public boolean isWarzone() {
	return getId().equals(Factions.ID_WARZONE);
    }
    public boolean isNone() {
	return getBacking().isNone();
    }
    public MassiveFaction[] getAllies() {
	List<Faction> rawAllies = getAllWithRelation(Rel.ALLY);
	List<MassiveFaction> allies = fromRawList(rawAllies);
	return allies.toArray(new MassiveFaction[allies.size()]);
    }
    public MassiveFaction[] getEnemies() {
	List<Faction> rawEnemies = getAllWithRelation(Rel.ALLY);
	List<MassiveFaction> enemies = fromRawList(rawEnemies);
	return enemies.toArray(new MassiveFaction[enemies.size()]);
    }
    
    public List<MassiveFaction> fromRawList(List<Faction> raw) {
	return Lists.transform(raw, new Function<>() {
		@Override
		public MassiveFaction apply(Faction raw) {
		    return MassiveFaction.this.getFactions().getMassiveFaction(raw);
		}
        });
    }
    
    public List<Faction> getAllWithRelation(Rel relation) {
	List<Faction> allWithRelation = Lists.newArrayList();
	for (Faction faction : FactionColl.getAll()) {
	    if (getBacking().getRelationTo(faction).equals(relation)) {
		allWithRelation.add(faction);
	    }
	}
	return allWithRelation;
    }
    
    

}