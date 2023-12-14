package pers.sea.shield.dispatch.common.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * JUC test
 *
 * @author moon on 12/15/2023
 */
public class TestCountDownLatch {

    @DisplayName("闭锁操作 其他线程都执行完成后当前线程才能继续执行1")
    @Test
    void case1() {
        CountDownLatch latch = new CountDownLatch(2);
        long start = System.currentTimeMillis();
        new Thread(new LatchDemo(latch)).start();
        new Thread(new LatchDemo(latch)).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间===============================" + (end - start));
        System.out.println("main thread");
    }

    static class LatchDemo implements Runnable {
        private final CountDownLatch latch;

        public LatchDemo(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this) {
                try {
                    for (int i = 0; i < 1000; i++) {
                        if (i % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + "-------------" + i);
                        }
                    }
                } finally {
                    // 线程执行完毕后 countdown 减一
                    latch.countDown();
                }
            }
        }
    }
}


