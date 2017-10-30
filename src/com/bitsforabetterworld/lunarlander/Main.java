package com.bitsforabetterworld.lunarlander;

enum Command {
	Thrust,
	RollClockwise,
	RollCounterclockwise,
	DeployLandingGear
}


class Position {
	private final double x;
	private final double y;
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}


class Velocity {
	private final double dx;
	private final double dy;
	
	public Velocity(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public double getDx() {
		return dx;
	}
	
	public double getDy() {
		return dy;
	}
}
class WorldView {
	
}

class SensorUnit {
	public WorldView getWorldView() {
		return null;
	}
}

interface ControlUnit {
	public Command getCommand(WorldView worldView);
}

public class Main {

	void mainEventLoop(SensorUnit sensorUnit, ControlUnit controlUnit) {
		boolean isDone = false;
		while (!isDone) {
			WorldView worldView = sensorUnit.getWorldView();
			Command command = controlUnit.getCommand(worldView);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
