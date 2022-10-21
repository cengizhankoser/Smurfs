package Game;


import javax.swing.ImageIcon;

import java.awt.Image;

public class Oyuncu extends Karakter{
	 private String oyuncuID;
	    private String oyuncuAdi;
	    private String oyuncuTur; 
     public int skor;
public Oyuncu() {
	this.skor=0;

}
public Oyuncu(String oyuncuID,String oyuncuAdi,String oyuncuTur,int skor) {
	
	this.skor=skor;
	this.oyuncuID=oyuncuID;
	this.oyuncuAdi=oyuncuAdi;
	this.oyuncuTur=oyuncuTur;
}
public void setOyuncuAdi(String oyuncuAdi) {
	this.oyuncuAdi=oyuncuAdi;
}
public String getOyuncuAdi() {
	return oyuncuAdi;
}
public void setOyuncuTur(String oyuncuTur) {
	this.oyuncuTur=oyuncuTur;
}
public String getOyuncuTur() {
	return oyuncuTur;
}
public void setOyuncuID(String oyuncuID) {
	this.oyuncuID=oyuncuID;
}
public String getOyuncuID() {
	return oyuncuID;
}
public void setSkor(int skor) {
    this.skor = skor;
}

public int puanGoster()
{
    return this.skor;
}
 
	class GozlukluSirin extends Oyuncu  {
		public Image o1m;
	
		

		public GozlukluSirin(String oyuncuID,String oyuncuAdi,String oyuncuTur,int skor) {
			
             this.skor=skor;
			ImageIcon img = new ImageIcon("C:\\Users\\bjk_c\\OneDrive\\Belgeler\\NetBeansProjects\\Proje_Deneme\\src\\main\\java\\Game\\gozluklusirin.png");
	        o1m = img.getImage();
			
		
		}
		
		public void setSkor(int skor) {
		    this.skor = skor;
		}
        public int getSkor() {
	    return skor;
         }
		public int puanGoster()
		{
		    return this.skor;
		}
		public Image getPlayer() {
	        return o1m;
	    }
	
		public void move(int tx,int ty) {
			lokasyonX += 2*tx;
			lokasyonY += 2*ty;
		}
}
 class TembelSirin extends Oyuncu{
	 public Image o2m;

		

		public TembelSirin(String oyuncuID,String oyuncuAdi,String oyuncuTur,int skor) {
			
			
            this.skor=skor;
			ImageIcon img = new ImageIcon("C:\\Users\\bjk_c\\OneDrive\\Belgeler\\NetBeansProjects\\Proje_Deneme\\src\\main\\java\\Game\\sirintembel.png");
	        o2m = img.getImage();
			
		
		}
		
		public void setSkor(int skor) {
		    this.skor = skor;
		}
		public int getSkor() {
		    return skor;
		}

		public int puanGoster()
		{
		    return this.skor;
		}
		public Image getPlayer() {
	        return o2m;
	    }
		
		public void move(int tx,int ty) {
			lokasyonX += tx;
			lokasyonY += ty;
		
 }}
public class Puan extends Oyuncu {
	
public int puanGoster() {
	return this.skor;
}
 }
}
