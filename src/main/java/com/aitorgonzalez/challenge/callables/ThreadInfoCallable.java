package com.aitorgonzalez.challenge.callables;

import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import com.aitorgonzalez.challenge.model.ThreadInfo;

/**
 * A Callable that contains relevant information about the Thread.
 * 
 * @author aitor
 */
public class ThreadInfoCallable implements Callable<ThreadInfo> {

	private static final AtomicLong ai1 = new AtomicLong();

	private String stepName;

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
