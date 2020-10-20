package com.battle.services;

import com.animalbatlle.utils.InputOutput;
import com.animalbattle.api.exceptions.NullParticipantsException;
import com.animalbattle.api.services.IFightersService;
import com.animalbattle.dao.FightersDao;
import com.animalbattle.entities.Animal;

import java.io.IOException;
import java.util.List;

public class FightersService implements IFightersService {

    private final FightersDao fightersDao = new FightersDao();

    @Override
    public void addFighters() throws IOException, NullParticipantsException {

        List<Animal> fightersReadFromFile = InputOutput.readFile();

        if (!fightersReadFromFile.isEmpty()) {
            fightersDao.addFighters(fightersReadFromFile);
        } else {
            throw new NullParticipantsException();
        }
    }

    @Override
    public List<Animal> getFighters() {

        return fightersDao.getFighters();
    }
}
