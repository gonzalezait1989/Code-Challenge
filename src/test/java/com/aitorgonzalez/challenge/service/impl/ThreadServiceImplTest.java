package com.aitorgonzalez.challenge.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.aitorgonzalez.challenge.vo.ThreadInfoVO;

@RunWith(MockitoJUnitRunner.class)
public class ThreadServiceImplTest {

	@Mock
	private ExecutorService executor;

	@InjectMocks
	private ThreadServiceImpl threadService;

	@Test
	public void testRunThreads() throws Exception {
		List<ThreadInfoVO> threadInfoList = this.threadService.runThreads();
		assertThat(threadInfoList).isNotNull().hasSize(4);
		assertThat(threadInfoList.parallelStream().filter(threadInfo -> threadInfo.getStepName().equals("Step 1")))
				.hasSize(3);
		assertThat(threadInfoList.parallelStream().filter(threadInfo -> threadInfo.getStepName().equals("Step 2")))
				.hasSize(1);

		List<ThreadInfoVO> threadInfoStep1 = threadInfoList.parallelStream()
				.filter(threadInfo -> threadInfo.getStepName().equals("Step 1")).collect(Collectors.toList());
		List<ThreadInfoVO> threadInfoStep2 = threadInfoList.parallelStream()
				.filter(threadInfo -> threadInfo.getStepName().equals("Step 2")).collect(Collectors.toList());

		assertTrue(threadInfoStep1.parallelStream()
				.allMatch(threadInfo -> threadInfo.getFinish().before(threadInfoStep2.get(0).getStart())));
	}

}
