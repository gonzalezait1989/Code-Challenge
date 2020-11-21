package com.wefox.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wefox.challenge.model.Toy;
import com.wefox.challenge.repository.ToyRepository;
import com.wefox.challenge.vo.ToyVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToyService {

	@Autowired
    private  ToyRepository toyRespository;
	
	public Optional<ToyVO> findById(Long id) {
		return toyRespository.findById(id).map(t -> getToyVO(t));
	}
	
	/**
     * Transform Toy to ToyVO
     * @param toy
     * @return
     */
    private ToyVO getToyVO(Toy toy) {
        return ToyVO.builder()
                .created(toy.getCreated())
                .updated(toy.getUpdated())
                .id(toy.getId())
                .name(toy.getName())
                .build();
    }

    /**
     * Transform ToyVO to Toy
     * @param toyVO
     * @return
     */
    private Toy getToy(ToyVO toyVO) {
        return Toy.builder()
                .created(toyVO.getCreated())
                .updated(toyVO.getUpdated())
                .id(toyVO.getId())
                .name(toyVO.getName())
                .build();
    }

	
	
}
