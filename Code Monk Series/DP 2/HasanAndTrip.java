import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class TestClass {
 
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String token;
		String[] sp;
		long[][] pos = new long[N][3];
		for(int i=0;i<N;i++){
			
			token = br.readLine();
			sp = token.split(" ");
			pos[i][0] = Integer.parseInt(sp[0]);
			pos[i][1] = Integer.parseInt(sp[1]);
			pos[i][2] = Integer.parseInt(sp[2]);
			
		}
		
		double[] dp = new double[N];
		for(int i=0;i<N;i++){
			dp[i] = Integer.MIN_VALUE;
		}
		dp[0] = pos[0][2];
		for(int i=0;i<N-1;i++){
			for(int j=i+1;j<N;j++){
				
				long x = pos[j][0] - pos[i][0];
				long y = pos[j][1] - pos[i][1];
				long a = x*x;
				long b = y*y;
				a += b;
				double dist = Math.sqrt(a);
				double  happiness = (pos[j][2] + dp[i] - dist);
				
				if(happiness > dp[j]){
					dp[j] = happiness;
				}
				
			}
		}
		
		
		System.out.println(String.format("%.6f", dp[N-1]));
	}
 
}