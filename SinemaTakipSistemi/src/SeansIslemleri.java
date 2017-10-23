import java.sql.ResultSet;

public class SeansIslemleri{

	private SqlBaglanti sqlBaglanti;
	private String tabloIsmi;
	
	public SeansIslemleri(SqlBaglanti sqlBaglanti, String tabloIsmi) {
		this.sqlBaglanti = sqlBaglanti;
		this.tabloIsmi = tabloIsmi;
	}
	
	public void baglantiDegistir(SqlBaglanti sqlBaglanti){
		this.sqlBaglanti = sqlBaglanti;
	}
	
	public void tabloIsmiDegistir(String tabloIsmi){
		this.tabloIsmi = tabloIsmi;
	}
	
	public boolean seansUygunMu(){
		return false;
	}
	
	public int seansEkle(FilmSeans filmSeans){
		
		int sonuc = sqlBaglanti.ekle(tabloIsmi,"filmKodu,seans,seansBitis,salonNo,tarih",
				filmSeans.getFilmKodu() + ",\"" + filmSeans.getBaslangicSaati() +
				"\",\"" + filmSeans.getBitisSaati() + "\"," + filmSeans.getSalonNo() + ",\"" +
				filmSeans.getTarih() + "\"");
		
		return sonuc;
		
	}
	
	/**
	 * 
	 * filmin belirtilen seansin bulundugu salonu siler.
	 * @param film
	 */
	public void seanstakiBelirtilenSalonuSil(Film film){
		
	}
	
	/**
	 * filmin belirtilen seanstaki tum salonlari siler
	 * @param film
	 */
	public void seanstakiTumSalonlariSil(Film film){
		
	}
	
	public ResultSet tumSeanslar(){
		
		if(tabloIsmi.isEmpty())
			return null;
			
		return sqlBaglanti.tumKayitlar(tabloIsmi);
		
	}
		
}
