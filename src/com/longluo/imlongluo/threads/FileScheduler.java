package com.imlongluo.threads;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �ļ�����Ļ�ȡ��д��
 */
class FileScheduler {
    FileFlow flow;
    Lock lock;
    Condition[] condition;
    boolean[] turn;// ˳��
    ConcurrentMap isVisiting;// �ļ��Ƿ�ռ��
    ConcurrentMap lockObjMap;// �ſ�����
    ConcurrentMap outputMap;

    public FileScheduler(FileFlow flow, int threadNums) {
        this.flow = flow;
        this.lock = new ReentrantLock();
        this.turn = new boolean[threadNums];
        this.condition = new Condition[threadNums];
        this.isVisiting = new ConcurrentHashMap();
        this.lockObjMap = new ConcurrentHashMap();
        this.outputMap = new ConcurrentHashMap<String, StringBuffer>();

        for (int i = 0; i < threadNums; i++) {
            turn[i] = i == 0 ? true : false;
            condition[i] = this.lock.newCondition();
            lockObjMap.put(i, new Integer(i));
        }
    }

    /**
     * �������ȡ�ļ�
     * @param order
     * @return
     * @throws Exception
     */
    public String schedule(int order) throws Exception {
        String file;
        lock.lock();
        try {
            // �����ν���ķ�1�߳� ��ȴ� ��1�߳̿�ʼ��ȡ ��������һ�߳�
            while (!turn[order]) {
                condition[order].await();
            }

            file = flow.getFileInFlow();
            Thread.sleep(100);

            turn[order] = false;// ����ȴ�
            order = order == 3 ? 0 : order + 1;
            turn[order] = true;// ������һλ

            condition[order].signal();
        } finally {
            lock.unlock();
        }
        return file;
    }

    /**
     * д���ļ�
     * @param order
     * @param file
     * @param Content
     * @throws Exception
     */
    public void write(int order, String file, String Content) throws Exception {

        synchronized (FileScheduler.class) {
            if (!isVisiting.containsKey(file)) {
                isVisiting.put(file, false);
                outputMap.put(file, new StringBuffer(file + ":"));
            }
        }

        synchronized (FileScheduler.class) {
            while ((Boolean) isVisiting.get(file)) {
                Integer lockObj = (Integer) lockObjMap.get(order);// �ſ�
                synchronized (lockObj) {
                    System.out.println("�߳�" + (order + 1) + "�ȴ�" + file);
                    lockObj.wait();
                }
            }
        }

        // ����д��
        isVisiting.put(file, true);
        System.out.println("�߳�" + (order + 1) + "��ʼд�ļ�" + file + "..");
        Thread.sleep(new Random().nextInt(4) * 1000);
        System.out.println("�߳�" + (order + 1) + "д�ļ�" + file + "����..");
        StringBuffer sb = (StringBuffer) outputMap.get(file);
        System.out.println(sb.append(Content).toString());
        isVisiting.put(file, false);

        // �����ڵȴ��߳� ��ͨ��ſ�����
        Integer nextLockObj = (Integer) lockObjMap.get(order == 3 ? 0 : order + 1);
        synchronized (nextLockObj) {
            nextLockObj.notify();
        }
    }
}
