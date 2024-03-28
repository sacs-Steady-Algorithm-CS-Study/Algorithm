import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_5052 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            HashSet<String> hs = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            String[] arr = new String[N];
            int minLen = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                arr[j] = br.readLine();
                hs.add(arr[j]);
                minLen = Math.min(minLen, arr[j].length());
            }
            boolean consist = true;
            for (int j = 0; j < N; j++) {
                if (minLen < arr[j].length()) {
                    for (int k = minLen; k < arr[j].length(); k++) {
                        String key = arr[j].substring(0, k);
                        if (hs.contains(key)) {
                            consist = false;
                            break;
                        }
                    }
                }
                if (!consist) break;
            }
            if (consist) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }
}
