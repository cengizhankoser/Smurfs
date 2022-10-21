package Game;

import static Game.Board.secim;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;

public class Karakter {

    private String karakterID;
    private String karakter›sim;
    private String karakterTur;
    protected int lokasyonX;
    protected int lokasyonY;
    private static Scanner dosya_a;
    private static Scanner dosya_b;
    public static Block[][] matris_a = new Block[13][11];
    public static Block[][] matris_b = new Block[13][11];
    public int[] a = new int[13];

    public static Block[][] getMatris_b() {
        return matris_b;
    }

    public static void setMatris_b(Block[][] matris_b) {
        Karakter.matris_b = matris_b;
    }
    private static Map map;

    public Karakter() {
        this.karakterID = "Henuz Girilmedi";
        this.karakter›sim = "Henuz Girilmedi";
        this.karakterTur = "Henuz Girilmedi";
        this.lokasyonX = 0;
        this.lokasyonY = 0;
        a[0] = 1;

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {

                matris_a[j][i] = new Block(1, 1, 2.0, a, true);
            }
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {

                matris_b[j][i] = new Block(1, 1, 2.0, a, true);
            }
        }

        map = new Map();
    }

    public Karakter(String karakterID, String Karakter›sim, String KarakterTur) {
        this.karakterID = karakterID;
        this.karakter›sim = karakter›sim;
        this.karakterTur = karakterTur;

    }

    public String getKarakterID() {
        return karakterID;
    }

    public void setKarakterID(String karakterID) {
        this.karakterID = karakterID;
    }

    public String getKarakter›sim() {
        return karakter›sim;
    }

    public void setKarakter›sim(String karakter›sim) {
        this.karakter›sim = karakter›sim;
    }

    public String getKarakterTur() {
        return karakterTur;
    }

    public void setKarakterTur(String karakterTur) {
        this.karakterTur = karakterTur;
    }

    public int getLokasyonX() {
        return lokasyonX;
    }

    public void setLokasyonX(int lokasyonX) {
        this.lokasyonX = lokasyonX;
    }

    public int getLokasyonY() {
        return lokasyonY;
    }

    public static Block[][] getMatris_a() {
        return matris_a;
    }

    public static void setMatris_a(Block[][] matris_a) {
        Karakter.matris_a = matris_a;
    }

    public void setLokasyonY(int lokasyonY) {
        this.lokasyonY = lokasyonY;
    }

    public static int[] minDistance_a() {
        double min_weight = Double.POSITIVE_INFINITY;
        int[] min_index = new int[13];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                if (matris_a[j][i].isVisited() == false && matris_a[j][i].getWeight() <= min_weight) {
                    min_weight = matris_a[j][i].getWeight();
                    min_index[0] = j;
                    min_index[1] = i;
                }
            }
        }

        return min_index;
    }

    public static int[] minDistance_b() {
        double min_weight = Double.POSITIVE_INFINITY;
        int[] min_index = new int[13];

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                if (matris_b[j][i].isVisited() == false && matris_b[j][i].getWeight() <= min_weight) {
                    min_weight = matris_b[j][i].getWeight();
                    min_index[0] = j;
                    min_index[1] = i;
                }
            }
        }

        return min_index;
    }

    public static void Create_a() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                matris_a[j][i] = new Block();
            }

        }

    }

    public static void Create_b() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                matris_b[j][i] = new Block();
            }

        }

    }

    public static void Set_a(int x, int y) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {

                matris_a[j][i].setWeight(Double.POSITIVE_INFINITY);
                if (map.getMap(j, i).equals("0")) {
                    matris_a[j][i].setVisited(true);
                }
                if (map.getMap(j, i).equals("1")) {
                    matris_a[j][i].setVisited(false);
                }
                //BURADA DUSMANIN LOKASYONUNUN WEIGHT'INI 0'A ESITLEMEMIZ GEREKLI (weight = kaynak block'a olan uzaklik)
                //BUNUN ICIN DE DISARIDAN FONKSIYONA DUSMANIN LOKASYONUNU GONDERMEMIZ GEREKLI
            }
        }
        matris_a[x][y].setWeight(0);

    }

    public static void Set_b(int x, int y) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {

                matris_b[j][i].setWeight(Double.POSITIVE_INFINITY);
                if (map.getMap(j, i).equals("0")) {
                    matris_b[j][i].setVisited(true);
                }
                if (map.getMap(j, i).equals("1")) {
                    matris_b[j][i].setVisited(false);
                }
                //BURADA DUSMANIN LOKASYONUNUN WEIGHT'INI 0'A ESITLEMEMIZ GEREKLI (weight = kaynak block'a olan uzaklik)
                //BUNUN ICIN DE DISARIDAN FONKSIYONA DUSMANIN LOKASYONUNU GONDERMEMIZ GEREKLI
            }
        }
        matris_b[x][y].setWeight(0);
    }

    public static void Dijkstra(int x, int y, int a, int b) {
        int[] u; //matris[u[0]][u[1]] is the block we are visiting
        int[] v;
        int[] t = new int[2];

        if (a == 0) {
            while (true) {
                u = minDistance_a();//ziyaret edilmemis weight'i en az olan block'un lokasyonu

                if ((u[0] == x && u[1] == y)) {// hedefe ulasildiysa dur
                    break;
                }

                matris_a[u[0]][u[1]].setVisited(true);
                //yukari asagi sag ve soluna gidince mapin disina cikilip cikilmadigi, cikilmadiysa ziyaret edilip edilmedigi ve
                //uzakliginin bulunulan matris_a +1'den fazla olup olunmadigi kontrol ediliyor. hepsi saglaniyorsa iceri girip uzakligi yeniliyor ve
                //yukari asagi sag ve solundaki blocklarin previous block'unu kendisi yapiyor.
                if ((u[1] - 1) >= 0 && matris_a[u[0]][u[1] - 1].isVisited() == false && ((matris_a[u[0]][u[1]].getWeight() + 1) < ((matris_a[u[0]][u[1] - 1]).getWeight()))) {
                    matris_a[u[0]][u[1] - 1].setWeight(matris_a[u[0]][u[1]].getWeight() + 1);
                    matris_a[u[0]][u[1] - 1].setPrevious(u);
                }
                if (((u[1] + 1)) <= 10 && matris_a[u[0]][u[1] + 1].isVisited() == false && ((matris_a[u[0]][u[1]].getWeight() + 1) < ((matris_a[u[0]][u[1] + 1]).getWeight()))) {
                    matris_a[u[0]][u[1] + 1].setWeight(matris_a[u[0]][u[1]].getWeight() + 1);
                    matris_a[u[0]][u[1] + 1].setPrevious(u);
                }
                if ((u[0] - 1) >= 0 && matris_a[u[0] - 1][u[1]].isVisited() == false && ((matris_a[u[0]][u[1]].getWeight() + 1) < ((matris_a[u[0] - 1][u[1]]).getWeight()))) {
                    matris_a[u[0] - 1][u[1]].setWeight(matris_a[u[0]][u[1]].getWeight() + 1);
                    matris_a[u[0] - 1][u[1]].setPrevious(u);
                }
                if (((u[0] + 1) <= 12) && matris_a[u[0] + 1][u[1]].isVisited() == false && ((matris_a[u[0]][u[1]].getWeight() + 1) < ((matris_a[u[0] + 1][u[1]]).getWeight()))) {
                    matris_a[u[0] + 1][u[1]].setWeight(matris_a[u[0]][u[1]].getWeight() + 1);
                    matris_a[u[0] + 1][u[1]].setPrevious(u);
                }
                // previous'u takip ederek oyuncudan dusmana ulasirken gittigimiz yollari boyayacagiz
                // bunun icin ayri bir fonksiyon olusturmamiz gerekecek eger yanlis dusunmuyorsam
            }

        }
        if (b == 0) {
            while (true) {
                v = minDistance_b();//ziyaret edilmemis weight'i en az olan block'un lokasyonu

                if ((v[0] == x && v[1] == y)) {// hedefe ulasildiysa dur
                    break;
                }

                matris_b[v[0]][v[1]].setVisited(true);
                //yukari asagi sag ve soluna gidince mapin disina cikilip cikilmadigi, cikilmadiysa ziyaret edilip edilmedigi ve
                //uzakliginin bulunulan matris_a +1'den fazla olup olunmadigi kontrol ediliyor. hepsi saglaniyorsa iceri girip uzakligi yeniliyor ve
                //yukari asagi sag ve solundaki blocklarin previous block'unu kendisi yapiyor.
                if ((v[1] - 1) >= 0 && matris_b[v[0]][v[1] - 1].isVisited() == false && ((matris_b[v[0]][v[1]].getWeight() + 1) < ((matris_b[v[0]][v[1] - 1]).getWeight()))) {
                    matris_b[v[0]][v[1] - 1].setWeight(matris_b[v[0]][v[1]].getWeight() + 1);
                    matris_b[v[0]][v[1] - 1].setPrevious(v);
                }
                if (((v[1] + 1)) <= 10 && matris_b[v[0]][v[1] + 1].isVisited() == false && ((matris_b[v[0]][v[1]].getWeight() + 1) < ((matris_b[v[0]][v[1] + 1]).getWeight()))) {
                    matris_b[v[0]][v[1] + 1].setWeight(matris_b[v[0]][v[1]].getWeight() + 1);
                    matris_b[v[0]][v[1] + 1].setPrevious(v);
                }
                if ((v[0] - 1) >= 0 && matris_b[v[0] - 1][v[1]].isVisited() == false && ((matris_b[v[0]][v[1]].getWeight() + 1) < ((matris_b[v[0] - 1][v[1]]).getWeight()))) {
                    matris_b[v[0] - 1][v[1]].setWeight(matris_b[v[0]][v[1]].getWeight() + 1);
                    matris_b[v[0] - 1][v[1]].setPrevious(v);
                }
                if (((v[0] + 1) <= 12) && matris_b[v[0] + 1][v[1]].isVisited() == false && ((matris_b[v[0]][v[1]].getWeight() + 1) < ((matris_b[v[0] + 1][v[1]]).getWeight()))) {
                    matris_b[v[0] + 1][v[1]].setWeight(matris_b[v[0]][v[1]].getWeight() + 1);
                    matris_b[v[0] + 1][v[1]].setPrevious(v);
                }
                /*
                if ((v[1] - 2) >= 0 && matris_b[v[0]][v[1]-2].isVisited() == false && ((matris_b[v[0]][v[1]].getWeight() + 2) < ((matris_b[v[0]][v[1]-2]).getWeight()))) {
                    t[0] = v[0];
                    t[1] = v[1]-1;
                    matris_b[v[0]][v[1]-2].setWeight(matris_b[v[0]][v[1]].getWeight() + 2);
                    matris_b[v[0]][v[1]-2].setPrevious(t);
                }
                if (((v[1] + 2)) <= 10 && matris_b[v[0]][v[1]+2].isVisited() == false && ((matris_b[v[0]][v[1]].getWeight() + 2) < ((matris_b[v[0]][v[1]+2]).getWeight()))) {
                    t[0] = v[0];
                    t[1] = v[1]+1;
                    matris_b[v[0]][v[1]+2].setWeight(matris_b[v[0]][v[1]].getWeight() + 2);
                    matris_b[v[0]][v[1]+2].setPrevious(t);
                }
                if ((v[0] - 2) >= 0 && matris_b[v[0]-2][v[1]].isVisited() == false && ((matris_b[v[0]][v[1]].getWeight() + 2) < ((matris_b[v[0]-2][v[1]]).getWeight()))) {
                    t[0] = v[0]-1;
                    t[1] = v[1];
                    matris_b[v[0]-2][v[1]].setWeight(matris_b[v[0]][v[1]].getWeight() + 2);
                    matris_b[v[0]-2][v[1]].setPrevious(t);
                }
                if (((v[0] + 2) <= 12) && matris_b[v[0]+2][v[1]].isVisited() == false && ((matris_b[v[0]][v[1]].getWeight() + 2) < ((matris_b[v[0]+2][v[1]]).getWeight()))) {
                    t[0] = v[0]+1;
                    t[1] = v[1];
                    matris_b[v[0]+2][v[1]].setWeight(matris_b[v[0]][v[1]].getWeight() + 2);
                    matris_b[v[0]+2][v[1]].setPrevious(t);
                }*/
                // previous'u takip ederek oyuncudan dusmana ulasirken gittigimiz yollari boyayacagiz
                // bunun icin ayri bir fonksiyon olusturmamiz gerekecek eger yanlis dusunmuyorsam
            }
        }
    }

    public static int[][] Path_a(int h_x, int h_y) {//h_x h_y hedef koordinat
        double weight;
        weight = matris_a[h_x][h_y].getWeight();
        int w = (int) weight;
        int prev_1, prev_2;
        int[] prevArray = new int[2];
        int[][] shortestPath = new int[w][2];
        prevArray = matris_a[h_x][h_y].getPrevious();
        prev_1 = prevArray[0];
        prev_2 = prevArray[1];
        for (int i = 0; i < w; i++) {
            if (i != 0) {
                prevArray = matris_a[prev_1][prev_2].getPrevious();
            }
            for (int j = 0; j < 2; j++) {
                prev_1 = prevArray[0];
                prev_2 = prevArray[1];
                if (j == 0) {
                    shortestPath[w - i - 1][j] = prev_1;
                }
                if (j == 1) {
                    shortestPath[w - i - 1][j] = prev_2;
                }

            }

        }

        return shortestPath;
    }

    public static int[][] Path_b(int h_x, int h_y) {//h_x h_y hedef koordinat
        double weight;
        weight = matris_b[h_x][h_y].getWeight();
        int w = (int) weight;
        int prev_1, prev_2;
        int[] prevArray = new int[2];
        int[][] shortestPath = new int[w][2];
        prevArray = matris_b[h_x][h_y].getPrevious();
        prev_1 = prevArray[0];
        prev_2 = prevArray[1];
        for (int i = 0; i < w; i++) {
            if (i != 0) {
                prevArray = matris_b[prev_1][prev_2].getPrevious();
            }
            for (int j = 0; j < 2; j++) {
                prev_1 = prevArray[0];
                prev_2 = prevArray[1];
                if (j == 0) {
                    shortestPath[w - i - 1][j] = prev_1;
                }
                if (j == 1) {
                    shortestPath[w - i - 1][j] = prev_2;
                }

            }

        }

        return shortestPath;
    }

    public static int Size_a(int h_x, int h_y) {
        double weight;
        weight = matris_a[h_x][h_y].getWeight();
        int w = (int) weight;
        return w;
    }

    public static int Size_b(int h_x, int h_y) {
        double weight;
        weight = matris_b[h_x][h_y].getWeight();
        int w = (int) weight;
        return w;
    }

}
