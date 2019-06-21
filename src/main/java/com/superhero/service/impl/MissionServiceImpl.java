package com.superhero.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.superhero.domain.Mission;
import com.superhero.restrepositories.MissionRepository;
import com.superhero.service.MissionService;

@Service
public class MissionServiceImpl implements MissionService {

  @Autowired
  private MissionRepository missionRepo;

  @Override
  public Mission createMission(final Mission mission) {
    return missionRepo.save(mission);
  }

  @Override
  public void deleteMission(final long missionId) {
    missionRepo.deleteById(missionId);
  }

  @Override
  public Mission updateMission(final Mission mission) {
    return missionRepo.save(mission);
  }

  @Override
  public Mission getMission(final long missionId) {
    return missionRepo.findById(missionId)
      .orElseThrow(() -> new ResourceNotFoundException("Mission not found for this id :: " + missionId));
  }

}
