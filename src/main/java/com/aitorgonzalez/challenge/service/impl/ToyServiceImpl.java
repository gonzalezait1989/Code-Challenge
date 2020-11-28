package com.aitorgonzalez.challenge.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aitorgonzalez.challenge.repository.ToyRepository;
import com.aitorgonzalez.challenge.service.ToyService;
import com.aitorgonzalez.challenge.vo.ToyVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToyServiceImpl implements ToyService {

	@Autowired
	private ToyRepository toyRespository;

	@Override
	public Optional<ToyVO> findById(Long id) {
		return toyRespository.findById(id).map(this::getToyVO);
	}
}
