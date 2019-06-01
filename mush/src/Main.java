import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int
                n = scanner.nextInt(),
                m = scanner.nextInt(),
                l = scanner.nextInt();

        boolean[][] mat = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] line = scanner.nextLine().toCharArray();
            for (int j = 0; j < m; j++)
                mat[i][j] = line[j] == '*';
        }

        for (; l > 0; l--) {
            boolean[][] prevMat = new boolean[n][m];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    prevMat[i][j] = wasMush(mat,i,j);
        }


    }

    private static boolean wasMush(boolean[][] mat, int i, int j) {

    }
}
