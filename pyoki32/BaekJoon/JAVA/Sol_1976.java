import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1976 {
    static int[][] adjMatrix;
    static int N, M;
    static boolean[][] visited;
    static boolean[] rootCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        adjMatrix = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int[] node = new int[M];
        for (int i = 0; i < M; i++) {
            node[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[N + 1][N + 1];
        rootCheck = new boolean[N + 1];
        rootCheck[node[0]] = true;
        dfs(node[0]);
        boolean answerCheck = true;
            for (int i = 0; i < M; i++) {
            if (!rootCheck[node[i]]) {
                answerCheck = false;
                break;
            }
        }
        if (answerCheck) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void dfs(int start) {
        for (int i = 1; i <= N; i++) {
            if (!visited[start][i] && adjMatrix[start][i] == 1) {
                visited[start][i] = true;
                rootCheck[i] = true;
                dfs(i);
            }
        }
    }
}
