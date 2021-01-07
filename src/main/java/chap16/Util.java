package chap16;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Util {

    private static final Random RANDOM = new Random(0);
    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    /**
    * @author : 김하빈(hbkim@bpnsolution.com)
    * * @description : 가격 정보를 얻는 행위 메소드(1초 지연을 흉내)
    * @Date : 2021. 01. 05
    * @Time : 15:09:12
    */
    public static void delay() {
        int delay = 1000;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double format(double number) {
        synchronized (formatter) {
            return Double.valueOf(formatter.format(number));
        }
    }

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        return CompletableFuture.supplyAsync(() -> futures.stream()
                .map(future -> future.join())
                .collect(Collectors.<T>toList()));
    }
    
}
