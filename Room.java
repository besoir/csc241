public class Room {
	private Room roomN, roomE, roomS, roomW, ceiling;
	private String roomName, description, roomSN, roomSS, roomSE, roomSW, stringToEnum;
	private State state;
	private BST<Creature> creatureList;
	private LinkedList<Creature> ll;
	private PC pc;
	
	public Room(String roomName, String description, String stringToEnum, String roomSN,
				String roomSS, String roomSE, String roomSW) {
		creatureList = new BST<Creature>();
		ll = new LinkedList<Creature>();
		this.roomName = roomName;
		this.description = description;
		this.roomSN = roomSN;
		this.roomSS = roomSS;
		this.roomSE = roomSE;
		this.roomSW = roomSW;

		changeState(stringToEnum);
	}
	
	public enum State {
		CLEAN, DIRTY, HALFDIRTY;
	}
	
	public BST<Creature> getBST() {
		return creatureList;
	}
	public void addCreature(Creature c) {
		if((creatureList.getCount() < 10)) {
			creatureList.insert(c);
			ll.append(c);
		} else {
			System.out.println("Room full.");
		}
	}
	
	public void changeState(String changedState) {
		switch(changedState) {
			case "clean": 
				this.state = State.CLEAN;
				break;
			case "half-dirty":
				this.state = State.HALFDIRTY;
				break;
			case "dirty":
				this.state = State.DIRTY;
				break;
			default:
				System.out.println("Invalid Input");
				break;
		}
	}
	
	public void setPC(PC pc) {
		this.pc = pc;
	}
	
	public PC getThatPC() {
		return pc;
	}
	
	public Creature getCreatureByName(String cName) {
		Animal a = new Animal(cName, null);
		Creature cToReturn = creatureList.search(a);
		return cToReturn;
	}
	public Room remove(Creature c, String dName) {
		Room roomToReturn = null;
		if(ll.remove(c)) {
			creatureList.remove(c);
			roomToReturn = this.getRoomByOrientation(dName);
			roomToReturn.addCreature(c);
		}
		return roomToReturn;
	}
	
	public LinkedList<Creature> getCreatureList() {
		return ll;
	}
	
	public Room getRoomByOrientation(String dName) {
		Room roomToReturn = null; 
		String request = dName;
		switch(request){
			case "north":
				if(roomN != null)
					roomToReturn = roomN;
				else 
					System.out.println("No room to the North.");
				break;
			case "south":
				if(roomS != null)
					roomToReturn = roomS;
				else 
					System.out.println("No room to the South.");
				break;
			case "east":
				if(roomE != null)
					roomToReturn = roomE;
				else 
					System.out.println("No room to the East.");
				break;
			case "west":
				if(roomW != null)
					roomToReturn = roomW;
				else 
					System.out.println("No room to the West.");
				break;
			default:
				roomToReturn = null;
				break;
		}
		return roomToReturn;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public String getNorth() {
		return roomSN;
	}
	
	public String getSouth() {
		return roomSS;
	}
	
	public String getEast() {
		return roomSE;
	}
	
	public String getWest() {
		return roomSW;
	}
	
	public void setNorth(Room r){
		roomN = r;
	}
	
	public void setSouth(Room s) {
		roomS = s;
	}
	
	public void setEast(Room t) {
		roomE = t;
	}
	
	public void setWest(Room u) {
		roomW = u;
	}
	
	public Room getRNorth() {
		return this.roomN;
	}
	
	public Room getRSouth() {
		return this.roomS;
	}
	
	public Room getREast() {
		return this.roomE;
	}
	
	public Room getRWest() {
		return this.roomW;
	}
	
	public State getState() {
		return state;
	}
	
	public int getCount() {
		return ll.getCount();
	}
	
	@Override
	public String toString() {
		String output = this.roomName + ", " + this.description + ", " + this.state
		+ "\n North: " + this.roomSN + " South: " + this.roomSS + " East: " + this.roomSE
		+ " West: " + this.roomSW;
		/*
		for (int i = 1; i <= ll.getCount(); i++) {
			try{
				output = output + " " + ll.get(i);
			} catch(NullPointerException k) {
				System.out.println(k);
			}
		}
		*/
		creatureList.printInOrder();
		//creatureList.printTree();
		return output;
	}
}