package com.animalbattle.api.services;

import com.animalbattle.api.exceptions.NullParticipantsException;
import com.animalbattle.entities.Animal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IFightersService {

    void addFighters() throws SQLException, NullParticipantsException;

    List<Animal> getFighters();
}
