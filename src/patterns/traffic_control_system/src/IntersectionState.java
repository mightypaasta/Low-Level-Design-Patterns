package patterns.traffic_control_system.src;

interface IntersectionState {
    void update(IntersectionController context) throws InterruptedException;
}

class EastWestGreenState implements IntersectionState{

    @Override
    public void update(IntersectionController context) throws InterruptedException {
        System.out.println("Starting East-West direction is GREEN. North-South direction is RED.");
        context.getLight(Direction.EAST).startGreen();
        context.getLight(Direction.WEST).startGreen();
        context.getLight(Direction.NORTH).setSignalColor(SignalColor.RED);
        context.getLight(Direction.SOUTH).setSignalColor(SignalColor.RED);

        Thread.sleep(context.getGreenDuration());

        context.getLight(Direction.EAST).transition();
        context.getLight(Direction.WEST).transition();

        Thread.sleep(context.getYellowDuration());

        context.getLight(Direction.EAST).transition();
        context.getLight(Direction.WEST).transition();

        context.setCurrentState(new NorthSouthGreenState());
    }
}

class NorthSouthGreenState implements IntersectionState{

    @Override
    public void update(IntersectionController context) throws  InterruptedException{
        System.out.println("Starting North-South direction is GREEN. East-West direction is RED.");
        context.getLight(Direction.NORTH).startGreen();
        context.getLight(Direction.SOUTH).startGreen();
        context.getLight(Direction.EAST).setSignalColor(SignalColor.RED);
        context.getLight(Direction.WEST).setSignalColor(SignalColor.RED);

        Thread.sleep(context.getGreenDuration());

        context.getLight(Direction.NORTH).transition();
        context.getLight(Direction.SOUTH).transition();

        Thread.sleep(context.getYellowDuration());

        context.getLight(Direction.NORTH).transition();
        context.getLight(Direction.SOUTH).transition();

        context.setCurrentState(new NorthSouthGreenState());
    }
}
