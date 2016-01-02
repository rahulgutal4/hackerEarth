package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HasanAndWalk {

    static int N, M, H, W, Xh, Yh, Xc, Yc;
    static String way;
    static long[][][] dp = new long[202][202][202];
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String token = br.readLine();
        String[] sp = token.split(" ");
        
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        H = Integer.parseInt(sp[2]);
        W = Integer.parseInt(sp[3]);
        Xh = Integer.parseInt(sp[4]);
        Yh = Integer.parseInt(sp[5]);
        Xc = Integer.parseInt(sp[6]);
        Yc = Integer.parseInt(sp[7]);
        
        way = br.readLine();
        
        dp[N][Yc][Xc] = 1;
        for(int step=N-1;step>=0;step--){
            
            for(int r=1;r<=H;r++){
                
                for(int c=1;c<=W;c++){
                    
                    dp[step][r][c] = 0;
                    if(r > 1){
                        dp[step][r][c] += dp[step+1][r-1][c];
                    }
                    
                    if(r < H){
                        dp[step][r][c] += dp[step+1][r+1][c];
                    }
                    
                    if(c < W){
                        dp[step][r][c] += dp[step+1][r][c+1];
                    }
                    
                    if(c > 1){
                        dp[step][r][c] += dp[step+1][r][c-1];
                    }
                    
                    dp[step][r][c] %= M;
                }
            }
        }
        
 /*       for(int i=0;i<=N;i++){
            for(int j=1;j<=H;j++){
                for(int k=1;k<=W;k++){
                    System.out.print(dp[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println("---------------------");
        }
*/        
        int curr_r = Yh;
        int curr_c = Xh;
        long ans = 0;
        for(int i=0;i<N;i++){
            char ch = way.charAt(i);
            if(curr_r>1 && ch>'D'){
                ans+=dp[i+1][curr_r-1][curr_c];
            }
            if(curr_r<H && ch>'U'){
                ans+=dp[i+1][curr_r+1][curr_c];
            }
            if(curr_c>1 && ch>'L'){
                ans+=dp[i+1][curr_r][curr_c-1];
            }
            if(curr_c<W && ch>'R'){
                ans+=dp[i+1][curr_r][curr_c+1];
            }
            ans%=M;
            if(ch=='R')curr_c++;
            if(ch=='L')curr_c--;
            if(ch=='D')curr_r--;
            if(ch=='U')curr_r++;
        }
        System.out.println(ans);
    }

}
