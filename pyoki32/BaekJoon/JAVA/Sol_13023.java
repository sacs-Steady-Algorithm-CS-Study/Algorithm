import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_13023 {
    static boolean answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        answer = false;
        visited = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            visited[i] =true;
            dfs(adjList, i, 1);
            if (answer) break;
            visited[i] =false;
        }
        if (answer) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static void dfs(ArrayList<ArrayList<Integer>> adjList, int start, int depth) {
        if (depth >= 5) {
            answer = true;
            return;
        }
        ArrayList<Integer> nextNodes = adjList.get(start);
        for (int nextNode : nextNodes) {
            if (!visited[nextNode]) {
                visited[nextNode] = true;
                dfs(adjList, nextNode, depth + 1);
                visited[nextNode] = false;
            }
        }
    }
}
