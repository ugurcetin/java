package Class_Object;

/**
 * Created by UGURCETIN on 22.06.2017.
 */
public class Hesap {
    public static void main(String[] args) {
        Ev daire = new Ev();

        daire.boy=7;
        daire.en=5;
        daire.yukseklik=1;

        System.out.println("Dairenin boyu: " + daire.boy +"\n" +
        "Dairenin eni: " + daire.en + "\n" +
        "Dairenin yüksekliği: " + daire.yukseklik + "\n");

        double hacim = daire.boy * daire.en * daire.yukseklik;
        System.out.println("Dairenin hacmi: " + hacim );
    }
}
