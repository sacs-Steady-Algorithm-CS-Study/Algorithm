import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        int goodCnt = 0;
        for (int i = 0; i < N; i++) {
            int startIdx = 0;
            int endIdx = N-1;
            if(startIdx == i ) startIdx++;
            if(endIdx == i) endIdx--;
            boolean goodCheck = false;
                while(startIdx < endIdx){
                    long twoSum = arr[startIdx] + arr[endIdx];
                    //System.out.println(startIdx+"-----"+endIdx+"--->"+twoSum+"  arr->"+arr[i]+" idx"+i);
                    if( twoSum == arr[i] && startIdx != i && endIdx != i ){
                       // System.out.println(">>>?");
                        goodCheck = true;
                        break;
                    }
                    if(twoSum < arr[i]){
                        startIdx++;
                        if(startIdx == i ) startIdx++;

                    }else if(twoSum > arr[i]){
                        endIdx--;
                        if(endIdx == i) endIdx--;
                    }
                }
            if(goodCheck){
                goodCnt++;
            }
        }
        System.out.println(goodCnt);
    }
}
