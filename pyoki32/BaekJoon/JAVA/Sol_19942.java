import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_19942 {
    static class RES {
        int cost;
        String foodIdxStr;

        public RES(int cost, String foodIdxStr) {
            this.cost = cost;
            this.foodIdxStr = foodIdxStr;
        }
    }

    static int[][] food;
    static int[] minNut;
    static int N;
    static int answer;
    static PriorityQueue<RES> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        minNut = new int[4];
        food = new int[N][5];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minNut[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                food[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[] pick = new boolean[N];
        answer = Integer.MAX_VALUE;

        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.cost == o2.cost) {
                return o1.foodIdxStr.compareTo(o2.foodIdxStr);
            }
            return o1.cost - o2.cost;
        });
        pickIdx(pick, 0, 0);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
            System.out.println(Objects.requireNonNull(pq.poll()).foodIdxStr);
        }
    }

    static void pickIdx(boolean[] pick, int start, int depth) {
        solve(pick);
        if (depth > (N / 2) - 1) {
            return;
        }
        for (int i = start; i < N; i++) {
            pick[i] = true;
            pickIdx(pick, i + 1, depth + 1);
            pick[i] = false;
        }
    }

    static void solve(boolean[] pick) {
        int[] leftFood = new int[5];
        int[] rightFood = new int[5];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 5; j++) {
                if (pick[i]) {
                    leftFood[j] += food[i][j];
                } else {
                    rightFood[j] += food[i][j];
                }
            }
        }

        if (answerCheck(leftFood)) {
            answer = leftFood[4];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                if (pick[i]) {
                    sb.append((i + 1)).append(" ");
                }
            }
            pq.add(new RES(answer, sb.toString()));
        }

        if (answerCheck(rightFood)) {
            answer = rightFood[4];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                if (!pick[i]) sb.append((i + 1)).append(" ");
            }
            pq.add(new RES(answer, sb.toString()));
        }
    }

    static boolean answerCheck(int[] resFood) {
        if (resFood[4] > answer) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (minNut[i] > resFood[i]) {
                return false;
            }
        }
        return true;
    }
}