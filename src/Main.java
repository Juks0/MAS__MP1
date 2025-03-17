import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
//    Racer racer1 = new Racer("Racer1", "Fanatec McLaren GT3", "Fanatec CSL Elite");
//    Racer racer2 = new Racer("Racer2", "Logitech G29", "Logitech G29", "Logitech HB");
//    Racer racer3 = new Racer("Racer3", "Moza CSS", "Moza CSL", "Moza HB v1.5");
//    List<Racer> racers = new ArrayList<>();
//    racers.add(racer1);
//    racers.add(racer2);
//    racers.add(racer3);
//    racer1.setFastestLapTime(45.5);
//    racer2.setFastestLapTime(46.5);
//    racer3.setFastestLapTime(47.5);
//    Race race = new Race("Tor Poznan", racers);
static Scanner scanner = new Scanner(System.in);
public static void main(String[] args) {
    try {
        ObjectPlus.loadExtent();
    } catch (Exception e) {
        System.out.println("No previous data found. Starting fresh.");
    }

    boolean running = true;
    while (running) {
        System.out.println("\n=== Racing Management System ===");
        System.out.println("1. Create Racer");
        System.out.println("2. Create Race");
        System.out.println("3. Add Racer to Race");
        System.out.println("4. Display All Races");
        System.out.println("5. Find Racer by Nickname");
        System.out.println("6. Add Money to Racer");
        System.out.println("7. Remove Money from Racer");
        System.out.println("8. Set Racer's Best Lap Time");
        System.out.println("9. Save & Exit");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> createRacer();
            case 2 -> createRace();
            case 3 -> addRacerToRace();
            case 4 -> displayAllRaces();
            case 5 -> findRacerByNickname();
            case 6 -> addMoneyToRacer();
            case 7 -> removeMoneyFromRacer();
            case 8 -> setRacerBestLapTime();
            case 9 -> {
                saveAndExit();
                running = false;
            }
            default -> System.out.println("Invalid choice. Try again.");
        }
    }
}

    private static void createRacer() {
        System.out.print("Enter nickname: ");
        String nickname = scanner.nextLine();

        System.out.print("Enter wheel: ");
        String wheel = scanner.nextLine();

        System.out.print("Enter pedals: ");
        String pedals = scanner.nextLine();

        System.out.print("Enter handbrake (optional): ");
        String handbrake = scanner.nextLine();

        Racer racer;
        if (!handbrake.isBlank()) {
            racer = new Racer(nickname, wheel, pedals, handbrake);
        } else {
            racer = new Racer(nickname, wheel, pedals);
        }

        System.out.println("Racer created successfully: " + racer);
    }

    private static void createRace() {
        System.out.print("Enter track name: ");
        String trackName = scanner.nextLine();

        List<Racer> racers = new ArrayList<>();
        System.out.println("Enter racers' nicknames (type 'done' to finish):");
        while (true) {
            System.out.print("Racer nickname: ");
            String nickname = scanner.nextLine();
            if (nickname.equalsIgnoreCase("done")) break;

            try {
                Racer racer = (Racer) Account.findPlayerByNickname(nickname);
                if (racer != null) {
                    racers.add(racer);
                } else {
                    System.out.println("Racer not found. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error finding racer: " + e.getMessage());
            }
        }

        if (!racers.isEmpty()) {
            Race race = new Race(trackName, racers);
            System.out.println("Race created successfully:");
            System.out.println(race);
        } else {
            System.out.println("No racers added. Race creation failed.");
        }
    }

    private static void addRacerToRace() {
        System.out.print("Enter race ID: ");
        int raceId = scanner.nextInt();
        scanner.nextLine();

        Race race = Race.getRaceById(raceId);
        if (race == null) {
            System.out.println("Race not found.");
            return;
        }

        System.out.print("Enter nickname of racer to add: ");
        String nickname = scanner.nextLine();

        try {
            Racer racer = (Racer) Account.findPlayerByNickname(nickname);
            if (racer != null) {
                race.addRacer(racer);
                System.out.println("Racer added successfully.");
            } else {
                System.out.println("Racer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error adding racer: " + e.getMessage());
        }
    }

    private static void displayAllRaces() {
        List<Race> races = ObjectPlus.getExtentFromClass(Race.class);
        if (races.isEmpty()) {
            System.out.println("No races available.");
        } else {
            for (Race race : races) {
                System.out.println(race);
            }
        }
    }

    private static void findRacerByNickname() {
        System.out.print("Enter nickname: ");
        String nickname = scanner.nextLine();

        try {
            Racer racer = (Racer) Account.findPlayerByNickname(nickname);
            if (racer != null) {
                System.out.println("Racer found: " + racer);
            } else {
                System.out.println("Racer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error finding racer: " + e.getMessage());
        }
    }

    private static void saveAndExit() {
        try {
            ObjectPlus.saveExtent();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    private static void addMoneyToRacer() {
        System.out.print("Enter nickname: ");
        String nickname = scanner.nextLine();

        try {
            Racer racer = (Racer) Account.findPlayerByNickname(nickname);
            if (racer != null) {
                System.out.print("Enter amount to add: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                racer.addMoney(amount);
                System.out.println("Money added successfully.");
            } else {
                System.out.println("Racer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error adding money: " + e.getMessage());
        }
    }

    private static void removeMoneyFromRacer() {
        System.out.print("Enter nickname: ");
        String nickname = scanner.nextLine();

        try {
            Racer racer = (Racer) Account.findPlayerByNickname(nickname);
            if (racer != null) {
                System.out.print("Enter amount to remove: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                racer.removeMoney(amount);
                System.out.println("Money removed successfully.");
            } else {
                System.out.println("Racer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error removing money: " + e.getMessage());
        }
    }

    private static void setRacerBestLapTime() {
        System.out.print("Enter nickname: ");
        String nickname = scanner.nextLine();

        try {
            Racer racer = (Racer) Account.findPlayerByNickname(nickname);
            if (racer != null) {
                System.out.print("Enter fastest lap time: ");
                double lapTime = scanner.nextDouble();
                scanner.nextLine();
                racer.setFastestLapTime(lapTime);
                System.out.println("Best lap time updated successfully.");
            } else {
                System.out.println("Racer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating lap time: " + e.getMessage());
        }
    }
}