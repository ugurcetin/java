import java.sql.ResultSet;

public class FilmIslemleri {

	private SqlBaglanti sqlBaglanti;
	private String tabloIsmi;
	
	public FilmIslemleri(SqlBaglanti sqlBaglanti,String tabloIsmi){
		this.sqlBaglanti = sqlBaglanti;
		this.tabloIsmi = tabloIsmi;
	}
	
	public void baglantiDegistir(SqlBaglanti sqlBaglanti){
		this.sqlBaglanti = sqlBaglanti;
	}
	
	public void tabloIsmiDegistir(String tabloIsmi){
		this.tabloIsmi = tabloIsmi;
	}
	
	private boolean filmKontrol(Film film){
		
		if(film.getAdi().isEmpty()){
			MesajDiyalog.mesajVer("Film adý boþ olamaz. Lütfen ilgili alaný doldurun.",
								   MesajDiyalog.MESAJ_UYARI);
			return false;
		}
		else if(film.getGosterimBaslangicTarihi().isEmpty()){
			MesajDiyalog.mesajVer("Film gösterim tarihi boþ olamaz.",
								   MesajDiyalog.MESAJ_UYARI);
			return false;
		}
		else if(film.getGosterimBitisTarihi().isEmpty()){
			MesajDiyalog.mesajVer("Film gösterim bitiþ tarihi boþ olamaz", 
								  MesajDiyalog.MESAJ_UYARI);
			return false;
		}
		else if(film.getSure() == 0){
			MesajDiyalog.mesajVer("Film süresi sýfýr(0) olamaz",
								   MesajDiyalog.MESAJ_UYARI);
			return false;
		}
		
		return true;
		
	}
	
	public void filmEkle(Film film){
		
		if(filmKontrol(film) == false)
			return;
		
		if(filmVarMi(film.getAdi()))
			MesajDiyalog.mesajVer("\"" + film.getAdi() + "\" filmi zaten listede mevcut!",
								  MesajDiyalog.MESAJ_UYARI);
		else{
			
			int sonuc = sqlBaglanti.ekle(tabloIsmi,"filmAdi,gosterimBaslangicTarihi,gosterimBitisTarihi,sure",
										"\"" + film.getAdi() + "\",\"" + film.getGosterimBaslangicTarihi() +
										"\",\"" + film.getGosterimBitisTarihi() + "\"," + film.getSure());
			
			if(sonuc == 1)
				MesajDiyalog.mesajVer("\"" + film.getAdi() + "\" filmi listeye baþarýyla eklendi.",
									  MesajDiyalog.MESAJ_BASARILI);
			else
				MesajDiyalog.mesajVer("\"" + film.getAdi() + "\" filmi listeye eklenemedi!",
									  MesajDiyalog.MESAJ_HATA);
			
		}

	}
	
	public boolean filmDuzenle(Film film){
		
		if(filmKontrol(film) == false)
			return false;
		
		if(MesajDiyalog.onayIste(MesajDiyalog.ONAY_DUZENLEME) == false)
			return false;
			
		int sonuc = sqlBaglanti.duzenle(tabloIsmi," filmAdi = \"" + film.getAdi() + "\"," + 
											  " gosterimBaslangicTarihi = \"" + 
											  film.getGosterimBaslangicTarihi() + "\"," +
											  " gosterimBitisTarihi = \"" + 
											  film.getGosterimBitisTarihi() + "\"," +
											  " sure = " + film.getSure(),
											  " filmKodu = " + film.getKodu());
			
		if(sonuc == 1)
			MesajDiyalog.mesajVer("\"" + film.getAdi() + "\" filmi baþarýyla düzenlendi.",
								  MesajDiyalog.MESAJ_BASARILI);
		else{
			MesajDiyalog.mesajVer("\"" + film.getAdi() + "\" filmi düzenlenemedi!",
								  MesajDiyalog.MESAJ_HATA);
			return false;
		}
		
		return true;
		
	}
	
	public void filmCikar(String filmAdi){
		
		if(tabloIsmi.isEmpty())
			return;
		
		sqlBaglanti.cikar(tabloIsmi,"filmAdi = \"" + filmAdi + "\"");
	
	}
	
	public void filmCikar(int filmKodu){
		
		if(tabloIsmi.isEmpty())
			return;
		
		sqlBaglanti.cikar(tabloIsmi,"filmKodu = " + filmKodu);
	
	}
	
	public boolean filmVarMi(String filmAdi){

		if(tabloIsmi.isEmpty())
			return false;
		
		return sqlBaglanti.varMi(tabloIsmi,"filmAdi = \"" + filmAdi + "\"");
		
	}
	
	public ResultSet tumFilmler(){
	
		if(tabloIsmi.isEmpty())
			return null;
		
		return sqlBaglanti.tumKayitlar(tabloIsmi);
	
	}
	
	public int filmSayisi(){
		
		if(tabloIsmi.isEmpty())
			return 0;
		
		return sqlBaglanti.kayitSayisi(tabloIsmi);
	}
	
	
}
