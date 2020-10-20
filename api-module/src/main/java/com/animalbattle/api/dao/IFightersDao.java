package com.animalbattle.api.dao;

import com.animalbattle.entities.Animal;

import java.util.List;

public interface IFightersDao {

    void addFighters(List<Animal> fightersReadFromFile);

    List<Animal> getFighters();
}
