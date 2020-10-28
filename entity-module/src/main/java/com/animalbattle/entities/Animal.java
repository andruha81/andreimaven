package com.animalbattle.entities;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
public abstract class Animal implements Serializable {

    private static final long serialVersionUID = 6613826656822751310L;
    protected static final int DEFAULT_HEALTH = 10;
    protected int id;
    protected String name;
    protected TypeOfAnimals typeOfAnimal;
    protected int force;
    protected int agility;
    protected int health = DEFAULT_HEALTH;

    public Animal(int id, String name, int force, int agility) {
        this.id = id;
        this.name = name;
        this.force = force;
        this.agility = agility;
    }

    @Override
    public String toString() {
        return this.typeOfAnimal + " " + name
                + "\n" + "Force: " + force + ", Agility: " + agility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return this.name.equals(animal.getName()) &&
                this.typeOfAnimal == animal.getTypeOfAnimal();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, typeOfAnimal);
    }

    public String getFullName() {
        return this.typeOfAnimal + " " + this.getName();
    }

    public int getDefaultHealth() {
        return DEFAULT_HEALTH;
    }

    public void setHealthDefault() {
        this.health = DEFAULT_HEALTH;
    }

    public void editHealth(int healthDamage) {
        this.health -= healthDamage;
        this.health = Math.max(this.health, 0);
    }
}
