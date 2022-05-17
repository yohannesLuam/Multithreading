public class MyThread implements Runnable {
    Thread thread;

    MyThread(String name){
        thread = new Thread(this, name);
    }

    public static MyThread createAndStart(String name){
        MyThread myThread = new MyThread(name);
        myThread.thread.start();
        return myThread;
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + " starts to operate");
        try{
            for (int count = 0; count < 10; count++){
                Thread.sleep(100);
                System.out.println(thread.getName() + " is executed, the counter value: " + count);
            }
        } catch (InterruptedException e) {
            System.out.println(thread.getName() + " has been interrupted");
        }
        System.out.println(thread.getName() + " finishes running");
    }
}

class MoreThreads{
    public static void main(String[] args) {
        System.out.println("The main thread starts running");

        MyThread mythread1 = MyThread.createAndStart("Child thread #1");
        MyThread mythread2 = MyThread.createAndStart("Child thread #2");
        MyThread mythread3 = MyThread.createAndStart("Child thread #3");

        System.out.print(".");
        try {
            mythread1.thread.join();
            mythread2.thread.join();
            mythread3.thread.join();
        } catch (InterruptedException e) {
            System.out.println("The main thread has been terminated");
        }
        System.out.println("The main thread is exiting");
        }
    }
