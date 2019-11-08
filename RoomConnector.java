import java.util.ArrayList;
public class RoomConnector {
	private LinkedList<Room> roomList;
	private Room r;
	public RoomConnector(LinkedList<Room> stuff) {
		roomList = stuff;
	}
	public void setNeighbors() {
    	for(int i = 0; i <= roomList.getCount(); i++) {
    		for(int j = 0; j <= roomList.getCount(); j++) {
    			if(roomList.get(i).getRoomName().equals(roomList.get(j).getSouth())) {
    				roomList.get(i).setNorth(roomList.get(j));
    				roomList.get(j).setSouth(roomList.get(i));
    			} else if(roomList.get(i).getRoomName().equals(roomList.get(j).getWest())) {
    				roomList.get(i).setEast(roomList.get(j));
    				roomList.get(j).setWest(roomList.get(i));
    			} 
    		}
    	}		
    }
}