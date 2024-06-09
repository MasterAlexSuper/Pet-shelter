
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimalSerializerTest {
    Animal testAnimal1 = new Animal("Test1", 1, "Type");
    Animal testAnimal2 = new Animal("Test2", 2, "Type");

    @Test
    public void serializeListTest() throws IOException {
        AnimalSerializer serializer = new AnimalSerializer();
        File file = new File("src/test/resources/shelter_data");
        List<Animal> animals = List.of(testAnimal1, testAnimal2);

        serializer.serializeList(animals, file);
        List<Animal> deserializedAnimals = serializer.deserializeList(file);

        Assert.assertEquals(deserializedAnimals, animals);
        serializer.serializeList(new ArrayList<>(), file);
    }

    @Test
    public void deserializeListTest() throws IOException {
        AnimalSerializer serializer = new AnimalSerializer();
        File file = new File("src/test/resources/shelter_data");
        List<Animal> animals = List.of(testAnimal1, testAnimal2);

        serializer.serializeList(animals, file);
        List<Animal> deserializedAnimals = serializer.deserializeList(file);

        Assert.assertEquals(deserializedAnimals, animals);
        serializer.serializeList(new ArrayList<>(), file);

    }
}
