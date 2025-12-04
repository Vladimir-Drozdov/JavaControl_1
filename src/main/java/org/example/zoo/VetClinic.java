package org.example.zoo;
import org.example.animals.Animal;
public class VetClinic implements IVetClinic {
    @Override
    public boolean checkHealth(Animal animal) {
        return Math.random() > 0.2;
    }
}
