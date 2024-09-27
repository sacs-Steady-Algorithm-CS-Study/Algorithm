package pyoki32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_11658 {

    static int N, M;
    static int[][] arr;
    static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][N + 1];
        tree = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                update(i, j, arr[i][j]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            if (w == 0) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                update(x, y, c - arr[x][y]);
                arr[x][y] = c;
            } else {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int res = sum(x2, y2) - (sum(x2, y1 - 1) + sum(x1 - 1, y2)) + sum(x1 - 1, y1 - 1);
                sb.append(res).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void update(int r, int c, int val) {
        while (r <= N) {
            for (int i = c; i <= N; ) {
                tree[r][i] += val;
                i += i & -i;
            }
            r += r & -r;
        }
    }

    static int sum(int r, int c) {
        int result = 0;
        while (r > 0) {
            for (int i = c; i > 0; ) {
                result += tree[r][i];
                i -= i & -i;
            }
            r -= r & -r;
        }
        return result;
    }
}