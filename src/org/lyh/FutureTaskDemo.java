package org.lyh;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author lyh
 * @version 2019-10-09 15:44
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        FutureTask<Integer> futureTask = new FutureTask<>(new Job());

        /**
         * 同步方式
         */
        //futureTask.run();

        /**
         * 异步方式
         */
        es.submit(futureTask);
        System.out.println("主线程可以做自己的事情");
        System.out.println(futureTask.get());
        es.shutdown();
    }
}
