package org.example.animals;
import org.example.alive.IAlive;
import org.example.inventory.IInventory;
public abstract class Animal implements IAlive, IInventory {
    private final int food;
    private final int number;

    public Animal(int food, int number) {
        this.food = food;
        this.number = number;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public abstract boolean isFriendly();
}
