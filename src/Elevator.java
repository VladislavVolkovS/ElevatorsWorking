
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Elevator {

    static enum Direction {UP, DOWN, FREE}

    static final int MaxPeople = 5;

    int curFloor = 0;
    int peopleInside = 0;
    Direction direction = Direction.FREE;
    List<Passenger> passengers = new CopyOnWriteArrayList<Passenger>();

    Elevator(int curFloor) {
        this.curFloor = curFloor;
    }

    void exitPeople() {
        for (Passenger passenger : passengers) {
            if (passenger.toFloor == curFloor) {
                passengers.remove(passenger);
                peopleInside--;
            }
        }
    }

    void enterPeople(Controller controller) {
        for (Request request : controller.queries.get(curFloor)) {
            if (request.direction == direction && peopleInside < Elevator.MaxPeople) {
                peopleInside++;
                request.passenger.elevator = this;
                passengers.add(request.passenger);
                controller.queries.get(curFloor).remove(request);
            }
        }

    }

    void setDirection(Controller controller) {
        if (controller.isUpperQueries(curFloor)) {
            direction = Elevator.Direction.UP;
        }
        if (controller.isDownerQueries(curFloor)) {
            direction = Elevator.Direction.DOWN;
        }
    }

    void move() {
        if (direction == Direction.UP) {
            if (curFloor < Main.countFloor - 1) {
                curFloor++;
            } else {
                direction = Direction.FREE;
            }
        }

        if (direction == Direction.DOWN) {
            if (curFloor > 0) {
                curFloor--;
            } else {
                direction = Direction.FREE;
            }
        }
    }
}
