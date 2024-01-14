import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        login();
    }

    public static void login() {
        int tryCount = 3;

        System.out.println("--- Login ---");

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("username:");
            String usernameInput = sc.nextLine();

            Scanner sc2 = new Scanner(System.in);
            System.out.print("password:");
            String passwordInput = sc2.nextLine();

            if ((usernameInput.equals("admin")) && (passwordInput.equals("admin"))) {
                System.out.println("Successfuly login");
                start();
                break;
            }

            tryCount--;
            if (tryCount == 0) {
                System.out.println("You banned");
                System.exit(0);
            } else if (tryCount == 1) {
                System.out.println("You have 1 chance left.");
            } else {
                System.out.println("You have " + tryCount + " chances left.");
            }
        }
    }

    public static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Register contestants\n2.Start contest\n3.Logout\n4.Exit");
        int selected = sc.nextInt();

        switch (selected) {
            case 1:
                registerContestants();
                start();
                break;
            case 2:
                startContest();
                start();
                break;
            case 3:
                login();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Wrong choice");
                start();
        }
    }

    public static void registerContestants() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Contestant count:");
        int count = sc.nextInt();

        Config.contestant = new Contestant[count];

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ".Contestant");

            Scanner sc2 = new Scanner(System.in);
            System.out.println("Contestant name:");
            String name = sc2.nextLine();

            Scanner sc3 = new Scanner(System.in);
            System.out.println("Contestant surname:");
            String surname = sc3.nextLine();

            Config.contestant[i] = new Contestant(name, surname);
        }

        System.out.println("Registration ended successfully");
    }

    public static void startContest() {
        Random r = new Random();
        int count = Config.contestant.length;
        int randomNumber = r.nextInt(count) + 1;

        System.out.println("Guess the winning contestant");

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + Config.contestant[i]);
        }

        Scanner sc = new Scanner(System.in);
        int guess = sc.nextInt();

        if (guess == randomNumber) {
            System.out.println("Congratulations you won!");
        } else {
            System.out.println("You failed");
            System.out.println("Winner is " + Config.contestant[randomNumber - 1]);
        }
    }
}