package Game;
import java.util.*;
import java.io.*;
public class Map {
	private Scanner map;
	private String Map[]=new String[11];
	
public Map() {
	openFile();
	readFile();
	closeFile();
	
}

public String getMap(int x,int y) {
		String index=Map[y].substring(x, x+1);
		return index;
	}
public void openFile() {
	try {
	map= new Scanner(new File("C:\\Users\\bjk_c\\OneDrive\\Belgeler\\NetBeansProjects\\Proje_Deneme\\src\\main\\java\\Game\\map.txt"));
	}catch(Exception e) {
		System.out.println("Error loading file");
	}
}
public void readFile() {
while(map.hasNext()) {
	for( int i=0;i<11;i++) {
		Map[i]=map.next();
	}
}
}
public void closeFile() {
	map.close();
}
}
