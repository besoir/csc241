import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.util.ArrayList;

public class XMLParser extends DefaultHandler {
	private LinkedList<Room> roomList;
	private LinkedList<Creature> creatureList;
	private PC myPC;
	private String roomName, roomDesc, roomSt, roomN, roomE, roomS, roomW, cName, cDesc;
	
	public void startDocument() throws SAXException {
	 	roomList = new LinkedList<Room>();
		creatureList = new LinkedList<Creature>();
	}
	
	public void startElement(String namespaceURI,
							 String localName,
							 String qName,
							 Attributes atts) throws SAXException {
		switch(qName.toLowerCase()) {
			case "room":
				roomName = atts.getValue("name");
				roomDesc = atts.getValue("description");
				roomSt = atts.getValue("state");
				roomN = atts.getValue("north");
				roomE = atts.getValue("east");
				roomS = atts.getValue("south");
				roomW = atts.getValue("west");
				break;
			
			case "animal":
				cName = atts.getValue("name");
				cDesc = atts.getValue("description");
				Animal a = new Animal(cName, cDesc);
				creatureList.append(a);
				cName = cDesc = "";
				break;	
				
			case "npc":
				cName = atts.getValue("name");
				cDesc = atts.getValue("description");
				NPC npc = new NPC(cName, cDesc);
				creatureList.append(npc);
				cName = cDesc = "";
				break;
				
			case "pc":
				cName = atts.getValue("name");
				cDesc = atts.getValue("description");
				PC pc = new PC(cName, cDesc);
				myPC = pc;
				creatureList.append(pc);
				cName = cDesc = "";
				break;
		}
	}
	
	public void endElement(String namespaceURI,
                           String localName,
                           String qName) throws SAXException {
        if(qName.equals("room")) {
			Room ro = new Room(roomName, roomDesc, roomSt, roomN, roomS, roomE, roomW);
			roomList.append(ro);
			for(int i = 1; i <= creatureList.getCount(); i++){
				if(creatureList.get(i).equals(myPC)){
					ro.setPC(myPC);
					ro.addCreature(creatureList.get(i));
					creatureList.get(i).setCreatureRoom(ro);
				} else {
					ro.addCreature(creatureList.get(i));
					creatureList.get(i).setCreatureRoom(ro);
				}
			}
			creatureList.removeAll();
		}		
    }
    
    public void endDocument() throws SAXException {
    	for(int i = 0; i <= roomList.getCount(); i++)
    		roomList.get(i).setPC(myPC);
    	RoomConnector rc = new RoomConnector(roomList);
    	rc.setNeighbors();
    }
    
    public void getRoomInfo(String enteredName) {
    	for(int i = 1; i <= creatureList.getCount(); i++){
    		if(roomList.get(i).getRoomName().equals(enteredName))
    			System.out.println(roomList.get(i).toString());
    	}
    }
    
    public PC getPC() {
    	return myPC;
    }
}