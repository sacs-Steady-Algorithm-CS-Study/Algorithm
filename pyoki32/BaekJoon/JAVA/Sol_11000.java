import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_11000 {

    static class SuGang {
        int start, end;

        public SuGang(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<SuGang> spq = new PriorityQueue<>((o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            spq.add(new SuGang(S, T));
        }

        PriorityQueue<Integer> classPq = new PriorityQueue<>();
        classPq.add(spq.poll().end);

        while (!spq.isEmpty()) {
            SuGang sq = spq.poll();
            int start = sq.start;
            int end = sq.end;

            if (classPq.peek() <= start) {
                classPq.poll();
            }
            classPq.add(end);
        }
        System.out.println(classPq.size());
    }

}
