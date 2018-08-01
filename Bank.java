package thread;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Zhang
 * @date 2018/8/1
 * @Description    银行类，包含锁和条件变量
 */
public class Bank {
    private final double[] accounts;

    private Lock bankLock;                       //可重入锁

    private Condition sufficientFunds;                                 //条件变量

    public Bank(int n, double initialBalance){
        accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount){
        bankLock.lock();
        try {
            while (accounts[from] < amount)
                sufficientFunds.await();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total Balance: %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        }catch (InterruptedException e){}
        finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance(){
        double sum = 0;

        for (double a : accounts)
            sum += a;

        return sum;
    }

    public  int size(){
        return accounts.length;
    }
}
