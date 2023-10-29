import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_16916 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String P = br.readLine();
        if(KMP(S,P)){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
    public static int[] makeTable(String pattern) {
        int[] table = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }
        return table;
    }

    public static boolean KMP(String S, String P) {
        boolean include = false;
        int[] pTable = makeTable(P);
        int j = 0;

        for (int i = 0; i < S.length(); i++) {
            while (j > 0 && S.charAt(i) != P.charAt(j)) {
                j = pTable[j - 1];
            }
            if (S.charAt(i) == P.charAt(j)) {
                if (j == P.length() - 1) {
                    include = true;
                    break;
                } else {
                    j++;
                }
            }
        }
        return include;
    }
}
