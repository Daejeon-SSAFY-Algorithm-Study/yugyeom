package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2615 {

	// 오른쪽으로, 아래로, 오른쪽위, 오른쪽아래로,
	static int[] dr = new int[] { 0, 1, -1, 1 };
	static int[] dc = new int[] { 1, 0, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// index 넘어감을 방하기위해 21로 설정 
		int[][] map = new int[21][21];
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		for (int i = 1; i <= 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 마지막 출력해야하는 값이 가장 왼쪽이면서 위에있는 바둑알을 출력해야하기때문에 열, 행순서로 조회 
		boolean flag = false;
		for (int i = 1; i <= 19; i++) { // 열
			for (int j = 1; j <= 19; j++) { // 행
				int curColor = map[j][i];
				
				if (curColor <= 0 || curColor >= 3) continue;
				
				for(int k = 0; k < 4; k++) {
					int count = 1;

					int nR = j;
					int nC = i;
					for (int l = 0; l < 5; l++) { 
						nR += dr[k];
						nC += dc[k];
						
						if (nR > 19 || nR < 1 || nC > 19 || nC < 1) {
							break;
						}
						if (curColor == map[nR][nC]) {
							count++;
						} else {
							break;
						}
					}

					// 5목일 경우 첫번째 오목의 이전 위치를 확인해 6목인지 확인 
					if (count == 5 && map[j-dr[k]][i-dc[k]] != curColor) { 
						sb.append(curColor).append("\n");
						sb.append(j).append(" ").append(i).append("\n");
						flag = true;
						break;
					}
				}
				if (flag) {
					break;
				}
			}
		}
		if (flag) {
			System.out.println(sb);
		} else {
			System.out.println(0);
		}
	}
}
