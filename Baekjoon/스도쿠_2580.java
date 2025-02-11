package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2580_2 {

    static int[][] map = new int[10][10];
    static ArrayList<int[]> blanks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        for (int i = 1; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                }
            }
        }
        func(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static boolean func(int k) {
        if (k == blanks.size()) {
            return true;
        }

        int[] curBlankPos = blanks.get(k);
        int cRow = curBlankPos[0];
        int cCol = curBlankPos[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(cRow, cCol, num)) {
                map[cRow][cCol] = num;
                if (func(k + 1)) {
                    return true;
                }
                map[cRow][cCol] = 0;
            }
        }
        return false;
    }

    private static boolean isValid(int r, int c, int num) {
        for (int i = 1; i < 10; i++) {
            if (map[r][i] == num) {
                return false;
            }
        }
        for (int i = 1; i < 10; i++) {
            if (map[i][c] == num) {
                return false;
            }
        }
        int baseRow = ((r - 1) / 3) * 3 + 1;
        int baseCol = ((c - 1) / 3) * 3 + 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[baseRow + i][baseCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
