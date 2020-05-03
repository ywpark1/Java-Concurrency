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

        /* NewBankAccount */
//        NewBankAccount account1 = new NewBankAccount("12345-678", 500.00);
//        NewBankAccount account2 = new NewBankAccount("98765-432", 1000.00);
//
//        new Thread(new Transfer(account1, account2, 10.00), "Transfer1").start();
//        new Thread(new Transfer(account2, account1, 55.88), "Transfer2").start();

        /* Tutor and Student */
        // DeadLock issue
        // Possible solution 1 : use synchronized blocks instead of entire methods
        // Possible solution 2 : instead of circular reference, use of parameter
        // Possible solution 3 : use ReentrantLock objects and tryLock() method with timeout()
//        Tutor tutor = new Tutor();
//        Student student = new Student(tutor);
//        tutor.setStudent(student);
//
//        Thread tutorThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                tutor.studyTime();
//            }
//        });
//
//        Thread studentThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                student.handInAssignment();
//            }
//        });
//
//        tutorThread.start();
//        studentThread.start();

        /* NewTutor and NewStudent */
        // DeadLock issue
        // check the place where the code calls wait() and see if that may cause a deadlock.
        // The wait() method results in a thread releasing a lock before it has exited the synchronized block,
        // and this premature release can lead to problems if we’re not careful
        // Possible solution : use ReentrantLock objects. The tutor thread release the student lock
        // before it calls wait(), and then try to reaquire it again when it wakes up.
        final NewTutor tutor = new NewTutor();
        final NewStudent student = new NewStudent(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }
}
