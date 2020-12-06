package com.aitorgonzalez.challenge.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aitorgonzalez.challenge.callables.ThreadInfoCallable;
import com.aitorgonzalez.challenge.model.ThreadInfo;
import com.aitorgonzalez.challenge.service.ThreadService;
import com.aitorgonzalez.challenge.vo.ThreadInfoVO;

@Service
public class ThreadServiceImpl implements ThreadService {

	@Override
	public List<ThreadInfoVO> runThreads(Long timeWait) {

		// Linkedlist in order to keep the order of the thread info as the threads
		// finish
		List<ThreadInfoVO> threadInfoVOs = new LinkedList<>();

		// Launches 3 concurrent threads
		ExecutorService executor = Executors.newFixedThreadPool(3);
		List<Future<ThreadInfo>> futures = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			Callable<ThreadInfo> callable = new ThreadInfoCallable("Step 1", timeWait);
			Future<ThreadInfo> future = executor.submit(callable);
			futures.add(future);
		}
		// Recovers it's status
		threadInfoVOs.addAll(futures.parallelStream().map(future -> {
			try {
				return this.getThreadInfoVO(future.get());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}).filter(Objects::nonNull).collect(Collectors.toList()));

		// Launches one thread
		executor = Executors.newFixedThreadPool(1);
		Callable<ThreadInfo> callable = new ThreadInfoCallable("Step 2", timeWait);

		Future<ThreadInfo> future = executor.submit(callable);

		// Recovers it status
		try {
			threadInfoVOs.add(this.getThreadInfoVO(future.get()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		executor.shutdown();

		return threadInfoVOs;
	}
}
