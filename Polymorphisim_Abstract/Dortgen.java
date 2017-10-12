package Polimorfizim_Abstract;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Dortgen extends Sekil {

    public Dortgen(int kenar1, int kenar2) {
        super(kenar1, kenar2);
    }

    @Override
    public float alanHesapla(){

        System.out.println("DORTGEN SINIFI ALAN HESAPLAMA METODU");

        return this.kenar1 * this.kenar2;
    }
}
