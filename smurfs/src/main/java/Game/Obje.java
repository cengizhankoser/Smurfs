package Game;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Obje{
	
	class Altin{
	public Image altin_resim;
	public	int altin_no;
	
	public Altin() {
		
		this.altin_no=5;
	    ImageIcon img = new ImageIcon("C:\\Users\\bjk_c\\OneDrive\\Belgeler\\NetBeansProjects\\Proje_Deneme\\src\\main\\java\\Game\\gold.png");
	    altin_resim= img.getImage();
		}
				
		public int getAltinNo() {
			return altin_no;
		}
				
		public Image getPlayer() {
			        return altin_resim;
		}
			
	
	 }
	class Mantar{
		public Image mantar_resim;
	
	public Mantar() {
		
	ImageIcon img = new ImageIcon("C:\\Users\\bjk_c\\OneDrive\\Belgeler\\NetBeansProjects\\Proje_Deneme\\src\\main\\java\\Game\\mantar.png");
	   mantar_resim= img.getImage();
	}
				
    public Image getPlayer() {
	return mantar_resim;
	}
			
	
	}
}
