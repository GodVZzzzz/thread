package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author Zhang
 * @date 2018/7/29
 * @Description
 */
public class ThreadTest {
    public static void main(String[] args) {
        /*第一种方式，实现Runnable接口*/
        Runnable r = () -> {
            while (true) {
                try {
                    System.out.print("*");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        };

        Thread t1 = new Thread(r);
        t1.start();

        /*第二种方法，创建Thread子类对象*/
        MyThread t2 = new MyThread();
        t2.start();

        /*第三种方法，使用Callable和Future创建线程*/
        Callable<Character> callable = () -> {

            System.out.print("&");
            return '$';

        };

        FutureTask<Character> futureTask = new FutureTask<>(callable);
        Thread t3=new Thread(futureTask);
        t3.start();
        try {
            System.out.print(futureTask.get());
        }catch (Exception e){}


    }
}


/*继承Thread类*/
class MyThread extends Thread{
    public void run(){
        while (true){
            try {
                System.out.print("=");
                Thread.sleep(1000);
            }catch (InterruptedException e){}
        }
    }
}