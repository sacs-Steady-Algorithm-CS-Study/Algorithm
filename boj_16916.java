
import java.io.*;
import java.util.*;

public class boj_16916 {
    static String S, P;
    static int size;
    static int[] pi;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        P = br.readLine();
        size = P.length();
        pi = new int[size];
        System.out.println(KMP());
    }
    static int KMP(){
        // j 접두사
        int j =0;
        // 모르겠넹...
        // i 접미사
        for(int i = 1; i < size; ++i){
            while(j > 0 && P.charAt(i) != P.charAt(j)) {
                System.out.println(j + " j value");
                j = pi[j - 1];
            }
            if(P.charAt(i) == P.charAt(j))
                pi[i] = ++j;
        }

        j = 0;
        for(int i = 0; i < S.length(); ++i){
            while(j > 0 && S.charAt(i) != P.charAt(j))
                j = pi[j-1];
            if(S.charAt(i) == P.charAt(j)){
                if(j == size-1){
                    return 1;
                }
                else ++j;
            }
        }
        return 0;
    }
}