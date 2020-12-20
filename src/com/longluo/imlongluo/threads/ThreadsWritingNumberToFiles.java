package com.imlongluo.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Date: 2014/07/07
 */
public class ThreadsWritingNumberToFiles {

    private static int order = 0;

    public static void main(String[] args) {
        final String[] Contents = new String[] { "1", "2", "3", "4" };
        int srcNums = 4;
        int threadNums = 4;
        FileFlow flow = new FileFlow(srcNums);
        final FileScheduler fileScheduler = new FileScheduler(flow, threadNums);

        ExecutorService es = Executors.newFixedThreadPool(threadNums);

        for (int i = 0; i < threadNums; i++) {
            es.execute(new Runnable() {

                @Override
                public void run() {
                    int o = order++;
                    try {
                        while (true) {
                            fileScheduler.write(o, fileScheduler.schedule(o), Contents[o]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        es.shutdown();
    }
}
