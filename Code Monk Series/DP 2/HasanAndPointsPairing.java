package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class HasanAndPointsPairing {

    static int N;
    static int[] X;
    static int[] Y;
    static double[] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String token = br.readLine();
        String[] sp;
        N = Integer.parseInt(token);
        N *= 2;

        X = new int[N + 1];
        Y = new int[N + 1];
        dp = new double[1 << N + 5];

        for (int i = 0; i < N; i++) {
            token = br.readLine();
            sp = token.split(" ");
            X[i] = Integer.parseInt(sp[0]);
            Y[i] = Integer.parseInt(sp[1]);
        }

        DecimalFormat df = new DecimalFormat("####0.000000");
        for (int mask = 1; mask < (1 << N); mask++) {
            
            if(count(mask)%2 == 1) continue;
            
            dp[mask] = 99999999;
            for (int i = 0; i < N; i++) {
                
                if(bit(i,mask)){
                    
                    for(int j=i+1;j<N;j++){
                        
                        if(bit(j,mask)){
                            dp[mask] = Math.min(dp[mask], dp[mask^(1<<i)^(1<<j)] + dist(i,j));
                        }
                    }
                }
            }
            
        }
        System.out.println(df.format(dp[(1<<N) - 1]));
    }

    public static Boolean bit(int i, int mask) {

        if ((mask & (1 << i)) == 0)
            return false;
        return true;

    }

    public static int count(int mask) {

        int ret = 0;
        while (mask > 0) {
            if (mask % 2 == 1)
                ret++;
            mask /= 2;
        }
        return ret;

    }

    public static Double dist(int a, int b) {

        long distance = 0;
        distance = (X[a] - X[b]) * (X[a] - X[b]) + (Y[a] - Y[b]) * (Y[a] - Y[b]);
        return Math.sqrt(distance);

    }

}
