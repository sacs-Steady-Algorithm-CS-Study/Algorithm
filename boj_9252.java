import java.io.*;
import java.util.*;
// 다시 풀어봐야겠다
// 숫자 구할 때와 문자 구할 때 반대로 생각하기
public class boj_9252{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        String input1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String input2 = st.nextToken();

        char[] str1 = input1.toCharArray();
        boolean[] visited = new boolean[input1.length()+1];
        char[] str2 = input2.toCharArray();

        int len1 = input1.length();
        int len2 = input2.length();
        int [][] arr = new int[len1+1][len2+1];
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(str1[i-1] == str2[j-1]) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                }
                else
                    arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
            }
        }
        System.out.println(arr[len1][len2]);

        Stack<Character> stack = new Stack<>();
        int wd = len1;
        int ht = len2;
        while(wd >= 1 && ht >= 1){
            if(arr[wd][ht] == arr[wd-1][ht])
                wd--;
            else if(arr[wd][ht] == arr[wd][ht-1])
                ht--;
            else {
                stack.push(str1[wd-1]);
                wd--;
                ht--;
            }

        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }

}