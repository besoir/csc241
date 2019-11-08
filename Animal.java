public class Animal extends Creature {
	//Wants rooms to be clean
	//Will only stay in room if clean/half-dirty
	public Animal(String name, String description) {
		super(name, description);
	}
	
	public void alert(String s, int val) {
		if(s.equals("dirty")) {
			growl(val);
			changePlaces();
		} else if(s.equals("hddirtied")) {
			growl(val);
		} else {
			lickFace(val);
		}
	}
	
		public void changePlace(String direction) {
		switch(direction) {
			case "north":
				try{
				switch(super.inRoom.getRNorth().getState()) {
				case CLEAN:
					super.inRoom = super.inRoom.remove(this, "north");
					System.out.println(this.name + " the Animal goes North.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "north");
					System.out.println(this.name + " the Animal goes North.");
					break;
				case DIRTY:
					inRoom = inRoom.remove(this, "north");
					super.inRoom.changeState("clean");
					LinkedList<Creature> arr = super.inRoom.getCreatureList();
						for(int i = arr.getCount(); i > 0; i--)
							arr.get(i).alert("hdcleaned", 1);
					//BST<Creature> newBST = super.inRoom.getBST();
					//BSTIterator<Creature> it = newBST.iterator();
				default:
					break;
				}
				} catch(NullPointerException e) {
					changePlaces();
				}
				break;
			case "south":
				try{
				switch(super.inRoom.getRSouth().getState()) {
				case CLEAN:
					super.inRoom = super.inRoom.remove(this, "south");
					System.out.println(this.name + " the Animal goes South.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "south");
					System.out.println(this.name + " the Animal goes South.");
					break;
				case DIRTY:
					super.inRoom.changeState("clean");
					inRoom = inRoom.remove(this, "south");
					LinkedList<Creature> arr = super.inRoom.getCreatureList();
						for(int i = arr.getCount(); i > 0; i--)
							arr.get(i).alert("hdcleaned", 1);
				default:
					break;
				}
				} catch(NullPointerException e) {
					changePlaces();
				}
				break;
			case "east":
				try{
				switch(super.inRoom.getREast().getState()) {
				case CLEAN:
					super.inRoom = super.inRoom.remove(this, "east");
					System.out.println(this.name + " the Animal goes East.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "east");
					System.out.println(this.name + " the Animal goes East.");
					break;
				case DIRTY:
					super.inRoom.changeState("clean");
					inRoom = inRoom.remove(this, "east");
					LinkedList<Creature> arr = super.inRoom.getCreatureList();
						for(int i = arr.getCount(); i > 0; i--)
							arr.get(i).alert("hdcleaned", 1);
				default:
					break;
				}
				} catch(NullPointerException e) {
					changePlaces();
				}
				break;
			case "west":
				try{
				switch(super.inRoom.getRWest().getState()) {
				case CLEAN:
					super.inRoom = super.inRoom.remove(this, "west");
					System.out.println(this.name + " the Animal goes West.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "west");
					System.out.println(this.name + " the Animal goes West.");
					break;
				case DIRTY:
					super.inRoom.changeState("clean");
					inRoom = inRoom.remove(this, "west");
					LinkedList<Creature> arr = super.inRoom.getCreatureList();
						for(int i = arr.getCount(); i > 0; i--)
							arr.get(i).alert("hdcleaned", 1);
				default:
					break;
				}
				} catch(NullPointerException e) {
					changePlaces();
				}
				break;
				default:
					break;
			}
		}
	
	public void changePlaces() {		
		try{
			switch(super.inRoom.getRNorth().getState()) {
				case CLEAN:
					super.inRoom = super.inRoom.remove(this, "north");
					System.out.println(this.name + " the Animal goes North.");
					break;
				case HALFDIRTY:
					super.inRoom = super.inRoom.remove(this, "north");
					System.out.println(this.name + " the Animal goes North.");
					break;
				default:
					try{
					switch(super.inRoom.getRSouth().getState()) {
						case CLEAN:
							super.inRoom = super.inRoom.remove(this, "south");
							System.out.println(this.name + " the Animal goes South.");
							break;
						case HALFDIRTY:
							super.inRoom = super.inRoom.remove(this, "south");
							System.out.println(this.name + " the Animal goes South.");
							break;
						default:
							try {
								switch(super.inRoom.getREast().getState()) {
								case CLEAN:
									super.inRoom = super.inRoom.remove(this, "east");
									System.out.println(this.name + " the Animal goes East.");
									break;
								case HALFDIRTY:
									super.inRoom = super.inRoom.remove(this, "east");
									System.out.println(this.name + " the Animal goes East.");
									break;
								default:
									try {
										switch(super.inRoom.getRWest().getState()) {
										case CLEAN:
										super.inRoom = super.inRoom.remove(this, "west");
										System.out.println(this.name + " the Animal goes West.");
										break;
									case HALFDIRTY:
										super.inRoom = super.inRoom.remove(this, "west");
										System.out.println(this.name + " the Animal goes West.");
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
			" the Animal growls and leaves the house through the roof because all adjacet rooms are full.");
		}
	}
	
	public void growl(int val) {
		//Reacts when angry which is when the room is changed to dirty
		//This is called is changePlaces()
		//Decreases respect by 1
		System.out.println(this.name + " the Animal growls");
		super.inRoom.getThatPC().decreaseScore(val);
	}
	
	public void lickFace(int val) {
		//Reacts when happy which is when the room is changed to clean
		//This is called when alert is dirty
		//Increases the respect by 1
		System.out.println(this.name + " the Animal licks your face.");
		super.inRoom.getThatPC().increaseScore(val);
	}
	
	@Override
	public String toString() {
		return "Animal: " + name + " " + description;
	}
}