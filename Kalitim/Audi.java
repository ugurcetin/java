package Kalitim;

/**
 * Created by UGURCETIN on 14.07.2017.
 */
public class Audi extends Arac {

    private boolean sunroof;//1-var 0-yok

    public Audi(String marka, String model, String renk, int yili, int beygir, boolean sunroof) {
        super(marka, model, renk, yili, beygir);
        this.sunroof = sunroof;
    }

    public boolean isSunroof() {
        return sunroof;
    }

    public void setSunroof(boolean sunroof) {
        this.sunroof = sunroof;
    }

}
