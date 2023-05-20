
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Controller {

    ArrayList<Elevator> elevators = new ArrayList<>();
    ArrayList<List<Request>> queries = new ArrayList<>(Main.countFloor);

    Controller() {
        for (int i = 0; i < Main.countFloor; i++) {
            queries.add(new CopyOnWriteArrayList<>());
        }

        for (int i = 0; i < Main.countOfElevators; i++) {
            elevators.add(new Elevator(0));
        }

    }

    boolean isUpperQueries(int floor) {
        int people = Elevator.MaxPeople;
        boolean flag = false;
        for (int i = floor; i < Main.countFloor; i++) {
            if (queries.get(i).size() != 0) {
                for (Request request : queries.get(i)) {
                    if (!request.watched) {
                        request.watched = true;
                        people--;
                        flag = true;
                        if (people == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        if (flag) return true;
        return false;
    }

    boolean isDownerQueries(int floor) {
        int people = Elevator.MaxPeople;
        boolean flag = false;
        for (int i = floor; i >= 0; i--) {
            if (queries.get(i).size() != 0) {
                for (Request request : queries.get(i)) {
                    if (!request.watched) {
                        flag = true;
                        request.watched = true;
                        people--;
                        if (people == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        if (flag) return true;
        return false;
    }

    void print() {
        System.out.println();
        System.out.format("%9s", "");
        for (int i = 0; i < Main.countOfElevators; i++) {
            System.out.format("%-15s", " Elevator: " + (i+1));
        }
        System.out.println("");

        for (int floor = Main.countFloor - 1; floor >= 0; floor--) {
            System.out.format("%2d %-7s",floor + 1, " floor");
            for (Elevator elevator : elevators) {
                if (elevator.curFloor == floor) {
                    System.out.format("%-15s", "_____" + elevator.peopleInside + "_____");
                } else {
                    System.out.print("               ");
                }
            }

            System.out.println(queries.get(floor).size());
        }

    }

}
