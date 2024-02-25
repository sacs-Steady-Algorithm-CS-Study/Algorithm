import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_1759 {
    static HashSet<Character> vowelCheck;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[] arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        sb = new StringBuilder();
        vowelCheck = new HashSet<>();
        vowelCheck.add('a');
        vowelCheck.add('e');
        vowelCheck.add('i');
        vowelCheck.add('o');
        vowelCheck.add('u');
        Arrays.sort(arr);
        char[] pick = new char[L];
        solve(arr, pick, 0, 0, 0);
        System.out.println(sb.toString());
    }

    static void solve(char[] arr, char[] pick, int start, int depth, int vowelCnt) {

        if (depth > pick.length - 1) {
            if (vowelCnt > 0 && (pick.length - vowelCnt) > 1) {
                for (int i = 0; i < pick.length; i++) {
                    sb.append(pick[i]);
                }
                sb.append('\n');
            }
            return;
        }
        for (int k = start; k < arr.length; k++) {
            pick[depth] = arr[k];
            int nVowelCnt = vowelCnt;
            if (vowelCheck.contains(arr[k])) {
                nVowelCnt++;
            }
            solve(arr, pick, k + 1, depth + 1, nVowelCnt);
        }
    }
}
