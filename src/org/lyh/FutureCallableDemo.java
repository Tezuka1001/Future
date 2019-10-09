package org.lyh;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author lyh
 * @version 2019-10-09 15:43
 */
public class FutureCallableDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = new Job();

        /**
         * 同步方式
         */
        //System.out.println(callable.call());

        /**
         * 异步方式
         */
        Future<Integer> future = es.submit(callable);
        System.out.println("主线程可以做自己的事情");
        System.out.println(future.get());
        es.shutdown();
    }
}
