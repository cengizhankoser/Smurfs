package Game;

import java.awt.Image;
import javax.swing.ImageIcon;



public class Dusman extends Karakter {
	private String dusmanID;
    private String dusmanAdi;
    private String dusmanTur;
	 public Dusman(){
	
	}
	 
	  
public Dusman(String dusmanID,String dusmanAdi,String dusmanTur) {
	
	this.dusmanID=dusmanID;
	this.dusmanAdi=dusmanAdi;
	this.dusmanTur=dusmanTur;
}
public void setDusmanID(String dusmanID) {
	this.dusmanID=dusmanID;
}
public String getDusmanID() {
	return dusmanID;
}
public void setDusmanAdi(String dusmanAdi) {
	this.dusmanAdi=dusmanAdi;
}
public String getDusmanAdi() {
	return dusmanAdi;
}
public void setDusmanTur(String dusmanTur) {
	this.dusmanTur=dusmanTur;
}
public String getDusmanTur() {
	return dusmanTur;
}
}
class Azman extends Dusman  {
	public Image d1m;



	public Azman(String dusmanID,String dusmanAdi,String dusmanTur) {
		ImageIcon img = new ImageIcon("C:\\Users\\bjk_c\\OneDrive\\Belgeler\\NetBeansProjects\\Proje_Deneme\\src\\main\\java\\Game\\azman.png");
        d1m = img.getImage();
		
	}
	public Image getPlayer() {
        return d1m;
    }
	public void move(int tx,int ty) {
		lokasyonX += tx;
		lokasyonY += ty;
	
}
	
}
class Gargamel extends Dusman{
 public Image d2m;

	

	public Gargamel(String dusmanID,String dusmanAdi,String dusmanTur) {
		ImageIcon img = new ImageIcon("C:\\Users\\bjk_c\\OneDrive\\Belgeler\\NetBeansProjects\\Proje_Deneme\\src\\main\\java\\Game\\gargamel.png");
        d2m = img.getImage();
		
	}
	public Image getPlayer() {
        return d2m;
    }
	public void move(int tx,int ty) {
		lokasyonX += 2*tx;
		lokasyonY += 2*ty;
	}
	}