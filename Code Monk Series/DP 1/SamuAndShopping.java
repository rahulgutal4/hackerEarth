package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SamuAndShopping {

    static int test,shops;
    static int[][] cost = new int[100005][3];
    static long[][] dp = new long[100005][3];
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String token = br.readLine();
        test = Integer.parseInt(token);
        
        String[] sp;
        while(test-- > 0){
            
            shops = Integer.parseInt(br.readLine());
            for(int i=0;i<shops;i++){
                token = br.readLine();
                sp = token.split(" ");
                cost[i][0] = Integer.parseInt(sp[0]);
                cost[i][1] = Integer.parseInt(sp[1]);
                cost[i][2] = Integer.parseInt(sp[2]);
            }
            
            dp[0][0] = cost[0][0];
            dp[0][1] = cost[0][1];
            dp[0][2] = cost[0][2];
            
            for(int i=1;i<shops;i++){
                
                dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1]) + cost[i][2];
                
            }
            
            System.out.println(Math.min(dp[shops-1][0], Math.min(dp[shops-1][1], dp[shops-1][2])));
        }
        
    }

}
