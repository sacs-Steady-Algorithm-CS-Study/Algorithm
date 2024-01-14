import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_2644 {

    static int n;
    static ArrayList<ArrayList<Integer>> adjList;
    static int answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        adjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adjList.get(x).add(y);
            adjList.get(y).add(x);
        }
        answer = Integer.MAX_VALUE;
        visited = new boolean[n + 1];
        dfs(startNode, endNode, 0);
        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

    static void dfs(int start, int end, int depth) {
        if (end == start) {
            answer = Math.min(depth, answer);
            return;
        }

        ArrayList<Integer> nextNodes = adjList.get(start);
        for (int nextNode : nextNodes) {
            if (!visited[nextNode]) {
                visited[nextNode] = true;
                dfs(nextNode, end, depth + 1);
                visited[nextNode] = false;
            }
        }
    }
}
