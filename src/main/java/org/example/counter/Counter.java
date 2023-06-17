package org.example.counter;

public class Counter implements Runnable {

    private int count = 0;

    @Override
    public void run() {
        synchronized (this) {//쓰레드 동기화
            initRun();
        }
        //initRun();//쓰레드 세이프하지 않게 작동함
    }

    private void initRun() {
        this.increment();
        System.out.println("value for Thread After increment = " + Thread.currentThread().getName() + " " + this.getValue());
        this.decrement();
        System.out.println("value for Thread last = " + Thread.currentThread().getName() + " " + this.getValue());
    }

    public void increment() {
        this.count++;
    }

    public int getValue() {
        return this.count;
    }

    public void decrement() {
        this.count--;
    }
}
