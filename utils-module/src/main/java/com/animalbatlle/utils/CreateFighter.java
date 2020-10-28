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

    public static Animal createFighter(int id, String type, String name, int force, int agility) {

        Animal createdFighter;

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
                createdFighter = new Cat(id, name, force, agility);
                break;
            case ("Dog"):
                createdFighter = new Dog(id, name, force, agility);
                break;
            case ("Monkey"):
                createdFighter = new Monkey(id, name, force, agility);
                break;
            default:
                createdFighter = new Cat(id, name, force, agility);
        }
        System.out.printf("New fighter: %s%n", createdFighter);
        return createdFighter;
    }
}
