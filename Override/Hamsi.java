package Override;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Hamsi extends Balik {

    private String turu;

    /*
   public Hamsi(){
        super();
    }

    public Hamsi(String turu) {
        this.turu = turu;
    }

    public Hamsi(int yumurtalamaMevsimi, double omru, boolean gocDurumu, String rengi) {
        super(yumurtalamaMevsimi, omru, gocDurumu, rengi);

    }
*/
    public Hamsi(int yumurtalamaMevsimi, double omru, boolean gocDurumu, String rengi, String turu) {
        super(yumurtalamaMevsimi, omru, gocDurumu, rengi);
        this.turu = turu;
    }

    @Override
    protected String balikBilgisiniVer(){


        return super.balikBilgisiniVer() + "Turu:" + this.turu + "\n";
    }
}
