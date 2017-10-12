package Methods;

/**
 * Created by UGURCETIN on 9.07.2017.
 */
public class Arsa {
    double en;
    double boy;

    double alanHesapla(){
        return  en*boy;
    }
}

class Hesap {

    public static void main(String[] args) {

        double alan;
        Arsa arsa = new Arsa();
        Arsa arsa2 = new Arsa();

        arsa.en = 10;
        arsa.boy = 10;

        alan = arsa.en*arsa.boy;
        System.out.println("Arsanın alanı = " + alan);

        System.out.println("Metodtan gelen alan = " + arsa.alanHesapla());
    }
}
