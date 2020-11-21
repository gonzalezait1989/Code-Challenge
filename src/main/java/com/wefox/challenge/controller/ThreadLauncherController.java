package com.wefox.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wefox.challenge.vo.AccountVO;
import com.wefox.challenge.vo.ThreadInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/thread")
@Slf4j
@RequiredArgsConstructor
public class ThreadLauncherController {
	
	@Autowired
	private ThreadService threadService;
		
	@GetMapping
    public ResponseEntity<List<ThreadInfoVO>> runJob() {
        return ResponseEntity.ok(threadService.runJob());
    }
	
}
