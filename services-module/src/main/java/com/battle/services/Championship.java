package com.battle.services;

import com.animalbattle.api.exceptions.NullParticipantsException;
import com.animalbattle.api.services.IChampionship;
import com.animalbattle.api.services.ICombat;
import com.animalbattle.api.services.IFightersService;
import com.animalbattle.entities.Animal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Championship implements IChampionship {

    private int numberOfCombats;
    private final IFightersService fightersService = new FightersService();
    private final ICombat combat = new Combat();
    private Map<Animal, Integer> championshipTable = null;
    private List<Animal> fightersReady;

    @Override
    public boolean start() {

        boolean isChampionshipStarted;

        try {
            fightersService.addFighters();
            isChampionshipStarted = true;
            championshipTable = fightersService.getFighters().stream().collect(Collectors.toMap(Function.identity(), x -> 0));
            fightersReady = new ArrayList<>(fightersService.getFighters());

        } catch (SQLException | NullParticipantsException exception) {
            isChampionshipStarted = false;
        }
        return isChampionshipStarted;
    }

    @Override
    public void startCombats() {

        for (int i = 0; i < fightersReady.size() - 1; i++) {
            for (int j = i + 1; j < fightersReady.size(); j++) {
                numberOfCombats++;
                System.out.printf("COMBAT %d%n", numberOfCombats);
                Animal winner = combat.startCombat(fightersReady.get(i), fightersReady.get(j));
                championshipTable.put(winner, championshipTable.get(winner) + 1);
            }
        }
    }

    @Override
    public boolean checkWinner() {

        int maxNumberOfVictories = Collections.max(championshipTable.values());

        fightersReady = championshipTable.entrySet()
                .stream()
                .filter(x -> x.getValue() == maxNumberOfVictories)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (fightersReady.size() == 1) {
            System.out.printf("The winner of the championship is %s%n", fightersReady.get(0).getFullName());
            return true;
        } else if (maxNumberOfVictories == 0) {
            System.out.println("The championship was cancelled");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printResults() {

        System.out.println("Result Table:");

        championshipTable.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(x -> System.out.printf("%s -  %s victories %n", x.getKey().getFullName(), x.getValue()));
    }

    @Override
    public void saveResultsToFile() {
        fightersService.saveResults(championshipTable);
    }
}
