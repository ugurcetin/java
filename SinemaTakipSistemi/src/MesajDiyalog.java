import javax.swing.JOptionPane;


public class MesajDiyalog {

	
	public static final int MESAJ_HATA = 0;
	public static final int MESAJ_UYARI = 1;
	public static final int MESAJ_BASARILI = 2;
	
	public static final int ONAY_VARSAYILAN = 0;
	public static final int ONAY_EKLEME = 1;
	public static final int ONAY_SILME = 2;
	public static final int ONAY_DUZENLEME = 3;
	public static final int ONAY_OZEL = 4;
	
	public static void mesajVer(String mesaj,int tipi){

		if(tipi == MESAJ_HATA)
			JOptionPane.showMessageDialog(null,mesaj,"Hata!",JOptionPane.ERROR_MESSAGE);
		else if(tipi == MESAJ_BASARILI)
			JOptionPane.showMessageDialog(null,mesaj,"Baþarýlý!",JOptionPane.INFORMATION_MESSAGE);
		else if(tipi == MESAJ_UYARI)
			JOptionPane.showMessageDialog(null,mesaj,"Uyarý!",JOptionPane.WARNING_MESSAGE);
	}
	
	public static void mesajVer(String mesaj){
		mesajVer(mesaj,JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean onayIste(){
			int sonuc = JOptionPane.showConfirmDialog(null,"Ýþleme devam etmek istiyor musunuz?","Onay isteði",
					JOptionPane.YES_NO_OPTION);
			
			if(sonuc == 0)
				return true;
			
			return false;
			
	}
	
	public static boolean onayIste(String mesaj,int tipi){
		
		int sonuc;
		String msg = null;
		
		if(tipi == ONAY_VARSAYILAN)
			return onayIste();
		else if(tipi == ONAY_EKLEME)
			msg = "Ekleme";
		else if(tipi == ONAY_SILME)
			msg = "Silme";
		else if(tipi == ONAY_DUZENLEME)
			msg = "Düzenleme";
		
		if(tipi == ONAY_OZEL)
			sonuc = JOptionPane.showConfirmDialog(null,mesaj,"Onay isteði",JOptionPane.YES_OPTION);
		else
			sonuc = JOptionPane.showConfirmDialog(null,msg + " iþlemine devam etmek istiyor musunuz?","Onay isteði",
					JOptionPane.YES_OPTION);

		if(sonuc == 0)
			return true;
		
		return false;
	}
	
	public static boolean onayIste(int tipi){
		return onayIste(null,tipi);
	}
	
}
