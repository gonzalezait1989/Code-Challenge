package com.wefox.challenge.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wefox.challenge.service.ToyService;
import com.wefox.challenge.vo.ToyVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/toys")
@Slf4j
@RequiredArgsConstructor
public class ToyController {
	
	@Autowired
	private ToyService toyService;

	@GetMapping("/{id}")
    public ResponseEntity<ToyVO> findById(@PathVariable Long id) {
        Optional<ToyVO> toy = toyService.findById(id);
        if (!toy.isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(toy.get());
    }
}
