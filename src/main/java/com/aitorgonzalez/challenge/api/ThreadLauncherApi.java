package com.aitorgonzalez.challenge.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aitorgonzalez.challenge.service.ThreadService;
import com.aitorgonzalez.challenge.vo.ThreadInfoVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/thread")
@RequiredArgsConstructor
public class ThreadLauncherApi {

	@Autowired
	private ThreadService threadService;

	@GetMapping("/{timeWait}")
	public ResponseEntity<List<ThreadInfoVO>> runThreads(@PathVariable Long timeWait) {
		return ResponseEntity.ok(threadService.runThreads(timeWait));
	}

}
