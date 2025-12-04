package org.example.inventory;

public abstract class Thing implements IInventory {
    private final int number;
    public Thing(int number) {
        this.number = number;
    }
    @Override
    public int getNumber() {
        return number;
    }
}

