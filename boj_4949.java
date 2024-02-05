import java.io.*;
import java.util.*;

public class boj_4949 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String tmp = br.readLine();
//            solve(tmp);
            if(tmp.length() == 1)
                break;
            solve(tmp);
        }
        System.out.println(sb.toString());
    }
    static void solve(String tmp){
//        System.out.print(tmp+ " ");
        Stack<Character> s = new Stack<>();
        int chk = 0;
        // 0  시작 값 // 1 : ( // 2 : [
        // 예외케이스가 있어 boolean chk도 필요
        int tp[] = new int[100];
        boolean visit[] = new boolean[100];
        int cnt = 0;
        for(char c: tmp.toCharArray()){
            if(c == '(') {
//                System.out.println(c);
                s.push(c);
                chk = 1; // (가 맨 위에 있는 거 표시
                tp[++cnt] = 1;
//                cnt++;
            }else if(c == '['){
                s.push(c);
                chk = 2;
                tp[++cnt] = 2;
//                cnt++;
            }else if(c == ')' && chk == 1){
                s.pop();
                chk = tp[cnt-1];
//                visit[cnt-1] = true;
                cnt--;
            }else if(c == ']' && chk == 2){
                s.pop();
                chk = tp[cnt-1];
                cnt--;
            }
        }
        if(s.isEmpty()) {
//            System.out.println("yes");
            sb.append("yes").append("\n");
        }
        else {
//            System.out.println(s.pop()+ " " +"no");
            sb.append("no").append("\n");
        }
    }
}
