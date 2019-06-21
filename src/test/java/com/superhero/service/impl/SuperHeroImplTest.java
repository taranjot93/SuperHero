package com.superhero.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.superhero.domain.Mission;
import com.superhero.domain.SuperHero;
import com.superhero.dto.SuperHeroResponseDTO;
import com.superhero.restrepositories.SuperHeroRepository;

@RunWith(MockitoJUnitRunner.class)
public class SuperHeroImplTest {

  @InjectMocks
  private SuperHeroServiceImpl superHeroServiceImpl;

  @Mock
  private SuperHeroRepository superHeroRepository;

  private SuperHero superHero;

  private List<Mission> missions;

  @Before
  public void setup() {
    missions = new ArrayList<>();
    missions.add(Mission.builder()
      .id(1)
      .isCompleted(true)
      .build());
    missions.add(Mission.builder()
      .id(2)
      .isCompleted(false)
      .build());
    missions.add(Mission.builder()
      .id(3)
      .isCompleted(true)
      .build());

    superHero = SuperHero.builder()
      .firstname("Hero")
      .missions(missions)
      .build();

  }

  @Test
  public void testGetSuperHero() {
    when(superHeroRepository.findById(1L)).thenReturn(Optional.of(superHero));

    final SuperHeroResponseDTO superHeroResponseDTO = superHeroServiceImpl.getSuperHero(1L);

    Assert.assertEquals(superHeroResponseDTO.getFirstname(), superHero.getFirstname());

    Assert.assertEquals(1, superHeroResponseDTO.getActiveMissions()
      .size());
    Assert.assertEquals(2, superHeroResponseDTO.getCompletedMissions()
      .size());

  }

  @Test
  public void testCreateSuperHero() {
    superHeroServiceImpl.createHero(superHero);
    verify(superHeroRepository, Mockito.times(1)).save(superHero);
  }

  @Test
  public void testUpdateSuperHero() {
    when(superHeroRepository.findById(1L)).thenReturn(Optional.of(superHero));
    final SuperHero heroToUpdate = SuperHero.builder()
      .id(1L)
      .firstname("SuperHero")
      .build();
    when(superHeroRepository.save(heroToUpdate)).thenReturn(heroToUpdate);
    final SuperHero updatedHero = superHeroServiceImpl.updateHero(heroToUpdate);

    verify(superHeroRepository, Mockito.times(1)).save(updatedHero);
    Assert.assertEquals(updatedHero.getFirstname(), heroToUpdate.getFirstname());

  }

  @Test
  public void testDeleteSuperHero() {
    superHeroServiceImpl.deleteHero(1L);
    verify(superHeroRepository, Mockito.times(1)).deleteById(1L);
  }

  @Test(
    expected = ResourceNotFoundException.class)
  public void testUpdateNonExistingHero() {
    final SuperHero heroToUpdate = SuperHero.builder()
      .id(1L)
      .firstname("SuperHero")
      .build();
    superHeroServiceImpl.updateHero(heroToUpdate);
  }

}
