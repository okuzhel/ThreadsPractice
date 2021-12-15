package com.company.classes;

import java.util.concurrent.locks.ReentrantLock;

class MultipleLocksBySameThread implements Runnable {
    private final String threadName;
    ReentrantLock lock;

    MultipleLocksBySameThread(String threadName, ReentrantLock lock) {
        this.threadName = threadName;
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("In run method trying to acquire lock - " + threadName);
        lock.lock();
        try {
            System.out.println(threadName + " has got lock");
            lockMethod();
        } finally {
            lock.unlock();
            System.out.println("Count of locks held by " + threadName +
                    " - " + lock.getHoldCount());
        }

    }

    public void lockMethod() {
        System.out.println("In lockMethod, " + threadName +
                " is waiting to get lock");
        lock.lock();
        try {
            System.out.println("Count of locks held by " + threadName +
                    " - " + lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }
}
