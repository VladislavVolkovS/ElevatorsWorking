
public class Passenger {

    static int visitorCounter = 0;

    int fromFloor = 0;
    int toFloor = 0;
    Controller controller;

    Elevator elevator;

    Passenger() { visitorCounter++; }

    void callLift(Controller controller) {

        fromFloor = (int) (Math.random() * Main.countFloor);
        toFloor = (int) (Math.random() * Main.countFloor);

        while (toFloor == fromFloor) {
            toFloor = (int) (Math.random() * Main.countFloor);
        }


        Elevator.Direction direction;
        if (toFloor > fromFloor) {
            direction = Elevator.Direction.UP;
        } else {
            direction = Elevator.Direction.DOWN;
        }
        Request request = new Request(this, direction);
        request.passenger = this;

        synchronized (controller) {
            controller.queries.get(fromFloor).add(request);
        }
    }
}
