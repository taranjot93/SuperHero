package com.superhero.restrepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.superhero.domain.SuperHero;

@RepositoryRestResource
public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {

}
