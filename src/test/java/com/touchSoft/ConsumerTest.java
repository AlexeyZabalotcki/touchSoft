package com.touchSoft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumerTest {

    @Test
    public void  shouldBe0WhenNoNumbersAccepted() {
        Consumer consumer = new Consumer();
        assertEquals(0.0, consumer.mean(), 0.001, "Mean should be 0 when no numbers are accepted.");
    }

    @Test
    public void shouldBe10AfterAccepting10() {
        Consumer consumer = new Consumer();
        consumer.accept(10);
        assertEquals(10.0, consumer.mean(), 0.001, "Mean should be 10 after accepting 10.");
    }

    @Test
    public void shouldBe20() {
        Consumer consumer = new Consumer();
        consumer.accept(10);
        consumer.accept(20);
        consumer.accept(30);
        assertEquals(20.0, consumer.mean(), 0.001, "Mean should be 20 for numbers 10, 20, 30.");
    }

    @Test
    public void shouldBe20WhenNumbersAcceptedInDifferentSeconds() throws InterruptedException {
        Consumer consumer = new Consumer();

        consumer.accept(10);
        Thread.sleep(1100);
        consumer.accept(20);
        Thread.sleep(1100);
        consumer.accept(30);

        assertEquals(20.0, consumer.mean(), 0.001, "Mean should be 20 when numbers are accepted in different seconds.");
    }

    @Test
    public void shouldBe100BeforeExpiration() throws InterruptedException {
        Consumer consumer = new Consumer();
        consumer.accept(100);

        Thread.sleep(1100);
        assertEquals(100.0, consumer.mean(), 0.001, "Mean should be 100 before expiration.");
    }
}