import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.TimerTask;

class Timer {
    private long startTime;
    private AvgCalc avgCalc = new AvgCalc();
    private String title;

    Timer(boolean tic, boolean print, String title, int milli) {
        this.title = title;
        if (tic)
            tic();
        if (print) {
            java.util.Timer timer = new java.util.Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    print();
                }
            };
            timer.schedule(task, 0, milli);
        }
    }

    Timer(String title) {
        this(false, false, title, -1);
    }

    Timer(String title, int milli) {
        this(false, true, title, milli);
    }

    void tic() {
        startTime = System.nanoTime();
    }

    void print() {
        System.out.format("%s:%s\n", title, getAvg());
    }

    String toc() {
        long dur = System.nanoTime() - startTime;
        avgCalc.add(dur);
        return getAvg();
    }

    String getAvg() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("###,###.00", symbols);
        return df.format(avgCalc.getAvg() / 1000) + " µs";
    }
}

class AvgCalc {
    private long n;
    private double avg;

    double add(long num) {
        avg = (avg * n + num) / (++n);
        return avg;
    }

    public double getAvg() {
        return avg;
    }
}