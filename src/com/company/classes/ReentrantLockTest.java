package com.company.classes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        sameThreadMultipleLocks(lock);
        sameThreadThroughDifferentMethods(lock);
    }

    // 1. same thread can lock same lock multiple times
    private static void sameThreadMultipleLocks(ReentrantLock lock) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 4; i++) {
            threadPool.execute(new MultipleLocksBySameThread("MultipleLocksBySameThread-" + i, lock));
        }
        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        System.out.println();
    }

    // 2. same thread can lock and unlock in different methods
    private static void sameThreadThroughDifferentMethods(ReentrantLock lock) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 4; i++) {
            threadPool.execute(new SameLockThroughMultipleMethodsBySameThread("SameLockThroughMultipleMethodsBySameThread-" + i, lock));
        }
        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        System.out.println();
    }


}


