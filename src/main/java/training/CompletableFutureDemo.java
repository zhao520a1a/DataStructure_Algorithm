package training;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description  异步回调
 * @author: Golden
 * @date: 2020/3/22
 */

public class CompletableFutureDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //没有返回结果
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 没有返回值");
        });
        System.out.println(voidCompletableFuture.get());

        System.out.println("============");

        //有返回结果
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 有返回值");
//            int i = 100 / 0; //人为造异常
            return 1024;
        });
        Integer result = integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("异步任务执行完成:" + t + " 错误信息:" + u);
        }).exceptionally(e -> {
            System.out.println("有异常:" + e.getMessage());
            return 500;
        }).get();
        System.out.println(result);


    }

}
