package com.aitorgonzalez.challenge.exceptions;

public class PokemonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5387762457062300538L;

	public PokemonNotFoundException(String exception) {
		super(exception);
	}

}
