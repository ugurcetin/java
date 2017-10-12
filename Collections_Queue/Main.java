package Collections_Queue;

import java.util.LinkedList;
import java.util.Queue;
/**
 * Created by UGURCETIN on 18.07.2017.
 */
public class Main {
    public static void main(String[] args) {

        Queue queue = new LinkedList();

        queue.add("Şeyma");
        queue.add("Burak");
        queue.add("Berkan");

        String nextElement = (String) queue.peek();
        nextElement = (String) queue.poll();

        System.out.println();
    }
}
