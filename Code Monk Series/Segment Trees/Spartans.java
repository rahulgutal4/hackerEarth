package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Spartans {

    public static class node{
        int prefix, suffix, ans;
    }
    
    static long[] arr;
    static node[] tree = new node[300030];
    
    public static void build(int node, int start, int end){
        
        node seg = new node();
        seg.prefix = 0;
        seg.suffix = 0;
        seg.ans = 0;
        tree[node] = seg;
        
        if(start == end){
            tree[node].prefix = 1;
            tree[node].suffix = 1;
            tree[node].ans = 1;
        }else{
            
            int mid = (start + end)>>1;
            build(2*node,start,mid);
            build(2*node+1,mid+1,end);
            
            int left = 2*node;
            int right = 2*node + 1;
            int a = -1;
            if(arr[mid] < arr[mid+1]){
                a = tree[left].suffix + tree[right].prefix;
            }
            
            tree[node].ans = Math.max(tree[left].ans, tree[right].ans);
            tree[node].ans = Math.max(tree[node].ans, a);
            
            if(tree[left].prefix == (mid - start + 1) && arr[mid] < arr[mid+1]){
                tree[node].prefix = tree[left].prefix + tree[right].prefix;
            }else{
                tree[node].prefix = tree[left].prefix;
            }
            
            if(tree[right].suffix == (end - mid) && arr[mid] < arr[mid+1]){
                tree[node].suffix = tree[left].suffix + (end - mid);
            }else{
                tree[node].suffix = tree[right].suffix;
            }
            
        }
        
    }
    
    public static node query(int node, int start, int end, int l, int r){
        
        node temp = new node();
        temp.prefix = 0;
        temp.suffix = 0;
        temp.ans = 0;
        
        if(r < start || l > end) return temp;
        
        if(l <= start && end <= r){
            return tree[node];
        }
        
        int mid = (start + end)>>1;
        node a = query(2*node,start,mid,l,r);
        node b = query(2*node + 1, mid+1, end, l ,r);
        
        int cnt = -1;
        if(arr[mid] < arr[mid+1]){
            cnt = a.suffix + b.prefix;
        }
        
        temp.ans = Math.max(a.ans, b.ans);
        temp.ans = Math.max(temp.ans, cnt);
        
        if(a.prefix == (mid - start + 1) && arr[mid] < arr[mid+1]){
            temp.prefix = a.prefix + b.prefix;
        }else{
            temp.prefix = a.prefix;
        }
        
        if(b.suffix == (end - mid) && arr[mid] < arr[mid+1]){
            temp.suffix = a.suffix + b.suffix;
        }else{
            temp.suffix = b.suffix;
        }
        
        return temp;
    }
    
    public static void update(int node, int start, int end, int index, int val){
        
        if(start == end){
            arr[index] += val;
        }else{
            
            int mid = (start + end)>>1;
            if(index >= start && index <= mid){
                update(2*node, start, mid, index, val);
            }else{
                update(2*node+1, mid+1, end, index, val);
            }
            
            int left = 2*node;
            int right = 2*node + 1;
            int a = -1;
            if(arr[mid] < arr[mid+1]){
                a = tree[left].suffix + tree[right].prefix;
            }
            
            tree[node].ans = Math.max(tree[left].ans, tree[right].ans);
            tree[node].ans = Math.max(tree[node].ans, a);
            
            if(tree[left].prefix == (mid - start + 1) && arr[mid] < arr[mid+1]){
                tree[node].prefix = tree[left].prefix + tree[right].prefix;
            }else{
                tree[node].prefix = tree[left].prefix;
            }
            
            if(tree[right].suffix == (end - mid) && arr[mid] < arr[mid+1]){
                tree[node].suffix = tree[left].suffix + tree[right].suffix;
            }else{
                tree[node].suffix = tree[right].suffix;
            }
            
        }
        
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String token = br.readLine();
        int test = Integer.parseInt(token);
        String[] sp;
        StringBuilder result = new StringBuilder();
        while (test-- > 0) {
         
            token = br.readLine();
            sp = token.split(" ");
            int N = Integer.parseInt(sp[0]);
            int Q = Integer.parseInt(sp[1]);
            
            arr = new long[N];
            
            token = br.readLine();
            sp = token.split(" ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(sp[i]);
            }
            
            build(1,0,N-1);
            
            int t,x,y;
            while(Q-- > 0){
                
                token = br.readLine();
                sp = token.split(" ");
                
                t = Integer.parseInt(sp[0]);
                x = Integer.parseInt(sp[1]);
                y = Integer.parseInt(sp[2]);
               
                if(t == 0){
                    x--;y--;
                    node ans = query(1,0,N-1,x,y);
                    result.append(ans.ans);
                    result.append("\n");
                }else{
                    x--;
                    update(1, 0, N-1, x, y);
                }
                
            }
            
        }
        System.out.println(result);
    }

}
