package com.superhero.dto;

import java.util.ArrayList;
import java.util.List;

import com.superhero.domain.Mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(
  toBuilder = true)
public class SuperHeroResponseDTO {

  private long statusCode;

  private String firstname;

  private String lastname;

  private String superheroname;

  @Builder.Default
  private List<Mission> completedMissions = new ArrayList();

  @Builder.Default
  private List<Mission> activeMissions = new ArrayList();

}
