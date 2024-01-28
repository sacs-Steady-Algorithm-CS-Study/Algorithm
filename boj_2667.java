import java.io.*;
import java.util.*;

public class boj_2667 {

    static class Node{
        int y , x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static int dy[] = {-1,0,1,0};
    static int dx[] = {0,1,0,-1};
    static int map[][];
    static boolean visited[][];
    static int n;
    static int[] input; // 단지별 숫자 담기
    static int answer; // 총 단지수
    static int[] ans; // 제출용 단지별 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map  = new int[n][n];
        visited = new boolean[n][n];
        input = new int[n*n]; // n개보다는 작을거니까
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            for(int j = 0; j < a.length(); j++){
                char tp = a.charAt(j);
                String tpp = String.valueOf(tp);
                map[i][j] = (Integer.parseInt(tpp));
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    input[cnt]= sol(i, j);
                    cnt++;
                }
            }
            answer = cnt;
        }
        System.out.println(answer);
        ans = new int[cnt];
        for(int i = 0; i < cnt; i++){
            ans[i] = input[i];
        }
        Arrays.sort(ans);
        for(int i = 0 ; i < cnt; i++){
            System.out.println(ans[i]);
        }
    }
    static int sol(int y, int x){
        int cnt = 0;
        Queue<Node> q= new LinkedList<>();
        q.add(new Node(y, x));

        while(!q.isEmpty()){
            Node tmp = q.poll();
            cnt++;
            visited[tmp.y][tmp.x] = true;
            for(int i = 0; i < 4; i++){
                int diy = tmp.y + dy[i];
                int dix = tmp.x + dx[i];
                if(diy < 0 || diy >= n || dix < 0 || dix >= n)
                    continue;
                if(visited[diy][dix])
                    continue;
                if(map[diy][dix] == 1)
                q.add(new Node(diy, dix));
                visited[diy][dix] = true;
            }
        }


        return cnt;
    }
}
