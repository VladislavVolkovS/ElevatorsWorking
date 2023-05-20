import java.util.Scanner;

public class Main {
    static int countFloor = 15;
    static int countOfElevators = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("По дефолту выбрано здание с двумя лифтами и 15 этажами, хотите выбрать другое здание?");
        System.out.println("1 — Да");
        System.out.println("2 — Нет");
        int choice = scanner.nextInt();
        if (choice == 1){
            System.out.println("Введите количество этажей:");
            countFloor = scanner.nextInt();
            while (countFloor < 1){
                System.out.println("Количество этажей не может быть меньше 0! Введите корректное количество:");
                countFloor = scanner.nextInt();
            }
            System.out.println("Введите количество лифтов:");
            countOfElevators = scanner.nextInt();
            while (countOfElevators < 1){
                System.out.println("Количество лифтов не может быть меньше 0! Введите корректное количество:");
                countOfElevators = scanner.nextInt();
            }
        }

        System.out.println("Введите количество заявок:");
        int requestsNumber = scanner.nextInt();
        while (requestsNumber < 1){
            System.out.println("Количество заявок должно быть больше 0! Введите корректное количество:");
            requestsNumber = scanner.nextInt();
        }
        System.out.println("Введите интервал генерации в мс:");
        int interval = scanner.nextInt();
        while (interval < 1){
            System.out.println("Интервал должен быть больше 0! Введите корректное число:");
            interval = scanner.nextInt();
        }
        Controller controller = new Controller();

        Thread lifts = new Thread(new WorkSystem(controller));
        lifts.start();

        for (int i = 0; i < requestsNumber; i++) {
            Passenger passenger = new Passenger();
            passenger.callLift(controller);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}