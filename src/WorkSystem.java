

public class WorkSystem implements Runnable {

    Controller controller;

    WorkSystem(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (controller) {

                for (Elevator elevator : controller.elevators) {

                    synchronized (elevator) {
                        if (elevator.direction == Elevator.Direction.FREE) {
                            elevator.setDirection(controller);
                        }

                        if (elevator.direction != Elevator.Direction.FREE) {
                            elevator.exitPeople();
                            elevator.enterPeople(controller);
                            elevator.move();
                        }
                    }
                }

                controller.print();

            }


            try {
                Thread.sleep(1000);
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
