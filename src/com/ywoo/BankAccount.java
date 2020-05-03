package com.ywoo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {

    private double balance;
    private String accountNumber;

    private Lock lock = new ReentrantLock();

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
//        this.lock = new ReentrantLock();
    }

    // Using synchronized function
//    public synchronized void deposit(double amount) {
//        balance += amount;
//    }
//
//    public synchronized void withdraw(double amount) {
//        balance -= amount;
//    }

    // Using synchronized object(this)
    public void deposit(double amount) {

        boolean status = false;

        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance += amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }
        } catch(InterruptedException e) {}

        System.out.println("Transactions status = " + status);
    }

    public void withdraw(double amount) {

        boolean status = false;
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }
        } catch(InterruptedException e) {}

        System.out.println("Transactions status = " + status);
    }

    // No need to add synchronized keyword here
    public String getAccountNumber() {
        return accountNumber;
    }

    // No need to add synchronized keyword here
    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber);
    }
}