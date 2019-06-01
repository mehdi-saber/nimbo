import java.util.Scanner;

public class Main {
    private static int[] cols;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        cols = new int[n];
        for (int i = 0; i < n; i++)
            cols[i] = scanner.nextInt();

        int maxVal = cols[0];
        for (int i = 0; i < n; i++)
            if (cols[i] > maxVal)
                maxVal = cols[i];

        System.out.println(getSteps(0, n - 1, maxVal));
    }

    private static long getSteps(int sIndex, int eIndex, int maxVal) {
        long steps = 0;
        int localMaxIndex = sIndex, localMax = cols[sIndex];
        for (int i = sIndex; i <= eIndex; i++)
            if (cols[i] > localMax) {
                localMax = cols[i];
                localMaxIndex = i;
            }
        int delta = maxVal - cols[localMaxIndex];
        steps += delta;
        if (delta > 0 && localMax < maxVal)
            for (int i = sIndex; i <= eIndex; i++)
                cols[i] = cols[i] + delta;

        int[] bounds = findNewBound(sIndex, eIndex, localMaxIndex, maxVal);
        int lStart = bounds[0], lEnd = bounds[1], rStart = bounds[2], rEnd = bounds[3];

        if (lStart <= lEnd)
            steps += getSteps(lStart, lEnd, maxVal);
        if (rStart <= rEnd)
            steps += getSteps(rStart, rEnd, maxVal);
        return steps;
    }

    private static int[] findNewBound(int sIndex, int eIndex, int localMaxIndex, int maxVal) {
        int lStart;
        for (lStart = sIndex; lStart < localMaxIndex - 1 && cols[lStart] == maxVal; lStart++) ;
        int lEnd;
        for (lEnd = localMaxIndex - 1; lEnd > lStart + 1 && cols[lEnd] == maxVal; lEnd--) ;

        int rStart;
        for (rStart = localMaxIndex + 1; rStart < eIndex && cols[rStart] == maxVal; rStart++) ;
        int rEnd;
        for (rEnd = eIndex; rEnd > rStart + 1 && cols[rEnd] == maxVal; rEnd--) ;

        return new int[]{lStart, lEnd, rStart, rEnd};
    }
}