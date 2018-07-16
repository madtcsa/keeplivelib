package com.xdandroid.hellodaemon.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kkxx
 * @date 2018/6/21
 */
public class ThreadPool {

  private ExecutorService executors;

  private static class ThreadPoolHolder {
    static ThreadPool threadPool = new ThreadPool();
  }

  public static ThreadPool getInstance() {
    return ThreadPoolHolder.threadPool;
  }

  private ThreadPool() {
    ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("note-pool-%d").build();
    this.executors =
        new ThreadPoolExecutor(
            2,
            5,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(20),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy());
  }

  public void submitTask(Runnable task) {
    this.executors.submit(task);
  }
}
