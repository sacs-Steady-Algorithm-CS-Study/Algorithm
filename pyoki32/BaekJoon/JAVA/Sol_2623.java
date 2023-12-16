import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_2623 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> singerOrder = new ArrayList<>();
        for(int i = 0; i<=N;i++){
            singerOrder.add(new ArrayList<>());
        }
        int [] inDegree = new int [N+1];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int singerCnt =  Integer.parseInt(st.nextToken());
            int exNum = -1;

            if(singerCnt > 1) {
                for (int j = 0; j < singerCnt; j++) {
                    if (exNum == -1) {
                        exNum = Integer.parseInt(st.nextToken());
                    } else {
                        int num = Integer.parseInt(st.nextToken());
                        singerOrder.get(exNum).add(num);
                        inDegree[num]++;
                        exNum = num;
                    }
                }
            }
        }
        StringBuilder sb  = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        for(int i= 1;i <= N;i++){
            if(inDegree[i] == 0 ){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int startNum = q.poll();
            sb.append(startNum).append('\n');
            ArrayList<Integer> nextSinger = singerOrder.get(startNum);
            for(int nextNum : nextSinger){
                inDegree[nextNum]--;
                if(inDegree[nextNum] == 0){
                    q.add(nextNum);
                }
            }
        }
        boolean circle = false;
        for(int i= 1;i <= N;i++){
            if(inDegree[i] != 0 ){
                circle =true;
                break;
            }
        }
        if(circle){
            System.out.println(0);
        }else{
            System.out.println(sb);
        }
    }
}
