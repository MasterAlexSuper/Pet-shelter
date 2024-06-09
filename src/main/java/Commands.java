import java.util.Scanner;

public class Commands {
    Scanner scanner;
    ShelterService shelterService;

    public Commands(Scanner scanner, ShelterService shelterService) {
        this.scanner = scanner;
        this.shelterService = shelterService;
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
