import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    private String name;
    private int age;
    private String type;

    Animal(String name) {
        this.name = name;
    }
}