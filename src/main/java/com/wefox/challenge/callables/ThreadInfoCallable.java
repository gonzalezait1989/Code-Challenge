package com.wefox.challenge.callables;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import com.wefox.challenge.model.ThreadInfo;

public class ThreadInfoCallable implements Callable<ThreadInfo> {

	private static final AtomicLong ai1 = new AtomicLong();
	
	@Override
	public ThreadInfo call() throws Exception {
		Long stepNo = ai1.incrementAndGet();
		ThreadInfo threadInfo = new ThreadInfo();
		threadInfo.setStepNo(stepNo);
		threadInfo.setStepName("Callable ".concat(stepNo.toString()));
		threadInfo.setStart(OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime());
		Thread.sleep(5000);
		threadInfo.setFinish(OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime());
		return threadInfo;
	}

}
