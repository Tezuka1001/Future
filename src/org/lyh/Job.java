package org.lyh;

import java.util.concurrent.Callable;

/**
 * @author lyh
 * @version 2019-10-09 15:44
 */
public class Job implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        System.out.println("这是子线程");
        return 1;
    }
}
