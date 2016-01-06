package triesAndSuffixTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class sonyaAndStringShifts {

	static int N, Q;
	static String s;
	static private int[][] suffixMatrix;
	static private record[] records;
	static private int[] minArray;

	private static class record implements Comparable {
		int firstHalf, secondHalf, index;

		@Override
		public int compareTo(Object o) {

			if(o == null){
				return 0;
			}
			
			record r = (record) o;
			if (firstHalf == r.firstHalf) {
				if (secondHalf > r.secondHalf) {
					return 1;
				} else if(secondHalf < r.secondHalf){
					return -1;
				}else{
					return 0;
				}
			} else {
				if (firstHalf > r.firstHalf) {
					return 1;
				} else {
					return -1;
				}
			}

		}
	}

	private static void suffixArray(String str) {

		int len = str.length();
		int totalSteps = (int) Math.ceil(Math.log(len) / Math.log(2));

		suffixMatrix = new int[totalSteps + 1][len];
		records = new record[len];

		for (int i = 0; i < len; i++) {
			suffixMatrix[0][i] = str.charAt(i) - 'a';
		}

		for (int steps = 1, i = 1; i <= totalSteps; steps = (int) Math.pow(2, i), i++) {

			for (int j = 0; j < len; j++) {
				record oneRecord = new record();
				oneRecord.firstHalf = suffixMatrix[i - 1][j];
				// if((j + steps) >= len) oneRecord.secondHalf = -1;
				oneRecord.secondHalf = suffixMatrix[i - 1][(j + steps) % len];
				oneRecord.index = j;

				records[j] = oneRecord;
			}

			Arrays.sort(records);

			suffixMatrix[i][records[0].index] = 0;
			for (int j = 1; j < len; j++) {

				if (records[j].firstHalf == records[j - 1].firstHalf
						&& records[j].secondHalf == records[j - 1].secondHalf) {
					suffixMatrix[i][records[j].index] = suffixMatrix[i][records[j - 1].index];
				} else {
					suffixMatrix[i][records[j].index] = j;
				}

			}

			/*
			 * for(int j=0;j<len;j++){ System.out.print(suffixMatrix[steps][j] +
			 * " "); } System.out.println();
			 */

		}

		minArray = new int[len];
		minArray[0] = 0;
		for (int i = 1; i < N; i++) {
			
			if(suffixMatrix[totalSteps][minArray[i-1]] <= suffixMatrix[totalSteps][i]){
				minArray[i] = minArray[i-1];
			}else{
				minArray[i] = i;
			}
			
		}

		/*
		 * for(int i=0;i<len;i++){ System.out.println(str.substring(i) + " " +
		 * suffixMatrix[2][i]); }
		 */

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		s = br.readLine();
		Q = Integer.parseInt(br.readLine());

		suffixArray(s);
		
		StringBuilder output = new StringBuilder();
		while (Q-- > 0) {

			int a = Integer.parseInt(br.readLine());
			output.append(minArray[a] + "\n");

		}
		
		System.out.println(output);
	}

}
