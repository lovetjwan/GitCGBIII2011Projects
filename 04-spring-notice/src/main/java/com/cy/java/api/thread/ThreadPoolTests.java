package com.cy.java.api.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTests {

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 3;
        int keepAliveTime = 60;
        TimeUnit unit = TimeUnit.SECONDS;//指定时间单位
        //任务队列，存储要执行的任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1);
        //创建线程的工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            private String prefix = "do-service-thread-";
            private AtomicInteger atomicInteger = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable task) {
                return new Thread(task,prefix+atomicInteger.getAndIncrement());
            }
        };
        //构建线程池对象
        RejectedExecutionHandler handler =
//                new ThreadPoolExecutor.AbortPolicy();//不能执行任务时抛出异常
                new ThreadPoolExecutor.CallerRunsPolicy();//由调用者去执行
        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        unit,
                        workQueue,
                        threadFactory,
                        handler);

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();
                System.out.println(tName+"->execute->task1");
                try {
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();
                System.out.println(tName+"->execute->task2");
                try {
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();
                System.out.println(tName+"->execute->task3");
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();
                System.out.println(tName+"->execute->task4");
                try {
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();
                System.out.println(tName+"->execute->task5");
                try {
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
