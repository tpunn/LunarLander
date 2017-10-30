package com.bitsforabetterworld.lunarlander;

class WorldView {
	private final Position position;
	private final Velocity velocity;
	
	public WorldView(Position position, Velocity velocity)
	{
		this.position = position;
		this.velocity = velocity;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public Velocity getVelocity() {
		return velocity;
	}
}