package org.example.zoo;

import org.example.animals.*;
import org.example.inventory.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZooTest {

    @Test
    void addAnimalShouldAddHealthyAnimal() {
        IVetClinic clinic = mock(IVetClinic.class);
        when(clinic.checkHealth(any())).thenReturn(true);

        Zoo zoo = new Zoo(clinic);
        Rabbit rabbit = new Rabbit(5, 101, 10);

        boolean result = zoo.addAnimal(rabbit);

        assertTrue(result);
        assertEquals(1, zoo.getAnimalsCount());
        assertEquals(1, zoo.getInventory().size());
        assertTrue(zoo.getInventory().contains(rabbit));

        verify(clinic).checkHealth(rabbit);
    }

    @Test
    void addAnimalShouldRejectUnhealthyAnimal() {
        IVetClinic clinic = mock(IVetClinic.class);
        when(clinic.checkHealth(any())).thenReturn(false);

        Zoo zoo = new Zoo(clinic);
        Monkey monkey = new Monkey(4, 202, 5);

        boolean result = zoo.addAnimal(monkey);

        assertFalse(result);
        assertEquals(0, zoo.getAnimalsCount());
        assertEquals(0, zoo.getInventory().size());

        verify(clinic).checkHealth(monkey);
    }

    @Test
    void addThingShouldAddToInventory() {
        IVetClinic clinic = mock(IVetClinic.class);
        Zoo zoo = new Zoo(clinic);

        Thing table = new Table(501);
        zoo.addThing(table);

        assertEquals(1, zoo.getInventory().size());
        assertTrue(zoo.getInventory().contains(table));
    }

    @Test
    void totalFoodShouldReturnSumOfFoods() {
        IVetClinic clinic = mock(IVetClinic.class);
        when(clinic.checkHealth(any())).thenReturn(true);

        Zoo zoo = new Zoo(clinic);

        zoo.addAnimal(new Rabbit(3, 1, 5));   // food = 3
        zoo.addAnimal(new Tiger(10, 2));      // food = 10
        zoo.addAnimal(new Monkey(4, 3, 2));   // food = 4

        int result = zoo.totalFood();

        assertEquals(17, result);
    }

    @Test
    void friendlyAnimalsShouldReturnOnlyFriendlyOnes() {
        IVetClinic clinic = mock(IVetClinic.class);
        when(clinic.checkHealth(any())).thenReturn(true);

        Zoo zoo = new Zoo(clinic);

        zoo.addAnimal(new Rabbit(2, 1, 10));  // friendly
        zoo.addAnimal(new Monkey(3, 2, 7));   // friendly
        zoo.addAnimal(new Wolf(8, 3));        // not friendly

        List<Animal> friendly = zoo.friendlyAnimals();

        assertEquals(2, friendly.size());
        assertTrue(friendly.stream().allMatch(Animal::isFriendly));
    }

    @Test
    void getAnimalsCountShouldReturnCorrectNumber() {
        IVetClinic clinic = mock(IVetClinic.class);
        when(clinic.checkHealth(any())).thenReturn(true);

        Zoo zoo = new Zoo(clinic);

        zoo.addAnimal(new Tiger(10, 11));
        zoo.addAnimal(new Wolf(8, 12));

        assertEquals(2, zoo.getAnimalsCount());
    }

    @Test
    void getInventoryShouldReturnAllItems() {
        IVetClinic clinic = mock(IVetClinic.class);
        when(clinic.checkHealth(any())).thenReturn(true);

        Zoo zoo = new Zoo(clinic);

        Rabbit r = new Rabbit(3, 1, 5);
        Table t = new Table(500);

        zoo.addAnimal(r);
        zoo.addThing(t);

        List<IInventory> inv = zoo.getInventory();

        assertEquals(2, inv.size());
        assertTrue(inv.contains(r));
        assertTrue(inv.contains(t));
    }
}
