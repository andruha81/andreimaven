package com.animalbattle.entities;

public class Monkey extends Animal {

    public Monkey(String newName, int force, int agility) {
        super(newName, force, agility);
        this.typeOfAnimal = TypeOfAnimals.MONKEY;
    }
}
