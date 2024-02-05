public class programmers_cart{
    public static void main(String[] args) {
        Solution s = new Solution();
        //1, 4], [0, 0], [2, 3]
        int maze[][] = {{1, 4},{0,0} ,{2,3}};
        int answ = s.solution(maze);
        System.out.println(answ);
    }
}

class Solution {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static class Node {
        int y, x;
        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    private boolean[][] visited1;
    private boolean[][] visited2;
    private boolean redEnd, blueEnd;
    static int[][] map;
    static int n,m;
    // static Node red = null;
    // static Node blue = null;
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        map = new int[n][m];
        visited1 = new boolean[n][m];
        visited2 = new boolean[n][m];
        Node red = null;
        Node blue = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = maze[i][j]; // maze 값 복사해주기
                if (maze[i][j] == 1) {
                    red = new Node(i, j);
                } else if (maze[i][j] == 2) {
                    blue = new Node(i, j);
                }
            }
        }
        visited1[red.y][red.x] = true;
        visited2[blue.y][blue.x] = true;
        int answer = solve(red, blue, 0);
        if (answer == INF) {
            return 0;
        }
        return answer;
    }
    public int solve(Node red, Node blue, int count) {
        if (redEnd && blueEnd) {
            return count;
        }
        int answer = INF;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Node nRed;
                Node nBlue;
                if (redEnd) {
                    nRed = new Node(red.y, red.x);
                } else {
                    nRed = new Node(red.y + dy[i], red.x + dx[i]);
                }
                if (blueEnd) {
                    nBlue = new Node(blue.y, blue.x);
                } else {
                    nBlue = new Node(blue.y + dy[j], blue.x + dx[j]);
                }
                // 기본조건
                if (isInvalid(nRed) || isInvalid(nBlue) || map[nRed.y][nRed.x] == 5 || map[nBlue.y][nBlue.x] == 5) {
                    continue;
                }
                // 크로스되면 안 됨
                if ((nRed.y == blue.y && nRed.x == blue.x) && (nBlue.y == red.y && nBlue.x == red.x)) {
                    continue;
                }
                // 목적지 도찯하지 않았는데 방문했을 때
                if ((!redEnd && visited1[nRed.y][nRed.x]) || (!blueEnd && visited2[nBlue.y][nBlue.x])) {
                    continue;
                }
                // 겹치면 안 됨
                if (nRed.y == nBlue.y && nRed.x == nBlue.x) {
                    continue;
                }
                if (map[nRed.y][nRed.x] == 3) {
                    redEnd = true;
                }
                if (map[nBlue.y][nBlue.x] == 4) {
                    blueEnd = true;
                }
                visited1[nRed.y][nRed.x] = true;
                visited2[nBlue.y][nBlue.x] = true;
                answer = Math.min(solve(nRed, nBlue, count + 1), answer);
                visited1[nRed.y][nRed.x] = false;
                visited2[nBlue.y][nBlue.x] = false;
                redEnd = false;
                blueEnd = false;
            }
        }
        return answer;
    }
    public boolean isInvalid(Node node) {
        return node.y < 0 || node.y >= n || node.x < 0 || node.x >= m;
    }
}



