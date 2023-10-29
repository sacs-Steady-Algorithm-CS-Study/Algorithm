import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_2805 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long[] tree = new long[n];
        long maxH = Long.MIN_VALUE;


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tree[i] = Long.parseLong(st.nextToken());
            maxH = Math.max(maxH, tree[i]);
        }

        long start = 0;
        long end = maxH;

        while (start <= end) {
            long half = (start + end) / 2;
            long getTree = 0;

            for (int i = 0; i < n; i++) {
                if (tree[i] > half) {
                    getTree += tree[i] - half;
                }
            }
            if (getTree >= M) {
                start = half + 1;
            } else {
                end = half - 1;
            }
        }
        System.out.println(end);
    }
}
