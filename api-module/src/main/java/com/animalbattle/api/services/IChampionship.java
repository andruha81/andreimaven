package com.animalbattle.api.services;

public interface IChampionship {

    boolean start();

    void startCombats();

    boolean checkWinner();

    void printResults();

    void saveResultsToFile();
}
