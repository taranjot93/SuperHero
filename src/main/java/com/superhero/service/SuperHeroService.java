package com.superhero.service;

import com.superhero.domain.SuperHero;
import com.superhero.dto.SuperHeroResponseDTO;

public interface SuperHeroService {

  public SuperHero createHero(SuperHero hero);

  public void deleteHero(final long superHeroId);

  public SuperHero updateHero(SuperHero hero);

  public SuperHeroResponseDTO getSuperHero(final long superHeroId);
}
