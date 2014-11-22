package techcable.minecraft.factionsapi;

public enum FRank {
    OWNER {
	@Override
	public boolean isOutrank(FRank other) {
	    return true;
	}
    },
    MODERATOR {
	@Override
	public boolean isOutrank(FRank other) {
	    if (other.equals(FRank.OWNER)) {
		return false;
	    } else { 
		return true;
	    }
	}
    },
    MEMBER {
       @Override
       public boolean isOutrank(FRank other) {
	   if (other.equals(FRank.RECRUIT)) {
	       return true;
	   } else {
	       return false;
	   }
       }
    
    },
    RECRUIT; //Never returns true so don't need to override
    
    //Override if ever return true
    public boolean isOutrank(FRank other) {
	return false;
    }
}