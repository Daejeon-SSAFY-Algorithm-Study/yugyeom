package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15651 {

    static int[] track;
    static int N, M;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        track = new int[M+1];

        sb = new StringBuilder();
        func(1);
        System.out.println(sb);
    }

    private static void func(int k) {
        if (k == M+1) {
            for (int i = 1; i <= M; i++) {
                sb.append(track[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            track[k] = i;
            func(k+1);
        }
    }
}
