package com.wefox.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneId;
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
		t.setId(1l);
		t.setName("Pokemon Toy");
		t.setCreated(OffsetDateTime.now(ZoneId.systemDefault()).toLocalDateTime());
		t.setUpdated(OffsetDateTime.now(ZoneId.systemDefault()).toLocalDateTime());
		
		Mockito.when(toyRespository.findById(1l)).thenReturn(Optional.of(t));
		Mockito.when(toyRespository.findById(2l)).thenReturn(Optional.empty());
		
		Optional<ToyVO> tOptional = this.toyService.findById(1l);
		Optional<ToyVO> tOptional2 = this.toyService.findById(2l);
		
		assertThat(tOptional).isNotEmpty();
		assertThat(tOptional.get()).isNotNull();
		assertThat(tOptional.get().getId()).isEqualTo(t.getId());
		assertThat(tOptional.get().getName()).isEqualTo(t.getName());
		assertThat(tOptional.get().getCreated()).isEqualTo(t.getCreated());
		assertThat(tOptional.get().getUpdated()).isEqualTo(t.getUpdated());
		assertThat(tOptional2).isEmpty();
	}

}
