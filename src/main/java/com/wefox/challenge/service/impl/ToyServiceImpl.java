package com.wefox.challenge.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wefox.challenge.model.Toy;
import com.wefox.challenge.repository.ToyRepository;
import com.wefox.challenge.service.ToyService;
import com.wefox.challenge.vo.ToyVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToyServiceImpl implements ToyService {

  @Autowired
  private ToyRepository toyRespository;

  @Override
  public Optional<ToyVO> findById(Long id) {
    return toyRespository.findById(id).map(t -> getToyVO(t));
  }

  /**
   * Transform Toy to ToyVO
   * 
   * @param toy
   * @return
   */
  private ToyVO getToyVO(Toy toy) {
    return toy != null
        ? ToyVO.builder().created(toy.getCreated()).updated(toy.getUpdated()).id(toy.getId())
            .name(toy.getName()).build()
        : ToyVO.builder().build();
  }

}
