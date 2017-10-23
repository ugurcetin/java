import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;

import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.SystemColor;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.DropMode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;


public class AnaForm extends JFrame {

	private JPanel contentPane;
	private static JTextField txtFieldOgrenciSayisi;
	private static JButton btnOnayla;
	private static JTable filmTable;
	private static JComboBox cBoxTarih;
	private static JComboBox cBoxSeans;
	private static JComboBox cBoxSalon;
	private JLabel lblUyari;
	
	private static SqlBaglanti sqlBaglanti;
	private FilmIslemleri filmIslemleri;
	private Film film;
	private SeansIslemleri seansIslemleri;
	private static JLabel lblGecerliTarih;
	private Date gecerliTarih;
	private HashSet hset;
	private JButton btnYerSec;
	private static JLabel lblYerSecimi;
	private static JList biletList;
	
	private static JLabel label_1;
	private static JLabel lbl4;
	private static JLabel label_2;
	private static JLabel lblFilmAd;
	private static JLabel lblSalonNo;
	private static JLabel lblSeans;
	private static JLabel lblToplamHasilat;
	private static JLabel lblGunlukHasilat;
	private static JLabel lblGunlukSatilanBiletSayisi;
	private static JLabel lblToplamSatilanBiletSayisi;
	private static JLabel lblGunlukOgrenciBiletSayisi;
	private static JLabel lblGunlukTamBiletSayisi;
	private static JLabel lblToplamOgrenciBiletSayisi;
	private static JLabel lblToplamTamBiletSayisi;
	private static JLabel lblUcret;
	
	public static Film secilenFilm;
	public static ArrayList secilenKoltuklar;
	public static int biletSayisi = 0;
	public static boolean islemdeMi = false;
	private static int tutar;
	private boolean odemeYapilabilirMi = true;
	
	private static void satinAlmaIsleminiGerceklestir(){
		
		Iterator i = secilenKoltuklar.iterator();
		while(i.hasNext()){
			sqlBaglanti.updateCalistir("INSERT INTO biletler(filmKodu,seans,salonNo,koltuk,tarih) "+
					"VALUES(" + secilenFilm.getKodu() + ",\"" + secilenFilm.getSeans() + "\"," +
					secilenFilm.getSalonNo() + ",\"" + i.next() + "\",\"" + 
					secilenFilm.getTarih() + "\")");
		}
		
		ResultSet update = sqlBaglanti.sorguCalistir("SELECT * FROM satis WHERE tarih = \"" +
												 	 lblGecerliTarih.getText() + "\"");
		int ogrenciBiletSayisi,tamBiletSayisi,gunlukHasilat;
		int iOgrenciBiletSayisi,iTamBiletSayisi;
		ogrenciBiletSayisi = tamBiletSayisi = gunlukHasilat = 0;
		iOgrenciBiletSayisi = Integer.parseInt(txtFieldOgrenciSayisi.getText());
		iTamBiletSayisi = biletSayisi - iOgrenciBiletSayisi;
		try{
			if(update.next()){ /* eger onceden kayit varsa */
				ogrenciBiletSayisi = update.getInt("ogrenciBiletSayisi");
				tamBiletSayisi = update.getInt("tamBiletSayisi");
				gunlukHasilat = update.getInt("gunlukHasilat");
				ogrenciBiletSayisi += iOgrenciBiletSayisi;
				tamBiletSayisi += iTamBiletSayisi;
				gunlukHasilat += iTamBiletSayisi * 12 + iOgrenciBiletSayisi * 10;
				sqlBaglanti.updateCalistir("UPDATE satis SET " +
						"ogrenciBiletSayisi = " + ogrenciBiletSayisi +
						", tamBiletSayisi = " + tamBiletSayisi + 
						", gunlukHasilat = " + gunlukHasilat +
						" WHERE tarih = \"" + lblGecerliTarih.getText() + "\"");
			}
			else{ /* ilk ekleme */
				gunlukHasilat = iTamBiletSayisi * 12 + iOgrenciBiletSayisi * 10;
				sqlBaglanti.updateCalistir("INSERT INTO satis(tarih,ogrenciBiletSayisi,tamBiletSayisi,"+
						"gunlukHasilat) VALUES(\"" + lblGecerliTarih.getText() + "\"," +
						iOgrenciBiletSayisi + "," + iTamBiletSayisi + "," + gunlukHasilat + ")");
			}
		}catch(SQLException sqlException){
			
		}
		
		tutar = 0;
		islemDurumundanCik();
		secilenKoltuklar = null;
		biletListesiniYinele();
		istatistikYinele();
	}
	
	private static void istatistikYinele(){
		
		int tamBiletSayisi,ogrenciBiletSayisi,toplamHasilat,toplamBiletSayisi;
		tamBiletSayisi = ogrenciBiletSayisi = toplamHasilat = toplamBiletSayisi = 0;
		ResultSet satis = sqlBaglanti.sorguCalistir("SELECT * FROM satis");
		
		try{
			while(satis.next()){
				if(satis.getString("tarih").equals(lblGecerliTarih.getText())){
						lblGunlukHasilat.setText(satis.getString("gunlukHasilat") + " TL");
						lblGunlukOgrenciBiletSayisi.setText(satis.getString("ogrenciBiletSayisi"));
						lblGunlukTamBiletSayisi.setText(satis.getString("tamBiletSayisi"));
						lblGunlukSatilanBiletSayisi.setText(Integer.toString(
								satis.getInt("ogrenciBiletSayisi") + 
								satis.getInt("tamBiletSayisi")
								));
				}
				ogrenciBiletSayisi += satis.getInt("ogrenciBiletSayisi");
				tamBiletSayisi += satis.getInt("tamBiletSayisi");
				toplamHasilat += satis.getInt("gunlukHasilat");
			}
			
			toplamBiletSayisi = ogrenciBiletSayisi + tamBiletSayisi;
			lblToplamHasilat.setText(Integer.toString(toplamHasilat) + " TL");
			lblToplamOgrenciBiletSayisi.setText(Integer.toString(ogrenciBiletSayisi));
			lblToplamTamBiletSayisi.setText(Integer.toString(tamBiletSayisi));
			lblToplamSatilanBiletSayisi.setText(Integer.toString(toplamBiletSayisi));
		}
		catch(SQLException sqlException){
		}
		
		
	}
	
	private static void islemDurumunaGec(){
		lblYerSecimi.setText(biletSayisi + " adet yer seçildi");
		lblFilmAd.setText(secilenFilm.getAdi());
		lblSalonNo.setText(Integer.toString(secilenFilm.getSalonNo()));
		lblSeans.setText(secilenFilm.getSeans());
		
		islemdeMi = true;
		txtFieldOgrenciSayisi.setEnabled(true);
		btnOnayla.setEnabled(true);
		filmTable.setEnabled(false);
		cBoxSalon.setEnabled(false);
		cBoxSeans.setEnabled(false);
		cBoxTarih.setEnabled(false);
	}
	
	private static void islemDurumundanCik(){
		biletSayisi = 0;
		lblYerSecimi.setText("0 adet yer seçildi");
		lblFilmAd.setText("Yok");
		lblSalonNo.setText("Yok");
		lblSeans.setText("Yok");
		
		islemdeMi = false;
		txtFieldOgrenciSayisi.setEnabled(false);
		btnOnayla.setEnabled(false);
		filmTable.setEnabled(true);
		cBoxSalon.setEnabled(true);
		cBoxSeans.setEnabled(true);
		cBoxTarih.setEnabled(true);
		lblUcret.setText("0 TL");
		tutar = 0;
		txtFieldOgrenciSayisi.setText("0");
	}

	
	public static void biletListesiniYinele(){
		
		DefaultListModel listModel = new DefaultListModel(); 
		listModel.clear();
		biletList.setModel(listModel);
		if(secilenKoltuklar == null){
			islemDurumundanCik();
			return;
		}
		
		
		Iterator i = secilenKoltuklar.iterator();
		String txt = new String("<html>");
		while(i.hasNext()){
			listModel.addElement(i.next().toString());
		}
		islemDurumunaGec();
		tutar = biletSayisi * 12;
		lblUcret.setText(Integer.toString(tutar) + " TL");
		txtFieldOgrenciSayisi.setText("0");

	}
	
	private void filmTablosunuTemizle(){
		/* Tum tabloyu temizle */
		DefaultTableModel dtModel = (DefaultTableModel)filmTable.getModel();
		dtModel.getDataVector().removeAllElements();
		filmTable.revalidate();
	}
	
	private void hazirliklariYap(){
		sqlBaglanti = new SqlBaglanti("root","11741174bk.","sinemadb");
		sqlBaglanti.baglantiyiBaslat();
		filmIslemleri = new FilmIslemleri(sqlBaglanti,"filmler");
		seansIslemleri = new SeansIslemleri(sqlBaglanti,"filmgosterim");
		gecerliTarih = new Date();
		hset = new HashSet();
		secilenKoltuklar = new ArrayList();
		secilenFilm = new Film();
		lblGecerliTarih.setText(new SimpleDateFormat("yyyy-MM-dd").format(gecerliTarih.getTime()).toString());
		filmListesiniYinele();
	}
	
	private void tarihCBoxYinele(){
	

		if(filmTable.getSelectedRow() == -1)
			return;
		DefaultComboBoxModel cBoxTarihModel = (DefaultComboBoxModel)cBoxTarih.getModel();
		cBoxTarihModel.removeAllElements();
		hset.clear();
		
		ResultSet sonuc = seansIslemleri.tumSeanslar();
		try{
			while(sonuc.next()){
				String filmKodu = sonuc.getString("filmKodu");
				if(filmKodu.equals(filmTable.getValueAt(filmTable.getSelectedRow(),0))){
					hset.add(sonuc.getString("tarih"));
				}
			}
		}
		catch(SQLException sqlException){
			
		}
		
		int indis = 0;
		Iterator iterator = hset.iterator();
		while(iterator.hasNext()){
			cBoxTarihModel.addElement(iterator.next());
		}
		
	}
	
	private void seansCBoxYinele(){
		
		if(cBoxTarih.getSelectedIndex() == -1)
			return;

		DefaultComboBoxModel cBoxSeansModel = (DefaultComboBoxModel)cBoxSeans.getModel();
		cBoxSeansModel.removeAllElements();
		HashSet hset = new HashSet();
		
		ResultSet sonuc = sqlBaglanti.sorguCalistir("SELECT * FROM filmgosterim WHERE tarih = \"" + 
								cBoxTarih.getSelectedItem().toString() + "\"");
		
		try{
			while(sonuc.next()){
				String tarih = sonuc.getString("tarih");
				String filmKodu = sonuc.getString("filmKodu");
				if(tarih.equals(cBoxTarih.getSelectedItem().toString()) && 
			       filmKodu.equals(filmTable.getValueAt(filmTable.getSelectedRow(),0)) ){
					hset.add(sonuc.getString("seans"));
				}
			}
		}
		catch(SQLException sqlException){
			
		}
		Iterator iterator = hset.iterator();
		while(iterator.hasNext()){
			cBoxSeansModel.addElement(iterator.next());
		}
		
	}
	
	private void salonCBoxYinele(){
		
		if(cBoxSeans.getSelectedIndex() == -1)
			return;
		
		DefaultComboBoxModel cBoxSalonModel = (DefaultComboBoxModel)cBoxSalon.getModel();
		cBoxSalonModel.removeAllElements();
		HashSet hset = new HashSet();
		
		ResultSet sonuc = sqlBaglanti.sorguCalistir("SELECT * FROM filmgosterim WHERE seans = \"" + 
								cBoxSeans.getSelectedItem().toString() + "\"");
		
		try{
			while(sonuc.next()){
				String tarih = sonuc.getString("tarih");
				String filmKodu = sonuc.getString("filmKodu");
				String seans = sonuc.getString("seans");
				if(tarih.equals(cBoxTarih.getSelectedItem().toString()) && 
				   filmKodu.equals(filmTable.getValueAt(filmTable.getSelectedRow(),0)) &&
				   seans.equals(cBoxSeans.getSelectedItem().toString())){
					hset.add(sonuc.getString("salonNo"));
				}
			
			}
		}
		catch(SQLException sqlException){
			
		}
		
		Iterator iterator = hset.iterator();
		while(iterator.hasNext()){
			cBoxSalonModel.addElement(iterator.next());
		}
		
	}
	
	private void filmListesiniYinele(){
		
		filmTablosunuTemizle();
		int toplamFilmSayisi = filmIslemleri.filmSayisi();
		int satir = 0;
		Object[][] veri = new Object[toplamFilmSayisi][2]; 
		String[] sutunIsýmleri = new String[] {"Film Kodu","Film Adý"};
		ResultSet sonuc = filmIslemleri.tumFilmler();

		try{
			
			while(sonuc.next()){
				veri[satir][0] = sonuc.getString("filmKodu");
				veri[satir][1] = sonuc.getString("filmAdi");
				satir++;
			}
			
		}
		catch(SQLException sqlException){
			System.out.println("filmListesiniYinele() : " + sqlException.getMessage());
		}
		
		filmTable.setModel(new DefaultTableModel(veri,sutunIsýmleri){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		satir++;
		filmTable.getColumnModel().getColumn(0).setResizable(false);
		filmTable.getColumnModel().getColumn(0).setPreferredWidth(64);
		filmTable.getColumnModel().getColumn(0).setMaxWidth(72);
		filmTable.getColumnModel().getColumn(1).setResizable(false);
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaForm frame = new AnaForm();
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
	public AnaForm() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent paramWindowEvent) {
				
			}
			public void windowLostFocus(WindowEvent paramWindowEvent) {
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent paramWindowEvent) {
				hazirliklariYap();
				istatistikYinele();
			}
		});
		setResizable(false);
		setTitle("Sinema Takip Program\u0131");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 563);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u0130\u015Flem");
		menuBar.add(mnNewMenu);
		
		JMenuItem mnýtmFilmEklekar = new JMenuItem("Film Ekle/\u00C7\u0131kar");
		mnýtmFilmEklekar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FilmEkleCikar filmEkleCikarForm = new FilmEkleCikar();
				filmEkleCikarForm.setVisible(true);
			}
		});
		mnNewMenu.add(mnýtmFilmEklekar);
		
		JMenuItem menuYenile = new JMenuItem("Yenile");
		menuYenile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				filmListesiniYinele();
				DefaultComboBoxModel cBoxTarihModel = (DefaultComboBoxModel)cBoxTarih.getModel();
				cBoxTarihModel.removeAllElements();
				DefaultComboBoxModel cBoxSeansModel = (DefaultComboBoxModel)cBoxSeans.getModel();
				cBoxSeansModel.removeAllElements();
				DefaultComboBoxModel cBoxSalonModel = (DefaultComboBoxModel)cBoxSalon.getModel();
				cBoxSalonModel.removeAllElements();
				
			}
		});
		mnNewMenu.add(menuYenile);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mnýtmk = new JMenuItem("\u00C7\u0131k\u0131\u015F");
		mnýtmk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				dispose();
			}
		});
		mnNewMenu.add(mnýtmk);
		
		JMenu mnHakknda = new JMenu("Hakk\u0131nda");
		mnHakknda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
				MesajDiyalog.mesajVer("Sinema Takip Programý\n" +
						  "Burak Köken - G130910032\n" +
						  "Fýrat Çanakçý - \n" + 
						  "Özge Aydýn - ",MesajDiyalog.MESAJ_BASARILI);
			}
		});

		menuBar.add(mnHakknda);
		contentPane = new JPanel();
		contentPane.setDoubleBuffered(false);
		contentPane.setFocusTraversalKeysEnabled(false);
		contentPane.setVerifyInputWhenFocusTarget(false);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 55, 2, 2);
		contentPane.add(scrollPane);
		
		cBoxSeans = new JComboBox();
		cBoxSeans.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent paramItemEvent) {
				if(paramItemEvent.getStateChange() == ItemEvent.SELECTED)
					salonCBoxYinele();
			}
		});

		cBoxSeans.setBounds(495, 59, 131, 20);
		contentPane.add(cBoxSeans);
		
		JLabel lblFilmler = new JLabel("Film Listesi :");
		lblFilmler.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFilmler.setBounds(10, 30, 200, 27);
		contentPane.add(lblFilmler);
		
		JLabel lblSeanslar = new JLabel("Seanslar :");
		lblSeanslar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSeanslar.setBounds(495, 30, 131, 27);
		contentPane.add(lblSeanslar);
		
		JLabel lblSalon = new JLabel("Salonlar :");
		lblSalon.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSalon.setBounds(646, 30, 131, 27);
		contentPane.add(lblSalon);
		
		cBoxSalon = new JComboBox();
		cBoxSalon.setBounds(646, 59, 131, 20);
		contentPane.add(cBoxSalon);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 59, 330, 118);
		contentPane.add(scrollPane_1);
		
		filmTable = new JTable();
		filmTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
				btnYerSec.setEnabled(true);
				tarihCBoxYinele();
			}
		});
		filmTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Film Kodu", "Film Ad\u0131"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		filmTable.getColumnModel().getColumn(0).setResizable(false);
		filmTable.getColumnModel().getColumn(0).setPreferredWidth(72);
		filmTable.getColumnModel().getColumn(0).setMaxWidth(72);
		filmTable.getColumnModel().getColumn(1).setResizable(false);
		filmTable.getColumnModel().getColumn(1).setPreferredWidth(72);
		scrollPane_1.setViewportView(filmTable);
		
		btnYerSec = new JButton("Yer Se\u00E7");
		btnYerSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				if(cBoxSalon.getSelectedIndex() == -1)
					return;
				
				secilenFilm.setAdi((String) filmTable.getValueAt(filmTable.getSelectedRow(),1));
				secilenFilm.setKodu(Integer.parseInt((String)filmTable.getValueAt(filmTable.getSelectedRow(),0)));
				secilenFilm.setSalonNo(Integer.parseInt(cBoxSalon.getSelectedItem().toString()));
				secilenFilm.setSeans((String)cBoxSeans.getSelectedItem().toString());
				secilenFilm.setTarih((String)cBoxTarih.getSelectedItem().toString());
				
				if(islemdeMi == false)
					biletSayisi = 0;
				
				if(cBoxSalon.getSelectedItem().toString().equals("1")){
					Salon1 salon1 = new Salon1();
					salon1.setVisible(true);
				}
				else if(cBoxSalon.getSelectedItem().toString().equals("2")){
					Salon2 salon2 = new Salon2();
					salon2.setVisible(true);
				}
				else if(cBoxSalon.getSelectedItem().toString().equals("3")){
					Salon3 salon3 = new Salon3();
					salon3.setVisible(true);
				}
			}
		});
		btnYerSec.setEnabled(false);
		btnYerSec.setBackground(SystemColor.control);
		btnYerSec.setBounds(646, 120, 128, 40);
		contentPane.add(btnYerSec);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 197, 787, 20);
		contentPane.add(separator_1);
		
		lblYerSecimi = new JLabel("0 Adet Yer Se\u00E7ildi");
		lblYerSecimi.setForeground(Color.BLACK);
		lblYerSecimi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYerSecimi.setBounds(413, 275, 131, 27);
		contentPane.add(lblYerSecimi);
		
		lbl4 = new JLabel("Film Ad\u0131 : ");
		lbl4.setForeground(Color.BLACK);
		lbl4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl4.setBounds(10, 273, 71, 27);
		contentPane.add(lbl4);
		
		label_1 = new JLabel("Salon :");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 299, 54, 27);
		contentPane.add(label_1);
		
		label_2 = new JLabel("Seans :");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 326, 54, 27);
		contentPane.add(label_2);
		
		JLabel lblKoltukNumaralar = new JLabel("Koltuk Numaralar\u0131 :");
		lblKoltukNumaralar.setForeground(Color.BLACK);
		lblKoltukNumaralar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKoltukNumaralar.setBounds(10, 353, 119, 27);
		contentPane.add(lblKoltukNumaralar);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(391, 205, 2, 297);
		contentPane.add(separator_2);
		
		JLabel lblBiletBilgileri = new JLabel("Bilet Bilgileri");
		lblBiletBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
		lblBiletBilgileri.setForeground(Color.BLACK);
		lblBiletBilgileri.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBiletBilgileri.setBounds(10, 242, 371, 27);
		contentPane.add(lblBiletBilgileri);
		
		lblFilmAd = new JLabel("Yok");
		lblFilmAd.setForeground(SystemColor.windowBorder);
		lblFilmAd.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFilmAd.setBounds(74, 273, 290, 27);
		contentPane.add(lblFilmAd);
		
		lblSalonNo = new JLabel("Yok");
		lblSalonNo.setForeground(SystemColor.windowBorder);
		lblSalonNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalonNo.setBounds(58, 299, 88, 27);
		contentPane.add(lblSalonNo);
		
		lblSeans = new JLabel("Yok");
		lblSeans.setForeground(SystemColor.windowBorder);
		lblSeans.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSeans.setBounds(58, 326, 88, 27);
		contentPane.add(lblSeans);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(149, 362, 230, 105);
		contentPane.add(scrollPane_2);
		
		biletList = new JList();
		scrollPane_2.setViewportView(biletList);
		
		JLabel lbldemeBilgileri = new JLabel("\u00D6deme Bilgileri");
		lbldemeBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
		lbldemeBilgileri.setForeground(Color.BLACK);
		lbldemeBilgileri.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbldemeBilgileri.setBounds(403, 242, 361, 27);
		contentPane.add(lbldemeBilgileri);
		
		JLabel lblrenciSays = new JLabel("\u00D6\u011Frenci Say\u0131s\u0131 :");
		lblrenciSays.setForeground(Color.BLACK);
		lblrenciSays.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblrenciSays.setBounds(413, 313, 119, 27);
		contentPane.add(lblrenciSays);
		
		txtFieldOgrenciSayisi = new JTextField();
		txtFieldOgrenciSayisi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent paramKeyEvent) {
				int sayi;
				try{
					sayi = Integer.parseInt(txtFieldOgrenciSayisi.getText());
				}
				catch(Exception e){
					return;
				}
				if(sayi < 0){
					lblUyari.setText("Negatif olamaz !!!");
					lblUyari.setVisible(true);
					odemeYapilabilirMi = false;
					return;
				}
				else if(sayi > biletSayisi){
					lblUyari.setText("Bilet Sayýsýný Aþtý !!!");
					lblUyari.setVisible(true);
					odemeYapilabilirMi = false;
					return;
				}
				else if(sayi == 0 && biletSayisi == 0){
					lblUyari.setVisible(false);
					odemeYapilabilirMi = false;
					return;
				}
				else{
					lblUyari.setVisible(false);
					odemeYapilabilirMi = true;
				}
					
				tutar = (biletSayisi - sayi) * 12 + sayi * 10;
				lblUcret.setText(Integer.toString(tutar) + " TL");
			}
		});
		txtFieldOgrenciSayisi.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent paramInputMethodEvent) {
			}
			public void inputMethodTextChanged(InputMethodEvent paramInputMethodEvent) {
				
			}
		});
		txtFieldOgrenciSayisi.setEnabled(false);
		txtFieldOgrenciSayisi.setText("0");
		txtFieldOgrenciSayisi.setBounds(510, 315, 116, 25);
		contentPane.add(txtFieldOgrenciSayisi);
		txtFieldOgrenciSayisi.setColumns(10);
		
		JLabel lblToplamcret = new JLabel("Toplam \u00DCcret  : ");
		lblToplamcret.setForeground(Color.BLACK);
		lblToplamcret.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblToplamcret.setBounds(413, 382, 96, 27);
		contentPane.add(lblToplamcret);
		
		lblUcret = new JLabel("0 TL");
		lblUcret.setForeground(Color.BLACK);
		lblUcret.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUcret.setBounds(530, 380, 96, 27);
		contentPane.add(lblUcret);
		
		btnOnayla = new JButton("ONAYLA");

		btnOnayla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				
				if(odemeYapilabilirMi == false){
					MesajDiyalog.mesajVer("Öðrenci sayýsý alanýný kontrol edin. " +
								"Uygun formatta deðil.",MesajDiyalog.MESAJ_UYARI);
					return;
				}
				
				if(MesajDiyalog.onayIste("Satýn alma iþlemine devam etmek istiyor musunuz?",
									MesajDiyalog.ONAY_VARSAYILAN)){
					satinAlmaIsleminiGerceklestir();
					
				}
				
			}
		});
		btnOnayla.setEnabled(false);
		btnOnayla.setBackground(SystemColor.control);
		btnOnayla.setBounds(497, 420, 209, 55);
		contentPane.add(btnOnayla);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(807, 11, 2, 491);
		contentPane.add(separator_3);
		
		JLabel lblTarihler = new JLabel("Tarihler :");
		lblTarihler.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTarihler.setBounds(350, 30, 131, 27);
		contentPane.add(lblTarihler);
		
		cBoxTarih = new JComboBox();
		cBoxTarih.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent paramItemEvent) {
				if(paramItemEvent.getStateChange() == ItemEvent.SELECTED)
					seansCBoxYinele();
			}
		});
		cBoxTarih.setBounds(350, 59, 131, 20);
		contentPane.add(cBoxTarih);
		
		JLabel lblIstatistik = new JLabel("\u0130statistik");
		lblIstatistik.setHorizontalAlignment(SwingConstants.CENTER);
		lblIstatistik.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIstatistik.setBounds(819, 30, 231, 27);
		contentPane.add(lblIstatistik);
		
		lblGecerliTarih = new JLabel("");
		lblGecerliTarih.setHorizontalAlignment(SwingConstants.CENTER);
		lblGecerliTarih.setBounds(903, 11, 81, 14);
		contentPane.add(lblGecerliTarih);
		
		JLabel lblTarih = new JLabel("Tarih :");
		lblTarih.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarih.setForeground(Color.BLUE);
		lblTarih.setBounds(837, 11, 81, 14);
		contentPane.add(lblTarih);
		
		JLabel lblSatlanBiletSays = new JLabel("Sat\u0131lan bilet say\u0131s\u0131 :");
		lblSatlanBiletSays.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSatlanBiletSays.setBounds(819, 112, 131, 27);
		contentPane.add(lblSatlanBiletSays);
		
		JLabel lblToplamHaslat = new JLabel("G\u00FCnl\u00FCk Has\u0131lat :");
		lblToplamHaslat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblToplamHaslat.setBounds(819, 185, 131, 27);
		contentPane.add(lblToplamHaslat);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(809, 93, 251, 20);
		contentPane.add(separator_4);
		
		JLabel lblBugn = new JLabel("Bug\u00FCn");
		lblBugn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBugn.setBounds(819, 62, 131, 27);
		contentPane.add(lblBugn);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(809, 370, 251, 20);
		contentPane.add(separator_5);
		
		JLabel lblTamBilet = new JLabel("Tam bilet say\u0131s\u0131 :");
		lblTamBilet.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTamBilet.setBounds(819, 137, 131, 27);
		contentPane.add(lblTamBilet);
		
		JLabel lblrenciBilet = new JLabel("\u00D6\u011Frenci bilet say\u0131s\u0131 :");
		lblrenciBilet.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblrenciBilet.setBounds(819, 159, 131, 27);
		contentPane.add(lblrenciBilet);
		
		JLabel lblToplam = new JLabel("Toplam");
		lblToplam.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblToplam.setBounds(819, 333, 131, 27);
		contentPane.add(lblToplam);
		
		JLabel label_4 = new JLabel("Sat\u0131lan bilet say\u0131s\u0131 :");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(819, 382, 131, 27);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Tam bilet say\u0131s\u0131 :");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_5.setBounds(819, 407, 131, 27);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u00D6\u011Frenci bilet say\u0131s\u0131 :");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(819, 429, 131, 27);
		contentPane.add(label_6);
		
		JLabel lblToplamHaslat_1 = new JLabel("Toplam Has\u0131lat :");
		lblToplamHaslat_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblToplamHaslat_1.setBounds(819, 455, 131, 27);
		contentPane.add(lblToplamHaslat_1);
		
		lblGunlukSatilanBiletSayisi = new JLabel("0");
		lblGunlukSatilanBiletSayisi.setBounds(960, 120, 71, 13);
		contentPane.add(lblGunlukSatilanBiletSayisi);
		
		lblGunlukTamBiletSayisi = new JLabel("0");
		lblGunlukTamBiletSayisi.setBounds(960, 144, 71, 13);
		contentPane.add(lblGunlukTamBiletSayisi);
		
		lblGunlukOgrenciBiletSayisi = new JLabel("0");
		lblGunlukOgrenciBiletSayisi.setBounds(960, 166, 71, 13);
		contentPane.add(lblGunlukOgrenciBiletSayisi);
		
		lblGunlukHasilat = new JLabel("0 TL");
		lblGunlukHasilat.setBounds(960, 192, 71, 13);
		contentPane.add(lblGunlukHasilat);
		
		lblToplamSatilanBiletSayisi = new JLabel("0");
		lblToplamSatilanBiletSayisi.setBounds(960, 389, 71, 13);
		contentPane.add(lblToplamSatilanBiletSayisi);
		
		lblToplamTamBiletSayisi = new JLabel("0");
		lblToplamTamBiletSayisi.setBounds(960, 414, 71, 13);
		contentPane.add(lblToplamTamBiletSayisi);
		
		lblToplamOgrenciBiletSayisi = new JLabel("0");
		lblToplamOgrenciBiletSayisi.setBounds(960, 440, 71, 13);
		contentPane.add(lblToplamOgrenciBiletSayisi);
		
		lblToplamHasilat = new JLabel("0 TL");
		lblToplamHasilat.setBounds(960, 462, 71, 13);
		contentPane.add(lblToplamHasilat);
		
		lblUyari = new JLabel("Bilet Say\u0131s\u0131n\u0131 A\u015Ft\u0131 !");
		lblUyari.setVisible(false);
		lblUyari.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUyari.setForeground(Color.RED);
		lblUyari.setBounds(648, 319, 198, 14);
		contentPane.add(lblUyari);
	}
}
