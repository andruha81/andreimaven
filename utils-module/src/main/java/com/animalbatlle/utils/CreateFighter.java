package com.animalbatlle.utils;

import com.animalbattle.entities.Animal;
import com.animalbattle.entities.Cat;
import com.animalbattle.entities.Dog;
import com.animalbattle.entities.Monkey;
import org.apache.commons.lang3.RandomUtils;

public class CreateFighter {

    private static final String DEFAULT_TYPE = "Cat";
    private static int numberOfFighterWithNoName = 1;

    private CreateFighter() {
    }

    public static Animal createFighter(String animalParameters) {

        Animal createdFighter;
        String type = null;
        String name = null;
        int force = 0;
        int agility = 0;

        String[] arrayParameters = animalParameters.split(";");

        for (String parameter : arrayParameters) {

            String parameterName = parameter.substring(0, parameter.indexOf(":")).trim();
            String parameterValue = parameter.substring(parameter.indexOf(":") + 1).trim();

            switch (parameterName) {
                case ("id_type"):
                    type = parameterValue;
                    break;
                case ("id_name"):
                    name = parameterValue;
                    break;
                case ("id_force"):
                    if ((parameterValue.matches("[0-9]+"))) {
                        force = Integer.parseInt(parameterValue);
                    }
                    break;
                case ("id_agility"):
                    if ((parameterValue.matches("[0-9]+"))) {
                        agility = Integer.parseInt(parameterValue);
                    }
                    break;
            }
        }

        if (type == null) {
            type = DEFAULT_TYPE;
        }

        if (name == null) {
            name = "NoName fighter " + numberOfFighterWithNoName;
            numberOfFighterWithNoName++;
        }

        if (force == 0) {
            force = RandomUtils.nextInt(1, 10);
        }

        if (agility == 0) {
            agility = RandomUtils.nextInt(1, 10);
        }

        switch (type) {
            case ("Cat"):
                createdFighter = new Cat(name, force, agility);
                break;
            case ("Dog"):
                createdFighter = new Dog(name, force, agility);
                break;
            case ("Monkey"):
                createdFighter = new Monkey(name, force, agility);
                break;
            default:
                createdFighter = new Cat(name, force, agility);
        }

        System.out.printf("New fighter: %s%n", createdFighter);

        return createdFighter;
    }
}
