package SQL;

import java.sql.ResultSet;

/**
 * Created by UGURCETIN on 11.10.2017.
 */
public interface SQLIslemleri {

    public int ekle(String tabloIsmi,String sutunIsimleri,String degerler);
    public int duzenle(String tabloIsmi, String degisiklikler, String ifade);
    public int cikar(String tabloIsmi,String ifade);
    public boolean varMi(String tabloIsmi,String ifade);
    public ResultSet tumKayitlar(String tabloIsmi);
    public int kayitSayisi(String tabloIsmi);

}
