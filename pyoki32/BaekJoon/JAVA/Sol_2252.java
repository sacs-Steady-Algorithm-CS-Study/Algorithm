import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_2252 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [] inDegreeCnt = new int[N + 1];
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            inDegreeCnt[b]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if(inDegreeCnt[i] == 0){
                pq.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
             int setLine = pq.poll();
             sb.append(setLine).append(' ');
             ArrayList<Integer> linkNum = adjList.get(setLine);
             for( int num : linkNum){
                 inDegreeCnt[num]--;
                 if(inDegreeCnt[num] == 0 ){
                     pq.add(num);
                 }
             }
        }

        System.out.println(sb.toString());
    }
}
