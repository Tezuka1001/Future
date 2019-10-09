package org.lyh;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lyh
 * @version 2019-10-09 15:44
 */
public class CompletableFutureDemo {

    private static ExecutorService es = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 最基本的Future功能演示
         */
        System.out.println(CompletableFuture.supplyAsync(() -> "mysql IO", es)
                .get());
        System.out.println("--------------------------------------------------------------------------");

        /**
         * combine演示
         */
        CompletableFuture.supplyAsync( () -> 123, es)
                .thenCombine(CompletableFuture.supplyAsync( () -> 456, es), (x, y) -> x + " " + y)
                .whenComplete((s, v) -> System.out.println("合并结果: " + s));
        System.out.println("--------------------------------------------------------------------------");

        /**
         * 创建CompletableFuture对象计算，转化结果，计算完成后进行消费，并返回结果（或者进行纯消费）演示
         */
        List<Integer> taskList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        CompletableFuture[] cfs = taskList.stream() // stream
                .map(o -> CompletableFuture.supplyAsync(() -> cal(o), es) // 创建CompletableFuture对象
                        .thenApply(h -> Integer.toString(h)) // 结果转化
                        .whenComplete((v, e) -> System.out.println("任务" + v + "完成!result=" + v + "，异常 e=" + e + "," + new Date())) // 计算完成时消费
                )
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs).join(); // 等待所有的CompletableFuture执行完成，然后返回结果
    }
    private static Integer cal(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println("task线程：" + Thread.currentThread().getName() + "任务i=" + i + ",完成！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}
