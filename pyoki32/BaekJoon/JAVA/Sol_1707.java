import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_1707 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
            for (int j = 0; j <= V; j++) {
                adjList.add(new ArrayList<>());
            }
            for (int k = 0; k < E; k++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adjList.get(a).add(b);
                adjList.get(b).add(a);
            }
            if (BipartiteGraphCheck(adjList, V)) {
                sb.append("YES").append('\n');
            } else {
                sb.append("NO").append('\n');
            }
        }
        System.out.println(sb);
    }

    public static boolean BipartiteGraphCheck(ArrayList<ArrayList<Integer>> adjList, int V) {
        boolean[] visited = new boolean[V + 1];
        int[] color = new int[V + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                q.add(i);
                visited[i] = true;
                color[i] = 1;
                while (!q.isEmpty()) {
                    int node = q.poll();
                    ArrayList<Integer> nextNodeList = adjList.get(node);
                    for (int nextNode : nextNodeList) {
                        if (!visited[nextNode]) {
                            if (color[node] == 1) {
                                color[nextNode] = 2;
                            } else {
                                color[nextNode] = 1;
                            }
                            visited[nextNode] = true;
                            q.add(nextNode);
                        } else {
                            if (color[node] == color[nextNode]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
