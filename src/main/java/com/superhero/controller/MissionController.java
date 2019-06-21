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

import com.superhero.domain.Mission;
import com.superhero.service.impl.MissionServiceImpl;

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
@RequestMapping("/api/v2")
public class MissionController {

  private final MissionServiceImpl missionServiceImpl;

  @ApiOperation("Create new Mission")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(
    path = "/addMission",
    produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Mission> addSuperHero(@Valid @RequestBody final Mission mission) throws Exception {
    log.info("Recieved Mission info for {}", mission.getMissionName());
    try {
      final Mission newMission = missionServiceImpl.createMission(mission);
      return ResponseEntity.accepted()
        .body(newMission);
    }
    catch (final Exception e) {
      log.error("Could not create Mission.", e);
      throw new Exception("Could not create Mission.", e);
    }
  }

  @GetMapping("/Mission/{id}")
  public ResponseEntity<Mission> getMissionById(@PathVariable(
    value = "id") final Long missionId) throws ResourceNotFoundException {
    final Mission mission = missionServiceImpl.getMission(missionId);
    return ResponseEntity.ok()
      .body(mission);
  }

}
