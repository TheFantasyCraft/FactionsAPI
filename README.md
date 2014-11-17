FactionsAPI
===========

A cross version factions API

###Supported Versions
- FactionsUUID 1.6.x
- Factions 2.x

###Usage

The Api is based around the factions class
To get an instance of the class

```java
Factions factions = Factions.getInstance();
if (factions == null) {
   //Factions is not on this server/isn't a supported version
} else {
  //Integration code here
}
```

To get a player's faction info

```java
FPlayer fplayer = factions.getFPlayer(player);
Faction faction = fplayer.getFaction();
FRank rank = fplayer.getRank();
switch (rank) {
       case OWNER :
       	    System.out.prinln("your are an owner");
       	    break;
       case MODERATOR : //Or Officer
       	    System.out.println("You are a moderator/officer");
	    break;
       case MEMBER :
       	    System.out.println("You are a member");
	    break;
       case RECRUIT : //Only FactionsUUID
       	    System.out.println("You are a recruit");
	    break;
       default : throw new RuntimeException();
       }
}
```

To get info from a faction

```java
String name = faction.getName();
String description = faction.getDescription();
boolean safezone = faction.isSafezone();
boolean none = faction.isNone();
boolean warzone = faction.isWarzone();
Faction[] allies = faction.getAllies();
Faction[] enemies = faction.getEnemies();
boolean allyWithWarzone = faction.isAlly(Faction.WARZONE);
boolean allyWithSafezone = faction.isAlly(Faction.SAFEZONE);
FPlayer player = faction.getOwner();
FPlayer[] moderators = faction.getModerators();
FPlayer[] members = faction.getMembers(); //Just the members
FPlayer[] allMembers = faction.getAllMembers();

//Rule The world with all this version independent information

```