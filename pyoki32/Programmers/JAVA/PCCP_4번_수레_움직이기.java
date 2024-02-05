import java.util.*;

class PCCP_4번_수레_움직이기 {
    static class Cart {
        int red_r, red_c, blue_r, blue_c, depth;
        boolean[][] rVisited, bVisited;

        public Cart(int red_r, int red_c, int blue_r, int blue_c, int depth) {
            this.red_r = red_r;
            this.red_c = red_c;
            this.blue_r = blue_r;
            this.blue_c = blue_c;
            this.depth = depth;
        }

        public boolean[][] getRVisited() {
            return rVisited;
        }

        public void setRVisited(boolean[][] rVisited) {
            this.rVisited = rVisited;
        }

        public boolean[][] getBVisited() {
            return bVisited;
        }

        public void setBVisited(boolean[][] bVisited) {
            this.bVisited = bVisited;
        }
    }

    static int[] dRow = {0, 0, -1, 1};
    static int[] dCol = {-1, 1, 0, 0};
    static int sizeR;
    static int sizeC;

    public int solution(int[][] maze) {
        int answer = 0;
        sizeR = maze.length;
        sizeC = maze[0].length;

        answer = bfs(maze);
        return answer;
    }

    public int bfs(int[][] maze) {

        int answer = 0;
        Queue<Cart> q = new LinkedList();

        boolean[][][][] visited = new boolean[sizeR][sizeC][sizeR][sizeC];

        int init_red_r = -1;
        int init_red_c = -1;
        int init_blue_r = -1;
        int init_blue_c = -1;

        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                if (maze[i][j] == 1) {
                    init_red_r = i;
                    init_red_c = j;
                } else if (maze[i][j] == 2) {
                    init_blue_r = i;
                    init_blue_c = j;
                }
            }
        }
        visited[init_red_r][init_red_c][init_blue_r][init_blue_c] = true;
        Cart initCart = new Cart(init_red_r, init_red_c, init_blue_r, init_blue_c, 0);
        boolean[][] initRVisited = new boolean[sizeR][sizeC];
        boolean[][] initBVisited = new boolean[sizeR][sizeC];
        initRVisited[init_red_r][init_red_c] = true;
        initBVisited[init_blue_r][init_blue_c] = true;
        initCart.setRVisited(initRVisited);
        initCart.setBVisited(initBVisited);
        q.add(initCart);

        while (!q.isEmpty()) {
            Cart cart = q.poll();
            int red_r = cart.red_r;
            int red_c = cart.red_c;
            int blue_r = cart.blue_r;
            int blue_c = cart.blue_c;
            int depth = cart.depth;
            boolean[][] rVisited = cart.getRVisited();
            boolean[][] bVisited = cart.getBVisited();
            boolean endRed = false;
            boolean endBlue = false;
            if (maze[red_r][red_c] == 3) endRed = true;
            if (maze[blue_r][blue_c] == 4) endBlue = true;
            if (endRed && endBlue) {
                return depth;
            }
            for (int ri = 0; ri < 4; ri++) {
                for (int bi = 0; bi < 4; bi++) {
                    int n_red_r = red_r + dRow[ri];
                    int n_red_c = red_c + dCol[ri];
                    int n_blue_r = blue_r + dRow[bi];
                    int n_blue_c = blue_c + dCol[bi];
                    //도착칸일때 처리
                    if (endRed) {
                        n_red_r = red_r;
                        n_red_c = red_c;
                    }
                    if (endBlue) {
                        n_blue_r = blue_r;
                        n_blue_c = blue_c;
                    }

                    if (isRange(n_red_r, n_red_c, n_blue_r, n_blue_c)) {
                        if (rVisited != null || bVisited != null) {
                            if (setCheck(maze, rVisited, bVisited, endRed, endBlue, n_red_r, n_red_c, n_blue_r, n_blue_c)) {
                                if (crossCheck(red_r, red_c, blue_r, blue_c, n_red_r, n_red_c, n_blue_r, n_blue_c)) {
                                    Cart nCart = new Cart(n_red_r, n_red_c, n_blue_r, n_blue_c, depth + 1);
                                    boolean[][] n_rVisited = copyVisited(rVisited);
                                    boolean[][] n_bVisited = copyVisited(bVisited);
                                    n_rVisited[n_red_r][n_red_c] = true;
                                    n_bVisited[n_blue_r][n_blue_c] = true;
                                    nCart.setRVisited(n_rVisited);
                                    nCart.setBVisited(n_bVisited);
                                    q.add(nCart);
                                }
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }

    public boolean isRange(int rr, int cc, int br, int bc) {
        if (rr < 0 || rr >= sizeR || cc < 0 || cc >= sizeC) return false;
        if (br < 0 || br >= sizeR || bc < 0 || bc >= sizeC) return false;
        return true;
    }

    public boolean setCheck(int[][] maze, boolean[][] rVisited, boolean[][] bVisited, boolean endRed, boolean endBlue, int rr, int cc, int br, int bc) {
        if (rr == br && cc == bc) return false;
        if (maze[rr][cc] == 5 || maze[br][bc] == 5) return false;
        if (!endRed && rVisited[rr][cc]) return false;
        if (!endBlue && bVisited[br][bc]) return false;
        return true;
    }

    public boolean crossCheck(int rr, int cc, int br, int bc, int nrr, int ncc, int nbr, int nbc) {
        if (nrr == br && ncc == bc && nbr == rr && nbc == cc) return false;
        return true;
    }

    public boolean[][] copyVisited(boolean[][] visited) {
        boolean[][] copy = new boolean[sizeR][sizeC];
        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                copy[i][j] = visited[i][j];
            }
        }
        return copy;
    }
}