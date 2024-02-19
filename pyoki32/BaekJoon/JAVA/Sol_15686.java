import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_16236 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static ArrayList<Integer> partition;
    static int[][] map;
    static int[][] zeroMap;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        partition = new ArrayList<>();
        partition.add(-1);
        partition.add(-1);
        map = new int[N][M];
        zeroMap = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    int num = partition.size();
                    bfs(i, j, num);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    int res = calCnt(i, j);
                    res %= 10;
                    sb.append(res);
                } else {
                    sb.append('0');
                }
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    static int calCnt(int r, int c) {
        int res = 1;
        boolean[] checkNum = new boolean[partition.size()];
        for (int k = 0; k < 4; k++) {
            int row = r + dRow[k];
            int col = c + dCol[k];
            if (isRange(row, col)) {
                if (!checkNum[zeroMap[row][col]] && zeroMap[row][col] > 1) {
                    checkNum[zeroMap[row][col]] = true;
                    res += partition.get(zeroMap[row][col]);
                }
            }
        }
        return res;
    }

    static void bfs(int startR, int startC, int num) {

        visited[startR][startC] = true;
        zeroMap[startR][startC] = num;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startR, startC));
        int cnt = 1;
        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.r;
            int col = p.c;
            for (int k = 0; k < 4; k++) {
                int nRow = row + dRow[k];
                int nCol = col + dCol[k];
                if (isRange(nRow, nCol)) {
                    if (map[nRow][nCol] == 0 && !visited[nRow][nCol]) {
                        visited[nRow][nCol] = true;
                        zeroMap[nRow][nCol] = num;
                        cnt++;
                        q.add(new Point(nRow, nCol));
                    }
                }
            }
        }
        partition.add(cnt);
    }

    static boolean isRange(int nRow, int nCol) {
        if (nRow < 0 || nRow > N - 1 || nCol < 0 || nCol > M - 1) return false;
        return true;
    }
}
