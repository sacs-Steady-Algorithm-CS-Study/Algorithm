import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_1202 {
    static class JW {
        int M, V;

        public JW(int M, int V) {
            this.M = M;
            this.V = V;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<JW> pq = new PriorityQueue<>((j1, j2) -> {
            if (j1.M == j2.M) {
                return j2.V - j1.V;
            }
            return j1.M - j2.M;
        });
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            pq.add(new JW(M, V));
        }
        int[] bag = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bag[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(bag);
        long answer = 0;
        PriorityQueue<Integer> valuePq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < K; i++) {
            while (!pq.isEmpty()) {
                if (bag[i] >= pq.peek().M) {
                    valuePq.add(pq.poll().V);
                } else {
                    break;
                }
            }
            if (valuePq.isEmpty()) continue;
            answer += valuePq.poll();
        }
        System.out.println(answer);
    }
}
