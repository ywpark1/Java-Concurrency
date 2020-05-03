package com.ywoo;

public class NewTutor {
    private NewStudent student;

    public void setStudent(NewStudent student) {
        this.student = student;
    }

    public void studyTime() {

        synchronized (this) {
            System.out.println("Tutor has arrived");

            try {
                // wait for student to arrive
                System.out.println("Tutor is waiting for student");
                this.wait();
            } catch (InterruptedException e) {

            }

            synchronized (student) {

                student.startStudy();
                System.out.println("Tutor is studying with student");
            }
        }
    }

    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

