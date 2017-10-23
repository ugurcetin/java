import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Insets;

import javax.swing.AbstractListModel;


public class FilmEkleCikar extends JFrame {

	private JPanel contentPane;
	private JScrollPane filmScrollPane;
	private JLabel lblSeanslar;
	private JScrollPane seansScrollPane;
	private JSeparator separator;
	private JLabel lblYeniFilmEkle;
	private JTextField filmAdiTextField;
	private JLabel lblFilmAdi;
	private JButton btnEkle;
	private JLabel lblSeansEkle;
	private JLabel lblsttenSemiOlduuz;
	private JLabel lblYeniFilmEkleyin;
	private JLabel lblSeiliFilm;
	private JLabel lblSeans;
	private JComboBox cBoxSaatSecimi;
	private JLabel lblSalon;
	private JComboBox cBoxSalonSecimi;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel lblFilmSil;
	private JLabel lblSileceinizFilminAdnseansn;
	private JRadioButton tumuRadioBtn;
	private JRadioButton bSeanstakiSalonlarRadioBtn;
	private JRadioButton bSeanstakiSalonRadioBtn;
	private JRadioButton bTarihtekiSeanslarRadioBtn;
	private JButton button;
	private JButton btnSeansSil;
	private JLabel lblSeciliFilm;
	private JTable filmTable;
	private JLabel lblFilminKodu;
	private JList tarihList;
	private JList salonList;
	private JButton duzenleOnaylaBtn;
	
	private SqlBaglanti sqlBaglanti;
	private FilmIslemleri filmIslemleri;
	private Film film;
	private SeansIslemleri seansIslemleri;
	
	private JLabel lblGbalangTarihi;
	private JTextField txtFieldGosterimBaslangicTarihi;
	private JTextField txtFieldGosterimBitisTarihi;
	private JLabel lblyyyy;
	private JLabel label;
	private JLabel lblSre;
	private JTextField txtFieldSure;
	private JRadioButton ekleRadioBtn;
	private JRadioButton duzenleRadioBtn; 
	private JList seansList;
	
	private ButtonGroup ekleDuzenleBtnGroup;
	private ButtonGroup silBtnGroup;
	private JTextField txtFieldSeansTarih;
	private HashSet hset;
	private JLabel label_1;
	private JLabel lblGecerliTarih;
	private Date gecerliTarih;
	
	private final int TUMUNU_SIL = 0;
	private final int BELIRTILEN_TARIHTEKI_TUM_SEANSLAR = 1;
	private final int BELIRTILEN_SEANSTAKI_TUM_SALONLAR = 2;
	private final int BELIRTILEN_SEANS_SALONU = 3;
	
	
	private void silmeIslemiMesaj(int sonuc){
		
		if(sonuc != 0)
			MesajDiyalog.mesajVer("Silme iþlemi baþarýlý!",MesajDiyalog.MESAJ_BASARILI);
		else
			MesajDiyalog.mesajVer("Silme iþlemi baþarýsýz oldu.",MesajDiyalog.MESAJ_HATA);
		
	}
	
	private int tumunuSil(){
		
		int sonuc1 = sqlBaglanti.updateCalistir("DELETE FROM filmler WHERE filmKodu = " +
				(String)filmTable.getValueAt(filmTable.getSelectedRow(),0));
				
		int sonuc2 = sqlBaglanti.updateCalistir("DELETE FROM filmgosterim WHERE filmKodu = " +
				(String)filmTable.getValueAt(filmTable.getSelectedRow(),0));
		
		return sonuc1 + sonuc2;
		
	}
	
	private int belirtilenTarihtekiTumSeanslarýSil(){
		
		int sonuc = sqlBaglanti.updateCalistir("DELETE FROM filmgosterim WHERE filmKodu = " +
				(String)filmTable.getValueAt(filmTable.getSelectedRow(),0) + " AND tarih = \"" +
				tarihList.getSelectedValue().toString() + "\"");
		return sonuc;
		
	}
	
	private int belirtilenSeanstakiTumSalonlarýSil(){
		
		int sonuc = sqlBaglanti.updateCalistir("DELETE FROM filmgosterim WHERE filmKodu = " +
				(String)filmTable.getValueAt(filmTable.getSelectedRow(),0) + " AND tarih = \"" +
				tarihList.getSelectedValue().toString() + "\" AND seans = \"" +
				seansList.getSelectedValue().toString() + "\"");
		return sonuc;
		
	}
	
	private int belirtilenSeanstakiSalonuSil(){
		
		int sonuc = sqlBaglanti.updateCalistir("DELETE FROM filmgosterim WHERE filmKodu = " +
						(String)filmTable.getValueAt(filmTable.getSelectedRow(),0) + " AND tarih = \"" +
						tarihList.getSelectedValue().toString() + "\" AND seans = \"" +
						seansList.getSelectedValue().toString() + "\" AND salonNo = " + 
						salonList.getSelectedValue().toString());
		return sonuc;
	}
	
	private boolean silKontrol(int tip){
		
		if(filmTable.getSelectedRow() == -1){
			MesajDiyalog.mesajVer("Lütfen öncelikle film seçimi yapýnýz.");
			return false;
		}
		
		if(tip == TUMUNU_SIL)
			return true;
		else if(tip == BELIRTILEN_TARIHTEKI_TUM_SEANSLAR){
			if(tarihList.getSelectedIndex() == -1){
				MesajDiyalog.mesajVer("Lütfen öncelikle tarih seçimi yapýnýz.");
				return false;
			}
		}
		else{ /* BELIRTILEN_SEANSTAKI TUM SALONLAR ve BELIRTILEN_SEANS_SALONU */
			if(tarihList.getSelectedIndex() == -1 || seansList.getSelectedIndex() == -1){
				MesajDiyalog.mesajVer("Tarih ve seans seçimi yaptýðýnýzdan emin olun.");
				return false;
			}
			
			if(tip == BELIRTILEN_SEANS_SALONU && salonList.getSelectedIndex() == -1){
				MesajDiyalog.mesajVer("Salon seçimi yapýnýz.");
				return false;
			}
		}
		
		return true;
	}
	
	private void sil(int tip){
	
		if(silKontrol(tip) == false)
			return;
		
		if(MesajDiyalog.onayIste("Silme iþlemine devam etmek istiyor musunuz?",
				MesajDiyalog.ONAY_VARSAYILAN) == false){
			return;
		}
		
		int sonuc = 0;
		if(tip == TUMUNU_SIL)
			sonuc = tumunuSil();
		else if(tip == BELIRTILEN_TARIHTEKI_TUM_SEANSLAR)
			sonuc = belirtilenTarihtekiTumSeanslarýSil();
		else if(tip == BELIRTILEN_SEANSTAKI_TUM_SALONLAR)
			sonuc = belirtilenSeanstakiTumSalonlarýSil();
		else if(tip == BELIRTILEN_SEANS_SALONU)
			sonuc = belirtilenSeanstakiSalonuSil();
		else
			return;
		
		silmeIslemiMesaj(sonuc);
		if(sonuc != 0){
			tabloyuTemizle();
			filmListesiniYinele();
			tumunuTemizle();
		}
	}

	private	void hazirliklariYap(){
		sqlBaglanti = new SqlBaglanti("root","11741174bk.","sinemadb");
		sqlBaglanti.baglantiyiBaslat();
		filmIslemleri = new FilmIslemleri(sqlBaglanti,"filmler");
		seansIslemleri = new SeansIslemleri(sqlBaglanti,"filmgosterim");
		gecerliTarih = new Date();
		lblGecerliTarih.setText(new SimpleDateFormat("yyyy-MM-dd").format(gecerliTarih.getTime()).toString());
		hset = new HashSet();
		film = new Film("Yok",0);
		filmListesiniYinele();
	}
	
	
	private void tabloyuTemizle(){
		/* Tum tabloyu temizle */
		DefaultTableModel dtModel = (DefaultTableModel)filmTable.getModel();
		dtModel.getDataVector().removeAllElements();
		filmTable.revalidate();
	}
	
	private void kayitTextFieldlariTemizle(){
		filmAdiTextField.setText("");
		txtFieldGosterimBaslangicTarihi.setText("");
		txtFieldGosterimBitisTarihi.setText("");
		txtFieldSure.setText("");
	}
	
	private void seansVeSalonListeleriniTemizle(){
		DefaultListModel seansListModel = new DefaultListModel(); 
		seansListModel.clear();
		seansList.setModel(seansListModel);
		
		DefaultListModel salonListModel = new DefaultListModel(); 
		salonListModel.clear();
		salonList.setModel(salonListModel);
		
	}
	
	private void listeleriTemizle(){
		DefaultListModel tarihListModel = new DefaultListModel(); 
		tarihListModel.clear();
		tarihList.setModel(tarihListModel);
		
		seansVeSalonListeleriniTemizle();
	}
	
	private void tumunuTemizle(){
		kayitTextFieldlariTemizle();
		listeleriTemizle();
	}
	
	private void tarihListesiniYinele(){
		
		if(filmTable.getSelectedRow() == -1)
			return;
		
		DefaultListModel listModel = new DefaultListModel(); 
		listModel.clear();
		tarihList.setModel(listModel);
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
			listModel.add(indis++,iterator.next());
		}
		
	}
	
	private void seansListesiniYinele(){
		
		if(tarihList.getSelectedIndex() == -1)
			return;
		
		DefaultListModel listModel = new DefaultListModel(); 
		listModel.clear();
		seansList.setModel(listModel);
		hset.clear();
		
		ResultSet sonuc = sqlBaglanti.sorguCalistir("SELECT * FROM filmgosterim WHERE tarih = \"" + 
								tarihList.getSelectedValue().toString() + "\"");
		
		try{
			while(sonuc.next()){
				String tarih = sonuc.getString("tarih");
				String filmKodu = sonuc.getString("filmKodu");
				if(tarih.equals(tarihList.getSelectedValue().toString()) && 
			       filmKodu.equals(filmTable.getValueAt(filmTable.getSelectedRow(),0)) ){
					hset.add(sonuc.getString("seans"));
				}
			}
		}
		catch(SQLException sqlException){
			
		}
		
		int indis = 0;
		Iterator iterator = hset.iterator();
		while(iterator.hasNext()){
			listModel.add(indis++,iterator.next());
		}
		
	}
	
	private void salonListesiniYinele(){
		
		if(seansList.getSelectedIndex() == -1)
			return;
		
		DefaultListModel listModel = new DefaultListModel(); 
		listModel.clear();
		salonList.setModel(listModel);
		hset.clear();
		
		ResultSet sonuc = sqlBaglanti.sorguCalistir("SELECT * FROM filmgosterim WHERE seans = \"" + 
								seansList.getSelectedValue().toString() + "\"");
		
		try{
			while(sonuc.next()){
				String tarih = sonuc.getString("tarih");
				String filmKodu = sonuc.getString("filmKodu");
				String seans = sonuc.getString("seans");
				if(tarih.equals(tarihList.getSelectedValue().toString()) && 
				   filmKodu.equals(filmTable.getValueAt(filmTable.getSelectedRow(),0)) &&
				   seans.equals(seansList.getSelectedValue().toString())){
					hset.add(sonuc.getString("salonNo"));
				}
			}
		}
		catch(SQLException sqlException){
			
		}
		
		int indis = 0;
		Iterator iterator = hset.iterator();
		while(iterator.hasNext()){
			listModel.add(indis++,iterator.next());
		}
	}
	
	private void filmListesiniYinele(){
		
		lblSeciliFilm.setText("Yok");
		lblFilminKodu.setText("Yok");
		btnSeansSil.setEnabled(false);
		
		
		int toplamFilmSayisi = filmIslemleri.filmSayisi();
		int satir = 0;
		Object[][] veri = new Object[toplamFilmSayisi][5]; 
		String[] sutunIsýmleri = new String[] {"Film Kodu","Film Adý","Süre(dak)","G.Baþlangýç Tarihi","G.Bitiþ Tarihi"};
		ResultSet sonuc = filmIslemleri.tumFilmler();
				
		try{
			
			while(sonuc.next()){
				veri[satir][0] = sonuc.getString("filmKodu");
				veri[satir][1] = sonuc.getString("filmAdi");
				veri[satir][2] = sonuc.getString("sure");
				veri[satir][3] = sonuc.getDate("gosterimBaslangicTarihi");
				veri[satir][4] = sonuc.getDate("gosterimBitisTarihi");
				
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
				filmTable.getColumnModel().getColumn(2).setResizable(false);
				filmTable.getColumnModel().getColumn(3).setResizable(false);
				filmTable.getColumnModel().getColumn(4).setResizable(false);
			}
			
		}
		catch(SQLException sqlException){
			System.out.println("filmListesiniYinele() : " + sqlException.getMessage());
		}

	}
	
	private void filmDuzenle(Film film){
		
		film.setAdi(filmAdiTextField.getText());
		film.setGosterimBaslangicTarihi(txtFieldGosterimBaslangicTarihi.getText());
		film.setGosterimBitisTarihi(txtFieldGosterimBitisTarihi.getText());
		film.setSure(Integer.parseInt(txtFieldSure.getText()));
		
		boolean sonuc = filmIslemleri.filmDuzenle(film);
		if(sonuc == true){
			filmListesiniYinele();
			tumunuTemizle();
		}
	}
	
	private void filmEkle(Film film){
		filmIslemleri.filmEkle(film);
		filmListesiniYinele();
		tumunuTemizle();
	}

	
	private void seansEkle(){
		
		hset.clear();
		boolean eklemeKontrol = false;
		
		Date gbitisTarih = (Date) filmTable.getValueAt(filmTable.getSelectedRow(),4);
		Date gbaslangicTarih = (Date) filmTable.getValueAt(filmTable.getSelectedRow(),3);
		Date seansTarih = null;
		
		if(txtFieldSeansTarih.getText().isEmpty()){
			MesajDiyalog.mesajVer("Seans tarihi boþ olamaz. Lütfen ilgili alaný doldurun.",MesajDiyalog.MESAJ_UYARI);
			return;
		}
		else{
			try{
				seansTarih = new SimpleDateFormat("yyyy-MM-dd").parse(txtFieldSeansTarih.getText());
			}
			catch(Exception e){
				MesajDiyalog.mesajVer("Tarih formatý uygun deðil.",MesajDiyalog.MESAJ_UYARI);
				return;
			}
		}
		
		if(!(seansTarih.compareTo(gbitisTarih) < 1) || !(seansTarih.compareTo(gbaslangicTarih) > -1)){
			MesajDiyalog.mesajVer("Seans tarihi seçilen filmin gösterim baþlangýç"+
							      "ve bitiþ tarihleri arasýnda olmalýdýr.",MesajDiyalog.MESAJ_UYARI);
			return;
		}
		else if(gecerliTarih.compareTo(gbitisTarih) > 0){
			MesajDiyalog.mesajVer("Seçili filmin gösterimi bitmiþ. Seans eklenemez.",MesajDiyalog.MESAJ_UYARI);
			return;
		}
			
		int salonNo,filmSuresi = 0,saat,dak;
		String salon = cBoxSalonSecimi.getSelectedItem().toString();
		salonNo = Integer.parseInt(salon.substring(salon.length() - 1));
		filmSuresi = Integer.parseInt((String) filmTable.getValueAt(filmTable.getSelectedRow(),2));
		filmSuresi = filmSuresi + 15; /* 15 dk mola :) */
		saat = filmSuresi / 60;
		dak = filmSuresi - (saat * 60);
		Date bitisSaatiTime=null,baslangicSaatiTime=null;
		
		try{
			baslangicSaatiTime = new SimpleDateFormat("H:m").parse(cBoxSaatSecimi.getSelectedItem().toString());
			int bitisSaati,bitisDak;
			bitisSaati = baslangicSaatiTime.getHours() + saat;
			bitisDak = baslangicSaatiTime.getMinutes() + dak;
			if(bitisDak / 60 >= 1){
				bitisSaati += 1;
				bitisDak = bitisDak % 60;
			}
			bitisSaatiTime = new SimpleDateFormat("H:m").parse(bitisSaati + ":" + bitisDak);
		}
		catch(ParseException parseException){
			return;
		}
	
		ResultSet salonlar = sqlBaglanti.sorguCalistir("SELECT * FROM filmgosterim WHERE tarih = \"" +
													   new SimpleDateFormat("yyyy-MM-dd").format(seansTarih) + "\"" +
													   " AND salonNo =  " + salonNo);
		boolean kontrol = false;
		try{
			while(salonlar.next()){
					eklemeKontrol = true;
				try{
					
					Date seansBaslangici = new SimpleDateFormat("H:m").parse(salonlar.getString("seans"));
					Date seansBitisi = new SimpleDateFormat("H:m").parse(salonlar.getString("seansBitis"));

					if(baslangicSaatiTime.compareTo(seansBaslangici) < 0 && 
					   baslangicSaatiTime.compareTo(seansBitisi) < 0 && 
					   bitisSaatiTime.compareTo(seansBaslangici) < 0 && 
					   bitisSaatiTime.compareTo(seansBitisi) < 0){
						kontrol = true;
					}
					else if(baslangicSaatiTime.compareTo(seansBaslangici) > 0&& 
							baslangicSaatiTime.compareTo(seansBitisi) > 0 && 
					     	bitisSaatiTime.compareTo(seansBaslangici) > 0 && 
							bitisSaatiTime.compareTo(seansBitisi) > 0){
						kontrol = true;
					}
					else{
						kontrol = false;
						break;
					}
				}
				catch(ParseException parseException){
					
				}
				
			}
		}
		catch(SQLException sqlException){
			System.out.println(sqlException.getMessage());
		}
		
		if(kontrol == false && eklemeKontrol == true){
			MesajDiyalog.mesajVer("Seans eklenemedi.(Belirtilen saat aralýklarýnda o salonda " + 
								  "baþka film var)");		
		}
		else{
			FilmSeans filmSeans = new FilmSeans(
					Integer.parseInt((String)filmTable.getValueAt(filmTable.getSelectedRow(),0)),
					salonNo,
					new SimpleDateFormat("H:m").format(baslangicSaatiTime),
					new SimpleDateFormat("H:m").format(bitisSaatiTime),
					new SimpleDateFormat("yyyy-MM-dd").format(seansTarih));
			int sonuc = seansIslemleri.seansEkle(filmSeans);
			
			if(sonuc == 1)
				MesajDiyalog.mesajVer("Seans ekleme baþarýlý!");
			else{
				MesajDiyalog.mesajVer("Seans eklenemedi!",MesajDiyalog.MESAJ_HATA);
			}
			listeleriTemizle();
		}


	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilmEkleCikar frame = new FilmEkleCikar();
					
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
	public FilmEkleCikar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				hazirliklariYap();
			}
		});
		setResizable(false);
		setTitle("Film Ekle/\u00C7\u0131kar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1114, 580);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMev = new JLabel("Mevcut Film Listesi");
		lblMev.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMev.setBounds(10, 10, 200, 31);
		contentPane.add(lblMev);
		
		filmScrollPane = new JScrollPane();
		filmScrollPane.setBounds(10, 46, 568, 133);
		contentPane.add(filmScrollPane);
		
		filmTable = new JTable();
		filmTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seansVeSalonListeleriniTemizle();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				int seciliSatir = filmTable.getSelectedRow();
				
				if(seciliSatir == -1)
					return;
				
				film.setAdi((String) filmTable.getValueAt(seciliSatir,1));
				film.setKodu(Integer.parseInt((String)filmTable.getValueAt(seciliSatir,0)));
				film.setSure(Integer.parseInt((String)filmTable.getValueAt(seciliSatir,2)));
				film.setGosterimBaslangicTarihi(simpleDateFormat.format(filmTable.getValueAt(seciliSatir,3)));
				film.setGosterimBitisTarihi(simpleDateFormat.format(filmTable.getValueAt(seciliSatir,4)));
				lblSeciliFilm.setText(film.getAdi());
				lblFilminKodu.setText(Integer.toString(film.getKodu()));
				btnSeansSil.setEnabled(true);
				tarihListesiniYinele();
				
				if(duzenleRadioBtn.isSelected()){
					filmAdiTextField.setText(film.getAdi());
					txtFieldGosterimBaslangicTarihi.setText(film.getGosterimBaslangicTarihi());
					txtFieldGosterimBitisTarihi.setText(film.getGosterimBitisTarihi());
					txtFieldSure.setText(String.valueOf(film.getSure()));
				}
				
			}
		});
		filmTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		filmTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Film Kodu", "Film Ad\u0131", "S\u00FCre(dak)", "G.Ba\u015Flang\u0131\u00E7 Tarihi", "G.Biti\u015F Tarihi"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		filmTable.getColumnModel().getColumn(0).setResizable(false);
		filmTable.getColumnModel().getColumn(0).setPreferredWidth(64);
		filmTable.getColumnModel().getColumn(0).setMaxWidth(72);
		filmTable.getColumnModel().getColumn(1).setResizable(false);
		filmTable.getColumnModel().getColumn(2).setResizable(false);
		filmTable.getColumnModel().getColumn(2).setMinWidth(20);
		filmTable.getColumnModel().getColumn(3).setResizable(false);
		filmTable.getColumnModel().getColumn(3).setPreferredWidth(96);
		filmTable.getColumnModel().getColumn(3).setMinWidth(96);
		filmTable.getColumnModel().getColumn(4).setResizable(false);
		filmTable.getColumnModel().getColumn(4).setPreferredWidth(96);
		filmTable.getColumnModel().getColumn(4).setMinWidth(96);
		filmScrollPane.setViewportView(filmTable);
		
		lblSeanslar = new JLabel("Seanslar");
		lblSeanslar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeanslar.setBounds(776, 10, 105, 31);
		contentPane.add(lblSeanslar);
		
		seansScrollPane = new JScrollPane();
		seansScrollPane.setBounds(776, 46, 105, 133);
		contentPane.add(seansScrollPane);
		
		seansList = new JList();
		seansList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				salonListesiniYinele();
			}
		});
		seansList.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		seansScrollPane.setViewportView(seansList);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(388, 208, 1, 322);
		contentPane.add(separator);
		
		lblYeniFilmEkle = new JLabel("Yeni Film Ekle/D\u00FCzenle");
		lblYeniFilmEkle.setHorizontalAlignment(SwingConstants.CENTER);
		lblYeniFilmEkle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblYeniFilmEkle.setBounds(10, 224, 368, 31);
		contentPane.add(lblYeniFilmEkle);
		
		filmAdiTextField = new JTextField();
		filmAdiTextField.setBounds(144, 344, 234, 25);
		contentPane.add(filmAdiTextField);
		filmAdiTextField.setColumns(10);
		
		lblFilmAdi = new JLabel("Film Ad\u0131 :");
		lblFilmAdi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFilmAdi.setBounds(6, 340, 71, 31);
		contentPane.add(lblFilmAdi);
		
		btnEkle = new JButton("Film Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int sure = 0;
				if(txtFieldSure.getText().isEmpty())
					sure = 0;
				else
					sure = Integer.valueOf(txtFieldSure.getText());
				
				Film yeniFilm = new Film(filmAdiTextField.getText(),
										txtFieldGosterimBaslangicTarihi.getText(),
										txtFieldGosterimBitisTarihi.getText(),
										sure);
				filmEkle(yeniFilm);
			}
		});
		btnEkle.setBackground(SystemColor.menu);
		btnEkle.setBounds(43, 499, 135, 31);
		contentPane.add(btnEkle);
		
		lblSeansEkle = new JLabel("Seans Ekle");
		lblSeansEkle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeansEkle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSeansEkle.setBounds(399, 224, 335, 31);
		contentPane.add(lblSeansEkle);
		
		lblsttenSemiOlduuz = new JLabel("\u00DCstten se\u00E7mi\u015F oldu\u011Fuz filme seans ekleyin");
		lblsttenSemiOlduuz.setHorizontalAlignment(SwingConstants.CENTER);
		lblsttenSemiOlduuz.setBounds(409, 252, 325, 45);
		contentPane.add(lblsttenSemiOlduuz);
		
		lblYeniFilmEkleyin = new JLabel("Yeni Film Ekleyin/D\u00FCzenleyin");
		lblYeniFilmEkleyin.setHorizontalAlignment(SwingConstants.CENTER);
		lblYeniFilmEkleyin.setBounds(20, 252, 335, 31);
		contentPane.add(lblYeniFilmEkleyin);
		
		lblSeiliFilm = new JLabel("Se\u00E7ili Film : ");
		lblSeiliFilm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeiliFilm.setBounds(421, 309, 78, 31);
		contentPane.add(lblSeiliFilm);
		
		lblSeans = new JLabel("Seans :");
		lblSeans.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeans.setBounds(421, 378, 71, 31);
		contentPane.add(lblSeans);
		
		cBoxSaatSecimi = new JComboBox();
		cBoxSaatSecimi.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:00", "13:30", "14:00", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30"}));
		cBoxSaatSecimi.setBounds(479, 381, 143, 26);
		contentPane.add(cBoxSaatSecimi);
		
		lblSalon = new JLabel("Salon :");
		lblSalon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSalon.setBounds(421, 416, 71, 31);
		contentPane.add(lblSalon);
		
		cBoxSalonSecimi = new JComboBox();
		cBoxSalonSecimi.setModel(new DefaultComboBoxModel(new String[] {"Salon-1", "Salon-2", "Salon-3"}));
		cBoxSalonSecimi.setBounds(479, 420, 143, 26);
		contentPane.add(cBoxSalonSecimi);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 190, 1078, 2);
		contentPane.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(744, 208, 1, 322);
		contentPane.add(separator_2);
		
		lblFilmSil = new JLabel("Film Sil");
		lblFilmSil.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilmSil.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFilmSil.setBounds(753, 224, 335, 31);
		contentPane.add(lblFilmSil);
		
		lblSileceinizFilminAdnseansn = new JLabel("<html>Silece\u011Finiz filmin ad\u0131n\u0131,seans\u0131n\u0131 ve salonunu ait \r\n<br>bilgilerini se\u00E7iniz<html>");
		lblSileceinizFilminAdnseansn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSileceinizFilminAdnseansn.setBounds(755, 262, 343, 31);
		contentPane.add(lblSileceinizFilminAdnseansn);
		
		ekleDuzenleBtnGroup = new ButtonGroup();
		silBtnGroup = new ButtonGroup();
		
		tumuRadioBtn = new JRadioButton("T\u00FCm\u00FC(Film ve filme ait seans bilgilerini siler)");
		tumuRadioBtn.setSelected(true);
		tumuRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
		
		tumuRadioBtn.setBackground(Color.WHITE);
		tumuRadioBtn.setBounds(776, 300, 312, 31);
		silBtnGroup.add(tumuRadioBtn);
		contentPane.add(tumuRadioBtn);
		
		bSeanstakiSalonlarRadioBtn = new JRadioButton("Se\u00E7ilen filme ait seanstaki t\u00FCm salonlar siler.");
		bSeanstakiSalonlarRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		bSeanstakiSalonlarRadioBtn.setBackground(Color.WHITE);
		bSeanstakiSalonlarRadioBtn.setBounds(776, 379, 312, 31);
		silBtnGroup.add(bSeanstakiSalonlarRadioBtn);
		contentPane.add(bSeanstakiSalonlarRadioBtn);
		
		bSeanstakiSalonRadioBtn = new JRadioButton("Sadece belirtilen seans salonunu siler.");
		bSeanstakiSalonRadioBtn.setBackground(Color.WHITE);
		bSeanstakiSalonRadioBtn.setBounds(776, 417, 256, 31);
		silBtnGroup.add(bSeanstakiSalonRadioBtn);
		contentPane.add(bSeanstakiSalonRadioBtn);
		
		bTarihtekiSeanslarRadioBtn = new JRadioButton("Se\u00E7ilen filmin belirtilen tarihteki seanslar\u0131 siler.");
		bTarihtekiSeanslarRadioBtn.setBackground(Color.WHITE);
		bTarihtekiSeanslarRadioBtn.setBounds(776, 338, 312, 31);
		silBtnGroup.add(bTarihtekiSeanslarRadioBtn);
		contentPane.add(bTarihtekiSeanslarRadioBtn);
		
		button = new JButton("Sil");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tumuRadioBtn.isSelected())
					sil(TUMUNU_SIL);
				else if(bTarihtekiSeanslarRadioBtn.isSelected())
					sil(BELIRTILEN_TARIHTEKI_TUM_SEANSLAR);
				else if(bSeanstakiSalonlarRadioBtn.isSelected())
					sil(BELIRTILEN_SEANSTAKI_TUM_SALONLAR);
				else if(bSeanstakiSalonRadioBtn.isSelected())
					sil(BELIRTILEN_SEANS_SALONU);	
				
			}
		});
		button.setBackground(SystemColor.menu);
		button.setBounds(874, 499, 115, 31);
		contentPane.add(button);
		
		btnSeansSil = new JButton("Seans Ekle");
		btnSeansSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				seansEkle();
			}
		});
		btnSeansSil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//seansEkle();
			}
		});
		btnSeansSil.setEnabled(false);
		btnSeansSil.setBackground(SystemColor.menu);
		btnSeansSil.setBounds(542, 499, 115, 31);
		contentPane.add(btnSeansSil);
		
		lblSeciliFilm = new JLabel("Yok");
		lblSeciliFilm.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeciliFilm.setBounds(495, 311, 209, 29);
		contentPane.add(lblSeciliFilm);
		
		JLabel lblSalonlar = new JLabel("Salonlar");
		lblSalonlar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSalonlar.setBounds(927, 11, 105, 31);
		contentPane.add(lblSalonlar);
		
		JScrollPane salonScrollPane = new JScrollPane();
		salonScrollPane.setBounds(927, 46, 105, 133);
		contentPane.add(salonScrollPane);
		
		salonList = new JList();
		salonScrollPane.setViewportView(salonList);
		
		JLabel label2 = new JLabel("Filmin Kodu :");
		label2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label2.setBounds(421, 340, 87, 31);
		contentPane.add(label2);
		
		lblFilminKodu = new JLabel("Yok");
		lblFilminKodu.setHorizontalAlignment(SwingConstants.LEFT);
		lblFilminKodu.setBounds(507, 349, 209, 14);
		contentPane.add(lblFilminKodu);
		
		lblGbalangTarihi = new JLabel("G.Ba\u015Flang\u0131\u00E7 Tarihi :");
		lblGbalangTarihi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGbalangTarihi.setBounds(6, 378, 143, 31);
		contentPane.add(lblGbalangTarihi);
		
		txtFieldGosterimBaslangicTarihi = new JTextField();
		txtFieldGosterimBaslangicTarihi.setColumns(10);
		txtFieldGosterimBaslangicTarihi.setBounds(144, 382, 122, 25);
		contentPane.add(txtFieldGosterimBaslangicTarihi);
		
		JLabel lblGbitiTarihi = new JLabel("G.Biti\u015F Tarihi :");
		lblGbitiTarihi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGbitiTarihi.setBounds(6, 419, 128, 31);
		contentPane.add(lblGbitiTarihi);
		
		txtFieldGosterimBitisTarihi = new JTextField();
		txtFieldGosterimBitisTarihi.setColumns(10);
		txtFieldGosterimBitisTarihi.setBounds(144, 420, 122, 25);
		contentPane.add(txtFieldGosterimBitisTarihi);
		
		lblyyyy = new JLabel("(yyyy-MM-dd)");
		lblyyyy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblyyyy.setBounds(276, 379, 94, 31);
		contentPane.add(lblyyyy);
		
		label = new JLabel("(yyyy-MM-dd)");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(276, 420, 94, 31);
		contentPane.add(label);
		
		lblSre = new JLabel("S\u00FCre(dak) :");
		lblSre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSre.setBounds(6, 452, 128, 31);
		contentPane.add(lblSre);
		
		txtFieldSure = new JTextField();
		txtFieldSure.setColumns(10);
		txtFieldSure.setBounds(144, 458, 122, 25);
		contentPane.add(txtFieldSure);
		
		ekleRadioBtn = new JRadioButton("Ekle");
		ekleRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEkle.setEnabled(true);
				duzenleOnaylaBtn.setEnabled(false);
				tumunuTemizle();
						
			}
		});
		ekleRadioBtn.setSelected(true);
		ekleRadioBtn.setBackground(Color.WHITE);
		ekleRadioBtn.setBounds(113, 303, 78, 23);
		ekleDuzenleBtnGroup.add(ekleRadioBtn);
		contentPane.add(ekleRadioBtn);

		duzenleRadioBtn = new JRadioButton("D\u00FCzenle");
		duzenleRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(filmTable.getSelectedRow() == -1)
					return;
				
				
				btnEkle.setEnabled(false);
				duzenleOnaylaBtn.setEnabled(true);
				filmAdiTextField.setText(film.getAdi());
				txtFieldGosterimBaslangicTarihi.setText(film.getGosterimBaslangicTarihi());
				txtFieldGosterimBitisTarihi.setText(film.getGosterimBitisTarihi());
				txtFieldSure.setText(String.valueOf(film.getSure()));
				
			}
		});
		duzenleRadioBtn.setBackground(Color.WHITE);
		duzenleRadioBtn.setBounds(193, 303, 71, 23);
		ekleDuzenleBtnGroup.add(duzenleRadioBtn);
		contentPane.add(duzenleRadioBtn);
		
		duzenleOnaylaBtn = new JButton("D\u00FCzenlemeyi Onayla");
		duzenleOnaylaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filmDuzenle(film);
			}
		});
		duzenleOnaylaBtn.setEnabled(false);
		duzenleOnaylaBtn.setMargin(new Insets(0, 0, 0, 0));
		duzenleOnaylaBtn.setBackground(SystemColor.menu);
		duzenleOnaylaBtn.setBounds(212, 499, 143, 31);
		contentPane.add(duzenleOnaylaBtn);
		
		JLabel lblTarih = new JLabel("Tarih :");
		lblTarih.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTarih.setBounds(421, 454, 41, 31);
		contentPane.add(lblTarih);
		
		txtFieldSeansTarih = new JTextField();
		txtFieldSeansTarih.setColumns(10);
		txtFieldSeansTarih.setBounds(478, 458, 144, 25);
		contentPane.add(txtFieldSeansTarih);
		
		JScrollPane tarihScrollPane = new JScrollPane();
		tarihScrollPane.setBounds(626, 46, 105, 133);
		contentPane.add(tarihScrollPane);
		
		tarihList = new JList();
		tarihList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seansListesiniYinele();
			}
		});
		tarihScrollPane.setViewportView(tarihList);
		
		JLabel lblTarih_1 = new JLabel("Tarihler");
		lblTarih_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTarih_1.setBounds(629, 10, 105, 31);
		contentPane.add(lblTarih_1);
		
		label_1 = new JLabel("(yyyy-MM-dd)");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(632, 452, 94, 31);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel("Tarih");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(1027, 0, 81, 14);
		contentPane.add(lblNewLabel);
		
		lblGecerliTarih = new JLabel("");
		lblGecerliTarih.setHorizontalAlignment(SwingConstants.CENTER);
		lblGecerliTarih.setBounds(1027, 13, 81, 14);
		contentPane.add(lblGecerliTarih);
		

		

	}
}
