package com.battle.services;

import com.animalbattle.api.exceptions.NullParticipantsException;
import com.animalbattle.api.services.IFightersService;
import com.animalbattle.dao.FightersDao;
import com.animalbattle.entities.Animal;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class FightersService implements IFightersService {

    private final FightersDao fightersDao = new FightersDao();

    @Override
    public void addFighters() throws SQLException, NullParticipantsException {
        fightersDao.addFighters();
    }

    @Override
    public List<Animal> getFighters() {
        return fightersDao.getFighters();
    }

    @Override
    public void saveResults(Map<Animal, Integer> championshipTable) {
        try {
            fightersDao.saveResults(championshipTable);
            System.out.println("Results are saved to the database");
        } catch (SQLException throwables) {
            System.out.println("Couldn't save results of the championship");
        }
    }
}
