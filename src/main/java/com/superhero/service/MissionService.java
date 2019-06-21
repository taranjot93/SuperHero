package com.superhero.service;

import com.superhero.domain.Mission;

public interface MissionService {

  public Mission createMission(Mission mission);

  public void deleteMission(final long missionId);

  public Mission updateMission(Mission mission);

  public Mission getMission(final long missionId);
}
