package com.wefox.challenge.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wefox.challenge.service.ThreadService;
import com.wefox.challenge.vo.ThreadInfoVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/thread")
@RequiredArgsConstructor
public class ThreadLauncherAPI {
	
	@Autowired
	private ThreadService threadService;
		
	@GetMapping
    public ResponseEntity<List<ThreadInfoVO>> runThreads() {
        return ResponseEntity.ok(threadService.runThreads());
    }
	
}
