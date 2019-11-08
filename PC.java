import java.util.Scanner;
public class PC extends Creature {
	private int respect = 40;
	//Win >= 80 || Lose<=0
	
	public PC(String name, String description) {
		super(name, description);
	}

	public void alert(String s, int val) {}
	
	public void changePlaces() {}
	
	public void changePlace(String direction) {
		switch(direction) {
			case "north":
				if(super.inRoom.getRNorth() != null) {
					//super.inRoom.setPC(null);
					super.inRoom = super.inRoom.remove(this, "north");
					super.inRoom.setPC(this);
				} else {
					System.out.println("Not a valid move");
				}
				break;
			case "south":
				try{
					if(super.inRoom.getRSouth() != null) {
						//super.inRoom.setPC(null);
						super.inRoom = super.inRoom.remove(this, "south");
						super.inRoom.setPC(this);
					} else {
						System.out.println("Not a valid move.");
					}
				} catch(NullPointerException e) {
					System.out.println("Not a valid move.");
				}
				break;
			case "east":
					if(super.inRoom.getREast() != null) {
						//super.inRoom.setPC(null);
						super.inRoom = super.inRoom.remove(this, "east");
						super.inRoom.setPC(this);
					} else {
						System.out.println("Not a valid move.");
					}
				break;
			case "west":
					if(super.inRoom.getRWest() != null) {
						//super.inRoom.setPC(null);
						super.inRoom = super.inRoom.remove(this, "west");
						super.inRoom.setPC(this);
					} else {
						System.out.println("Not a valid move.");
					}
				break;
			default:
				break;
		}
	}
	
	public void play(Scanner s) {
		String cName = "";
		Scanner sc = s;
		String command = sc.nextLine();
		if (command.contains(":")) {
			String[] cNameAndCommand = command.split(":");
			cName = cNameAndCommand[0];
			command = cNameAndCommand[1];
		}
		
		while(!(command.equalsIgnoreCase("quit")) && (respect < 80) && (respect > 0)) {
			switch(command.toLowerCase()) {
				case "help":
					System.out.println(
						"look: prints the information of the room the player is currently in."
						+ "\n" +
						"Creature name:clean: makes a creature change the state of the room to either half-dirty or clean."
						+ "\n" +
						"Creature name:dirty: makes a creature change the state of the room to either half-dirty or dirty."
						+ "\n" +
						"clean: makes the player change the state of the room to either half-dirty or clean."
						+ "\n" +
						"dirty: makes the player change the state of the room to either half-dirty or dirty."
						+ "\n" +
						"Creature name:north: moves the Creature to the room north of the room they are currently in."
						+ "\n" +
						"Creature name:south: moves the Creature to the room south of the room they are currently in."
						+ "\n" +
						"Creature name:east: moves the Creature to the room east of the room they are currently in."
						+ "\n" +
						"Creature name:west: moves the Creature to the room west of the room they are currently in."
						+ "\n" +
						"score: prints the current score.");
					break;
				
				case "look":
					System.out.println(inRoom);
					break;
				
				case "clean":
					switch(super.inRoom.getState()) {
						case HALFDIRTY:
						System.out.println(super.inRoom.getRoomName() + " room cleaned.");
							LinkedList<Creature> arrg = super.inRoom.getCreatureList();
							for(int i = arrg.getCount(); i >= 0; i--) {
								if(arrg.get(i).getCreatureName().equals(cName))
									arrg.get(i).alert("clean", 3);
								else
									arrg.get(i).alert("clean", 1);
							}
							super.inRoom.changeState("clean");
							break;
						case DIRTY:
							System.out.println(super.inRoom.getRoomName() + " room cleaned.");
							LinkedList<Creature> arrs = super.inRoom.getCreatureList();
							for(int i = arrs.getCount(); i >= 0; i--) {
								if(arrs.get(i).getCreatureName().equals(cName))
									arrs.get(i).alert("hdcleaned", 3);
								else
									arrs.get(i).alert("hdcleaned", 1);
							}
							super.inRoom.changeState("half-dirty");
							break;
						case CLEAN:
							System.out.println("This room is already clean.");
							break;
						default:
							System.out.println("Not a valid command.");
							break;
					}
					break;
				
				case "dirty":
					switch(super.inRoom.getState()) {
						case DIRTY:
							System.out.println("This room is already dirty");
							break;
						case HALFDIRTY:
							System.out.println(super.inRoom.getRoomName() + " room dirtied.");
							LinkedList<Creature> arr = super.inRoom.getCreatureList();
							for(int i = arr.getCount(); i >= 0; i--) {
								if(arr.get(i).getCreatureName().equals(cName))
									arr.get(i).alert("dirty", 3);
								else
									arr.get(i).alert("dirty", 1);
							}
							super.inRoom.changeState("dirty");
							break;
						case CLEAN:
							System.out.println(super.inRoom.getRoomName() + " room dirtied.");
							LinkedList<Creature> arre = super.inRoom.getCreatureList();
							for(int i = arre.getCount(); i >= 0; i--) {
								if(arre.get(i).getCreatureName().equals(cName))
									arre.get(i).alert("hddirtied", 3);
								else
									arre.get(i).alert("hddirtied", 1);
							}
							super.inRoom.changeState("half-dirty");
							break;
						default:
							System.out.println("Not a valid command.");
							break;
					}
					break;
				
				case "north":
					if(cName.equals(this.name)){
						this.changePlace("north");
					} else {
						super.inRoom.getCreatureByName(cName).changePlace("north");
					}
					break;
				
				case "south":
					if(cName.equals(this.name)){
						this.changePlace("south");
					} else {
						super.inRoom.getCreatureByName(cName).changePlace("south");
					}
					break;
				
				case "east":
					if(cName.equals(this.name)){
						this.changePlace("east");
					} else {
						super.inRoom.getCreatureByName(cName).changePlace("east");
					}
					break;
				
				case "west":
					if(cName.equals(this.name)){
						this.changePlace("west");
					} else {
						super.inRoom.getCreatureByName(cName).changePlace("west");
					}
					break;
				case "score":
					printScore();
					break;
				default:
					System.out.println("Not a valid command.");
					break;
			}
			System.out.println("Enter a command: ");
			command = sc.nextLine();
			if (command.contains(":")) {
				String[] cNameAndCommand = command.split(":");
				cName = cNameAndCommand[0];
				command = cNameAndCommand[1];
			} else {
				command = command;
				cName = null;
			}
		} 
		if(!(command.equals("quit")))
			System.out.println(respect);
		if(respect <= 0)
			System.out.println("The Creatures don't respect you. You lose!");
		if(respect >= 80)
			System.out.println("You win!");
		if(command.equals("quit"));
			System.out.println("Your score was: " + respect + "\n" + "Goodbye, thanks for playing!");
	}

	public void increaseScore(int val) {
		respect += val;
	}
	
	public void decreaseScore(int val) {
		respect -= val;
	}
	
	public void printScore() {
		System.out.println(respect);
	}

	@Override
	public String toString() {
		return "PC: " + name + " " + description;
	}
}