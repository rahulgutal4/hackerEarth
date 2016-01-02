package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class SamuAndShootingGame {

    static int test,X,Y,N,W,P1,P2;
    static double[][] dp = new double[1005][1005]; 
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test = Integer.parseInt(br.readLine());
        
        String[] sp;
        DecimalFormat df = new DecimalFormat("####0.000000");
        while(test-- > 0){
            
            sp = br.readLine().split(" ");
            X = Integer.parseInt(sp[0]);
            Y = Integer.parseInt(sp[1]);
            N = Integer.parseInt(sp[2]);
            W = Integer.parseInt(sp[3]);
            P1 = Integer.parseInt(sp[4]);
            P2 = Integer.parseInt(sp[5]);
            
            for(int i=1;i<=N;i++){
                dp[0][i] = 0.0;
            }
            
            for(int i=0;i<=N;i++){
                dp[i][0] = 1.0;
            }
            
            double p1 = P1/100.0;
            double p2 = P2/100.0;
            
            for(int i=1;i<=N;i++){
                for(int j=1;j<=W;j++){
                    
                    double a = 0.0;
                    double b = 0.0;
                    int c = Math.max(j - X, 0);
                    int d = Math.max(j - Y, 0);
                    a = dp[i-1][c]*p1 + dp[i-1][j]*(1 - p1);
                    b = dp[i-1][d]*p2 + dp[i-1][j]*(1 - p2);
                    
                    dp[i][j] = Math.max(a, b);
               //     System.out.print(dp[i][j] + " ");
                }
              //  System.out.println();
            }
            
            System.out.println(df.format(dp[N][W]*100.0));
        }
        
    }

}
