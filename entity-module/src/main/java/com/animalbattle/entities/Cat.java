package com.animalbattle.entities;

public class Cat extends Animal {

    public Cat(int id, String newName, int force, int agility) {
        super(id, newName, force, agility);
        this.typeOfAnimal = TypeOfAnimals.CAT;
    }
}
