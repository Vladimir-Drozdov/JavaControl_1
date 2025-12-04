package org.example.animals;
public abstract class Predator extends Animal {
    public Predator(int food, int number) {
        super(food, number);
    }
    @Override
    public boolean isFriendly() {
        return false;
    }
}
