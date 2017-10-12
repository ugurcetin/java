package enumornek;

/**
 * Created by UGURCETIN on 29.10.2016.
 */
public class Main {

    public static void main(String[] args) {

        Mesaj mesaj = new Mesaj();
        mesaj.icerik = "dosya ulasilamadi!";
        mesaj.tur = MesajTuru.HATA;

        if(mesaj.tur == MesajTuru.HATA){
            System.out.println("hata mesajı");
        }
        else if(mesaj.tur == MesajTuru.UYARI){
            System.out.println("uyari mesaj");
        }
    }
}
