package com.animalbattle.entities;

public class Cat extends Animal {

    public Cat(String newName, int force, int agility) {

        super(newName, force, agility);

        this.typeOfAnimal = TypeOfAnimals.CAT;

    }
}
