package com.imlongluo.threads;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 */
class FileFlow {
    private ArrayList<String> list = new ArrayList<String>();
    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(4);
    private String[] filenames = { "A", "B", "C", "D" };

    public FileFlow(int nums) {
        for (int i = 0; i < nums && i < filenames.length; i++) {
            list.add(filenames[i]);
        }

        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int i;
                    for (i = 0; i < list.size(); i++) {
                        try {
                            queue.put(list.get(i));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    list.add(0, list.remove(i - 1));
                }
            }
        });
    }

    public String getFileInFlow() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int length() {
        return list.size();
    }
}
