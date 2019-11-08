import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		PC user;
		Scanner sc = new Scanner(System.in);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		
		System.out.println("Enter the name of the file: ");
		String fileName = sc.nextLine();
		
		try{
			InputStream xmlInput = new FileInputStream(fileName);
			SAXParser saxParser = spf.newSAXParser();
			XMLParser xmlp = new XMLParser();
			saxParser.parse(xmlInput, xmlp);

        	System.out.println("Enter a command:");
        	user = xmlp.getPC();
        	user.play(sc);
		}
		catch(SAXException|ParserConfigurationException|IOException e){
            e.printStackTrace();
        }
	}
}