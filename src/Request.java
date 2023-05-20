

public class Request {
    Passenger passenger;
    Elevator.Direction direction = Elevator.Direction.FREE;
    Boolean watched = false;

    Request(Passenger passenger, Elevator.Direction direction) {
        this.passenger = passenger;
        this.direction = direction;
    }
}
