package com.aitorgonzalez.challenge.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aitorgonzalez.challenge.exceptions.ResourceNotFoundException;
import com.aitorgonzalez.challenge.service.ToyService;
import com.aitorgonzalez.challenge.vo.ToyVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/toys")
@Slf4j
@RequiredArgsConstructor
public class ToyApi {

	@Autowired
	private ToyService toyService;

	@GetMapping("/{id}")
	public ResponseEntity<ToyVO> findById(@PathVariable Long id) {
		return toyService.findById(id).map(toy -> ResponseEntity.ok(toy)).orElseThrow(ResourceNotFoundException::new);
	}
}
