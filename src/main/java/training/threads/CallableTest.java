package training.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @DescriptionB
 * @author: Golden
 * @date: 2020/3/14
 */

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new MyCallable()); //适配模式
        //两个线程执行同一任务，为了提高效率，会将结果缓存，导致任务只会执行一次；
        new Thread(task,"Thread-A").start();
        new Thread(task,"Thread-B").start();
        //业务代码 填充中。。。。
        System.out.println("要将获取返回值放到最后，因为它会阻塞等待结果： " + task.get());
    }

}


class MyCallable implements Callable {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallable -- call method--");
        return "hello world";
    }
}
