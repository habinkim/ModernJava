package chap16.v1;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import chap16.Util;

public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        Util.delay();
        return Util.format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public Future<Double> getPriceAsync(String product) {
        // CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        // new Thread(() -> {
        //     try {
        //         double price = calculatePrice(product);
        //         futurePrice.complete(price);
        //     } catch (Exception e) {
        //         futurePrice.completeExceptionally(e);
        //     }
            
        // }).start();
        // return futurePrice;

        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public String getName() {
        return name;
    }
    
}
