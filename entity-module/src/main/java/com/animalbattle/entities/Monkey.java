package com.animalbattle.entities;

public class Monkey extends Animal {

    public Monkey(int id, String newName, int force, int agility) {
        super(id, newName, force, agility);
        this.typeOfAnimal = TypeOfAnimals.MONKEY;
    }
}
