package org.example.zoo;
import java.util.*;
import org.example.animals.*;
import org.example.inventory.*;
public class Zoo {
    private final IVetClinic clinic;
    private final List<Animal> animals = new ArrayList<>();
    private final List<IInventory> inventory = new ArrayList<>();

    public Zoo(IVetClinic clinic) {
        this.clinic = clinic;
    }

    public boolean addAnimal(Animal animal) {
        if (clinic.checkHealth(animal)) {
            animals.add(animal);
            inventory.add(animal);
            return true;
        }
        return false;
    }

    public void addThing(Thing thing) {
        inventory.add(thing);
    }

    public int totalFood() {
        return animals.stream().mapToInt(Animal::getFood).sum();
    }

    public List<Animal> friendlyAnimals() {
        return animals.stream().filter(Animal::isFriendly).toList();
    }

    public List<IInventory> getInventory() {
        return inventory;
    }

    public int getAnimalsCount() {
        return animals.size();
    }
}
