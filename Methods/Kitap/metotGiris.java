package Methods.Kitap;

/**
 * Created by UGURCETIN on 16.07.2017.
 */
public class metotGiris {
    public static void main(String[] args) {

        int son;
        int son2;
        double son3;
        int son4;

        son = toplama(21,12);
        son2 = faktoriyelAl();
        son3 = toplama(2.3,1.2);
        son4 = usAl(2,3);
        System.out.println("Sonuc :" + son);
        System.out.println("5 sayýsýnýn faktoriyeli :" + son2);
        System.out.println("Sonuc 2: " + son3);
        System.out.println("kuvvet : " + son4);
    }

//	Eriþim_Belirteci Veri_dönüþ_tipi metotAdý(int deger,String deger){
    //Kodlar
//		return deger;
//	}

    public static int usAl(int taban,int us){
        int sonuc = 0;
        if(taban>0 && us==0) sonuc = 1;
        if(taban>0 && us==1) sonuc = taban;
        if(taban>0 && us>0) sonuc = usAl(taban,us-1)*taban;
        return sonuc;
    }

    public static int toplama(int deger1,int deger2){
        int sonuc;
        sonuc = deger1 + deger2;
        return sonuc;
    }

    public static double toplama(double deger1,double deger2){
        double sonuc;
        sonuc = (2*deger1) + (2*deger2);
        return sonuc;
    }

    public static int faktoriyelAl(){
        int sayi = 5;
        int sonuc = 1;

        for(int i = 1;i<=sayi;i++){
            sonuc = sonuc * i;
        }

        return sonuc;
    }
}
