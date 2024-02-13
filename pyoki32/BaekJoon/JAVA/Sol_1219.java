import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int start, end, profit;

        public Edge(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    static int N, M;
    static int sCity, eCity;
    static int[][] costArr;
    static int[] profit;
    static int[] maxProfit;
    static boolean[] visited;

    static boolean cycleCheck;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        sCity = Integer.parseInt(st.nextToken());
        eCity = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        costArr = new int[N][N];

        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList.get(start).add(end);
            costArr[start][end] = cost;
        }
        st = new StringTokenizer(br.readLine());
        profit = new int[N];
        for (int i = 0; i < N; i++) {
            profit[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];
        goalCheck(sCity);
        if(sCity == eCity) visited[sCity] = true;
        bellmanford();

        if (!visited[eCity]) {
            System.out.println("gg");
        } else if (cycleCheck) {
            System.out.println("Gee");
        } else {
            System.out.println(maxProfit[eCity]);
        }
    }

    static void goalCheck(int node) {
        ArrayList<Integer> nextNodes = adjList.get(node);
        for (int nextNode : nextNodes) {
            if (!visited[nextNode]) {
                visited[nextNode] = true;
                goalCheck(nextNode);
            }
        }
    }

    static void bellmanford() {
        maxProfit = new int[N];
        for (int i = 0; i < N; i++) {
            maxProfit[i] = Integer.MIN_VALUE;
        }
        maxProfit[sCity] = profit[sCity];

        PriorityQueue<Edge> pq = new PriorityQueue<>((E1, E2) -> {
            return E2.profit - E1.profit;
        });

        ArrayList<Integer> initNodes = adjList.get(sCity);
        for (int nextNode : initNodes) {
            if (maxProfit[nextNode] < maxProfit[sCity] + (profit[nextNode] - costArr[sCity][nextNode])) {
                maxProfit[nextNode] = maxProfit[sCity] + profit[nextNode] - costArr[sCity][nextNode];
                pq.add(new Edge(sCity, nextNode, maxProfit[nextNode]));
            }
        }
        int[] upDateCnt = new int[N];

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int end = edge.end;
            ArrayList<Integer> nextNodes = adjList.get(end);
            for (int nextNode : nextNodes) {
                if (upDateCnt[nextNode] > N) {
                    break;
                }
                if (maxProfit[nextNode] < maxProfit[end] + (profit[nextNode] - costArr[end][nextNode])) {
                    maxProfit[nextNode] = maxProfit[end] + (profit[nextNode] - costArr[end][nextNode]);
                    upDateCnt[nextNode]++;
                    pq.add(new Edge(end, nextNode, maxProfit[nextNode]));
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if(upDateCnt[sCity] > 2){
                cycleCheck = true;
            }
        }
    }
}
