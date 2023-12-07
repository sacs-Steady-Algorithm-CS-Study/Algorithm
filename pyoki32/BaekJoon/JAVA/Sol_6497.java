import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_6497 {
    static class Road {
        int start, end, cost;

        public Road(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                break;
            }
            PriorityQueue<Road> pq = new PriorityQueue<>((o1, o2) -> {
                return o1.cost - o2.cost;
            });
            int totalCost = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                pq.add(new Road(start, end, cost));
                totalCost += cost;
            }

            parents = new int[m];
            for (int i = 0; i < m; i++) {
                parents[i] = i;
            }

            int minCost = 0;
            while (!pq.isEmpty()) {
                Road road = pq.poll();
                int qStart = road.start;
                int qEnd = road.end;
                int qCost = road.cost;
                if (union(qStart, qEnd)) {
                    minCost += qCost;
                }
            }
            sb.append(totalCost - minCost).append('\n');
        }
        System.out.println(sb);
    }

    static int find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    static boolean union(int x, int y) {
        int x_root = find(x);
        int y_root = find(y);

        if (x_root != y_root) {
            if (x_root < y_root) {
                parents[y_root] = x_root;
            } else {
                parents[x_root] = y_root;
            }
            return true;
        }
        return false;
    }
}