package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MonkAndOtakuland {

    static int MAX = 200000;
    static int N, M;
    static String input;
    static node[] tree;

    public static class node {
        int lr = 0;
        int rl = 0;
        int rev = 0;
    }

    public static void build(int node, int start, int end) {

        tree[node] = new node();
        tree[node].rev = 0;

        if (start == end) {

            if (input.charAt(start) == '>') {
                tree[node].lr = 1;
                tree[node].rl = 0;
            } else {
                tree[node].lr = 0;
                tree[node].rl = 1;
            }

        } else {
            int mid = (start + end) >> 1;
            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);

            tree[node].lr = tree[2 * node].lr + tree[2 * node + 1].lr;
            tree[node].rl = tree[2 * node].rl + tree[2 * node + 1].rl;
        }

    }

    public static void update(int node, int start, int end, int l, int r) {

        tree[node].rev %= 2;
        if (tree[node].rev != 0) {

            int temp = tree[node].lr;
            tree[node].lr = tree[node].rl;
            tree[node].rl = temp;

            if (start != end) {
                tree[2 * node].rev += tree[node].rev;
                tree[2 * node + 1].rev += tree[node].rev;
            }

            tree[node].rev = 0;
        }

        if (start > r || end < l) {
            return;
        }
        
        if (l <= start && end <= r) {

            int temp = tree[node].lr;
            tree[node].lr = tree[node].rl;
            tree[node].rl = temp;

            if (start != end) {
                tree[2 * node].rev++;
                tree[2 * node + 1].rev++;
            }

            return;
        }

        int mid = (start + end) >> 1;
        update(2 * node, start, mid, l, r);
        update(2 * node + 1, mid + 1, end, l, r);

        tree[node].lr = tree[2 * node].lr + tree[2 * node + 1].lr;
        tree[node].rl = tree[2 * node].rl + tree[2 * node + 1].rl;

    }

    public static int query(int node, int start, int end, int l, int r, boolean isLR) {

        if (start > end || start > r || end < l) {
            return 0;
        }

        tree[node].rev %= 2;
        if (tree[node].rev != 0) {

            int temp = tree[node].lr;
            tree[node].lr = tree[node].rl;
            tree[node].rl = temp;

            if (start != end) {
                tree[2 * node].rev++;
                tree[2 * node + 1].rev++;
            }

            tree[node].rev = 0;
        }

        if (l <= start && end <= r) {
            if (isLR == true) {
                return tree[node].rl;
            } else {
                return tree[node].lr;
            }
        }

        int mid = (start + end) >> 1;
        int a = query(2 * node, start, mid, l, r, isLR);
        int b = query(2 * node + 1, mid + 1, end, l, r, isLR);

        return (a + b);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] sp = line.split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);

        input = br.readLine();

        tree = new node[5 * MAX];
        build(1, 0, N - 2);

        StringBuilder output = new StringBuilder();
        while (M-- > 0) {

            line = br.readLine();
            sp = line.split(" ");

            int a = Integer.parseInt(sp[0]);
            int b = Integer.parseInt(sp[1]);
            int c = Integer.parseInt(sp[2]);

            if (a == 1) {
                update(1, 0, N - 2, b - 1, c - 2);
            } else {
                boolean flag = true;
                if(b > c){
                    flag = false;
                    int temp = b;
                    b = c;
                    c = temp;
                }
                output.append(query(1, 0, N - 2, b - 1, c - 2, flag));
                output.append('\n');
            }
        }
        System.out.println(output);
    }

}
