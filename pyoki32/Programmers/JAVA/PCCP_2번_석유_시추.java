import java.util.*;

class PCCP_2번_석유_시추 {
    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int n, m;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static ArrayList<Integer> numberOilList;

    public int solution(int[][] land) {

        n = land.length;
        m = land[0].length;
        numberOilList = new ArrayList<>();
        int[][] numberMap = new int[n][m];
        numberOilList.add(-1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && numberMap[i][j] == 0) {
                    int number = numberOilList.size();
                    int oil = getOil(land, numberMap, new Point(i, j), number);
                    numberOilList.add(oil);
                }
            }
        }

        int answer = 0;
        boolean[] pickCheck = new boolean[numberOilList.size()];
        for (int i = 0; i < m; i++) {
            int totalOil = 0;
            pickCheck = new boolean[numberOilList.size()];
            for (int j = 0; j < n; j++) {
                if (numberMap[j][i] != 0 && !pickCheck[numberMap[j][i]]) {
                    pickCheck[numberMap[j][i]] = true;
                    totalOil += numberOilList.get(numberMap[j][i]);
                }
            }
            answer = Math.max(answer, totalOil);
        }
        return answer;
    }

    public int getOil(int[][] land, int[][] numberMap, Point startP, int number) {

        int oil = 1;
        int startR = startP.row;
        int startC = startP.col;
        numberMap[startR][startC] = number;
        Queue<Point> q = new LinkedList();
        q.add(startP);
        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            for (int k = 0; k < 4; k++) {
                int nRow = row + dRow[k];
                int nCol = col + dCol[k];
                if (isRange(nRow, nCol)) {
                    if (land[nRow][nCol] == 1 && numberMap[nRow][nCol] == 0) {
                        numberMap[nRow][nCol] = number;
                        oil++;
                        q.add(new Point(nRow, nCol));
                    }
                }
            }
        }
        return oil;
    }

    public boolean isRange(int nRow, int nCol) {
        if ((nRow < 0 || nRow >= n) || (nCol < 0 || nCol >= m)) return false;
        return true;
    }
}