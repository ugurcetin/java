package Polimorfizim_Abstract;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Main {

    public static void main(String[] args) {

        Sekil sekilNesneReferansi;


        Dortgen dortgen = new Dortgen(19,38);
        Ucgen ucgen = new Ucgen(19,05,19);

        sekilNesneReferansi = dortgen;
        System.out.println(sekilNesneReferansi.alanHesapla());

        sekilNesneReferansi = ucgen;
        System.out.println(sekilNesneReferansi.alanHesapla());



    }
}
