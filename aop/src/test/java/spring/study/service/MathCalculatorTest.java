package spring.study.service;


public class MathCalculatorTest {

    public static void main(String[] args) throws InterruptedException {
        test test = new test();
        Thread thread1 = new Thread(test);
        thread1.setName("a");
        thread1.start();
        Thread thread2 = new Thread(test);
        thread2.setName("b");
        thread1.join();
        thread2.start();
    }
}
class test implements Runnable{

    @Override
        public void run() {
        if ("a".equals(Thread.currentThread().getName())) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
        System.out.println("A"+Thread.currentThread().getName());
    }
}