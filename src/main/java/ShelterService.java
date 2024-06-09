import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ShelterService {
    File data;
    List<Animal> animals;
    AnimalSerializer animalSerializer;

    public ShelterService(File data) {
        this.data = data;
        this.animalSerializer = new AnimalSerializer();
        this.animals = getAnimals();

    }

    public List<Animal> getAnimals() {
        try {
            if (data.createNewFile() || data.length() == 0) {
                PrintWriter writer = new PrintWriter(new FileWriter(data));
                writer.println("[]");
                writer.close();
                return new ArrayList<>();
            } else {
                return new ArrayList<>(animalSerializer.deserializeList(data));
            }
        } catch (IOException e) {
            System.err.println(Messages.fileErr.get());
            return null;
        }
    }

    public void add(Animal animal) {
        animals.add(animal);
        try {
            animalSerializer.serializeList(animals, data);
            Messages.success.print();
        } catch (IOException e) {
            System.err.println(Messages.fileErr.get());
        }
    }

    public void remove(String name) {
        Animal toRemove = null;
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                toRemove = animal;
                break;
            }
        }
        if (toRemove != null) {
            animals.remove(toRemove);
        } else {
            Messages.notfound.print();
            return;
        }

        try {
            animalSerializer.serializeList(animals, data);
            Messages.success.print();
        } catch (IOException e) {
            System.err.println(Messages.fileErr.get());
        }
    }

    public void showAnimals() {
        if (animals.isEmpty()) {
            Messages.nodata.print();
        } else {
            int index = 1;
            for (Animal animal : animals) {
                String output = String.format("%d. Name: %s, age: %d, type: %s", index, animal.getName(), animal.getAge(), animal.getType());
                System.out.println(output);
                index++;
            }
        }
    }
}
