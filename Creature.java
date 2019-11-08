public abstract class Creature implements Comparable<Creature> {
	protected Room inRoom;
	protected String name, description;
	//private boolean roomAlert = false;
	
	public Creature(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public abstract void alert(String s, int val);
	public abstract void changePlace(String direction);
	public abstract void changePlaces();

	public int compareTo(Creature c) {
		return this.name.compareTo(c.getCreatureName());
	}

	public void setCreatureRoom(Room r) {
		inRoom = r;
	}
	
	public String getCreatureName() {
		return name;
	}
	
	public void returnRoom() {
		System.out.println(inRoom.getRoomName());
	}
}