package enumsuzornek;


/**
 * Created by UGURCETIN on 29.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        Mesaj mesaj = new Mesaj();
        mesaj.mesaj = "dosya ulasilamadi";
        mesaj.mesajTuru = Mesaj.MESAJ_HATA;

        if(mesaj.mesajTuru == Mesaj.MESAJ_HATA){
            System.out.println("hata mesajı");
        }
        else if(mesaj.mesajTuru == Mesaj.MESAJ_UYARI){
            System.out.println("uyari mesaj");
        }
    }
}
