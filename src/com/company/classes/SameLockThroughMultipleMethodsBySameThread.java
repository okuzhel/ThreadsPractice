package com.company.classes;

import java.util.concurrent.locks.ReentrantLock;

class SameLockThroughMultipleMethodsBySameThread implements Runnable {
    private final String threadName;
    ReentrantLock lock;

    SameLockThroughMultipleMethodsBySameThread(String threadName, ReentrantLock lock) {
        this.threadName = threadName;
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("In run method trying to acquire lock - " + threadName);
        lock.lock();
        System.out.println(threadName + " has got lock");
        lockMethod();
    }

    public void lockMethod() {
        try {
            System.out.println("Count of locks held by " + threadName +
                    " - " + lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }
}
