package com.superhero.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.superhero.domain.SuperHero;
import com.superhero.dto.SuperHeroResponseDTO;
import com.superhero.restrepositories.SuperHeroRepository;
import com.superhero.service.SuperHeroService;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

  @Autowired
  private SuperHeroRepository superHeroRepo;

  @Override
  public SuperHero createHero(final SuperHero hero) {
    return superHeroRepo.save(hero);
  }

  @Override
  public void deleteHero(final long superHeroId) {

    superHeroRepo.deleteById(superHeroId);
  }

  @Override
  public SuperHero updateHero(final SuperHero heroToUpdate) {
    final Optional<SuperHero> hero = superHeroRepo.findById(heroToUpdate.getId());

    if (hero.isPresent()) {
      BeanUtils.copyProperties(heroToUpdate, hero.get());
      return superHeroRepo.save(hero.get());
    }
    else {
      throw new ResourceNotFoundException("Hero not found for this id :: " + heroToUpdate.getId());
    }

  }

  @Override
  public SuperHeroResponseDTO getSuperHero(final long superHeroId) {

    final SuperHero superHero = superHeroRepo.findById(superHeroId)
      .orElseThrow(() -> new ResourceNotFoundException("Hero not found for this id :: " + superHeroId));
    final SuperHeroResponseDTO superHeroDTO = SuperHeroResponseDTO.builder()
      .build();
    BeanUtils.copyProperties(superHero, superHeroDTO);
    if (Objects.nonNull(superHero.getMissions())) {
      for (int i = 0; i < superHero.getMissions()
        .size(); i++) {
        if (superHero.getMissions()
          .get(i)
          .getIsCompleted()) {
          superHeroDTO.getCompletedMissions()
            .add(superHero.getMissions()
              .get(i));
        }
        else {
          superHeroDTO.getActiveMissions()
            .add(superHero.getMissions()
              .get(i));
        }
      }
    }
    return superHeroDTO;
  }

}
