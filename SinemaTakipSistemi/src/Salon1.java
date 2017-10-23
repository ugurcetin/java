import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window.Type;

import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Salon1 extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JLabel lblFilm;
	
	private JButton a1;
	private JButton a2;
	private JButton a3;
	private JButton a4;
	private JButton a5;
	private JButton a6;
	private JButton a7;
	private JButton a8;
	private JButton a9;
	private JButton a10;
	private JButton a11;
	private JButton a12;
	
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private JButton b8;
	private JButton b9;
	private JButton b10;
	private JButton b11;
	private JButton b12;
	
	private JButton c1;
	private JButton c2;
	private JButton c3;
	private JButton c4;
	private JButton c5;
	private JButton c6;
	private JButton c7;
	private JButton c8;
	private JButton c9;
	private JButton c10;
	private JButton c11;
	private JButton c12;
	
	private JButton d1;
	private JButton d2;
	private JButton d3;
	private JButton d4;
	private JButton d5;
	private JButton d6;
	private JButton d7;
	private JButton d8;
	private JButton d9;
	private JButton d10;
	private JButton d11;
	private JButton d12;
	
	private JButton e1;
	private JButton e2;
	private JButton e3;
	private JButton e4;
	private JButton e5;
	private JButton e6;
	private JButton e7;
	private JButton e8;
	private JButton e9;
	private JButton e10;
	private JButton e11;
	private JButton e12;
	
	private JButton f1;
	private JButton f2;
	private JButton f3;
	private JButton f4;
	private JButton f5;
	private JButton f6;
	private JButton f7;
	private JButton f8;
	private JButton f9;
	private JButton f10;
	private JButton f11;
	private JButton f12;
	
	private JButton g1;
	private JButton g2;
	private JButton g3;
	private JButton g4;
	private JButton g5;
	private JButton g6;
	private JButton g7;
	private JButton g8;
	private JButton g9;
	private JButton g10;
	private JButton g11;
	private JButton g12;
	
	private ArrayList koltukListesi;
	private SqlBaglanti sqlBaglanti;
	private FilmIslemleri filmIslemleri;
	private Film film;
	private SeansIslemleri seansIslemleri;
	private JLabel lblGecerliTarih;
	private Date gecerliTarih;
	private HashSet hset;
	private ArrayList biletListesi;
	
	private void cikisOnay(){
		if(AnaForm.biletSayisi != 0){
			boolean onay = MesajDiyalog.onayIste(
					"Ýþlemi iptal etmek istediðinizden emin misiniz?",MesajDiyalog.ONAY_OZEL);
			if(onay == true){
				AnaForm.secilenKoltuklar = null;
				AnaForm.biletListesiniYinele();
				dispose();
			}
		}
		else{
			AnaForm.secilenKoltuklar = null;
			AnaForm.biletListesiniYinele();
			dispose();
		}
	}
	
	private void hazirliklariYap(){
		sqlBaglanti = new SqlBaglanti("root","11741174bk.","sinemadb");
		sqlBaglanti.baglantiyiBaslat();
		gecerliTarih = new Date();
		hset = new HashSet();
		biletListesi = new ArrayList();
	}
	
	private int koltukListeIndisBul(String koltuk){
		
		int grup = koltuk.charAt(0) - 97;
		int sira = Integer.parseInt(koltuk.substring(1)) - 1;
		
		return grup * 12 + sira;
	}
	
	private void seciliKoltukGorunumleriniAyarla(){
		
		if(biletListesi == null)
			return;
		
		Iterator biletler = biletListesi.iterator();

		while(biletler.hasNext()){
			int koltukListeIndis = koltukListeIndisBul(biletler.next().toString());
			Koltuk x = (Koltuk)koltukListesi.get(koltukListeIndis);
			x.koltukRenginiDegistir();
		}
		
	}
	
	private void koltukGorunumleriniAyarla(){
		
		ResultSet biletler = sqlBaglanti.sorguCalistir("SELECT * FROM biletler WHERE tarih = \"" + 
								AnaForm.secilenFilm.getTarih()
								+ "\" AND filmKodu = " + AnaForm.secilenFilm.getKodu() +
								" AND seans = \"" + AnaForm.secilenFilm.getSeans() +
								"\" AND salonNo = " + AnaForm.secilenFilm.getSalonNo());
		
		try{
			while(biletler.next()){
				int koltukListeIndis = koltukListeIndisBul(biletler.getString("koltuk"));
				Koltuk x = (Koltuk)koltukListesi.get(koltukListeIndis);
				x.koltuguDoluIsaretle();
			}
		}
		catch(SQLException sqlException){
			
		}
		
		if(AnaForm.islemdeMi == true){
			if(AnaForm.secilenKoltuklar != null)
				biletListesi = AnaForm.secilenKoltuklar;
			seciliKoltukGorunumleriniAyarla();
		}
	}
	
	private void koltukSeciminiYap(ActionEvent ae){
		
		Iterator i = koltukListesi.iterator();
		while(i.hasNext()){
			Koltuk koltuk = (Koltuk) i.next();
			if((JButton)ae.getSource() == koltuk.getBagliBtn()){
				koltuk.koltukRenginiDegistir();
				if(koltuk.getKoltukDurumu() == Koltuk.KOLTUK_SECILI){
					biletListesi.add(koltuk.getKoltuk());
					AnaForm.biletSayisi++;
				}
				else if(koltuk.getKoltukDurumu() == Koltuk.KOLTUK_BOS){
					biletListesi.remove(koltuk.getKoltuk());
					AnaForm.biletSayisi--;
				}
			}
		}
		
	}
	
	private void koltukListesiniHazirla(){
		koltukListesi = new ArrayList();
		
		koltukListesi.add(new Koltuk(a1,Koltuk.KOLTUK_BOS,"a","1"));
		koltukListesi.add(new Koltuk(a2,Koltuk.KOLTUK_BOS,"a","2"));
		koltukListesi.add(new Koltuk(a3,Koltuk.KOLTUK_BOS,"a","3"));
		koltukListesi.add(new Koltuk(a4,Koltuk.KOLTUK_BOS,"a","4"));
		koltukListesi.add(new Koltuk(a5,Koltuk.KOLTUK_BOS,"a","5"));
		koltukListesi.add(new Koltuk(a6,Koltuk.KOLTUK_BOS,"a","6"));
		koltukListesi.add(new Koltuk(a7,Koltuk.KOLTUK_BOS,"a","7"));
		koltukListesi.add(new Koltuk(a8,Koltuk.KOLTUK_BOS,"a","8"));
		koltukListesi.add(new Koltuk(a9,Koltuk.KOLTUK_BOS,"a","9"));
		koltukListesi.add(new Koltuk(a10,Koltuk.KOLTUK_BOS,"a","10"));
		koltukListesi.add(new Koltuk(a11,Koltuk.KOLTUK_BOS,"a","11"));
		koltukListesi.add(new Koltuk(a12,Koltuk.KOLTUK_BOS,"a","12"));
		
		koltukListesi.add(new Koltuk(b1,Koltuk.KOLTUK_BOS,"b","1"));
		koltukListesi.add(new Koltuk(b2,Koltuk.KOLTUK_BOS,"b","2"));
		koltukListesi.add(new Koltuk(b3,Koltuk.KOLTUK_BOS,"b","3"));
		koltukListesi.add(new Koltuk(b4,Koltuk.KOLTUK_BOS,"b","4"));
		koltukListesi.add(new Koltuk(b5,Koltuk.KOLTUK_BOS,"b","5"));
		koltukListesi.add(new Koltuk(b6,Koltuk.KOLTUK_BOS,"b","6"));
		koltukListesi.add(new Koltuk(b7,Koltuk.KOLTUK_BOS,"b","7"));
		koltukListesi.add(new Koltuk(b8,Koltuk.KOLTUK_BOS,"b","8"));
		koltukListesi.add(new Koltuk(b9,Koltuk.KOLTUK_BOS,"b","9"));
		koltukListesi.add(new Koltuk(b10,Koltuk.KOLTUK_BOS,"b","10"));
		koltukListesi.add(new Koltuk(b11,Koltuk.KOLTUK_BOS,"b","11"));
		koltukListesi.add(new Koltuk(b12,Koltuk.KOLTUK_BOS,"b","12"));
		
		koltukListesi.add(new Koltuk(c1,Koltuk.KOLTUK_BOS,"c","1"));
		koltukListesi.add(new Koltuk(c2,Koltuk.KOLTUK_BOS,"c","2"));
		koltukListesi.add(new Koltuk(c3,Koltuk.KOLTUK_BOS,"c","3"));
		koltukListesi.add(new Koltuk(c4,Koltuk.KOLTUK_BOS,"c","4"));
		koltukListesi.add(new Koltuk(c5,Koltuk.KOLTUK_BOS,"c","5"));
		koltukListesi.add(new Koltuk(c6,Koltuk.KOLTUK_BOS,"c","6"));
		koltukListesi.add(new Koltuk(c7,Koltuk.KOLTUK_BOS,"c","7"));
		koltukListesi.add(new Koltuk(c8,Koltuk.KOLTUK_BOS,"c","8"));
		koltukListesi.add(new Koltuk(c9,Koltuk.KOLTUK_BOS,"c","9"));
		koltukListesi.add(new Koltuk(c10,Koltuk.KOLTUK_BOS,"c","10"));
		koltukListesi.add(new Koltuk(c11,Koltuk.KOLTUK_BOS,"c","11"));
		koltukListesi.add(new Koltuk(c12,Koltuk.KOLTUK_BOS,"c","12"));
		
		koltukListesi.add(new Koltuk(d1,Koltuk.KOLTUK_BOS,"d","1"));
		koltukListesi.add(new Koltuk(d2,Koltuk.KOLTUK_BOS,"d","2"));
		koltukListesi.add(new Koltuk(d3,Koltuk.KOLTUK_BOS,"d","3"));
		koltukListesi.add(new Koltuk(d4,Koltuk.KOLTUK_BOS,"d","4"));
		koltukListesi.add(new Koltuk(d5,Koltuk.KOLTUK_BOS,"d","5"));
		koltukListesi.add(new Koltuk(d6,Koltuk.KOLTUK_BOS,"d","6"));
		koltukListesi.add(new Koltuk(d7,Koltuk.KOLTUK_BOS,"d","7"));
		koltukListesi.add(new Koltuk(d8,Koltuk.KOLTUK_BOS,"d","8"));
		koltukListesi.add(new Koltuk(d9,Koltuk.KOLTUK_BOS,"d","9"));
		koltukListesi.add(new Koltuk(d10,Koltuk.KOLTUK_BOS,"d","10"));
		koltukListesi.add(new Koltuk(d11,Koltuk.KOLTUK_BOS,"d","11"));
		koltukListesi.add(new Koltuk(d12,Koltuk.KOLTUK_BOS,"d","12"));
		
		koltukListesi.add(new Koltuk(e1,Koltuk.KOLTUK_BOS,"e","1"));
		koltukListesi.add(new Koltuk(e2,Koltuk.KOLTUK_BOS,"e","2"));
		koltukListesi.add(new Koltuk(e3,Koltuk.KOLTUK_BOS,"e","3"));
		koltukListesi.add(new Koltuk(e4,Koltuk.KOLTUK_BOS,"e","4"));
		koltukListesi.add(new Koltuk(e5,Koltuk.KOLTUK_BOS,"e","5"));
		koltukListesi.add(new Koltuk(e6,Koltuk.KOLTUK_BOS,"e","6"));
		koltukListesi.add(new Koltuk(e7,Koltuk.KOLTUK_BOS,"e","7"));
		koltukListesi.add(new Koltuk(e8,Koltuk.KOLTUK_BOS,"e","8"));
		koltukListesi.add(new Koltuk(e9,Koltuk.KOLTUK_BOS,"e","9"));
		koltukListesi.add(new Koltuk(e10,Koltuk.KOLTUK_BOS,"e","10"));
		koltukListesi.add(new Koltuk(e11,Koltuk.KOLTUK_BOS,"e","11"));
		koltukListesi.add(new Koltuk(e12,Koltuk.KOLTUK_BOS,"e","12"));

		koltukListesi.add(new Koltuk(f1,Koltuk.KOLTUK_BOS,"f","1"));
		koltukListesi.add(new Koltuk(f2,Koltuk.KOLTUK_BOS,"f","2"));
		koltukListesi.add(new Koltuk(f3,Koltuk.KOLTUK_BOS,"f","3"));
		koltukListesi.add(new Koltuk(f4,Koltuk.KOLTUK_BOS,"f","4"));
		koltukListesi.add(new Koltuk(f5,Koltuk.KOLTUK_BOS,"f","5"));
		koltukListesi.add(new Koltuk(f6,Koltuk.KOLTUK_BOS,"f","6"));
		koltukListesi.add(new Koltuk(f7,Koltuk.KOLTUK_BOS,"f","7"));
		koltukListesi.add(new Koltuk(f8,Koltuk.KOLTUK_BOS,"f","8"));
		koltukListesi.add(new Koltuk(f9,Koltuk.KOLTUK_BOS,"f","9"));
		koltukListesi.add(new Koltuk(f10,Koltuk.KOLTUK_BOS,"f","10"));
		koltukListesi.add(new Koltuk(f11,Koltuk.KOLTUK_BOS,"f","11"));
		koltukListesi.add(new Koltuk(f12,Koltuk.KOLTUK_BOS,"f","12"));
		
		koltukListesi.add(new Koltuk(g1,Koltuk.KOLTUK_BOS,"g","1"));
		koltukListesi.add(new Koltuk(g2,Koltuk.KOLTUK_BOS,"g","2"));
		koltukListesi.add(new Koltuk(g3,Koltuk.KOLTUK_BOS,"g","3"));
		koltukListesi.add(new Koltuk(g4,Koltuk.KOLTUK_BOS,"g","4"));
		koltukListesi.add(new Koltuk(g5,Koltuk.KOLTUK_BOS,"g","5"));
		koltukListesi.add(new Koltuk(g6,Koltuk.KOLTUK_BOS,"g","6"));
		koltukListesi.add(new Koltuk(g7,Koltuk.KOLTUK_BOS,"g","7"));
		koltukListesi.add(new Koltuk(g8,Koltuk.KOLTUK_BOS,"g","8"));
		koltukListesi.add(new Koltuk(g9,Koltuk.KOLTUK_BOS,"g","9"));
		koltukListesi.add(new Koltuk(g10,Koltuk.KOLTUK_BOS,"g","10"));
		koltukListesi.add(new Koltuk(g11,Koltuk.KOLTUK_BOS,"g","11"));
		koltukListesi.add(new Koltuk(g12,Koltuk.KOLTUK_BOS,"g","12"));
		
		koltukGorunumleriniAyarla();
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salon1 frame = new Salon1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Salon1() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				hazirliklariYap();
				koltukListesiniHazirla();
				lblFilm.setText(AnaForm.secilenFilm.getAdi() + " - " +
						   AnaForm.secilenFilm.getSeans());
			}
			@Override
			public void windowClosing(WindowEvent paramWindowEvent) {
				cikisOnay();
			}
		});
		
	
		setResizable(false);
		setVisible(true);
		setFont(new Font("Times New Roman", Font.BOLD, 18));
		setTitle("SALON 1");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		setBounds(100, 100, 982, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		g1 = new JButton("1");
		g1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g1.setForeground(new Color(0, 0, 0));
		g1.setMargin(new Insets(0, 0, 0, 0));
		g1.setBackground(Color.GREEN);
		g1.setBounds(68, 462, 55, 50);
		g1.addActionListener(this);
		contentPane.add(g1);
		
		g2 = new JButton("2");
		g2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g2.setMargin(new Insets(0, 0, 0, 0));
		g2.setForeground(new Color(0, 0, 0));
		g2.setBackground(Color.GREEN);
		g2.setBounds(133, 462, 55, 50);
		g2.addActionListener(this);
		contentPane.add(g2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(265, 14, 469, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SALON 1 : KOLTUK SE\u00C7\u0130M\u0130");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 469, 37);
		panel.add(lblNewLabel);
		
		lblFilm = new JLabel("");
		lblFilm.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilm.setForeground(SystemColor.controlDkShadow);
		lblFilm.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblFilm.setBounds(0, 30, 469, 28);
		panel.add(lblFilm);
		
		g3 = new JButton("3");
		g3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g3.setMargin(new Insets(0, 0, 0, 0));
		g3.setForeground(new Color(0, 0, 0));
		g3.setBackground(Color.GREEN);
		g3.setBounds(200, 462, 55, 50);
		g3.addActionListener(this);
		contentPane.add(g3);
		
		g4 = new JButton("4");
		g4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g4.setMargin(new Insets(0, 0, 0, 0));
		g4.setForeground(new Color(0, 0, 0));
		g4.setBackground(Color.GREEN);
		g4.setBounds(265, 462, 55, 50);
		g4.addActionListener(this);
		contentPane.add(g4);
		
		g5 = new JButton("5");
		g5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g5.setMargin(new Insets(0, 0, 0, 0));
		g5.setForeground(new Color(0, 0, 0));
		g5.setBackground(Color.GREEN);
		g5.setBounds(330, 462, 55, 50);
		g5.addActionListener(this);
		contentPane.add(g5);
		
		g6 = new JButton("6");
		g6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g6.setMargin(new Insets(0, 0, 0, 0));
		g6.setForeground(new Color(0, 0, 0));
		g6.setBackground(Color.GREEN);
		g6.setBounds(395, 462, 55, 50);
		g6.addActionListener(this);
		contentPane.add(g6);
		
		g7 = new JButton("7");
		g7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g7.setMargin(new Insets(0, 0, 0, 0));
		g7.setForeground(new Color(0, 0, 0));
		g7.setBackground(Color.GREEN);
		g7.setBounds(504, 462, 55, 50);
		g7.addActionListener(this);
		contentPane.add(g7);
		
		g8 = new JButton("8");
		g8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g8.setMargin(new Insets(0, 0, 0, 0));
		g8.setForeground(new Color(0, 0, 0));
		g8.setBackground(Color.GREEN);
		g8.setBounds(569, 462, 55, 50);
		g8.addActionListener(this);
		contentPane.add(g8);
		
		g9 = new JButton("9");
		g9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g9.setMargin(new Insets(0, 0, 0, 0));
		g9.setForeground(new Color(0, 0, 0));
		g9.setBackground(Color.GREEN);
		g9.setBounds(634, 462, 55, 50);
		g9.addActionListener(this);
		contentPane.add(g9);
		
		g10 = new JButton("10");
		g10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g10.setMargin(new Insets(0, 0, 0, 0));
		g10.setForeground(new Color(0, 0, 0));
		g10.setBackground(Color.GREEN);
		g10.setBounds(699, 462, 55, 50);
		g10.addActionListener(this);
		contentPane.add(g10);
		
		g11 = new JButton("11");
		g11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g11.setMargin(new Insets(0, 0, 0, 0));
		g11.setForeground(new Color(0, 0, 0));
		g11.setBackground(Color.GREEN);
		g11.setBounds(764, 462, 55, 50);
		g11.addActionListener(this);
		contentPane.add(g11);
		
		g12 = new JButton("12");
		g12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g12.setMargin(new Insets(0, 0, 0, 0));
		g12.setForeground(new Color(0, 0, 0));
		g12.setBackground(Color.GREEN);
		g12.setBounds(829, 462, 55, 50);
		g12.addActionListener(this);
		contentPane.add(g12);
		
		JLabel lblA = new JLabel("G");
		lblA.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblA.setBounds(38, 484, 20, 28);
		contentPane.add(lblA);
		
		JLabel lblB = new JLabel("F");
		lblB.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblB.setBounds(38, 423, 20, 28);
		contentPane.add(lblB);
		
		JLabel lblC = new JLabel("E");
		lblC.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblC.setBounds(38, 361, 20, 28);
		contentPane.add(lblC);
		
		JLabel lblD = new JLabel("D");
		lblD.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblD.setBounds(38, 299, 20, 28);
		contentPane.add(lblD);
		
		JLabel lblE = new JLabel("C");
		lblE.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblE.setBounds(38, 238, 20, 28);
		contentPane.add(lblE);
		
		JLabel lblF = new JLabel("B");
		lblF.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblF.setBounds(38, 177, 20, 28);
		contentPane.add(lblF);
		
		JLabel lblG = new JLabel("A");
		lblG.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblG.setBounds(38, 116, 20, 28);
		contentPane.add(lblG);
		
		JLabel lblG_1 = new JLabel("G");
		lblG_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblG_1.setBounds(900, 484, 20, 28);
		contentPane.add(lblG_1);
		
		JLabel lblF_1 = new JLabel("F");
		lblF_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblF_1.setBounds(900, 423, 20, 28);
		contentPane.add(lblF_1);
		
		JLabel lblE_1 = new JLabel("E");
		lblE_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblE_1.setBounds(900, 361, 20, 28);
		contentPane.add(lblE_1);
		
		JLabel label_3 = new JLabel("D");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 24));
		label_3.setBounds(900, 299, 20, 28);
		contentPane.add(label_3);
		
		JLabel lblC_1 = new JLabel("C");
		lblC_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblC_1.setBounds(900, 238, 20, 28);
		contentPane.add(lblC_1);
		
		JLabel lblB_1 = new JLabel("B");
		lblB_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblB_1.setBounds(900, 177, 20, 28);
		contentPane.add(lblB_1);
		
		JLabel lblA_1 = new JLabel("A");
		lblA_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblA_1.setBounds(900, 116, 20, 28);
		contentPane.add(lblA_1);
		
		f1 = new JButton("1");
		f1.setMargin(new Insets(0, 0, 0, 0));
		f1.setForeground(Color.BLACK);
		f1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f1.setBackground(Color.GREEN);
		f1.setBounds(68, 401, 55, 50);
		f1.addActionListener(this);
		contentPane.add(f1);
		
		f2 = new JButton("2");
		f2.setMargin(new Insets(0, 0, 0, 0));
		f2.setForeground(Color.BLACK);
		f2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f2.setBackground(Color.GREEN);
		f2.setBounds(133, 401, 55, 50);
		f2.addActionListener(this);
		contentPane.add(f2);
		
		f3 = new JButton("3");
		f3.setMargin(new Insets(0, 0, 0, 0));
		f3.setForeground(Color.BLACK);
		f3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f3.setBackground(Color.GREEN);
		f3.setBounds(200, 401, 55, 50);
		f3.addActionListener(this);
		contentPane.add(f3);
		
		f4 = new JButton("4");
		f4.setMargin(new Insets(0, 0, 0, 0));
		f4.setForeground(Color.BLACK);
		f4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f4.setBackground(Color.GREEN);
		f4.setBounds(265, 401, 55, 50);
		f4.addActionListener(this);
		contentPane.add(f4);
		
		f5 = new JButton("5");
		f5.setMargin(new Insets(0, 0, 0, 0));
		f5.setForeground(Color.BLACK);
		f5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f5.setBackground(Color.GREEN);
		f5.setBounds(330, 401, 55, 50);
		f5.addActionListener(this);
		contentPane.add(f5);
		
		f6 = new JButton("6");
		f6.setMargin(new Insets(0, 0, 0, 0));
		f6.setForeground(Color.BLACK);
		f6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f6.setBackground(Color.GREEN);
		f6.setBounds(395, 401, 55, 50);
		f6.addActionListener(this);
		contentPane.add(f6);
		
		f7 = new JButton("7");
		f7.setMargin(new Insets(0, 0, 0, 0));
		f7.setForeground(Color.BLACK);
		f7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f7.setBackground(Color.GREEN);
		f7.setBounds(504, 401, 55, 50);
		f7.addActionListener(this);
		contentPane.add(f7);
		
		f8 = new JButton("8");
		f8.setMargin(new Insets(0, 0, 0, 0));
		f8.setForeground(Color.BLACK);
		f8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f8.setBackground(Color.GREEN);
		f8.setBounds(569, 401, 55, 50);
		f8.addActionListener(this);
		contentPane.add(f8);
		
		f9 = new JButton("9");
		f9.setMargin(new Insets(0, 0, 0, 0));
		f9.setForeground(Color.BLACK);
		f9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f9.setBackground(Color.GREEN);
		f9.setBounds(634, 401, 55, 50);
		f9.addActionListener(this);
		contentPane.add(f9);
		
		f10 = new JButton("10");
		f10.setMargin(new Insets(0, 0, 0, 0));
		f10.setForeground(Color.BLACK);
		f10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f10.setBackground(Color.GREEN);
		f10.setBounds(699, 401, 55, 50);
		f10.addActionListener(this);
		contentPane.add(f10);
		
		f11 = new JButton("11");
		f11.setMargin(new Insets(0, 0, 0, 0));
		f11.setForeground(Color.BLACK);
		f11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f11.setBackground(Color.GREEN);
		f11.setBounds(764, 401, 55, 50);
		f11.addActionListener(this);
		contentPane.add(f11);
		
		f12 = new JButton("12");
		f12.setMargin(new Insets(0, 0, 0, 0));
		f12.setForeground(Color.BLACK);
		f12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		f12.setBackground(Color.GREEN);
		f12.setBounds(829, 401, 55, 50);
		f12.addActionListener(this);
		contentPane.add(f12);
		
		e1 = new JButton("1");
		e1.setMargin(new Insets(0, 0, 0, 0));
		e1.setForeground(Color.BLACK);
		e1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e1.setBackground(Color.GREEN);
		e1.setBounds(68, 339, 55, 50);
		e1.addActionListener(this);
		contentPane.add(e1);
		
		e2 = new JButton("2");
		e2.setMargin(new Insets(0, 0, 0, 0));
		e2.setForeground(Color.BLACK);
		e2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e2.setBackground(Color.GREEN);
		e2.setBounds(133, 339, 55, 50);
		e2.addActionListener(this);
		contentPane.add(e2);
		
		e3 = new JButton("3");
		e3.setMargin(new Insets(0, 0, 0, 0));
		e3.setForeground(Color.BLACK);
		e3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e3.setBackground(Color.GREEN);
		e3.setBounds(200, 339, 55, 50);
		e3.addActionListener(this);
		contentPane.add(e3);
		
		e4 = new JButton("4");
		e4.setMargin(new Insets(0, 0, 0, 0));
		e4.setForeground(Color.BLACK);
		e4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e4.setBackground(Color.GREEN);
		e4.setBounds(265, 339, 55, 50);
		e4.addActionListener(this);
		contentPane.add(e4);
		
		e5 = new JButton("5");
		e5.setMargin(new Insets(0, 0, 0, 0));
		e5.setForeground(Color.BLACK);
		e5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e5.setBackground(Color.GREEN);
		e5.setBounds(330, 339, 55, 50);
		e5.addActionListener(this);
		contentPane.add(e5);
		
		e6 = new JButton("6");
		e6.setMargin(new Insets(0, 0, 0, 0));
		e6.setForeground(Color.BLACK);
		e6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e6.setBackground(Color.GREEN);
		e6.setBounds(395, 339, 55, 50);
		e6.addActionListener(this);
		contentPane.add(e6);
		
		e7 = new JButton("7");
		e7.setMargin(new Insets(0, 0, 0, 0));
		e7.setForeground(Color.BLACK);
		e7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e7.setBackground(Color.GREEN);
		e7.setBounds(504, 339, 55, 50);
		e7.addActionListener(this);
		contentPane.add(e7);
		
		e8 = new JButton("8");
		e8.setMargin(new Insets(0, 0, 0, 0));
		e8.setForeground(Color.BLACK);
		e8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e8.setBackground(Color.GREEN);
		e8.setBounds(569, 339, 55, 50);
		e8.addActionListener(this);
		contentPane.add(e8);
		
		e9 = new JButton("9");
		e9.setMargin(new Insets(0, 0, 0, 0));
		e9.setForeground(Color.BLACK);
		e9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e9.setBackground(Color.GREEN);
		e9.setBounds(634, 339, 55, 50);
		e9.addActionListener(this);
		contentPane.add(e9);
		
		e10 = new JButton("10");
		e10.setMargin(new Insets(0, 0, 0, 0));
		e10.setForeground(Color.BLACK);
		e10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e10.setBackground(Color.GREEN);
		e10.setBounds(699, 339, 55, 50);
		e10.addActionListener(this);
		contentPane.add(e10);
		
		e11 = new JButton("11");
		e11.setMargin(new Insets(0, 0, 0, 0));
		e11.setForeground(Color.BLACK);
		e11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e11.setBackground(Color.GREEN);
		e11.setBounds(764, 339, 55, 50);
		e11.addActionListener(this);
		contentPane.add(e11);
		
		e12 = new JButton("12");
		e12.setMargin(new Insets(0, 0, 0, 0));
		e12.setForeground(Color.BLACK);
		e12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		e12.setBackground(Color.GREEN);
		e12.setBounds(829, 339, 55, 50);
		e12.addActionListener(this);
		contentPane.add(e12);
		
		d1 = new JButton("1");
		d1.setMargin(new Insets(0, 0, 0, 0));
		d1.setForeground(Color.BLACK);
		d1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d1.setBackground(Color.GREEN);
		d1.setBounds(68, 277, 55, 50);
		d1.addActionListener(this);
		contentPane.add(d1);
		
		d2 = new JButton("2");
		d2.setMargin(new Insets(0, 0, 0, 0));
		d2.setForeground(Color.BLACK);
		d2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d2.setBackground(Color.GREEN);
		d2.setBounds(133, 277, 55, 50);
		d2.addActionListener(this);
		contentPane.add(d2);
		
		d3 = new JButton("3");
		d3.setMargin(new Insets(0, 0, 0, 0));
		d3.setForeground(Color.BLACK);
		d3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d3.setBackground(Color.GREEN);
		d3.setBounds(200, 277, 55, 50);
		d3.addActionListener(this);
		contentPane.add(d3);
		
		d4 = new JButton("4");
		d4.setMargin(new Insets(0, 0, 0, 0));
		d4.setForeground(Color.BLACK);
		d4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d4.setBackground(Color.GREEN);
		d4.setBounds(265, 277, 55, 50);
		d4.addActionListener(this);
		contentPane.add(d4);
		
		d5 = new JButton("5");
		d5.setMargin(new Insets(0, 0, 0, 0));
		d5.setForeground(Color.BLACK);
		d5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d5.setBackground(Color.GREEN);
		d5.setBounds(330, 277, 55, 50);
		d5.addActionListener(this);
		contentPane.add(d5);
		
		d6 = new JButton("6");
		d6.setMargin(new Insets(0, 0, 0, 0));
		d6.setForeground(Color.BLACK);
		d6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d6.setBackground(Color.GREEN);
		d6.setBounds(395, 277, 55, 50);
		d6.addActionListener(this);
		contentPane.add(d6);
		
		d7 = new JButton("7");
		d7.setMargin(new Insets(0, 0, 0, 0));
		d7.setForeground(Color.BLACK);
		d7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d7.setBackground(Color.GREEN);
		d7.setBounds(504, 277, 55, 50);
		d7.addActionListener(this);
		contentPane.add(d7);
		
		d8 = new JButton("8");
		d8.setMargin(new Insets(0, 0, 0, 0));
		d8.setForeground(Color.BLACK);
		d8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d8.setBackground(Color.GREEN);
		d8.setBounds(569, 277, 55, 50);
		d8.addActionListener(this);
		contentPane.add(d8);
		
		d9 = new JButton("9");
		d9.setMargin(new Insets(0, 0, 0, 0));
		d9.setForeground(Color.BLACK);
		d9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d9.setBackground(Color.GREEN);
		d9.setBounds(634, 277, 55, 50);
		d9.addActionListener(this);
		contentPane.add(d9);
		
		d10 = new JButton("10");
		d10.setMargin(new Insets(0, 0, 0, 0));
		d10.setForeground(Color.BLACK);
		d10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d10.setBackground(Color.GREEN);
		d10.setBounds(699, 277, 55, 50);
		d10.addActionListener(this);
		contentPane.add(d10);
		
		d11 = new JButton("11");
		d11.setMargin(new Insets(0, 0, 0, 0));
		d11.setForeground(Color.BLACK);
		d11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d11.setBackground(Color.GREEN);
		d11.setBounds(764, 277, 55, 50);
		d11.addActionListener(this);
		contentPane.add(d11);
		
		d12 = new JButton("12");
		d12.setMargin(new Insets(0, 0, 0, 0));
		d12.setForeground(Color.BLACK);
		d12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		d12.setBackground(Color.GREEN);
		d12.setBounds(829, 277, 55, 50);
		d12.addActionListener(this);
		contentPane.add(d12);
		
		c1 = new JButton("1");
		c1.setMargin(new Insets(0, 0, 0, 0));
		c1.setForeground(Color.BLACK);
		c1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c1.setBackground(Color.GREEN);
		c1.setBounds(68, 216, 55, 50);
		c1.addActionListener(this);
		contentPane.add(c1);
		
		c2 = new JButton("2");
		c2.setMargin(new Insets(0, 0, 0, 0));
		c2.setForeground(Color.BLACK);
		c2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c2.setBackground(Color.GREEN);
		c2.setBounds(133, 216, 55, 50);
		c2.addActionListener(this);
		contentPane.add(c2);
		
		c3 = new JButton("3");
		c3.setMargin(new Insets(0, 0, 0, 0));
		c3.setForeground(Color.BLACK);
		c3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c3.setBackground(Color.GREEN);
		c3.setBounds(200, 216, 55, 50);
		c3.addActionListener(this);
		contentPane.add(c3);
		
		c4 = new JButton("4");
		c4.setMargin(new Insets(0, 0, 0, 0));
		c4.setForeground(Color.BLACK);
		c4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c4.setBackground(Color.GREEN);
		c4.setBounds(265, 216, 55, 50);
		c4.addActionListener(this);
		contentPane.add(c4);
		
		c5 = new JButton("5");
		c5.setMargin(new Insets(0, 0, 0, 0));
		c5.setForeground(Color.BLACK);
		c5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c5.setBackground(Color.GREEN);
		c5.setBounds(330, 216, 55, 50);
		c5.addActionListener(this);
		contentPane.add(c5);
		
		c6 = new JButton("6");
		c6.setMargin(new Insets(0, 0, 0, 0));
		c6.setForeground(Color.BLACK);
		c6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c6.setBackground(Color.GREEN);
		c6.setBounds(395, 216, 55, 50);
		c6.addActionListener(this);
		contentPane.add(c6);
		
		c7 = new JButton("7");
		c7.setMargin(new Insets(0, 0, 0, 0));
		c7.setForeground(Color.BLACK);
		c7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c7.setBackground(Color.GREEN);
		c7.setBounds(504, 216, 55, 50);
		c7.addActionListener(this);
		contentPane.add(c7);
		
		c8 = new JButton("8");
		c8.setMargin(new Insets(0, 0, 0, 0));
		c8.setForeground(Color.BLACK);
		c8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c8.setBackground(Color.GREEN);
		c8.setBounds(569, 216, 55, 50);
		c8.addActionListener(this);
		contentPane.add(c8);
		
		c9 = new JButton("9");
		c9.setMargin(new Insets(0, 0, 0, 0));
		c9.setForeground(Color.BLACK);
		c9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c9.setBackground(Color.GREEN);
		c9.setBounds(634, 216, 55, 50);
		c9.addActionListener(this);
		contentPane.add(c9);
		
		c10 = new JButton("10");
		c10.setMargin(new Insets(0, 0, 0, 0));
		c10.setForeground(Color.BLACK);
		c10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c10.setBackground(Color.GREEN);
		c10.setBounds(699, 216, 55, 50);
		c10.addActionListener(this);
		contentPane.add(c10);
		
		c11 = new JButton("11");
		c11.setMargin(new Insets(0, 0, 0, 0));
		c11.setForeground(Color.BLACK);
		c11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c11.setBackground(Color.GREEN);
		c11.setBounds(764, 216, 55, 50);
		c11.addActionListener(this);
		contentPane.add(c11);
		
		c12 = new JButton("12");
		c12.setMargin(new Insets(0, 0, 0, 0));
		c12.setForeground(Color.BLACK);
		c12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		c12.addActionListener(this);
		c12.setBackground(Color.GREEN);
		c12.setBounds(829, 216, 55, 50);
		contentPane.add(c12);
		
		b1 = new JButton("1");
		b1.setMargin(new Insets(0, 0, 0, 0));
		b1.setForeground(Color.BLACK);
		b1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b1.setBackground(Color.GREEN);
		b1.addActionListener(this);
		b1.setBounds(68, 155, 55, 50);
		contentPane.add(b1);
		
		b2 = new JButton("2");
		b2.setMargin(new Insets(0, 0, 0, 0));
		b2.addActionListener(this);
		b2.setForeground(Color.BLACK);
		b2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b2.setBackground(Color.GREEN);
		b2.setBounds(133, 155, 55, 50);
		contentPane.add(b2);
		
		b3 = new JButton("3");
		b3.setMargin(new Insets(0, 0, 0, 0));
		b3.addActionListener(this);
		b3.setForeground(Color.BLACK);
		b3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b3.setBackground(Color.GREEN);
		b3.setBounds(200, 155, 55, 50);
		contentPane.add(b3);
		
		b4 = new JButton("4");
		b4.setMargin(new Insets(0, 0, 0, 0));
		b4.addActionListener(this);
		b4.setForeground(Color.BLACK);
		b4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b4.setBackground(Color.GREEN);
		b4.setBounds(265, 155, 55, 50);
		contentPane.add(b4);
		
		b5 = new JButton("5");
		b5.addActionListener(this);
		b5.setMargin(new Insets(0, 0, 0, 0));
		b5.setForeground(Color.BLACK);
		b5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b5.setBackground(Color.GREEN);
		b5.setBounds(330, 155, 55, 50);
		contentPane.add(b5);
		
		b6 = new JButton("6");
		b6.addActionListener(this);
		b6.setMargin(new Insets(0, 0, 0, 0));
		b6.setForeground(Color.BLACK);
		b6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b6.setBackground(Color.GREEN);
		b6.setBounds(395, 155, 55, 50);
		contentPane.add(b6);
		
		b7 = new JButton("7");
		b7.addActionListener(this);
		b7.setMargin(new Insets(0, 0, 0, 0));
		b7.setForeground(Color.BLACK);
		b7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b7.setBackground(Color.GREEN);
		b7.setBounds(504, 155, 55, 50);
		contentPane.add(b7);
		
		b8 = new JButton("8");
		b8.setMargin(new Insets(0, 0, 0, 0));
		b8.setForeground(Color.BLACK);
		b8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b8.setBackground(Color.GREEN);
		b8.setBounds(569, 155, 55, 50);
		b8.addActionListener(this);
		contentPane.add(b8);
		
		b9 = new JButton("9");
		b9.setMargin(new Insets(0, 0, 0, 0));
		b9.setForeground(Color.BLACK);
		b9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b9.setBackground(Color.GREEN);
		b9.addActionListener(this);
		b9.setBounds(634, 155, 55, 50);
		contentPane.add(b9);
		
		b10 = new JButton("10");
		b10.setMargin(new Insets(0, 0, 0, 0));
		b10.addActionListener(this);
		b10.setForeground(Color.BLACK);
		b10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b10.setBackground(Color.GREEN);
		b10.setBounds(699, 155, 55, 50);
		contentPane.add(b10);
		
		b11 = new JButton("11");
		b11.setMargin(new Insets(0, 0, 0, 0));
		b11.setForeground(Color.BLACK);
		b11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b11.addActionListener(this);
		b11.setBackground(Color.GREEN);
		b11.setBounds(764, 155, 55, 50);
		contentPane.add(b11);
		
		b12 = new JButton("12");
		b12.setMargin(new Insets(0, 0, 0, 0));
		b12.setForeground(Color.BLACK);
		b12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b12.setBackground(Color.GREEN);
		b12.addActionListener(this);
		b12.setBounds(829, 155, 55, 50);
		contentPane.add(b12);
		
		a1 = new JButton("1");
		a1.setMargin(new Insets(0, 0, 0, 0));
		a1.addActionListener(this);
		a1.setForeground(Color.BLACK);
		a1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a1.setBackground(Color.GREEN);
		a1.setBounds(68, 94, 55, 50);
		contentPane.add(a1);
		
		a2 = new JButton("2");
		a2.setMargin(new Insets(0, 0, 0, 0));
		a2.setForeground(Color.BLACK);
		a2.addActionListener(this);
		a2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a2.setBackground(Color.GREEN);
		a2.setBounds(133, 94, 55, 50);
		contentPane.add(a2);
		
		a3 = new JButton("3");
		a3.setMargin(new Insets(0, 0, 0, 0));
		a3.addActionListener(this);
		a3.setForeground(Color.BLACK);
		a3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a3.setBackground(Color.GREEN);
		a3.setBounds(200, 94, 55, 50);
		contentPane.add(a3);
		
		a4 = new JButton("4");
		a4.setMargin(new Insets(0, 0, 0, 0));
		a4.setForeground(Color.BLACK);
		a4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a4.setBackground(Color.GREEN);
		a4.addActionListener(this);
		a4.setBounds(265, 94, 55, 50);
		contentPane.add(a4);
		
		a5 = new JButton("5");
		a5.setMargin(new Insets(0, 0, 0, 0));
		a5.addActionListener(this);
		a5.setForeground(Color.BLACK);
		a5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a5.setBackground(Color.GREEN);
		a5.setBounds(330, 94, 55, 50);
		contentPane.add(a5);
		
		a6 = new JButton("6");
		a6.setMargin(new Insets(0, 0, 0, 0));
		a6.setForeground(Color.BLACK);
		a6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a6.setBackground(Color.GREEN);
		a6.setBounds(395, 94, 55, 50);
		a6.addActionListener(this);
		contentPane.add(a6);
		
		a7 = new JButton("7");
		a7.setMargin(new Insets(0, 0, 0, 0));
		a7.setForeground(Color.BLACK);
		a7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a7.setBackground(Color.GREEN);
		a7.addActionListener(this);
		a7.setBounds(504, 94, 55, 50);
		contentPane.add(a7);
		
		a8 = new JButton("8");
		a8.setMargin(new Insets(0, 0, 0, 0));
		a8.setForeground(Color.BLACK);
		a8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a8.setBackground(Color.GREEN);
		a8.addActionListener(this);
		a8.setBounds(569, 94, 55, 50);
		contentPane.add(a8);
		
		a9 = new JButton("9");
		a9.setMargin(new Insets(0, 0, 0, 0));
		a9.setForeground(Color.BLACK);
		a9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a9.setBackground(Color.GREEN);
		a9.addActionListener(this);
		a9.setBounds(634, 94, 55, 50);
		contentPane.add(a9);
		
		a10 = new JButton("10");
		a10.setMargin(new Insets(0, 0, 0, 0));
		a10.setForeground(Color.BLACK);
		a10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a10.setBackground(Color.GREEN);
		a10.setBounds(699, 94, 55, 50);
		a10.addActionListener(this);
		contentPane.add(a10);
		
		a11 = new JButton("11");
		a11.setMargin(new Insets(0, 0, 0, 0));
		a11.setForeground(Color.BLACK);
		a11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a11.setBackground(Color.GREEN);
		a11.addActionListener(this);
		a11.setBounds(764, 94, 55, 50);
		contentPane.add(a11);
		
		a12 = new JButton("12");
		a12.setMargin(new Insets(0, 0, 0, 0));
		a12.setForeground(Color.BLACK);
		a12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		a12.setBackground(Color.GREEN);
		a12.addActionListener(this);
		a12.setBounds(829, 94, 55, 50);
		contentPane.add(a12);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.YELLOW);
		panel_2.setBounds(301, 551, 48, 48);
		contentPane.add(panel_2);
		
		JLabel label_7 = new JLabel("Se\u00E7ili Koltuk");
		label_7.setBounds(359, 568, 96, 14);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("Bo\u015F Koltuk");
		label_8.setBounds(509, 568, 80, 14);
		contentPane.add(label_8);
		
		JLabel label_10 = new JLabel("\u00D6\u011Frenci : 10.00 TL");
		label_10.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_10.setBounds(93, 560, 120, 14);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("Normal : 12.00 TL");
		label_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_11.setBounds(93, 585, 120, 14);
		contentPane.add(label_11);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.GREEN);
		panel_5.setBounds(451, 551, 48, 48);
		contentPane.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.RED);
		panel_6.setBounds(605, 551, 48, 48);
		contentPane.add(panel_6);
		
		JLabel label_12 = new JLabel("Dolu Koltuk");
		label_12.setBounds(674, 568, 80, 14);
		contentPane.add(label_12);
		
		JButton btnIslemiGerceklestir = new JButton("\u0130\u015Flemi Ger\u00E7ekle\u015Ftir");
		btnIslemiGerceklestir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				if(biletListesi.isEmpty())
					AnaForm.secilenKoltuklar = null;
				else
					AnaForm.secilenKoltuklar = biletListesi;
				AnaForm.biletListesiniYinele();
				dispose();
				
			}
		});
		btnIslemiGerceklestir.setBackground(Color.WHITE);
		btnIslemiGerceklestir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIslemiGerceklestir.setMargin(new Insets(0, 0, 0, 0));
		btnIslemiGerceklestir.setBounds(12, 11, 136, 51);
		contentPane.add(btnIslemiGerceklestir);
		
		JButton btnk = new JButton("\u0130ptal Et");
		btnk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {				
				cikisOnay();
			}
		});
		btnk.setBackground(Color.WHITE);
		btnk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnk.setMargin(new Insets(0, 0, 0, 0));
		btnk.setBounds(816, 11, 136, 51);
		contentPane.add(btnk);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		koltukSeciminiYap(e);
		
	}
}
