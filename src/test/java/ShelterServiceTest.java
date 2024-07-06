import org.junit.Assert;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class ShelterServiceTest {
    Path path = Path.of("src/test/resources/shelter_data");
    ShelterService shelterService = new ShelterService(new File(String.valueOf(path)));

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void getAnimalsTest() {
        Animal animal = new Animal("Tony", 3, "cat");
        shelterService.add(animal);
        List<Animal> actual = shelterService.getAnimals();

        List<Animal> expected = List.of(animal);
        Assert.assertEquals(expected, actual);
        shelterService.remove(animal.getName());
    }

    @Test
    public void addAnimalTest() {
        Animal animal = new Animal("Kiki", 10, "dog");
        shelterService.add(animal);

        List<Animal> actual = shelterService.getAnimals();
        List<Animal> expected = List.of(animal);

        Assert.assertEquals(expected, actual);

        shelterService.remove(animal.getName());
    }

    @Test
    public void removeAnimalTest() {
        Animal animal = new Animal("Kiki", 10, "dog");
        shelterService.add(animal);
        shelterService.remove(animal.getName());

        List<Animal> actual = shelterService.getAnimals();
        List<Animal> expected = new ArrayList<>();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void showAnimalsTest_NoData() {
        System.setOut(new PrintStream(outContent));

        shelterService.showAnimals();
        Assert.assertEquals(Messages.nodata.get().trim(), outContent.toString().trim());
    }

    @Test
    public void showAnimals_WithData() {

        Animal animal = new Animal("Test", 2, "type");
        shelterService.add(animal);
        System.setOut(new PrintStream(outContent));
        shelterService.showAnimals();
        String expected = "1. Name: Test, age: 2, type: type";
        Assert.assertEquals(expected.trim(), outContent.toString().trim());
        shelterService.remove(animal.getName());
    }
}
