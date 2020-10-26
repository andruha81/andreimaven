package com.animalbattle.api.services;

import com.animalbattle.entities.Animal;

public interface ICombat {
    Animal startCombat(Animal firstFighter, Animal secondFighter);
}
