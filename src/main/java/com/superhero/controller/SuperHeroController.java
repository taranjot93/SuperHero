package com.superhero.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.superhero.domain.SuperHero;
import com.superhero.dto.SuperHeroResponseDTO;
import com.superhero.service.impl.SuperHeroServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Product controller.
 */
@RestController
@Api(
  value = "Product Operations",
  tags = "Product Operations",
  produces = "application/json")
@AllArgsConstructor(
  onConstructor = @__(@Autowired))
@Slf4j
@Validated
@RequestMapping("/api/v1")
public class SuperHeroController {

  private final SuperHeroServiceImpl superHeroServiceImpl;

  @ApiOperation("Create new SuperHero")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(
    path = "/add",
    produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SuperHero> addSuperHero(@Valid @RequestBody final SuperHero superHero) throws Exception {
    log.info("Recieved SuperHero info for {}", superHero.getFirstname());
    try {
      final SuperHero newHero = superHeroServiceImpl.createHero(superHero);
      return ResponseEntity.accepted()
        .body(newHero);
    }
    catch (final Exception e) {
      log.error("Could not create Hero.", e);
      throw new Exception("Could not create hero.", e);
    }
  }

  @GetMapping("/superHero/{id}")
  public ResponseEntity<SuperHeroResponseDTO> getEmployeeById(@PathVariable(
    value = "id") final Long superHeroId) throws ResourceNotFoundException {
    final SuperHeroResponseDTO superHero = superHeroServiceImpl.getSuperHero(superHeroId);
    return ResponseEntity.ok()
      .body(superHero);
  }

}
