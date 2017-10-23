
public class FilmSeans {

	private int filmKodu;
	private int salonNo;
	private String baslangicSaati;
	private String bitisSaati;
	private String tarih;
	
	public FilmSeans(int filmKodu,
					 int salonNo,
					 String baslangicSaati,
					 String bitisSaati,
					 String tarih){
		this.filmKodu = filmKodu;
		this.salonNo = salonNo;
		this.baslangicSaati = baslangicSaati;
		this.bitisSaati = bitisSaati;
		this.tarih = tarih;
	}

	public int getFilmKodu() {
		return filmKodu;
	}

	public void setFilmKodu(int filmKodu) {
		this.filmKodu = filmKodu;
	}

	public int getSalonNo() {
		return salonNo;
	}

	public void setSalonNo(int salonNo) {
		this.salonNo = salonNo;
	}

	public String getBaslangicSaati() {
		return baslangicSaati;
	}

	public void setBaslangicSaati(String baslangicSaati) {
		this.baslangicSaati = baslangicSaati;
	}

	public String getBitisSaati() {
		return bitisSaati;
	}

	public void setBitisSaati(String bitisSaati) {
		this.bitisSaati = bitisSaati;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	
}
