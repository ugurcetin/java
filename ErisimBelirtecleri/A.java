package ErisimBelirtecleri;

/**
 * Created by UGURCETIN on 12.07.2017.
 */
public class A {
    //public > protected > default > private

    public int sayi1;//Tüm paketler erişebilir
    protected  int sayi2;//Kalıtım sayesinde erişilebilir
    int sayi3;//Kendi paketi içinde
    private int sayi4;// Kendi classında , get-set metodları gerekli

    public int getSayi1() {
        return sayi1;
    }

    public void setSayi1(int sayi1) {
        this.sayi1 = sayi1;
    }

    public int getSayi2() {
        return sayi2;
    }

    public void setSayi2(int sayi2) {
        this.sayi2 = sayi2;
    }

    public int getSayi3() {
        return sayi3;
    }

    public void setSayi3(int sayi3) {
        this.sayi3 = sayi3;
    }

    public int getSayi4() {
        return sayi4;
    }

    public void setSayi4(int sayi4) {
        this.sayi4 = sayi4;
    }


}
