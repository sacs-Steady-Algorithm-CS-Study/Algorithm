import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_18352 {

    static class Move {
        int node, dist;

        public Move(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int N, M, K, X;
    static ArrayList<Integer> ans;
    static ArrayList<ArrayList<Integer>> adjList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList.get(start).add(end);
        }
        ans = new ArrayList<>();
        bfs();

        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        for (int n : ans) {
            sb.append(n).append('\n');
        }
        if (ans.size() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
    }

    static void bfs() {
        boolean[] visited = new boolean[N + 1];
        Queue<Move> q = new LinkedList();
        visited[X] = true;
        q.add(new Move(X, 0));

        while (!q.isEmpty()) {
            Move m = q.poll();
            int node = m.node;
            int dist = m.dist;

            if (dist == K) {
                ans.add(node);
            }

            ArrayList<Integer> list = adjList.get(node);
            for (int nNode : list) {
                if (!visited[nNode]) {
                    visited[nNode] = true;
                    q.add(new Move(nNode, dist + 1));
                }
            }
        }
    }
}
