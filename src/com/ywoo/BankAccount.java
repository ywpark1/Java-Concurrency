package com.ywoo;

class BankAccount {

    private double balance;
    private String accountNumber;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
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
        synchronized (this) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        synchronized (this) {
            balance -= amount;
        }
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