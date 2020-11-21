package com.wefox.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wefox.challenge.callables.ThreadInfoCallable;
import com.wefox.challenge.model.ThreadInfo;
import com.wefox.challenge.vo.ThreadInfoVO;

@Service
public class ThreadService {

	public List<ThreadInfoVO> runThreads() {
		//Launches 3 concurrent threads 
		List<ThreadInfoVO> threadInfoVOs = new ArrayList<ThreadInfoVO>();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		List<Future<ThreadInfo>> futures = new ArrayList<Future<ThreadInfo>>();
		Callable<ThreadInfo> callable = new ThreadInfoCallable();
		for (int i = 0; i < 3; i++) {
			Future<ThreadInfo> future = executor.submit(callable);
			futures.add(future);
		}
		//Recovers it's status
		threadInfoVOs.addAll(futures.parallelStream().map(future -> {
			try {
				return this.getThreadInfoVO(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			}
		}).filter(threadInfo -> threadInfo != null).collect(Collectors.toList()));

		//Launches one thread
		executor = Executors.newFixedThreadPool(1);
		futures = new ArrayList<Future<ThreadInfo>>();
		Future<ThreadInfo> future = executor.submit(callable);

		//Recovers it status
		try {
			threadInfoVOs.add(this.getThreadInfoVO(future.get()));
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		
		return threadInfoVOs;
	}

	private ThreadInfoVO getThreadInfoVO(ThreadInfo threadInfo) {
		return ThreadInfoVO.builder()
				.stepNo(threadInfo.getStepNo())
				.stepName(threadInfo.getStepName())
				.start(threadInfo.getStart())
				.finish(threadInfo.getFinish())
				.build();
	}
}
