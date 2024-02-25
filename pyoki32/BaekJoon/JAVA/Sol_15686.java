import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_15686 {
    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M, answer;
    static int[][] city, dist;
    static ArrayList<Point> chickenHouse;
    static ArrayList<Point> house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        city = new int[N][N];
        house = new ArrayList<>();
        chickenHouse = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) {
                    house.add(new Point(i, j));
                } else if (city[i][j] == 2) {
                    chickenHouse.add(new Point(i, j));
                }
            }
        }
        dist = new int[house.size()][chickenHouse.size()];
        for (int i = 0; i < house.size(); i++) {
            Point hp = house.get(i);
            for (int j = 0; j < chickenHouse.size(); j++) {
                Point chp = chickenHouse.get(j);
                dist[i][j] = Math.abs(hp.row - chp.row) + Math.abs(hp.col - chp.col);
            }
        }
        int[] pickIdx = new int[M];
        answer = Integer.MAX_VALUE;
        setChicken(pickIdx, 0, 0);
        System.out.println(answer);

    }

    static void setChicken(int[] pickIdx, int start, int depth) {

        if (depth > M - 1) {
            int calDist = 0;
            for (int i = 0; i < house.size(); i++) {
                int minDist = Integer.MAX_VALUE;
                for (int j = 0; j < M; j++) {
                    minDist = Math.min(dist[i][pickIdx[j]], minDist);
                }
                calDist += minDist;
            }
            answer = Math.min(answer, calDist);
            return;
        }
        for (int i = start; i < chickenHouse.size(); i++) {
            pickIdx[depth] = i;
            setChicken(pickIdx, i + 1, depth + 1);
        }
    }
}
