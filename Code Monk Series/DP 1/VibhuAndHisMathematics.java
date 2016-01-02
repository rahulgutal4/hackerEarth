package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class VibhuAndHisMathematics {

    static int test,N,MOD = 1000000007;
    static long[] dp = new long[1000002]; 
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test = Integer.parseInt(br.readLine());
        
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=1000001;i++){
            dp[i] = (dp[i-1] + (i-1)*dp[i-2])%MOD;
        }
        
        StringBuilder output = new StringBuilder();
        while(test-- > 0){
            
            N = Integer.parseInt(br.readLine());
            output.append(dp[N] + "\n");
            
        }
        
        System.out.println(output);
    }

}
