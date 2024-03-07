import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_16234 {
    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Area {
        int num;
        int population;
        int cnt;

        public Area(int num, int population, int cnt) {
            this.num = num;
            this.population = population;
            this.cnt = cnt;
        }
    }

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[][] area;
    static int[] drow = {1, -1, 0, 0};
    static int[] dcol = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N][N];
        area = new int[N][N];
        int day = 0;

        while (day <= 2000) {
            ArrayList<Area> areaList = new ArrayList<>();
            areaList.add(new Area(-1, -1, -1));
            init();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int areaNum = areaList.size();
                        Area a = bfs(new Point(i, j), areaNum);
                        areaList.add(a);
                    }
                }
            }
            boolean move = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int aNum = area[i][j];
                    if (areaList.get(aNum).cnt > 1) {
                        map[i][j] = areaList.get(aNum).population / areaList.get(aNum).cnt;
                        move = true;
                    }
                }
            }
            if (!move) break;
            day++;
        }
        System.out.println(day);
    }

    static Area bfs(Point startP, int areaNum) {

        Queue<Point> q = new LinkedList<>();
        q.add(startP);
        visited[startP.row][startP.col] = true;
        area[startP.row][startP.col] = areaNum;
        int population = map[startP.row][startP.col];
        int cnt = 1;
        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            for (int k = 0; k < 4; k++) {
                int nRow = row + drow[k];
                int nCol = col + dcol[k];
                if (isRange(nRow, nCol)) {
                    if (moveCheck(row, col, nRow, nCol) && !visited[nRow][nCol]) {
                        area[nRow][nCol] = areaNum;
                        visited[nRow][nCol] = true;
                        cnt++;
                        population += map[nRow][nCol];
                        q.add(new Point(nRow, nCol));
                    }
                }
            }
        }

        return new Area(areaNum, population, cnt);
    }

    static boolean isRange(int nRow, int nCol) {
        if (nRow < 0 || nRow > N - 1 || nCol < 0 || nCol > N - 1) return false;
        return true;
    }

    static boolean moveCheck(int row, int col, int nRow, int nCol) {
        int gap = Math.abs(map[row][col] - map[nRow][nCol]);
        if (gap < L || gap > R) {
            return false;
        }
        return true;
    }

    static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                area[i][j] = 0;
                visited[i][j] = false;
            }
        }
    }
}
