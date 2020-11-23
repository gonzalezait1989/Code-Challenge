package com.wefox.challenge.callables;

import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import com.wefox.challenge.model.ThreadInfo;

public class ThreadInfoCallable implements Callable<ThreadInfo> {

  private String stepName;

  private static final AtomicLong ai1 = new AtomicLong();

  public ThreadInfoCallable(String stepName) {
    this.stepName = stepName;
  }

  @Override
  public ThreadInfo call() throws Exception {
    Long threadNo = ai1.incrementAndGet();
    ThreadInfo threadInfo = new ThreadInfo();
    threadInfo.setThreadNo(threadNo);
    threadInfo.setStepName(stepName);
    threadInfo.setStart(Calendar.getInstance().getTime());
    Thread.sleep(5000);
    threadInfo.setFinish(Calendar.getInstance().getTime());
    return threadInfo;
  }

}
