package com.touchSoft;

import java.util.concurrent.TimeUnit;

public class Consumer {
    private static final int WINDOW_SECONDS = 300;

    private static class Bucket {
        long timestamp;
        long sum;
        int count;
    }

    private final Bucket[] buckets = new Bucket[WINDOW_SECONDS];

    public Consumer() {
        for (int i = 0; i < WINDOW_SECONDS; i++) {
            buckets[i] = new Bucket();
        }
    }

    /**
     * Called every time a new number is received.
     */
    public void accept(int number) {
        long currentSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        int index = (int) (currentSecond % WINDOW_SECONDS);
        Bucket bucket = buckets[index];

        if (bucket.timestamp != currentSecond) {
            bucket.timestamp = currentSecond;
            bucket.sum = 0;
            bucket.count = 0;
        }

        bucket.sum += number;
        bucket.count++;
    }

    /**
     * Returns the mean (average) of numbers accepted in the last 5 minutes.
     */
    public double mean() {
        long currentSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        long totalSum = 0;
        int totalCount = 0;

        for (Bucket bucket : buckets) {
            if (currentSecond - bucket.timestamp < WINDOW_SECONDS) {
                totalSum += bucket.sum;
                totalCount += bucket.count;
            }
        }

        if (totalCount == 0) {
            return 0.0;
        }
        return (double) totalSum / totalCount;
    }
}
