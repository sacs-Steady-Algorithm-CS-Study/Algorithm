import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Sol_14719 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

       int H = Integer.parseInt(st.nextToken());
       int W = Integer.parseInt(st.nextToken());
       int [] arr = new int [W];
       boolean [] filled = new boolean[W];
       st = new StringTokenizer(br.readLine());
       for(int i = 0 ; i < W ; i++){
           arr[i] = Integer.parseInt(st.nextToken());
       }
       int water =0;
       int leftMaxHigh = -1;
       int leftWater = 0;
       int leftPoint = -1;
       for(int i = 0 ; i < W ;i++){
           if(leftMaxHigh == -1){
               leftMaxHigh = arr[i];
               leftPoint = i;
           }else{
                if(leftMaxHigh <= arr[i]){
                    leftMaxHigh = arr[i];
                    water += leftWater;
                    leftWater = 0;
                    for(int j = leftPoint ; j <= i ; j++){
                        filled[j] = true;
                    }
                }else{
                    leftWater += leftMaxHigh - arr[i];
                }
           }
       }
        int rightMaxHigh = -1;
        int rightWater = 0;
        for(int i = W-1 ; i >= 0 ;i--){
            if(rightMaxHigh == -1){
                rightMaxHigh = arr[i];
            }else{
                if(rightMaxHigh <= arr[i]){
                    rightMaxHigh = arr[i];
                    water += rightWater;
                    rightWater = 0;
                }else{
                    if(!filled[i]) rightWater += rightMaxHigh - arr[i];
                }
            }
        }
        System.out.println(water);
    }
}
