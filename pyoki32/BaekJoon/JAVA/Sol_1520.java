import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_1520 {

    static int M, N;
    static int[][] map;
    static int[][] inRouteMap;
    static int[][] answer;
    //-> <-  DOWN UP
    static int[] dRow = {0, 0, -1, 1};
    static int[] dCol = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        inRouteMap = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[M][N];
        checkRoute();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int ni = i + dRow[k];
                    int nj = j + dCol[k];
                    if (isRange(ni, nj)) {
                        if (visited[ni][nj] && map[i][j] < map[ni][nj]) {
                            inRouteMap[i][j]++;
                        }
                    }
                }
            }
        }
        answer = new int[M][N];
        solve();
        System.out.println(answer[M - 1][N - 1]);
    }

    static void checkRoute() {
        visited[0][0] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        while (!q.isEmpty()) {
            Point qp = q.poll();
            int row = qp.x;
            int col = qp.y;
            for (int k = 0; k < 4; k++) {
                int nRow = row + dRow[k];
                int nCol = col + dCol[k];
                if (isRange(nRow, nCol)) {
                    if (!visited[nRow][nCol] && map[nRow][nCol] < map[row][col]) {
                        visited[nRow][nCol] = true;
                        q.add(new Point(nRow, nCol));
                    }
                }
            }
        }
    }

    static void solve() {
        answer[0][0] = 1;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        while (!q.isEmpty()) {
            Point qp = q.poll();
            int row = qp.x;
            int col = qp.y;
            for (int k = 0; k < 4; k++) {
                int nRow = row + dRow[k];
                int nCol = col + dCol[k];
                if (isRange(nRow, nCol)) {
                    if (map[row][col] > map[nRow][nCol]) {
                        answer[nRow][nCol] += answer[row][col];
                        inRouteMap[nRow][nCol]--;
                        if (inRouteMap[nRow][nCol] == 0) q.add(new Point(nRow, nCol));
                    }
                }
            }
        }
    }

    static boolean isRange(int nRow, int nCol) {
        if (nRow < 0 || nRow > M - 1 || nCol < 0 || nCol > N - 1) return false;
        return true;
    }
}
