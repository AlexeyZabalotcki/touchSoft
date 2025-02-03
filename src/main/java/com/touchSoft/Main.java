package com.touchSoft;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Consumer consumer = new Consumer();

        consumer.accept(10);
        Thread.sleep(1000);
        consumer.accept(20);
        Thread.sleep(1000);
        consumer.accept(30);

        System.out.println("Mean: " + consumer.mean());
    }
}