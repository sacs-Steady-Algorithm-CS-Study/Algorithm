import java.util.*;

class KAKAO2024_도넛과막대그래프 {
    static ArrayList<ArrayList<Integer>> adjList;
    static int[] inDegree;
    static int[] outDegree;
    static boolean bar, eight;
    static HashSet<Integer> hs;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        adjList = new ArrayList();
        inDegree = new int[1000001];
        outDegree = new int[1000001];
        for (int i = 0; i <= 1000000; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int out = edges[i][0];
            int in = edges[i][1];
            outDegree[out]++;
            inDegree[in]++;
            adjList.get(out).add(in);
        }

        for (int i = 1; i <= 1000000; i++) {
            if (inDegree[i] == 0 && outDegree[i] >= 2) {
                answer[0] = i;
                break;
            }
        }
        int graphCnt = outDegree[answer[0]];
        ArrayList<Integer> nextGraph = adjList.get(answer[0]);

        for (int start : nextGraph) {
            hs = new HashSet();
            bar = false;
            eight = false;
            inDegree[start]--;
            hs.add(start);
            boolean zeroIn = false;
            boolean zeroOut = false;

            if (inDegree[start] <= 1) zeroIn = true;
            if (outDegree[start] == 0) zeroOut = true;
            dfs(start, zeroIn, zeroOut);

            if (bar) answer[2]++;
            if (eight) answer[3]++;
        }
        answer[1] = graphCnt - (answer[2] + answer[3]);
        return answer;
    }

    static void dfs(int start, boolean zeroIn, boolean zeroOut) {

        if (inDegree[start] >= 2 && outDegree[start] >= 2) {
            eight = true;
            return;
        } else if (zeroIn && zeroOut) {
            bar = true;
            return;
        }

        ArrayList<Integer> nextNode = adjList.get(start);
        for (int next : nextNode) {
            if (!hs.contains(next)) {
                hs.add(next);
                if (outDegree[next] == 0) dfs(next, zeroIn, true);
                else dfs(next, zeroIn, zeroOut);
            }
        }
    }

}