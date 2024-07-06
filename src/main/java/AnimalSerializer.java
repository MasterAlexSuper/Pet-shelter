import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AnimalSerializer {
    private final JsonMapper mapper;

    public AnimalSerializer() {
        this.mapper = new JsonMapper();
    }


    public void serializeList(List<Animal> animals, File file) throws IOException {
        this.mapper.writeValue(file, animals);
    }


    public List<Animal> deserializeList(File file) throws IOException {
        return this.mapper.readValue(file, new TypeReference<>() {
        });
    }
}
