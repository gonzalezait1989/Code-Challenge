package com.wefox.challenge.vo;

import lombok.Builder;
import lombok.Data;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;

@Data
@Builder
public class PokemonVO {
	
	private Pokemon pokemon;

}
