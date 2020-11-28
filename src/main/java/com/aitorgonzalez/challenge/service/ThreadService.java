package com.aitorgonzalez.challenge.service;

import java.util.List;

import com.aitorgonzalez.challenge.model.ThreadInfo;
import com.aitorgonzalez.challenge.vo.ThreadInfoVO;

/**
 * Service for launching Threads.
 * 
 * @author aitor
 */
public interface ThreadService {

	default ThreadInfoVO getThreadInfoVO(ThreadInfo threadInfo) {
		return threadInfo != null
				? ThreadInfoVO.builder().threadNo(threadInfo.getThreadNo()).stepName(threadInfo.getStepName())
						.start(threadInfo.getStart()).finish(threadInfo.getFinish()).build()
				: ThreadInfoVO.builder().build();
	}

	/**
	 * Runs a fixed number of threads. Three asynchronous threads and when those
	 * three have finished, throws another one.
	 * 
	 * @return a List of ThreadInfoVO with the execution information.
	 */
	public List<ThreadInfoVO> runThreads();
}
