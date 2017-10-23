
public class Film {

	private String adi;
	private int kodu;
	private int salonNo;
	private String seans;
	private int sure;
	private String tarih;
	
	private String gosterimBaslangicTarihi;
	private String gosterimBitisTarihi;
	
	public Film(){
		
	}
	
	public Film(String adi,String gosterimBaslangicTarihi,String gosterimBitisTarihi,int sure){
		this.adi = adi;
		this.gosterimBaslangicTarihi = gosterimBaslangicTarihi;
		this.gosterimBitisTarihi = gosterimBitisTarihi;
		this.sure = sure;
	}
	
	public Film(int kodu,int sure){
		this.kodu = kodu;
		this.sure = sure;
	}
	
	public Film(String adi,int kodu){
		this.adi = adi;
		this.kodu = kodu;
	}
	
	public Film(String adi,int kodu,int salonNo,int sure){
		this.adi = adi;
		this.kodu = kodu;
		this.salonNo = salonNo;
		this.sure = sure;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public int getKodu() {
		return kodu;
	}

	public void setKodu(int kodu) {
		this.kodu = kodu;
	}

	public int getSalonNo() {
		return salonNo;
	}

	public void setSalonNo(int salonNo) {
		this.salonNo = salonNo;
	}

	public int getSure() {
		return sure;
	}

	public void setSure(int sure) {
		this.sure = sure;
	}

	public String getSeans() {
		return seans;
	}

	public void setSeans(String seans) {
		this.seans = seans;
	}

	public String getGosterimBaslangicTarihi() {
		return gosterimBaslangicTarihi;
	}

	public void setGosterimBaslangicTarihi(String gosterimBaslangicTarihi) {
		this.gosterimBaslangicTarihi = gosterimBaslangicTarihi;
	}

	public String getGosterimBitisTarihi() {
		return gosterimBitisTarihi;
	}

	public void setGosterimBitisTarihi(String gosterimBitisTarihi) {
		this.gosterimBitisTarihi = gosterimBitisTarihi;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	
	
	
}
