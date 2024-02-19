import java.util.*;

class KAKAO2023_미로탈출명령어 {
    static class Point {
        int row, col, depth;
        StringBuilder sb;

        public Point(int row, int col, int depth, StringBuilder sb) {
            this.row = row;
            this.col = col;
            this.depth = depth;
            this.sb = sb;
        }
    }

    static int[] dRow = {1, 0, 0, -1};
    static int[] dCol = {0, -1, 1, 0};
    static char[] route = {'d', 'l', 'r', 'u'};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        String answer = "";
        Queue<Point> q = new LinkedList();
        StringBuilder init_sb = new StringBuilder();
        q.add(new Point(x - 1, y - 1, 0, init_sb));
        boolean[][][] visited = new boolean[n][m][k + 1];
        visited[x - 1][y - 1][0] = true;
        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            int depth = p.depth;
            StringBuilder sb = p.sb;
            if (row == r - 1 && col == c - 1 && depth == k) {
                answer = sb.toString();
                break;
            }

            if (depth < k) {
                for (int d = 0; d < 4; d++) {
                    int nRow = row + dRow[d];
                    int nCol = col + dCol[d];
                    if (isMapRange(n, m, nRow, nCol)) {
                        if (!visited[nRow][nCol][depth + 1]) {
                            visited[nRow][nCol][depth + 1] = true;
                            q.add(new Point(nRow, nCol, depth + 1, new StringBuilder(p.sb).append(route[d])));
                        }
                    }
                }
            }
        }
        if (answer.equals("")) {
            answer = "impossible";
        }
        return answer;
    }

    static boolean isMapRange(int n, int m, int nRow, int nCol) {
        if (nRow < 0 || nRow > n - 1 || nCol < 0 || nCol > m - 1) return false;
        return true;
    }

    static boolean isEscapeRange(int r, int c, int nRow, int nCol, int moveCnt) {
        int distR = Math.abs((r - 1) - nRow);
        int distC = Math.abs((c - 1) - nCol);
        if (distR + distC > moveCnt) return false;
        return true;
    }
}