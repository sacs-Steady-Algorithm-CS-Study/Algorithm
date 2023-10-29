import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Sol_14425 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String nString = br.readLine();
            hs.add(nString);
        }
        int ans = 0;

        for (int i = 0; i < M; i++) {
            String mString = br.readLine();
            if(hs.contains(mString)){
                ans++;
            }
        }
        System.out.println(ans);
    }
}
