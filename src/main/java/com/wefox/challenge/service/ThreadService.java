package com.wefox.challenge.service;

import java.util.List;

import com.wefox.challenge.vo.ThreadInfoVO;

/**
 * Service for launching Threads.
 * 
 * @author aitor
 */
public interface ThreadService {

  /**
   * Runs a fixed number of threads. Three asynchronous threads and when those
   * three have finished, throws another one.
   * 
   * @return a List of ThreadInfoVO with the execution information.
   */
  public List<ThreadInfoVO> runThreads();
}
