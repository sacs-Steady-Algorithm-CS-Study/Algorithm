import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_2637 {
    static class Parts {
        int num;
        int cnt;

        public Parts(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Parts>> partsList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            partsList.add((new ArrayList<>()));
        }

        int[] inDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            partsList.get(Y).add(new Parts(X, K));
            inDegree[X]++;
        }
        int[][] basicPartsCnt = new int[N + 1][N + 1];
        boolean[] basicPartsCheck = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                basicPartsCheck[i] = true;
                basicPartsCnt[i][i]++;
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int partsNum = q.poll();
            ArrayList<Parts> getParts = partsList.get(partsNum);
            for (Parts parts : getParts) {
                int num = parts.num;
                int cnt = parts.cnt;
                for (int i = 1; i <= N; i++) {
                    basicPartsCnt[num][i] += cnt * basicPartsCnt[partsNum][i];
                }
                inDegree[num]--;
                if (inDegree[num] == 0) {
                    q.add(num);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (basicPartsCheck[i]) {
                sb.append(i).append(" ").append(basicPartsCnt[N][i]).append('\n');
            }
        }
        System.out.println(sb);
    }
}
