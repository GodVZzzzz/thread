package thread;

import java.util.concurrent.*;

/**
 * @author Zhang
 * @date 2018/8/1
 * @Description  线程池
 */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
                return 1;
            }
        };
        for(int i = 0; i < 100 ; i++) {
            Future<Integer> result = pool.submit(callable);
        }
        pool.shutdown();
        System.out.println("最大数量为："+((ThreadPoolExecutor)pool).getLargestPoolSize());

    }
}
