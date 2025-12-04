package org.example.animals;
public abstract class Herbo extends Animal {
    private final int kindness;
    public Herbo(int food, int number, int kindness) {
        super(food, number);
        this.kindness = kindness;
    }
    @Override
    public boolean isFriendly() {
        return kindness > 5;
    }
}
