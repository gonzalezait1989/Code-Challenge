package com.wefox.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.wefox.challenge.model.Toy;
import com.wefox.challenge.repository.ToyRepository;
import com.wefox.challenge.vo.ToyVO;

@RunWith(MockitoJUnitRunner.class)
public class ToyServiceTest {

  @InjectMocks
  private ToyService toyService;

  @Mock
  private ToyRepository toyRespository;

  @Test
  public void testFindById() throws Exception {
    Toy t = new Toy();
    t.setId(1L);
    t.setName("Pokemon Toy");
    t.setCreated(Calendar.getInstance().getTime());
    t.setUpdated(Calendar.getInstance().getTime());

    Mockito.when(toyRespository.findById(1L)).thenReturn(Optional.of(t));
    Mockito.when(toyRespository.findById(2L)).thenReturn(Optional.empty());

    Optional<ToyVO> toyOptional = this.toyService.findById(1L);
    Optional<ToyVO> toyOptional2 = this.toyService.findById(2L);

    assertThat(toyOptional).isNotEmpty();
    assertThat(toyOptional.get()).isNotNull();
    assertThat(toyOptional.get().getId()).isEqualTo(t.getId());
    assertThat(toyOptional.get().getName()).isEqualTo(t.getName());
    assertThat(toyOptional.get().getCreated()).isEqualTo(t.getCreated());
    assertThat(toyOptional.get().getUpdated()).isEqualTo(t.getUpdated());
    assertThat(toyOptional2).isEmpty();
  }

}
