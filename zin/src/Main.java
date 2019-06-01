import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[][] mat = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                mat[i][j] = scanner.nextInt();

        if (n < 3 || m < 3) {
            System.out.println(0);
            return;
        }

        int cnt = 0;
        for (int i = 1; i < n - 1; i++)
            for (int j = 1; j < m - 1; j++) {
                boolean lrg = mat[i][j] > mat[i][j + 1] && mat[i][j] > mat[i][j - 1];
                boolean lrs = mat[i][j] < mat[i][j + 1] && mat[i][j] < mat[i][j - 1];
                boolean udg = mat[i][j] > mat[i + 1][j] && mat[i][j] > mat[i - 1][j];
                boolean uds = mat[i][j] < mat[i + 1][j] && mat[i][j] < mat[i - 1][j];
                cnt += (lrg && uds) || (lrs && udg) ? 1 : 0;
            }
        System.out.println(cnt);
    }
}
