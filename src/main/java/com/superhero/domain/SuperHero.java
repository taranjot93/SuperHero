package com.superhero.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class SuperHero {

  @Id
  @GeneratedValue(
    strategy = GenerationType.IDENTITY)
  @Column(
    name = "Id")
  private long id;

  private String firstname;

  private String lastname;

  private String superheroname;

  @ManyToMany
  private List<Mission> missions;

}
