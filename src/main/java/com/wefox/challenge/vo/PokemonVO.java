package com.wefox.challenge.vo;

import lombok.Builder;
import lombok.Data;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class PokemonVO {

  private Pokemon pokemon;

}
