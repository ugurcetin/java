package Override;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Balik {
    private int YumurtalamaMevsimi;
    private double omru;
    private boolean gocDurumu;
    private String rengi;

    public Balik(){
        super();
    }

    public Balik(int yumurtalamaMevsimi, double omru, boolean gocDurumu, String rengi) {
        YumurtalamaMevsimi = yumurtalamaMevsimi;
        this.omru = omru;
        this.gocDurumu = gocDurumu;
        this.rengi = rengi;
    }

    public int getYumurtalamaMevsimi() {
        return YumurtalamaMevsimi;
    }

    public void setYumurtalamaMevsimi(int yumurtalamaMevsimi) {
        YumurtalamaMevsimi = yumurtalamaMevsimi;
    }

    public double getOmru() {
        return omru;
    }

    public void setOmru(double omru) {
        this.omru = omru;
    }

    public boolean isGocDurumu() {
        return gocDurumu;
    }

    public void setGocDurumu(boolean gocDurumu) {
        this.gocDurumu = gocDurumu;
    }

    public String getRengi() {
        return rengi;
    }

    public void setRengi(String rengi) {
        this.rengi = rengi;
    }

    protected String balikBilgisiniVer(){

        String gocDurumBilgisi ,mevsim;

        if(this.gocDurumu == false) {

            gocDurumBilgisi="GOCMEN DEGIL";
        }

        else{

            gocDurumBilgisi="GOCMEN";
        }

        String sonuc = "Goc durumu:" + gocDurumBilgisi+ "\n" +
                        "Rengi:" + this.rengi+ "\n" +
                        "Omru:" + this.omru + "\n";
        return sonuc;
    }
}
