package training.threads.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @author: Golden
 */

public class ThreadRenameFactory implements ThreadFactory {

    private static final AtomicInteger poolNum = new AtomicInteger(1);
    private final AtomicInteger threadNum = new AtomicInteger(1);

    private final ThreadGroup threadGroup;
    private final String namePrefix;

    public ThreadRenameFactory(String threadNamePrefix) {
        SecurityManager s = System.getSecurityManager();
        this.threadGroup = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        poolNum.getAndIncrement();
        this.namePrefix = threadNamePrefix + "-pool-" + poolNum.getAndIncrement() + "-tid-";

    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, namePrefix + threadNum.getAndIncrement());
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
