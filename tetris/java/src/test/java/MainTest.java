import org.junit.Test;

import java.io.*;
import java.util.Random;
import java.util.stream.IntStream;

public class MainTest {
    //0
    @Test
    public void sameTest() {
        int n = 1_000_000;
        int m = 1_000_000_000;
        Integer[] cols = new Integer[n];
        for (int i = 0; i < n; i++)
            cols[i] = m;
        testCols(makeColsStr(cols));
    }


    //166700651360807
    @Test
    public void randomTest() {
        int n = 1_000_000;
        int m = 1_000_000_000;
        Integer[] cols = new Integer[n];
        Random random = new Random(1000);
        for (int i = 0; i < n; i++)
            cols[i] = random.nextInt(m) + 1;
        testCols(makeColsStr(cols));
    }

    //3
    @Test
    public void queraTest1() {
        testCols(makeColsStr(new Integer[]{1, 3, 2}));
    }

    //6
    @Test
    public void queraTest2() {
        testCols(makeColsStr(new Integer[]{4, 3, 1, 3, 7}));
    }

    private String makeColsStr(Integer[] cols) {
        StringBuilder sb = new StringBuilder();
        String lBreak = System.getProperty("line.separator");
        int n = cols.length;
        sb.append(n).append(lBreak);
        IntStream.range(0, n).boxed()
                .forEach(i -> sb.append(cols[i]).append(' '));
        sb.append(lBreak);
        return sb.toString();
    }

    private void testCols(String input) {
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Timer timer = new Timer("main time");
            Main.main(null);
            System.out.println(timer.toc());
        } finally {
            System.setIn(stdin);
        }
    }
}