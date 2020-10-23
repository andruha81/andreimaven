package com.animalbattle.entities;

public class Dog extends Animal {

    public Dog(String newName, int force, int agility) {
        super(newName, force, agility);
        this.typeOfAnimal = TypeOfAnimals.DOG;
    }
}
