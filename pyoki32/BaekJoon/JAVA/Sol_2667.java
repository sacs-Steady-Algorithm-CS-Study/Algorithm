import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_2667 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dRow = {0, 0, -1, 1};
    static int[] dCol = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    pq.add(bfs(i, j));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(pq.size()).append('\n');
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append('\n');
        }
        System.out.println(sb);
    }

    static int bfs(int r, int c) {

        int cnt = 1;
        visited[r][c] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));

        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.x;
            int col = p.y;
            for (int k = 0; k < 4; k++) {
                int nRow = row + dRow[k];
                int nCol = col + dCol[k];
                if (isRange(nRow, nCol)) {
                    if (!visited[nRow][nCol] && map[nRow][nCol] == 1) {
                        visited[nRow][nCol] = true;
                        cnt++;
                        q.add(new Point(nRow, nCol));
                    }
                }
            }
        }
        return cnt;
    }

    static boolean isRange(int nRow, int nCol) {
        if (nRow < 0 || nRow > N - 1 || nCol < 0 || nCol > N - 1) return false;
        return true;
    }
}
