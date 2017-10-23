import java.awt.Color;

import javax.swing.JButton;


public class Koltuk {

	public static final int KOLTUK_BOS = 0;
	public static final int KOLTUK_DOLU = 1;
	public static final int KOLTUK_SECILI = 2;
	
	private int koltukDurumu;
	private JButton bagliBtn;
	private String koltukGrubu;
	private String koltukNo;
	private String koltuk;
	
	public Koltuk(JButton bagliBtn,
				 int koltukDurumu,
				 String koltukGrubu,
				 String koltukNo){
		this.bagliBtn = bagliBtn;
		this.koltukDurumu = koltukDurumu;
		this.koltukGrubu = koltukGrubu;
		this.koltukNo = koltukNo;
		this.koltuk = koltukGrubu + koltukNo;
	}
	
	public void koltukRenginiDegistir(){
		
		if(koltukDurumu == KOLTUK_DOLU){
			MesajDiyalog.mesajVer("Koltuk satýn alýnmýþ seçilemez/iptal edilemez.",MesajDiyalog.MESAJ_UYARI);
			return;
		}
		
		if(koltukDurumu == KOLTUK_SECILI){
			bagliBtn.setBackground(Color.GREEN);
			koltukDurumu = KOLTUK_BOS;
		}
		else{
			bagliBtn.setBackground(Color.YELLOW);
			koltukDurumu = KOLTUK_SECILI;
		}

	}
	
	public void koltukRenginiDegistir(Color color){
			bagliBtn.setBackground(color);
	}
	
	public void koltuguDoluIsaretle(){
			koltukRenginiDegistir(Color.RED);
			koltukDurumu = KOLTUK_DOLU;
	}
	
	public int getKoltukDurumu() {
		return koltukDurumu;
	}
	public void setKoltukDurumu(int koltukDurumu) {
		this.koltukDurumu = koltukDurumu;
	}
	public JButton getBagliBtn() {
		return bagliBtn;
	}
	public void setBagliBtn(JButton bagliBtn) {
		this.bagliBtn = bagliBtn;
	}

	public String getKoltukGrubu() {
		return koltukGrubu;
	}

	public void setKoltukGrubu(String koltukGrubu) {
		this.koltukGrubu = koltukGrubu;
	}

	public String getKoltukNo() {
		return koltukNo;
	}

	public void setKoltukNo(String koltukNo) {
		this.koltukNo = koltukNo;
	}

	public String getKoltuk() {
		return koltuk;
	}

	public void setKoltuk(String koltuk) {
		this.koltuk = koltuk;
	}	
	
	
}
