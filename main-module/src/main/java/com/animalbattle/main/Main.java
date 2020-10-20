package com.animalbattle.main;

import com.animalbattle.api.services.IChampionship;
import com.battle.services.Championship;

public class Main {

    public static void main(String[] args) {

        IChampionship championship = new Championship();

        if (championship.start()) {

            System.out.println("Championship started");

            do {

                championship.startCombats();

                championship.printResults();

            } while (!championship.checkWinner());

            championship.SaveResultsToFile();

        } else {

            System.out.println("None fighters were found. Championship didn't start");
        }
    }
}
