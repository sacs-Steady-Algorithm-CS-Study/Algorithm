import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1654 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] lan = new long[K];
        long maxLan = 0;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            lan[i] = Long.parseLong(st.nextToken());
            maxLan = Math.max(maxLan, lan[i]);
        }
        long left = 1;
        long right = maxLan;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            int cnt = 0;
            for (int i = 0; i < K; i++) {
                cnt += lan[i] / mid;
            }
            //길이를 늘려야함
            if (cnt >= N) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
