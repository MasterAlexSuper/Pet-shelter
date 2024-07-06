import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class App {
    Path data = Path.of("src/main/resources/shelter_data");
    ShelterService shelterService = new ShelterService(new File(String.valueOf(data)));
    Scanner scanner = new Scanner(System.in);
    Commands commands = new Commands(scanner, shelterService);
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
                "exit", commands.exit(),
                "add", commands.add(),
                "remove", commands.remove(),
                "show", commands.show()
        );
    }
}