package pyoki32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1790 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = N;
        int mid = 0;
        int answer = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (calOrder(mid) <= k) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }

        if (calOrder(N) < k) {
            System.out.println(-1);
        } else {
            int order = calOrder(answer);
            if (order == k) {
                String str = Integer.toString(answer);
                System.out.println(str.charAt(str.length() - 1));
            } else {
                int gap = k - order;
                answer++;
                String str = Integer.toString(answer);
                System.out.println(str.charAt(gap - 1));
            }
        }
    }

    static int calOrder(int num) {
        int order = 0;
        String numStr = Integer.toString(num);
        for (int i = 0; i < numStr.length(); i++) {
            order += ((Math.pow(10, (i + 1)) - 1) - Math.pow(10, i) + 1) * (i + 1);
        }
        order += (num - Math.pow(10, numStr.length()) + 1) * numStr.length();
        return order;
    }
}