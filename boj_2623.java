import java.io.*;
import java.util.*;

public class boj_2623 {
    static int N, M; // N : ㄱㅏ수 , M : PD

    static class Node{
        int next;
        public Node(int next){
            this.next = next;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static ArrayList<Node>[] arr; // 순서 체크용
    static int[] indegree; // 위상정렬 체크용
    static boolean[] visited; // 방문 체크용
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];
        indegree = new int[N+1];
        visited = new boolean[N+1];

        for(int i = 1; i<= N;i++){
            arr[i] = new ArrayList<>();
        }
        int tmp[];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            tmp = new int[first];
            for(int j = 0; j < first; j++){
                tmp[j] = Integer.parseInt(st.nextToken());
            }
            for(int q = 0; q < first-1; q++){
                int tmp1 = tmp[q];
                int tmp2 = tmp[q+1];
                arr[tmp1].add(new Node(tmp2));
                indegree[tmp2]++;
            }
        }
        solve();
        for(int i = 1; i <= N; i++){
            if(indegree[i] != 0)
                System.out.println("0");
        }
        System.out.println(sb.toString());
    }
    static void solve(){
        Queue<Node> q = new LinkedList<>();
        for(int i =1; i<= N;i++){
//            System.out.println(" i + \"\" +indegree[i] = " +  i + "" +indegree[i]);
            if(indegree[i] == 0)
                q.offer(new Node(i));
        }
        while(!q.isEmpty()){
            Node n = q.poll();
            if(indegree[n.next] == 0 && !visited[n.next]){
                visited[n.next] = true;
//                System.out.println("n.next = " + n.next);
                sb.append(n.next+"\n");
            }
            for(int i = 0; i < arr[n.next].size(); i++) {
                Node next = arr[n.next].get(i);
                indegree[next.next]--;
                if(indegree[next.next] == 0) {
                    q.offer(new Node(next.next));
                }
            }
        }
    }

}
