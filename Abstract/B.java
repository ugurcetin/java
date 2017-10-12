package Abstract;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class B extends  A {

    @Override //ABSTRACT SINIFIN METODLARINI OVERRİDE ETMEK ZORUNDASIN
    int hesapla() {
        return 0;
    }

    @Override
    int topla() {
        return 0;
    }

    // A a = new A(); ABSTRACT SINIFIN TİPİNDE NESNE TANIMLANAMAZ!!!

    
}
