package training;

import lombok.AllArgsConstructor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 1. 继承RecursiveTask类
 * 2. 适用ForkJoinPool中的invoke()方法执行任务
 * <p>
 * fork() - 创建子任务
 * invoke() - 执行任务
 * join() - 返回结果
 *
 * @Description
 * @author: Golden
 */

@AllArgsConstructor
public class ForkJoinDemo extends RecursiveTask<Long> {

    private long start;
    private long end;
    //临界值
    private static final long temp = 10_0000;


    // 超过临界值，就分任务计算
    @Override
    public Long compute() {
        if ((end - start) <= temp) {
            Long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (end + start) / 2;
            ForkJoinDemo forkJoinDemo1 = new ForkJoinDemo(start, middle);
            ForkJoinDemo forkJoinDemo2 = new ForkJoinDemo(middle + 1, end);
            forkJoinDemo1.fork();
            forkJoinDemo2.fork();
//            invokeAll(forkJoinDemo1, forkJoinDemo2);
            return forkJoinDemo1.join() + forkJoinDemo2.join();
        }

    }


    public static Long test2(long start, long end) {
        long t3 = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(start, end);
        long sum2 = forkJoinPool.invoke(forkJoinDemo);
        long t4 = System.currentTimeMillis();
        System.out.println("ForkJoin:" + (t4 - t3) + ":" + sum2);
        return sum2;
    }




    public static void main(String[] args) {
        long start = 0;
        long end = 10_0000_0000; //10亿

        //方法1 - 普通累计
        long t1 = System.currentTimeMillis();
        long sum1 = 0;
        for (long i = start; i <= end; i++) {
            sum1 += i;
        }
        long t2 = System.currentTimeMillis();
        System.out.println("普通累计:" + (t2 - t1) + ":" + sum1);

        //方法2 - 建立forkjoin任务
        test2(start,end);

        //方法3  - 流式并行计算 （推荐） parallelStream
        long t5 = System.currentTimeMillis();
//        long sum3 = LongStream.rangeClosed(start, end).sum();
        long sum3 = LongStream.rangeClosed(start, end).parallel().reduce(0, Long::sum);
        long t6 = System.currentTimeMillis();
        System.out.println("流式并行计算:" + (t6 - t5) + ":" + sum3);

    }


}
