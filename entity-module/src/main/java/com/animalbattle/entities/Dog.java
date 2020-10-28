package com.animalbattle.entities;

public class Dog extends Animal {

    public Dog(int id, String newName, int force, int agility) {
        super(id, newName, force, agility);
        this.typeOfAnimal = TypeOfAnimals.DOG;
    }
}
