package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1697 {

    static int N, K;
    static int[] dc = {-1, 1};
    static int[] map = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Arrays.fill(map, 100000);

        bfs();

        System.out.println(map[K]);
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{N, 0});
        map[N] = 0;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cCol = cur[0];
            int cTime = cur[1];

            for (int i = 0; i < 3; i++) {
                int nCol;
                int nTime = cTime + 1;
                if (i == 2) {
                    nCol = cCol * 2;
                } else {
                    nCol = cCol + dc[i];
                }
                if (nCol > 100000 || nCol < 0) continue;
                if (map[nCol] > nTime) {
                    map[nCol] = nTime;
                    queue.offer(new int[]{nCol, nTime});
                }
            }
        }
    }
}
