package com.battle.services;

import com.animalbatlle.utils.InputOutput;
import com.animalbattle.api.services.ICombat;
import com.animalbattle.entities.Animal;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;

public class Combat implements ICombat {

    private Animal attacker;
    private Animal defender;

    public Animal startCombat(Animal firstFighter, Animal secondFighter) {

        attacker = firstFighter;
        defender = secondFighter;
        Animal winner = null;
        Animal serializedFighter = null;
        int round = 0;

        while (winner == null) {

            round++;
            chooseWhoAttack(round);

            System.out.printf("Round %s%n", round);
            System.out.printf("Attacker: %s, health: %s%n", attacker.getFullName(), attacker.getHealth());
            System.out.printf("Defender: %s, health: %s%n", defender.getFullName(), defender.getHealth());

            defender.editHealth(attack());

            if ((defender.getHealth() <= defender.getDefaultHealth() / 2) && (serializedFighter == null)) {
                serializedFighter = serializeFighter(defender);
            }

            if (defender.getHealth() == 0) {
                if ((serializedFighter == defender) && checkLuckyChance()) {
                    if (!deserializeFighter()) {
                        winner = attacker;
                    }
                } else {
                    winner = attacker;
                }
            }
        }
        System.out.printf("The winner of the combat is %s%n", winner.getFullName());
        firstFighter.setHealthDefault();
        secondFighter.setHealthDefault();
        return winner.equals(firstFighter) ? firstFighter : secondFighter;
    }

    private void chooseWhoAttack(int round) {
        Animal changeFighter;

        if (round == 1) {
            if (RandomUtils.nextInt(0, 2) == 1) {
                changeFighter = attacker;
                attacker = defender;
                defender = changeFighter;
            }
        } else if (checkLuckyChance()) {
            System.out.printf("Another chance to attack for %s%n", attacker.getFullName());
        } else {
            changeFighter = attacker;
            attacker = defender;
            defender = changeFighter;
        }
    }

    private boolean checkLuckyChance() {
        return (RandomUtils.nextInt(1, 5) == 3);
    }

    private int attack() {
        int damage = (attacker.getForce() - defender.getAgility());
        damage = Math.max(damage, 1);
        System.out.printf("%s is damaged by %s%n", defender.getFullName(), damage);
        return damage;
    }

    private Animal serializeFighter(Animal fighter) {

        try {
            InputOutput.serialiseFighter(fighter);
            System.out.printf("Fighter %s was serialised %n", fighter.getFullName());
            return fighter;
        } catch (IOException e) {
            System.out.printf("Fighter %s serialisation unsuccessfully %n", fighter.getFullName());
            return null;
        }
    }

    private boolean deserializeFighter() {

        try {
            defender = InputOutput.deserializeFighter();
            System.out.printf("Fighter %s was deserialized %n", defender.getFullName());
            return true;
        } catch (IOException | ClassNotFoundException exception) {
            System.out.printf("Fighter %s deserialization unsuccessfully %n", defender.getFullName());
            return false;
        }
    }
}
