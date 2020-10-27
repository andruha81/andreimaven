package com.animalbattle.api.dao;

import com.animalbattle.api.exceptions.NullParticipantsException;
import com.animalbattle.entities.Animal;

import java.sql.SQLException;
import java.util.List;

public interface IFightersDao {

    void addFighters() throws SQLException, NullParticipantsException;

    List<Animal> getFighters();
}
