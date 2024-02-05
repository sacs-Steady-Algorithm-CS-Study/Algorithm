import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14888 {
    static int maxValue, minValue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] op = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        int[] pick = new int[arr.length - 1];
        maxValue= Integer.MIN_VALUE;
        minValue = Integer.MAX_VALUE;
        solve(arr, op, pick, 0);
        System.out.println(maxValue);
        System.out.println(minValue);
    }

    static void solve(int[] arr, int[] op, int[] pick, int depth) {
        if (depth >= arr.length - 1) {
            int res = arr[0];
            for(int i=1;i<arr.length;i++){
                res = cal(res,arr[i],pick[i-1]);
            }
            maxValue = Math.max(res, maxValue);
            minValue = Math.min(res, minValue);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i]--;
                pick[depth] = i;
                solve(arr, op, pick, depth + 1);
                op[i]++;
            }
        }
    }

    static int cal(int a, int b, int idx) {
        int res = a;
        switch (idx) {
            case 0:
                res = a + b;
                break;
            case 1:
                res = a - b;
                break;
            case 2:
                res = a * b;
                break;
            case 3:
                res = a / b;
                break;
            default:
                break;
        }
        return res;
    }
}
