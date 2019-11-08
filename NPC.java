public class NPC extends Creature {
	//Wants rooms to be dirty
	//Will only stay in room in dirty/half-dirty
	public NPC(String name, String description) {
		super(name, description);
	}
	
	//Moves the NPC to another room based on what direction was commanded
	public void changePlace(String direction) {
		switch(direction) {
			case "north":
				if(super.inRoom.getRNorth() != null) {
				switch(super.inRoom.getRNorth().getState()) {
				case DIRTY:
					super.inRoom = super.inRoom.remove(this, "north");
					System.out.println(this.name + " the NPC goes North.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "north");
					System.out.println(this.name + " the NPC goes North.");
					break;
				case CLEAN:
					super.inRoom.changeState("dirty");
					inRoom = inRoom.remove(this, "north");
					LinkedList<Creature> arrs = super.inRoom.getCreatureList();
						for(int i = arrs.getCount(); i >= 0; i--)
							arrs.get(i).alert("hddirtied", 1);
					break;
				default:
					break;
				}
			} else 
				changePlaces();
				break;
			case "south":
				if(super.inRoom.getRSouth() != null) {
				switch(super.inRoom.getRSouth().getState()) {
				case DIRTY:
					super.inRoom = super.inRoom.remove(this, "south");
					System.out.println(this.name + " the NPC goes South.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "south");
					System.out.println(this.name + " the NPC goes South.");
					break;
				case CLEAN:
					super.inRoom.changeState("dirty");
					inRoom = inRoom.remove(this, "south");
					LinkedList<Creature> arrf = super.inRoom.getCreatureList();
						for(int i = arrf.getCount(); i >= 0; i--)
							arrf.get(i).alert("hddirtied", 1);
					break;
				default:
					break;
				}
				} else
					changePlaces();
				break;
			case "east":
				if(super.inRoom.getREast() != null) {
				switch(super.inRoom.getREast().getState()) {
				case DIRTY:
					super.inRoom = super.inRoom.remove(this, "east");
					System.out.println(this.name + " the NPC goes East.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "east");
					System.out.println(this.name + " the NPC goes East.");
					break;
				case CLEAN:
					super.inRoom.changeState("dirty");
					inRoom = inRoom.remove(this, "east");
					LinkedList<Creature> arrn = super.inRoom.getCreatureList();
						for(int i = arrn.getCount(); i >= 0; i--)
							arrn.get(i).alert("hddirtied", 1);
				default:
					break;
				}
				} else
					changePlaces();
				break;
			case "west":
				if(super.inRoom.getRWest() != null) {
				switch(super.inRoom.getRWest().getState()) {
				case DIRTY:
					super.inRoom = super.inRoom.remove(this, "west");
					System.out.println(this.name + " the NPC goes West.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "west");
					System.out.println(this.name + " the NPC goes West.");
					break;
				case CLEAN:
					super.inRoom.changeState("dirty");
					inRoom = inRoom.remove(this, "west");
					LinkedList<Creature> arrk = super.inRoom.getCreatureList();
						for(int i = arrk.getCount(); i >= 0; i--)
							arrk.get(i).alert("hddirtied", 1);
				default:
					break;
				}
				} else
					changePlaces();
				break;
			default:
				break;
			}
		}
	
	public void alert(String s, int val) {
		if(s.equals("clean")) {
			grumble(val);
			changePlaces();
		} else if(s.equals("hdcleaned")){
			grumble(val);
		} else {
			smile(val);
		}
			
	}
	//This is called when a Room's state is changed
	public void changePlaces() {		
		try{
			switch(super.inRoom.getRNorth().getState()) {
				case CLEAN:
					super.inRoom = super.inRoom.remove(this, "north");
					System.out.println(this.name + " the NPC goes North.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "north");
					System.out.println(this.name + " the NPC goes North.");
					break;
				default:
					try{
					switch(super.inRoom.getRSouth().getState()) {
						case CLEAN:
							super.inRoom = super.inRoom.remove(this, "south");
							System.out.println(this.name + " the NPC goes South.");
							break;
						case HALFDIRTY:
							super.inRoom = super.inRoom.remove(this, "south");
							System.out.println(this.name + " the NPC goes South.");
							break;
						default:
							try {
								switch(super.inRoom.getREast().getState()) {
								case CLEAN:
									super.inRoom = super.inRoom.remove(this, "east");
									System.out.println(this.name + " the NPC goes East.");
									break;
								case HALFDIRTY:
									super.inRoom = super.inRoom.remove(this, "east");
									System.out.println(this.name + " the NPC goes East.");
									break;
								default:
									try {
										switch(super.inRoom.getRWest().getState()) {
										case CLEAN:
										super.inRoom = super.inRoom.remove(this, "west");
										System.out.println(this.name + " the NPC goes West.");
										break;
									case HALFDIRTY:
										super.inRoom = super.inRoom.remove(this, "west");
										System.out.println(this.name + " the NPC goes West.");
										break;
									default:
										break;
								}
									} catch (NullPointerException l) {}
								break;
							}
							} catch (NullPointerException r) {}
							break;
						}
				} catch (NullPointerException s) {}
				break;
			}
		} catch(NullPointerException e) {
			super.inRoom = super.inRoom.remove(this, null);
			System.out.println(this.name + 
			" the NPC growls and leaves the house through the roof because all adjacet rooms are full.");
		}
	}
	
	public void grumble(int val) {
		System.out.println(this.name + " the NPC grumbles");
		super.inRoom.getThatPC().decreaseScore(val);
	}
	
	public void smile(int val) {
		System.out.println(this.name + " the NPC smiles.");
		super.inRoom.getThatPC().increaseScore(val);
	}
	
	@Override
	public String toString() {
		return "NPC: " + name + " " + description;
	}
}