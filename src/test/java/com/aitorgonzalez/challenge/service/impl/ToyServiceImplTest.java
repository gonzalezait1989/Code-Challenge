package com.aitorgonzalez.challenge.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.aitorgonzalez.challenge.model.Toy;
import com.aitorgonzalez.challenge.repository.ToyRepository;
import com.aitorgonzalez.challenge.vo.ToyVO;

@RunWith(PowerMockRunner.class)
public class ToyServiceImplTest {

	private Toy toy;

	@Mock
	private ToyRepository toyRespository;

	@InjectMocks
	private ToyServiceImpl toyService;

	@Before
	public void initializeData() {
		toy = new Toy();
		toy.setId(1L);
		toy.setName("Pokemon Toy");
		toy.setCreated(Calendar.getInstance().getTime());
		toy.setUpdated(Calendar.getInstance().getTime());
		Mockito.when(toyRespository.findById(1L)).thenReturn(Optional.of(toy));
		Mockito.when(toyRespository.findById(2L)).thenReturn(Optional.empty());
	}

	@Test
	public void testFindById() throws Exception {

		Optional<ToyVO> toyOptional = this.toyService.findById(1L);
		Optional<ToyVO> toyOptional2 = this.toyService.findById(2L);

		assertThat(toyOptional).isNotEmpty();
		assertThat(toyOptional.get()).isNotNull();
		assertThat(toyOptional.get().getId()).isEqualTo(toy.getId());
		assertThat(toyOptional.get().getName()).isEqualTo(toy.getName());
		assertThat(toyOptional.get().getCreated()).isEqualTo(toy.getCreated());
		assertThat(toyOptional.get().getUpdated()).isEqualTo(toy.getUpdated());
		assertThat(toyOptional2).isEmpty();
	}

}
