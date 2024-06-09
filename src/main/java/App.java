import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class App {
    Path data = Path.of("src/main/resources/shelter_data");

    ShelterService shelterService = new ShelterService(new File(String.valueOf(data)));
    Scanner scanner = new Scanner(System.in);
    Map<String, Executor> orchestrator = init();


    public void start() {
        Messages.greeting.print();
        Messages.commands.print();

        String command;
        do {
            command = scanner.next().trim().toLowerCase();
            scanner.nextLine();

            orchestrator
                    .getOrDefault(command, Messages.unknown::print)
                    .execute();

        } while (!command.equals("exit"));
        scanner.close();
    }

    private Map<String, Executor> init() {
        return Map.of(
                "exit", exit(),
                "add", add(),
                "remove", remove(),
                "show", show()
        );
    }


    public Executor exit() {
        return () -> {
            Messages.bye.print();
            System.exit(0);
        };
    }

    public Executor show() {
        return () -> shelterService.showAnimals();
    }

    public Executor add() {
        return () -> {
            Animal.AnimalBuilder builder = Animal.builder();

            Messages.name.print();
            String name = "";
            while (name.isEmpty()) {
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    Messages.tryAgain.print();
                }
            }
            builder.name(name);

            Messages.age.print();
            Integer age = null;
            boolean validAge = false;
            while (!validAge) {
                String input = scanner.nextLine().trim();
                try {
                    age = Integer.parseInt(input);
                    validAge = true;
                } catch (NumberFormatException e) {
                    Messages.tryAgain.print();
                }
            }
            builder.age(age);

            Messages.type.print();
            String type = "";
            while (type.isEmpty()) {
                type = scanner.nextLine().trim();
                if (type.isEmpty()) {
                    Messages.tryAgain.print();
                }
            }
            builder.type(type);

            shelterService.add(builder.build());
        };
    }

    public Executor remove() {
        return () -> {
            Messages.name.print();
            shelterService.remove(scanner.nextLine().trim());
        };
    }


}
