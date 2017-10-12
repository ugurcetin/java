package encapsulation;

/**
 * Created by UGURCETIN on 28.10.2016.
 */
public class Kisi {

    private String adSoyad;
    private int yas;

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        if(yas > 0){
            this.yas = yas;
        }

    }
}
