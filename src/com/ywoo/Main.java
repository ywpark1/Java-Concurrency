package com.ywoo;

public class Main {

    public static void main(String[] args) {
        //final BankAccount account = new BankAccount("12345-678", 1000.00);

        /* Create and start the threads */
        // using Thread
//        Thread trThread1 = new Thread() {
//            @Override
//            public void run() {
//                account.deposit(300.00);
//                account.withdraw(50.00);
//            }
//        };
//
//        Thread trThread2 = new Thread() {
//            public void run() {
//                account.deposit(203.75);
//                account.withdraw(100.00);
//            }
//        };

        // using Runnable
        // Considerations :
        // 1.Both threads need to compete for the same lock, so we create only one lock object
        // that we use in all the methods that have critical sections of code
        // 2. We put the critical section of code within a try/finally block, to ensure
        // that the lock will be released
//        Thread trThread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                account.deposit(300.00);
//                account.withdraw(50.00);
//            }
//        });
//
//        Thread trThread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                account.deposit(203.75);
//                account.withdraw(100.00);
//            }
//        });
//
//        trThread1.start();
//        trThread2.start();

        /* Use NewBankAccount */
        NewBankAccount account1 = new NewBankAccount("12345-678", 500.00);
        NewBankAccount account2 = new NewBankAccount("98765-432", 1000.00);

        new Thread(new Transfer(account1, account2, 10.00), "Transfer1").start();
        new Thread(new Transfer(account2, account1, 55.88), "Transfer2").start();
    }
}
