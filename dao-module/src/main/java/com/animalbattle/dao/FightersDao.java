package com.animalbattle.dao;

import com.animalbatlle.utils.SqlConnection;
import com.animalbattle.api.dao.IFightersDao;
import com.animalbattle.api.exceptions.NullParticipantsException;
import com.animalbattle.entities.Animal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FightersDao implements IFightersDao {

    private final List<Animal> fighters = new ArrayList<>();

    @Override
    public void addFighters() throws NullParticipantsException, SQLException {
        fighters.addAll(SqlConnection.getFighters());
        if (fighters.isEmpty()) {
            throw new NullParticipantsException();
        }
    }

    @Override
    public List<Animal> getFighters() {
        return fighters;
    }

    @Override
    public void saveResults(Map<Animal, Integer> championshipTable) throws SQLException {
        SqlConnection.saveResults(championshipTable);
    }
}
