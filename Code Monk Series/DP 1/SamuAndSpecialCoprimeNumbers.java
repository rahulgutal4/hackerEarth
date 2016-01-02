package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SamuAndSpecialCoprimeNumbers {

    static int test;
    static int[] digits = new int[20];
    static long L, R;
    static long[][][] arr = new long[20][200][1500];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp;
        test = Integer.parseInt(br.readLine());

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 200; j++) {
                for (int k = 0; k < 1500; k++) {
                    arr[i][j][k] = -1;
                }
            }
        }

        while (test-- > 0) {

            sp = br.readLine().split(" ");
            L = Integer.parseInt(sp[0]);
            R = Integer.parseInt(sp[1]);

            int len1 = 0, len2 = 0;
            for (int i = 0; i < 20; i++)
                digits[i] = 0;
            long num = L - 1;
            while (num != 0) {
                digits[len1++] = (int) (num % 10);
                num /= 10;
            }
            long a = dp(len1 - 1, 0, 0, true);

            for (int i = 0; i < 20; i++)
                digits[i] = 0;
            num = R;
            while (num != 0) {
                digits[len2++] = (int) (num % 10);
                num /= 10;
            }
            long b = dp(len2 - 1, 0, 0, true);

            System.out.println(b - a);
        }

    }

    public static long dp(int pos, int digitSum, int digitSquareSum, Boolean check) {

        if (pos == -1) {
            if (gcd(digitSum, digitSquareSum) == 1)
                return 1;
            return 0;
        }
        
        if (!check && arr[pos][digitSum][digitSquareSum] != -1)
            return arr[pos][digitSum][digitSquareSum];
        
        long ans = 0;
        int endingDigit;
        if (check) {
            endingDigit = digits[pos];
        } else {
            endingDigit = 9;
        }
        for (int currentDigit = 0; currentDigit <= endingDigit; currentDigit++) {
            ans += dp(pos - 1, digitSum + currentDigit, digitSquareSum + currentDigit * currentDigit, check && currentDigit == endingDigit);
        }
        if (!check)
            arr[pos][digitSum][digitSquareSum] = ans;
        return ans;
    }

    static int gcd(int x, int y) {
        if (x == 0 || y == 0)
            return 0;

        int r = 0, a, b;
        a = (x > y) ? x : y; // a is greater number
        b = (x < y) ? x : y; // b is smaller number

        r = b;
        while (a % b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }

}
