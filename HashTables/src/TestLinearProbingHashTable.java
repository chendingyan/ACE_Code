
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestLinearProbingHashTable {
    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String key;
            try {
                key = br.readLine();
                st.put(key, i);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for(String s: st.keys()){
            try {
                System.out.println(s + " "+ st.get(s));
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
