import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqlBaglanti implements SQLIslemleri {

	private String kullaniciAdi;
	private String kullaniciParola;
	private String veritabaniAdi;
	private String baglantiAdresi;
	
	private Connection baglanti;
	private Statement ifade;
	
	public SqlBaglanti(String kullaniciAdi,String kullaniciParola,String veritabaniAdi){
		this.kullaniciAdi = kullaniciAdi;
		this.kullaniciParola = kullaniciParola;
		this.veritabaniAdi = veritabaniAdi;
		baglantiAdresiOlustur();
	}
	
	private void baglantiAdresiOlustur(){
		baglantiAdresi = "jdbc:mysql://localhost:3306/" + veritabaniAdi;
	}
	
	public void veritabaniDegistir(String veritabaniAdi){
		this.veritabaniAdi = veritabaniAdi;
		baglantiAdresiOlustur();
	}
	
	public void baglantiyiBaslat(){
		
		try{
			baglanti = DriverManager.getConnection(baglantiAdresi, kullaniciAdi, kullaniciParola);
			ifade = baglanti.createStatement();
		}
		catch(SQLException sqlException){
			System.out.println(sqlException.getMessage());
		}
		
	}
	
	public void baglantiyiBitir(){
		
		try{
			baglanti.close();
		}
		catch(SQLException sqlException){
			System.out.println(sqlException.getMessage());
		}
		
	}
	
	public int updateCalistir(String sql){
		
		int sonuc = -1;
		
		try{
			sonuc = ifade.executeUpdate(sql);
		}
		catch(SQLException sqlException){
			System.out.println("sorguUpdate() : " + sqlException.getMessage());
		}
		return sonuc;
		
	}
	
	public ResultSet sorguCalistir(String sql){
		
		ResultSet sonuc = null;
		
		try{

				sonuc =  ifade.executeQuery(sql);
		}
		catch(SQLException sqlException){
			System.out.println("sorguCalistir() : " + sqlException.getMessage());
		}
	
		return sonuc;
		
	}

	@Override
	public int ekle(String tabloIsmi, String sutunIsimleri, String degerler) {
		
		return updateCalistir("INSERT INTO " + tabloIsmi +"("+ sutunIsimleri + ")" + 
				" VALUES(" + degerler + ")");
	}

	@Override
	public int cikar(String tabloIsmi, String ifade) {
		
		return updateCalistir("DELETE * FROM " + tabloIsmi + " WHERE " + ifade);

	}

	@Override
	public boolean varMi(String tabloIsmi, String ifade) {
		
		ResultSet sonuc = sorguCalistir("SELECT * FROM " + tabloIsmi + " WHERE " + ifade);
		
		try{
			if(sonuc.next())
				return true;
		}
		catch(SQLException sqlException){
			System.out.println("varMi() : " + sqlException.getMessage());
		}
		
		return false;
	}

	@Override
	public int kayitSayisi(String tabloIsmi) {
		
		ResultSet sonuc = sorguCalistir("SELECT * FROM " + tabloIsmi);
		try{
			while(sonuc.next()){
				if(sonuc.last())
					return sonuc.getRow();
			}
		}
		catch(SQLException sqlException){
			System.out.println("kayitSayisi() : " + sqlException.getMessage());
		}
		
		return 0;
	}

	@Override
	public ResultSet tumKayitlar(String tabloIsmi) {
		return sorguCalistir("SELECT * FROM " + tabloIsmi);
	}

	@Override
	public int duzenle(String tabloIsmi, String degisiklikler, String ifade) {
		return updateCalistir("UPDATE " + tabloIsmi + " SET " + degisiklikler + " WHERE " + ifade);
	}
	
	
}
