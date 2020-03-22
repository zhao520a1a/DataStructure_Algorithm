package training.threads.threadpool;

import java.util.concurrent.*;

/**
 * @Description
 * @author: Golden
 * @date: 2020/3/13
 */

public class ThreadUtils {

    public static ThreadPoolExecutor getThreadPool() {
        return getThreadPool(3, "Thread-Worker");
    }

    public static ThreadPoolExecutor getThreadPool(String threadNamePrefix) {
        return getThreadPool(3, threadNamePrefix);
    }


    public static ThreadPoolExecutor getThreadPool(int poolSize, String threadNamePrefix) {

        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(3);
        Executors.newCachedThreadPool();

        return new ThreadPoolExecutor(poolSize, poolSize, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<>(30), new ThreadRenameFactory(threadNamePrefix));
    }


}
