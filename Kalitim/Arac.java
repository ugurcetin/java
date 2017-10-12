package Kalitim;

/**
 * Created by UGURCETIN on 14.07.2017.
 */
public class Arac {
    private String marka;
    private String model;
    private String renk;
    private int yili;
    private int beygir;


    public Arac(String marka, String model, String renk, int yili, int beygir) {
        this.marka = marka;
        this.model = model;
        this.renk = renk;
        this.yili = yili;
        this.beygir = beygir;
    }

    public void gazaBas(){
        System.out.println("Gaza basildi.");
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public int getYili() {
        return yili;
    }

    public void setYili(int yili) {
        this.yili = yili;
    }

    public int getBeygir() {
        return beygir;
    }

    public void setBeygir(int beygir) {
        this.beygir = beygir;
    }


}
