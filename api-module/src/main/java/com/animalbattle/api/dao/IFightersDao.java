package com.animalbattle.api.dao;

import com.animalbattle.api.exceptions.NullParticipantsException;
import com.animalbattle.entities.Animal;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IFightersDao {

    void addFighters() throws SQLException, NullParticipantsException;

    List<Animal> getFighters();

    void saveResults(Map<Animal, Integer> championshipTable) throws SQLException;
}
