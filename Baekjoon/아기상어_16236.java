package practice;

import java.io.*;
import java.util.*;

public class Q16236 {

	static class Shark {
        int row, col, weight, exp;

		Shark(int row, int col) {
			this.row = row;
			this.col = col;
			this.weight = 2;
			this.exp = 0;
		}

		void levelUp() {
			this.weight++;
			this.exp = 0;
		}

		boolean canLevelUp() {
			return this.weight == this.exp;
		}
	}

	static int N;
	static int[][] map;
	static Shark baby;
	static int answer = 0;

	// 북, 서, 동, 남 순서로 탐색
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	static boolean[][] visited;
	static Queue<int[]> queue;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) { // 상어 무게가 9 이상일 경우를 위한 예외처리
					map[i][j] = 0;
					baby = new Shark(i, j);
				}
			}
		}

		search();
		System.out.println(answer);
	}

	public static void search() {
		visited[baby.row][baby.col] = true;
		queue = new LinkedList<>();
		queue.offer(new int[] { baby.row, baby.col, 0 }); // row, col, time

		int prevTime = 0;
        PriorityQueue<int[]> eatableQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] != o2[2]) return o1[2] - o2[2]; // 거리 우선
            if (o1[0] != o2[0]) return o1[0] - o2[0]; // 행 우선 (위쪽)
            return o1[1] - o2[1]; // 열 우선 (왼쪽)
        });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cRow = cur[0];
			int cCol = cur[1];
			int cTime = cur[2];

			for (int i = 0; i < 4; i++) {
				int nRow = cRow + dr[i];
				int nCol = cCol + dc[i];
				int nTime = cTime + 1;

				if (nRow <= 0 || nRow > N || nCol <= 0 || nCol > N || visited[nRow][nCol])
					continue;
				int weight = map[nRow][nCol];
				if (baby.weight < weight) { // 지나갈 수 없음
					continue;
				} else if (baby.weight == weight || weight == 0) { // 물고기를 먹지못하고 지나가는것과 빈 칸
					visited[nRow][nCol] = true;
					queue.offer(new int[] {nRow, nCol, nTime});
				} else { // 물고기 먹기 가능
					visited[nRow][nCol] = true;
					eatableQueue.offer(new int[] { nRow, nCol, nTime });
				}
			}

			if (!eatableQueue.isEmpty() && queue.isEmpty()) {
				int[] eQ= eatableQueue.poll();
				eat(eQ[0], eQ[1], eQ[2]);
				eatableQueue.clear();
			}
		}
	}

	public static void eat(int row, int col, int time) {
		visited = new boolean[N + 1][N + 1];
		visited[row][col] = true;
		map[row][col] = 0;
		baby.exp++;
		baby.row = row;
		baby.col = col;
		queue.clear();
		queue.offer(new int[] { baby.row, baby.col, 0 });

		if (baby.canLevelUp()) {
			baby.levelUp();
		}
		answer += time;
	}
}
