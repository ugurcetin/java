package Polimorfizim_Abstract;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public abstract class Sekil {

     int kenar1;
     int kenar2;

    public Sekil(int kenar1, int kenar2) {
        this.kenar1 = kenar1;
        this.kenar2 = kenar2;
    }

    public abstract float alanHesapla();
}
