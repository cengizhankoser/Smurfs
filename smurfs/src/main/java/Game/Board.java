package Game;

import Game.Obje;

import Game.Karakter;
import Game.Block;
import Game.Oyuncu;
import Game.Dusman;
import Game.Oyuncu.TembelSirin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener, KeyListener {

    public JFrame frame;
    private JTextField txtBirimIlerler;
    private JTextField txtImportJavaawtcolorImport;
    private JTextField txtAzmanaDokunursaOyuncu;
    private JTextField txtGargameleDokunursa;
    private JTextField txtLtfenOyuncunuzuSein;
    private Timer timer;
    private Map map;
    public Oyuncu o;
    public Oyuncu.GozlukluSirin o1;
    public Oyuncu.TembelSirin o2;
    public Azman d1;
    public Gargamel d2;
    public Obje obje;
    public Obje.Altin altin;
    public Obje.Mantar mantar;
    public Image sirine;
    Random random;
    static int secim, w;

    public int[] dizi1 = new int[15];
    public int[] dizi2 = new int[15];
    public int[] r2 = new int[4];
    public int[] m2 = new int[4];
    static int size_a;
    static int a, b, a_ka, a_kb, a_kc, a_kd, b_ka, b_kb, b_kc, b_kd, k, no, r1, m1, z1, z2, count, t;
    static int[][] shortestPath_a = new int[13][2];
    static int[][] shortestPath_b = new int[13][2];
    static int size_b;

    public Board() {
        initialize();
        addKeyListener(this);
        setFocusable(true);
        //1 inaktif 0 aktif
        //ayný anda  a , b, a_ka ,b_ka açýk olursa 2 düþman da ayný yerde olur, bu gibi durumlardan kaçýnmalýyýz
        a = 0;
        b = 0;
        a_ka = 1;
        a_kb = 0;
        a_kc = 1;
        a_kd = 1;
        b_ka = 1;
        b_kb = 1;
        b_kc = 0;
        b_kd = 1;
        //size_b=2;

        o = new Oyuncu();
        map = new Map();

        sirine = new ImageIcon("C:\\Users\\bjk_c\\OneDrive\\Belgeler\\NetBeansProjects\\Proje_Deneme\\src\\main\\java\\Game\\sirine.png").getImage();
        o1 = o.new GozlukluSirin("111", "GozlukluSirin", "Oyuncu", 20);
        o2 = o.new TembelSirin("111", "TembelSirin", "Oyuncu", 20);
        d1 = new Azman("222", "Azman", "Düþman");
        d2 = new Gargamel("221", "Gargamel", "Düþman");
        o1.setLokasyonX(6);
        o1.setLokasyonY(5);
        o2.setLokasyonX(6);
        o2.setLokasyonY(5);

        if (a == 0) {
            if (a_ka == 0) {
                d1.setLokasyonX(3);
                d1.setLokasyonY(1);

            }
            if (a_kb == 0) {
                d1.setLokasyonX(10);
                d1.setLokasyonY(1);
            }
            if (a_kc == 0) {
                d1.setLokasyonX(1);
                d1.setLokasyonY(5);
            }
            if (a_kd == 0) {
                d1.setLokasyonX(3);
                d1.setLokasyonY(9);
            }

        }
        if (b == 0) {
            if (b_ka == 0) {
                d2.setLokasyonX(3);
                d2.setLokasyonY(1);

            }
            if (b_kb == 0) {
                d2.setLokasyonX(10);
                d2.setLokasyonY(1);
            }
            if (b_kc == 0) {
                d2.setLokasyonX(1);
                d2.setLokasyonY(5);
            }
            if (b_kd == 0) {
                d2.setLokasyonX(3);
                d2.setLokasyonY(9);
            }

        }
        k = 0;
        count = -1;
        no = -1;
        random = new Random();
        obje = new Obje();
        altin = obje.new Altin();
        mantar = obje.new Mantar();

        //System.out.println(Karakter.getMatris_a());
        timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                k++;

                repaint();

            }
        });
        timer.start();

    }

    public void puan() {

        if (o1.lokasyonX == d1.lokasyonX && o1.lokasyonY == d1.lokasyonY) {
            o1.setSkor(o1.puanGoster() - 5);
        } else if (o1.lokasyonX == d2.lokasyonX && o1.lokasyonY == d2.lokasyonY) {
            o1.setSkor(o1.puanGoster() - 15);
        } else if (o2.lokasyonX == d1.lokasyonX && o2.lokasyonY == d1.lokasyonY) {
            o2.setSkor(o2.puanGoster() - 5);
        } else if (o2.lokasyonX == d2.lokasyonX && o2.lokasyonY == d2.lokasyonY) {
            o2.setSkor(o2.puanGoster() - 15);
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 13; x++) {
                if (x == 3 && y == 0) {
                    g.setColor(Color.pink);
                    //x*(size+space),y*(size+space),size,size
                    g.fillRect(x * 52, y * 52, 50, 50);
                    g.setColor(Color.black);
                    g.drawString("A", 175, 25);

                } else if (x == 10 && y == 0) {

                    g.setColor(Color.pink);
                    g.fillRect(x * 52, y * 52, 50, 50);
                    g.setColor(Color.black);
                    g.drawString("B", 540, 25);

                } else if (x == 0 && y == 5) {
                    g.setColor(Color.pink);
                    g.fillRect(x * 52, y * 52, 50, 50);
                    g.setColor(Color.black);
                    g.drawString("C", 20, 285);
                } else if (x == 3 && y == 10) {
                    g.setColor(Color.pink);
                    g.fillRect(x * 52, y * 52, 50, 50);
                    g.setColor(Color.black);
                    g.drawString("D", 175, 545);
                } else if (map.getMap(x, y).equals("1")) {
                    g.setColor(Color.white);
                    g.fillRect(x * 52, y * 52, 50, 50);
                } else if (map.getMap(x, y).equals("0")) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(x * 52, y * 52, 50, 50);
                }
                g.drawImage(sirine, 13 * 52, 7 * 52, null);

            }
        }
        for (t = 0; t <= 1000; t++) {

            while (count < 14) {
                if (!(map.getMap(z1 = random.nextInt(12), z2 = random.nextInt(10)).equals("0"))) {
                    count++;
                    dizi1[count] = z1;
                    dizi2[count] = z2;

                }
            }
            while (no < 3) {
                if (!(map.getMap(r1 = random.nextInt(12), m1 = random.nextInt(10)).equals("0"))) {
                    no++;
                    r2[no] = r1;
                    m2[no] = m1;

                }
            }

            if (k >= 20 + 30 * t && k <= 30 + 30 * t) {

                if (t == 0) {
                    for (int i = 0; i <= (altin.getAltinNo() - 1); i++) {
                        g.drawImage(altin.getPlayer(), dizi1[i] * 52, dizi2[i] * 52, null);
                        if (o1.lokasyonX == dizi1[i] && o1.lokasyonY == dizi2[i]) {
                            o1.setSkor(o1.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        } else if (o2.lokasyonX == dizi1[i] && o2.lokasyonY == dizi2[i]) {
                            o2.setSkor(o2.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        }

                    }
                } else if (t == 1) {
                    for (int i = 5; i <= (altin.getAltinNo() + 4); i++) {
                        g.drawImage(altin.getPlayer(), dizi1[i] * 52, dizi2[i] * 52, null);
                        if (o1.lokasyonX == dizi1[i] && o1.lokasyonY == dizi2[i]) {
                            o1.setSkor(o1.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        } else if (o2.lokasyonX == dizi1[i] && o2.lokasyonY == dizi2[i]) {
                            o2.setSkor(o2.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        }

                    }
                } else if (t == 2) {
                    for (int i = 10; i <= (altin.getAltinNo() + 9); i++) {
                        g.drawImage(altin.getPlayer(), dizi1[i] * 52, dizi2[i] * 52, null);
                        if (o1.lokasyonX == dizi1[i] && o1.lokasyonY == dizi2[i]) {
                            o1.setSkor(o1.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        } else if (o2.lokasyonX == dizi1[i] && o2.lokasyonY == dizi2[i]) {
                            o2.setSkor(o2.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        }

                    }
                }
            }
            if (k >= 40 + 54 * t && k <= 54 + 54 * t) {

                if (t == 0) {
                    g.drawImage(mantar.getPlayer(), r2[t] * 52, m2[t] * 52, null);
                    if (o1.lokasyonX == r2[t] && o1.lokasyonY == m2[t]) {
                        o1.setSkor(o1.puanGoster() + 50);
                        r2[t] = 20;
                        m2[t] = 20;
                    } else if (o2.lokasyonX == r2[t] && o2.lokasyonY == m2[t]) {
                        o2.setSkor(o2.puanGoster() + 50);
                        r2[t] = 20;
                        m2[t] = 20;
                    }
                } else if (t == 1) {
                    g.drawImage(mantar.getPlayer(), r2[t] * 52, m2[t] * 52, null);
                    if (o1.lokasyonX == r2[t] && o1.lokasyonY == m2[t]) {
                        o1.setSkor(o1.puanGoster() + 50);
                        r2[t] = 30;
                        m2[t] = 20;
                    } else if (o2.lokasyonX == r2[t] && o2.lokasyonY == m2[t]) {
                        o2.setSkor(o2.puanGoster() + 50);
                        r2[t] = 30;
                        m2[t] = 20;
                    }
                } else if (t == 2) {
                    g.drawImage(mantar.getPlayer(), r2[t] * 52, m2[t] * 52, null);
                    if (o1.lokasyonX == r2[t] && o1.lokasyonY == m2[t]) {
                        o1.setSkor(o1.puanGoster() + 50);
                        r2[t] = 40;
                        m2[t] = 20;
                    } else if (o2.lokasyonX == r2[t] && o2.lokasyonY == m2[t]) {
                        o2.setSkor(o2.puanGoster() + 50);
                        r2[t] = 40;
                        m2[t] = 20;
                    }
                }

            }
        }
        //azmanin cizimi
        if (a == 0) {
            if (a_ka == 0) {
                g.drawImage(d1.getPlayer(), d1.getLokasyonX() * 52, d1.getLokasyonY() * 52, null);

            }
            if (a_kb == 0) {
                g.drawImage(d1.getPlayer(), d1.getLokasyonX() * 52, d1.getLokasyonY() * 52, null);
            }
            if (a_kc == 0) {
                g.drawImage(d1.getPlayer(), d1.getLokasyonX() * 52, d1.getLokasyonY() * 52, null);
            }
            if (a_kd == 0) {
                g.drawImage(d1.getPlayer(), d1.getLokasyonX() * 52, d1.getLokasyonY() * 52, null);
            }
            for (int i = 1; i < size_a; i++) {

                g.setColor(Color.blue);
                g.fillRect(shortestPath_a[i][0] * 52, shortestPath_a[i][1] * 52, 50, 50);
                g.drawImage(d1.getPlayer(), shortestPath_a[0][0] * 52, shortestPath_a[0][1] * 52, null);
            }

        }
        //gargamelin cizimi
        if (b == 0) {
            if (b_ka == 0) {
                g.drawImage(d2.getPlayer(), d2.getLokasyonX() * 52, d2.getLokasyonY() * 52, null);

            }
            if (b_kb == 0) {
                g.drawImage(d2.getPlayer(), d2.getLokasyonX() * 52, d2.getLokasyonY() * 52, null);
            }
            if (b_kc == 0) {
                g.drawImage(d2.getPlayer(), d2.getLokasyonX() * 52, d2.getLokasyonY() * 52, null);
            }
            if (b_kd == 0) {
                g.drawImage(d2.getPlayer(), d2.getLokasyonX() * 52, d2.getLokasyonY() * 52, null);
            }
            for (int i = 1; i < size_b; i++) {

                g.setColor(Color.ORANGE);
                g.fillRect(shortestPath_b[i][0] * 52, shortestPath_b[i][1] * 52, 50, 50);
                g.drawImage(d2.getPlayer(), shortestPath_b[0][0] * 52, shortestPath_b[0][1] * 52, null);
            }

        }
        for (t = 0; t <= 1000; t++) {

            while (count < 14) {
                if (!(map.getMap(z1 = random.nextInt(12), z2 = random.nextInt(10)).equals("0"))) {
                    count++;
                    dizi1[count] = z1;
                    dizi2[count] = z2;

                }
            }
            while (no < 3) {
                if (!(map.getMap(r1 = random.nextInt(12), m1 = random.nextInt(10)).equals("0"))) {
                    no++;
                    r2[no] = r1;
                    m2[no] = m1;

                }
            }

            if (k >= 20 + 30 * t && k <= 30 + 30 * t) {

                if (t == 0) {
                    for (int i = 0; i <= (altin.getAltinNo() - 1); i++) {
                        g.drawImage(altin.getPlayer(), dizi1[i] * 52, dizi2[i] * 52, null);
                        if (o1.lokasyonX == dizi1[i] && o1.lokasyonY == dizi2[i]) {
                            o1.setSkor(o1.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        } else if (o2.lokasyonX == dizi1[i] && o2.lokasyonY == dizi2[i]) {
                            o2.setSkor(o2.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        }

                    }
                } else if (t == 1) {
                    for (int i = 5; i <= (altin.getAltinNo() + 4); i++) {
                        g.drawImage(altin.getPlayer(), dizi1[i] * 52, dizi2[i] * 52, null);
                        if (o1.lokasyonX == dizi1[i] && o1.lokasyonY == dizi2[i]) {
                            o1.setSkor(o1.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        } else if (o2.lokasyonX == dizi1[i] && o2.lokasyonY == dizi2[i]) {
                            o2.setSkor(o2.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        }

                    }
                } else if (t == 2) {
                    for (int i = 10; i <= (altin.getAltinNo() + 9); i++) {
                        g.drawImage(altin.getPlayer(), dizi1[i] * 52, dizi2[i] * 52, null);
                        if (o1.lokasyonX == dizi1[i] && o1.lokasyonY == dizi2[i]) {
                            o1.setSkor(o1.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        } else if (o2.lokasyonX == dizi1[i] && o2.lokasyonY == dizi2[i]) {
                            o2.setSkor(o2.puanGoster() + 5);
                            dizi1[i] = 20;
                            dizi2[i] = 20;
                        }

                    }
                }
            }
            if (k >= 40 + 54 * t && k <= 54 + 54 * t) {

                if (t == 0) {
                    g.drawImage(mantar.getPlayer(), r2[t] * 52, m2[t] * 52, null);
                    if (o1.lokasyonX == r2[t] && o1.lokasyonY == m2[t]) {
                        o1.setSkor(o1.puanGoster() + 50);
                        r2[t] = 20;
                        m2[t] = 20;
                    } else if (o2.lokasyonX == r2[t] && o2.lokasyonY == m2[t]) {
                        o2.setSkor(o2.puanGoster() + 50);
                        r2[t] = 20;
                        m2[t] = 20;
                    }
                } else if (t == 1) {
                    g.drawImage(mantar.getPlayer(), r2[t] * 52, m2[t] * 52, null);
                    if (o1.lokasyonX == r2[t] && o1.lokasyonY == m2[t]) {
                        o1.setSkor(o1.puanGoster() + 50);
                        r2[t] = 30;
                        m2[t] = 20;
                    } else if (o2.lokasyonX == r2[t] && o2.lokasyonY == m2[t]) {
                        o2.setSkor(o2.puanGoster() + 50);
                        r2[t] = 30;
                        m2[t] = 20;
                    }
                } else if (t == 2) {
                    g.drawImage(mantar.getPlayer(), r2[t] * 52, m2[t] * 52, null);
                    if (o1.lokasyonX == r2[t] && o1.lokasyonY == m2[t]) {
                        o1.setSkor(o1.puanGoster() + 50);
                        r2[t] = 40;
                        m2[t] = 20;
                    } else if (o2.lokasyonX == r2[t] && o2.lokasyonY == m2[t]) {
                        o2.setSkor(o2.puanGoster() + 50);
                        r2[t] = 40;
                        m2[t] = 20;
                    }
                }

            }
        }

        if (secim == 1) {

            g.drawImage(o1.getPlayer(), o1.getLokasyonX() * 52, o1.getLokasyonY() * 52, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial Black", Font.PLAIN, 10));
            g.drawString("Skor :" + o1.puanGoster(), 14 * 50, 2 * 52);
            if ((o1.lokasyonX == 12 && o1.lokasyonY == 7) || (o2.lokasyonX == 13 && o2.lokasyonY == 3)) {
                o1.setLokasyonX(13);
                o1.setLokasyonY(3);
                g.drawImage(sirine, 14 * 52, 3 * 52, null);
                g.drawString("Kazandýnýz!", 14 * 50, 3 * 52);
                g.setColor(Color.darkGray);
                g.fillRect(13 * 52, 7 * 52, 50, 50);

            } else if (o1.puanGoster() < 0) {
                o1.setLokasyonX(13);
                o1.setLokasyonY(3);
                g.drawImage(sirine, 20 * 52, 3 * 52, null);
                g.drawString("Kaybetiniz!", 14 * 50, 3 * 52);

                g.setColor(Color.darkGray);
                g.fillRect(13 * 52, 7 * 52, 50, 50);
            }

        } else if (secim == 0) {

            g.drawImage(o2.getPlayer(), o2.getLokasyonX() * 52, o2.getLokasyonY() * 52, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial Black", Font.PLAIN, 10));
            g.drawString("Skor : " + o2.puanGoster(), 14 * 50, 2 * 52);
            if ((o2.lokasyonX == 12 && o2.lokasyonY == 7) || (o2.lokasyonX == 13 && o2.lokasyonY == 3)) {
                o2.setLokasyonX(13);
                o2.setLokasyonY(3);
                g.drawImage(sirine, 14 * 52, 3 * 52, null);
                g.drawString("Kazandýnýz!", 14 * 50, 3 * 50);
                g.setColor(Color.darkGray);
                g.fillRect(13 * 52, 7 * 52, 50, 50);
            } else if (o2.puanGoster() < 0) {
                o2.setLokasyonX(14);
                o2.setLokasyonY(3);
                g.drawImage(sirine, 20 * 52, 3 * 52, null);
                g.drawString("Kaybetiniz!", 14 * 50, 3 * 52);
                g.setColor(Color.darkGray);
                g.fillRect(13 * 52, 7 * 52, 50, 50);
            }
        }

    }

    public void IlkDurum() {
        //Oyuncu ilk hareketini etmeden onceki durumda en kisa yolun bulunmasi
        Karakter.Create_a();
        Karakter.Create_b();
        if (a == 0) {
            Karakter.Set_a(d1.getLokasyonX(), d1.getLokasyonY());
        }
        if (b == 0) {
            Karakter.Set_b(d2.getLokasyonX(), d2.getLokasyonY());
        }
        if (a == 0 || b == 0) {
            Karakter.Dijkstra(6, 5, a, b);
        }
        //delete dijkstradan sonra gerceklesmesi gerekiyor o yuzden yukaridaki iflerde degil burada yazdim
        if (a == 0) {
            size_a = Karakter.Size_a(6, 5);
            shortestPath_a = new int[size_a][2];
            shortestPath_a = Karakter.Path_a(6, 5);
        }
        if (b == 0) {
            size_b = Karakter.Size_b(6, 5);
            shortestPath_b = new int[size_b][2];
            shortestPath_b = Karakter.Path_b(6, 5);
        }

    }

    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            if (secim == 1) {
                if (!(map.getMap(o1.getLokasyonX(), o1.getLokasyonY() - 2).equals("0")) && !(map.getMap(o1.getLokasyonX(), o1.getLokasyonY() - 1).equals("0"))) {
                    o1.move(0, -1);

                    if (a == 0) {
                        Karakter.Set_a(d1.getLokasyonX(), d1.getLokasyonY());
                    }
                    if (b == 0) {
                        Karakter.Set_b(d2.getLokasyonX(), d2.getLokasyonY());
                    }
                    if (a == 0 || b == 0) {
                        Karakter.Dijkstra(o1.getLokasyonX(), o1.getLokasyonY(), a, b);
                    }
                    if (a == 0) {
                        size_a = Karakter.Size_a(o1.getLokasyonX(), o1.getLokasyonY());
                        shortestPath_a = new int[size_a][2];
                        shortestPath_a = Karakter.Path_a(o1.getLokasyonX(), o1.getLokasyonY());

                    }
                    if (b == 0) {
                        size_b = Karakter.Size_b(o1.getLokasyonX(), o1.getLokasyonY());
                        shortestPath_b = new int[size_b][2];
                        shortestPath_b = Karakter.Path_b(o1.getLokasyonX(), o1.getLokasyonY());
                    }

                    //boyama icin fonksiyon yazilirsa Dijkstra ve Delete_a fonksiyonlarinin aralarinda cagirilir
                    //ayni sekilde dusmani da burada hareket ettiririz.
                }

            } else if (secim == 0) {
                if (!(map.getMap(o2.getLokasyonX(), o2.getLokasyonY() - 1).equals("0")) && !(map.getMap(o2.getLokasyonX(), o2.getLokasyonY() - 1).equals("0"))) {
                    o2.move(0, -1);
                    if (a == 0) {
                        Karakter.Set_a(d1.getLokasyonX(), d1.getLokasyonY());
                    }
                    if (b == 0) {
                        Karakter.Set_b(d2.getLokasyonX(), d2.getLokasyonY());
                    }
                    if (a == 0 || b == 0) {
                        Karakter.Dijkstra(o2.getLokasyonX(), o2.getLokasyonY(), a, b);
                    }
                    if (a == 0) {
                        size_a = Karakter.Size_a(o2.getLokasyonX(), o2.getLokasyonY());
                        shortestPath_a = new int[size_a][2];
                        shortestPath_a = Karakter.Path_a(o2.getLokasyonX(), o2.getLokasyonY());
                    }
                    if (b == 0) {
                        size_b = Karakter.Size_b(o2.getLokasyonX(), o2.getLokasyonY());
                        shortestPath_b = new int[size_b][2];
                        shortestPath_b = Karakter.Path_b(o2.getLokasyonX(), o2.getLokasyonY());
                    }

                }

            }
            puan();
            if (shortestPath_a[1][0] == d1.getLokasyonX() && shortestPath_a[1][1] == (d1.getLokasyonY() - 1) && (shortestPath_a[1][0] != d2.getLokasyonX() && shortestPath_a[1][1] != (d2.getLokasyonY() - 1))) {
                d1.move(0, -1);
            } else if (shortestPath_a[1][0] == d1.getLokasyonX() && shortestPath_a[1][1] == (d1.getLokasyonY() + 1) && (shortestPath_a[1][0] != d2.getLokasyonX() && shortestPath_a[1][1] != (d2.getLokasyonY() + 1))) {
                d1.move(0, 1);
            } else if (shortestPath_a[1][0] == (d1.getLokasyonX() - 1) && shortestPath_a[1][1] == d1.getLokasyonY() && (shortestPath_a[1][0] != (d2.getLokasyonX() - 1) && shortestPath_a[1][1] != d2.getLokasyonY())) {
                d1.move(-1, 0);
            } else if (shortestPath_a[1][0] == d1.getLokasyonX() + 1 && shortestPath_a[1][1] == d1.getLokasyonY() && (shortestPath_a[1][0] != (d2.getLokasyonX() + 1) && shortestPath_a[1][1] != d2.getLokasyonY())) {
                d1.move(1, 0);
            } if (shortestPath_b[2][0] == d2.getLokasyonX() && shortestPath_b[2][1] == d2.getLokasyonY() - 2) {
                d2.move(0, -1);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() && shortestPath_b[2][1] == d2.getLokasyonY() + 2) {
                d2.move(0, 1);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() - 2 && shortestPath_b[2][1] == d2.getLokasyonY()) {
                d2.move(-1, 0);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() + 2 && shortestPath_b[2][1] == d2.getLokasyonY()) {
                d2.move(1, 0);
            }

        }
        if (keyCode == KeyEvent.VK_DOWN) {
            if (secim == 1) {
                if (!(map.getMap(o1.getLokasyonX(), o1.getLokasyonY() + 2).equals("0")) && !(map.getMap(o1.getLokasyonX(), o1.getLokasyonY() + 1).equals("0"))) {
                    o1.move(0, 1);
                    if (a == 0) {
                        Karakter.Set_a(d1.getLokasyonX(), d1.getLokasyonY());
                    }
                    if (b == 0) {
                        Karakter.Set_b(d2.getLokasyonX(), d2.getLokasyonY());
                    }
                    if (a == 0 || b == 0) {
                        Karakter.Dijkstra(o1.getLokasyonX(), o1.getLokasyonY(), a, b);
                    }
                    if (a == 0) {
                        size_a = Karakter.Size_a(o1.getLokasyonX(), o1.getLokasyonY());
                        shortestPath_a = new int[size_a][2];
                        shortestPath_a = Karakter.Path_a(o1.getLokasyonX(), o1.getLokasyonY());
                    }
                    if (b == 0) {
                        size_b = Karakter.Size_b(o1.getLokasyonX(), o1.getLokasyonY());
                        shortestPath_b = new int[size_b][2];
                        shortestPath_b = Karakter.Path_b(o1.getLokasyonX(), o1.getLokasyonY());
                    }

                }
            } else if (secim == 0) {
                if (!(map.getMap(o2.getLokasyonX(), o2.getLokasyonY() + 1).equals("0")) && !(map.getMap(o2.getLokasyonX(), o2.getLokasyonY() + 1).equals("0"))) {

                    o2.move(0, 1);
                    if (a == 0) {
                        Karakter.Set_a(d1.getLokasyonX(), d1.getLokasyonY());
                    }
                    if (b == 0) {
                        Karakter.Set_b(d2.getLokasyonX(), d2.getLokasyonY());
                    }
                    if (a == 0 || b == 0) {
                        Karakter.Dijkstra(o2.getLokasyonX(), o2.getLokasyonY(), a, b);
                    }
                    if (a == 0) {
                        size_a = Karakter.Size_a(o2.getLokasyonX(), o2.getLokasyonY());
                        shortestPath_a = new int[size_a][2];
                        shortestPath_a = Karakter.Path_a(o2.getLokasyonX(), o2.getLokasyonY());
                    }
                    if (b == 0) {
                        size_b = Karakter.Size_b(o2.getLokasyonX(), o2.getLokasyonY());
                        shortestPath_b = new int[size_b][2];
                        shortestPath_b = Karakter.Path_b(o2.getLokasyonX(), o2.getLokasyonY());
                    }

                }
            }
            puan();
            if (shortestPath_a[1][0] == d1.getLokasyonX() && shortestPath_a[1][1] == (d1.getLokasyonY() - 1) && (shortestPath_a[1][0] != d2.getLokasyonX() && shortestPath_a[1][1] != (d2.getLokasyonY() - 1))) {
                d1.move(0, -1);
            } else if (shortestPath_a[1][0] == d1.getLokasyonX() && shortestPath_a[1][1] == (d1.getLokasyonY() + 1) && (shortestPath_a[1][0] != d2.getLokasyonX() && shortestPath_a[1][1] != (d2.getLokasyonY() + 1))) {
                d1.move(0, 1);
            } else if (shortestPath_a[1][0] == (d1.getLokasyonX() - 1) && shortestPath_a[1][1] == d1.getLokasyonY() && (shortestPath_a[1][0] != (d2.getLokasyonX() - 1) && shortestPath_a[1][1] != d2.getLokasyonY())) {
                d1.move(-1, 0);
            } else if (shortestPath_a[1][0] == d1.getLokasyonX() + 1 && shortestPath_a[1][1] == d1.getLokasyonY() && (shortestPath_a[1][0] != (d2.getLokasyonX() + 1) && shortestPath_a[1][1] != d2.getLokasyonY())) {
                d1.move(1, 0);
            } if (shortestPath_b[2][0] == d2.getLokasyonX() && shortestPath_b[2][1] == d2.getLokasyonY() - 2) {
                d2.move(0, -1);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() && shortestPath_b[2][1] == d2.getLokasyonY() + 2) {
                d2.move(0, 1);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() - 2 && shortestPath_b[2][1] == d2.getLokasyonY()) {
                d2.move(-1, 0);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() + 2 && shortestPath_b[2][1] == d2.getLokasyonY()) {
                d2.move(1, 0);
            }

        }
        if (keyCode == KeyEvent.VK_LEFT) {
            if (secim == 1) {
                if (!(map.getMap(o1.getLokasyonX() - 2, o1.getLokasyonY()).equals("0")) && !(map.getMap(o1.getLokasyonX() - 1, o1.getLokasyonY()).equals("0"))) {
                    o1.move(-1, 0);
                    if (a == 0) {
                        Karakter.Set_a(d1.getLokasyonX(), d1.getLokasyonY());
                    }
                    if (b == 0) {
                        Karakter.Set_b(d2.getLokasyonX(), d2.getLokasyonY());
                    }
                    if (a == 0 || b == 0) {
                        Karakter.Dijkstra(o1.getLokasyonX(), o1.getLokasyonY(), a, b);
                    }
                    if (a == 0) {
                        size_a = Karakter.Size_a(o1.getLokasyonX(), o1.getLokasyonY());
                        shortestPath_a = new int[size_a][2];
                        shortestPath_a = Karakter.Path_a(o1.getLokasyonX(), o1.getLokasyonY());
                    }
                    if (b == 0) {
                        size_b = Karakter.Size_b(o1.getLokasyonX(), o1.getLokasyonY());
                        shortestPath_b = new int[size_b][2];
                        shortestPath_b = Karakter.Path_b(o1.getLokasyonX(), o1.getLokasyonY());
                    }

                }
            } else if (secim == 0) {
                if (!(map.getMap(o2.getLokasyonX() - 1, o2.getLokasyonY()).equals("0")) && !(map.getMap(o2.getLokasyonX() - 1, o2.getLokasyonY()).equals("0"))) {

                    o2.move(-1, 0);
                    if (a == 0) {
                        Karakter.Set_a(d1.getLokasyonX(), d1.getLokasyonY());
                    }
                    if (b == 0) {
                        Karakter.Set_b(d2.getLokasyonX(), d2.getLokasyonY());
                    }
                    if (a == 0 || b == 0) {
                        Karakter.Dijkstra(o2.getLokasyonX(), o2.getLokasyonY(), a, b);
                    }
                    if (a == 0) {
                        size_a = Karakter.Size_a(o2.getLokasyonX(), o2.getLokasyonY());
                        shortestPath_a = new int[size_a][2];
                        shortestPath_a = Karakter.Path_a(o2.getLokasyonX(), o2.getLokasyonY());
                    }
                    if (b == 0) {
                        size_b = Karakter.Size_b(o2.getLokasyonX(), o2.getLokasyonY());
                        shortestPath_b = new int[size_b][2];
                        shortestPath_b = Karakter.Path_b(o2.getLokasyonX(), o2.getLokasyonY());
                    }

                }
            }
            puan();
            if (shortestPath_a[1][0] == d1.getLokasyonX() && shortestPath_a[1][1] == (d1.getLokasyonY() - 1) && (shortestPath_a[1][0] != d2.getLokasyonX() && shortestPath_a[1][1] != (d2.getLokasyonY() - 1))) {
                d1.move(0, -1);
            } else if (shortestPath_a[1][0] == d1.getLokasyonX() && shortestPath_a[1][1] == (d1.getLokasyonY() + 1) && (shortestPath_a[1][0] != d2.getLokasyonX() && shortestPath_a[1][1] != (d2.getLokasyonY() + 1))) {
                d1.move(0, 1);
            } else if (shortestPath_a[1][0] == (d1.getLokasyonX() - 1) && shortestPath_a[1][1] == d1.getLokasyonY() && (shortestPath_a[1][0] != (d2.getLokasyonX() - 1) && shortestPath_a[1][1] != d2.getLokasyonY())) {
                d1.move(-1, 0);
            } else if (shortestPath_a[1][0] == d1.getLokasyonX() + 1 && shortestPath_a[1][1] == d1.getLokasyonY() && (shortestPath_a[1][0] != (d2.getLokasyonX() + 1) && shortestPath_a[1][1] != d2.getLokasyonY())) {
                d1.move(1, 0);
            } if (shortestPath_b[2][0] == d2.getLokasyonX() && shortestPath_b[2][1] == d2.getLokasyonY() - 2) {
                d2.move(0, -1);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() && shortestPath_b[2][1] == d2.getLokasyonY() + 2) {
                d2.move(0, 1);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() - 2 && shortestPath_b[2][1] == d2.getLokasyonY()) {
                d2.move(-1, 0);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() + 2 && shortestPath_b[2][1] == d2.getLokasyonY()) {
                d2.move(1, 0);
            }

        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            if (secim == 1) {
                if (!(map.getMap(o1.getLokasyonX() + 2, o1.getLokasyonY()).equals("0")) && !(map.getMap(o1.getLokasyonX() + 1, o1.getLokasyonY()).equals("0"))) {
                    o1.move(1, 0);
                    if (a == 0) {
                        Karakter.Set_a(d1.getLokasyonX(), d1.getLokasyonY());
                    }
                    if (b == 0) {
                        Karakter.Set_b(d2.getLokasyonX(), d2.getLokasyonY());
                    }
                    if (a == 0 || b == 0) {
                        Karakter.Dijkstra(o1.getLokasyonX(), o1.getLokasyonY(), a, b);
                    }
                    if (a == 0) {
                        size_a = Karakter.Size_a(o1.getLokasyonX(), o1.getLokasyonY());
                        shortestPath_a = new int[size_a][2];
                        shortestPath_a = Karakter.Path_a(o1.getLokasyonX(), o1.getLokasyonY());
                    }
                    if (b == 0) {
                        size_b = Karakter.Size_b(o1.getLokasyonX(), o1.getLokasyonY());
                        shortestPath_b = new int[size_b][2];
                        shortestPath_b = Karakter.Path_b(o1.getLokasyonX(), o1.getLokasyonY());
                    }

                }
            } else if (secim == 0) {
                if (!(map.getMap(o2.getLokasyonX() + 1, o2.getLokasyonY()).equals("0")) && !(map.getMap(o2.getLokasyonX() + 1, o2.getLokasyonY()).equals("0"))) {
                    o2.move(1, 0);
                    if (a == 0) {
                        Karakter.Set_a(d1.getLokasyonX(), d1.getLokasyonY());
                    }
                    if (b == 0) {
                        Karakter.Set_b(d2.getLokasyonX(), d2.getLokasyonY());
                    }
                    if (a == 0 || b == 0) {
                        Karakter.Dijkstra(o2.getLokasyonX(), o2.getLokasyonY(), a, b);
                    }
                    if (a == 0) {
                        size_a = Karakter.Size_a(o2.getLokasyonX(), o2.getLokasyonY());
                        shortestPath_a = new int[size_a][2];
                        shortestPath_a = Karakter.Path_a(o2.getLokasyonX(), o2.getLokasyonY());
                    }
                    if (b == 0) {
                        size_b = Karakter.Size_b(o2.getLokasyonX(), o2.getLokasyonY());
                        shortestPath_b = new int[size_b][2];
                        shortestPath_b = Karakter.Path_b(o2.getLokasyonX(), o2.getLokasyonY());
                    }

                }
            }
            puan();
            if (shortestPath_a[1][0] == d1.getLokasyonX() && shortestPath_a[1][1] == (d1.getLokasyonY() - 1) && (shortestPath_a[1][0] != d2.getLokasyonX() && shortestPath_a[1][1] != (d2.getLokasyonY() - 1))) {
                d1.move(0, -1);
            } else if (shortestPath_a[1][0] == d1.getLokasyonX() && shortestPath_a[1][1] == (d1.getLokasyonY() + 1) && (shortestPath_a[1][0] != d2.getLokasyonX() && shortestPath_a[1][1] != (d2.getLokasyonY() + 1))) {
                d1.move(0, 1);
            } else if (shortestPath_a[1][0] == (d1.getLokasyonX() - 1) && shortestPath_a[1][1] == d1.getLokasyonY() && (shortestPath_a[1][0] != (d2.getLokasyonX() - 1) && shortestPath_a[1][1] != d2.getLokasyonY())) {
                d1.move(-1, 0);
            } else if (shortestPath_a[1][0] == d1.getLokasyonX() + 1 && shortestPath_a[1][1] == d1.getLokasyonY() && (shortestPath_a[1][0] != (d2.getLokasyonX() + 1) && shortestPath_a[1][1] != d2.getLokasyonY())) {
                d1.move(1, 0);
            } if (shortestPath_b[2][0] == d2.getLokasyonX() && shortestPath_b[2][1] == d2.getLokasyonY() - 2) {
                d2.move(0, -1);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() && shortestPath_b[2][1] == d2.getLokasyonY() + 2) {
                d2.move(0, 1);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() - 2 && shortestPath_b[2][1] == d2.getLokasyonY()) {
                d2.move(-1, 0);
            } else if (shortestPath_b[2][0] == d2.getLokasyonX() + 2 && shortestPath_b[2][1] == d2.getLokasyonY()) {
                d2.move(1, 0);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Board window = new Board();

                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initialize() {

        frame = new JFrame();
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setTitle("Hosgeldiniz");
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Tembel þirin");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                secim = 0;
                frame.setVisible(false);
                JFrame f = new JFrame();
                f.setTitle("Þirinler Oyunu");
                Board board = new Board();
                f.getContentPane().add(board);

                f.setSize(800, 650);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

        });
        btnNewButton.setBounds(56, 114, 109, 21);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Gözlüklü þirin");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                frame.setVisible(false);
                secim = 1;
                JFrame f = new JFrame();
                f.setTitle("Þirinler Oyunu");
                Board board = new Board();
                f.getContentPane().add(board);

                f.setSize(800, 650);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
        btnNewButton_1.setBounds(218, 114, 124, 21);
        frame.getContentPane().add(btnNewButton_1);

        txtBirimIlerler = new JTextField();
        txtBirimIlerler.setFont(new Font("Tahoma", Font.BOLD, 10));
        txtBirimIlerler.setSelectionColor(Color.WHITE);
        txtBirimIlerler.setText("1 birim ilerler.");
        txtBirimIlerler.setBounds(66, 145, 83, 43);
        frame.getContentPane().add(txtBirimIlerler);
        txtBirimIlerler.setColumns(10);

        txtImportJavaawtcolorImport = new JTextField();
        txtImportJavaawtcolorImport.setSelectionColor(Color.WHITE);
        txtImportJavaawtcolorImport.setForeground(SystemColor.textText);
        txtImportJavaawtcolorImport.setFont(new Font("Tahoma", Font.BOLD, 10));
        txtImportJavaawtcolorImport.setText("2 birim ilerler.\r\n");
        txtImportJavaawtcolorImport.setBounds(228, 145, 92, 43);
        frame.getContentPane().add(txtImportJavaawtcolorImport);
        txtImportJavaawtcolorImport.setColumns(10);

        txtAzmanaDokunursaOyuncu = new JTextField();
        txtAzmanaDokunursaOyuncu.setText(" Azman\u2019a dokunursa  5 puan kaybeder. ");
        txtAzmanaDokunursaOyuncu.setBounds(95, 187, 215, 29);
        frame.getContentPane().add(txtAzmanaDokunursaOyuncu);
        txtAzmanaDokunursaOyuncu.setColumns(10);

        txtGargameleDokunursa = new JTextField();
        txtGargameleDokunursa.setText(" Gargamel'e dokunursa  15 puan kaybeder. ");
        txtGargameleDokunursa.setColumns(10);
        txtGargameleDokunursa.setBounds(79, 212, 241, 29);
        frame.getContentPane().add(txtGargameleDokunursa);

        txtLtfenOyuncunuzuSein = new JTextField();
        txtLtfenOyuncunuzuSein.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtLtfenOyuncunuzuSein.setText("L\u00FCtfen Oyuncunuzu Se\u00E7in");
        txtLtfenOyuncunuzuSein.setBounds(112, 45, 182, 19);
        frame.getContentPane().add(txtLtfenOyuncunuzuSein);
        txtLtfenOyuncunuzuSein.setColumns(10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
