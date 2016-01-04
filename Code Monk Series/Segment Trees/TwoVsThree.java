package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TwoVsThree {

    public static class node{
        int val = 0;
        int count = 0;
    }
    
    static node[] tree;
    static String bin;
    
    public static int powMod(int count){
        if(count%2 == 0) return 1;
        return 2;
    }
    
    public static int calc(node a, node b){
        
        return (b.val%3 + ((a.val%3) * powMod(b.count)))%3;  
        
    }
    
    public static void build(int node, int start, int end){
        
        node seg = new node();
        seg.val = 0;
        seg.count = 0;
        tree[node] = seg;
        
        if(start == end){
            
            if(bin.charAt(start) == '0')
                tree[node].val = 0;
            else
                tree[node].val = 1;
            
            tree[node].count = 1;
            
        }else{
            
            int mid = (start+end)>>1;
            build(2*node,start,mid);
            build(2*node+1,mid+1,end);
            
            tree[node].count = tree[2*node].count + tree[2*node+1].count;
            tree[node].val = calc(tree[2*node], tree[2*node+1]);
            
        }
        
    }
    
    public static node query(int node, int start, int end, int l, int r){
        
        node temp = new node();
        temp.val = 0;
        temp.count = 0;
        
        if(l > end || r < start) return temp;
        
        if(l <= start && r >= end){
            return tree[node];
        }
        
        int mid = (start + end)>>1;
        node a = query(2*node, start, mid, l, r);
        node b = query(2*node+1, mid+1, end, l, r);
        
        temp.count = a.count + b.count;
        temp.val = calc(a, b);
        
        return temp;
    }
    
    public static void update(int node, int start, int end, int index){
        
        if(start == end){
            if(start == index){
                tree[node].val = 1;
                tree[node].count = 1;
            }
        }else{
            
            int mid = (start + end)>>1;
            if(index <= mid){
                update(2*node,start,mid,index);
            }else{
                update(2*node+1,mid+1,end,index);
            }
            
            tree[node].count = tree[2*node].count + tree[2*node+1].count;
            tree[node].val = calc(tree[2*node], tree[2*node+1]);
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String token = br.readLine();
        int N = Integer.parseInt(token);
        tree = new node[5*N];
        bin = br.readLine();
        char[] arr = bin.toCharArray();
        build(1, 0, N-1);
        
        token = br.readLine();
        int Q = Integer.parseInt(token);
        StringBuilder output = new StringBuilder();
        
        while(Q-- > 0){
            
            token = br.readLine();
            String[] sp = token.split(" ");
            
            int op = Integer.parseInt(sp[0]);
            int l = Integer.parseInt(sp[1]);
            int r = 0;
            if(op == 0){
                r = Integer.parseInt(sp[2]);
            }
            
            if(op == 0){
                output.append(query(1, 0, N-1, l, r).val);
                output.append('\n');
            }else{
                if(arr[l] == '0'){
                    arr[l] = '1';
                    update(1, 0, N-1, l);
                }
                
            }
            
            
        }
        
        System.out.println(output);

    }

}
