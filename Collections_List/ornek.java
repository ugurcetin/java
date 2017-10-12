package Collections_List;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by UGURCETIN on 25.07.2017.
 */
public class ornek {

        public static void main(String[] args) {
            ArrayList<String> list = new ArrayList<String>();
            list.add("Ahmet");
            list.add("Ozan");
            list.add("Eda");
            list.add("Ahu");


            for (int i = 0; i < list.size(); i++) {
                list.remove(i);
            }

            for (String str : list) {
                System.out.println(str);
            }
        }

}
