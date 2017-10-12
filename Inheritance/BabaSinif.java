package Kalitim;

/**
 * Created by UGURCETIN on 14.07.2017.
 */
public class BabaSinif {

    double sayi1;
    double sayi2;

    public BabaSinif(double sayi1, double sayi2) {
        this.sayi1 = sayi1;
        this.sayi2 = sayi2;
    }

    public double getSayi1() {
        return sayi1;
    }

    public void setSayi1(double sayi1) {
        this.sayi1 = sayi1;
    }

    public double getSayi2() {
        return sayi2;
    }

    public void setSayi2(double sayi2) {
        this.sayi2 = sayi2;
    }

    class EvlatSinif extends BabaSinif{

        int sayi3;

        public EvlatSinif(double sayi1, double sayi2, int sayi3) {
            super(sayi1, sayi2);
            this.sayi3 = sayi3;
        }
    }

}
