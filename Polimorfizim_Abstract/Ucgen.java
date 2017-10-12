package Polimorfizim_Abstract;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Ucgen extends Dortgen {

    int kenar3;

    public Ucgen(int kenar1, int kenar2, int kenar3) {
        super(kenar1, kenar2);
        this.kenar3 = kenar3;

    }

    @Override
    public float alanHesapla(){

        System.out.println("UCGEN SINIFI ALAN HESAPLAMA METODU");

        return (this.kenar1 * this.kenar2)/2;
    }
}
