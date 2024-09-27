package pyoki32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Sol_14426 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <str.length() ; j++){
                sb.append(str.charAt(j));
                if(!hs.contains(sb.toString())){
                    hs.add(sb.toString());
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            String mStr = br.readLine();
            if(hs.contains(mStr)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}