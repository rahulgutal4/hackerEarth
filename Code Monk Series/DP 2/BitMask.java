package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BitMask {

    static int N;
    static int[] X;
    static int[] Y;
    static double[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String token = br.readLine();
        String[] sp;
        N = Integer.parseInt(token);
        N *= 2;

        X = new int[N + 1];
        Y = new int[N + 1];
        dp = new double[1 << N][N + 1];

        for (int i = 0; i < N; i++) {
            token = br.readLine();
            sp = token.split(" ");
            X[i] = Integer.parseInt(sp[0]);
            Y[i] = Integer.parseInt(sp[1]);
        }

        for (int mask = 1; mask < (1 << N); mask++) {

            for (int i = 0; i < N; i++) {

                dp[mask][i] = 99999999;
                if (bit(i, mask)) {

                    if (count(mask) == 1) {
                        dp[mask][i] = 0;
                    } else {
                        for (int j = 0; j < N; j++) {
                            if (bit(j, mask)) {
                                dp[mask][i] = Math.min(dp[mask][i], dp[mask ^ (1 << i)][j] + dist(j, i));
                            }
                        }
                    }
                }
                System.out.print(dp[mask][i] + " ");
            }
            System.out.println();
            System.out.println();
        }

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
