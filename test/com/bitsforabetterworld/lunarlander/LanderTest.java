package com.bitsforabetterworld.lunarlander;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LanderTest {
	@Test
	void testPosition() {
		Lander lander = new Lander.Builder().x(32.3).y(62.6).theta(6.1).build();
		Position position = lander.getPosition();
		assertEquals(32.3, position.getX());
		assertEquals(62.6, position.getY());
		assertEquals(6.1, position.getTheta());
	}
	
	@Test
	void testFreeFallin() {
		// All the vampires walkin' throught the Valley
		// Move west down Ventura Boulevard
		// --Tom Petty
		Lander lander = new Lander.Builder().y(100.0).build();
		lander.clockTick(1.0);
		Position position = lander.getPosition();
		Velocity velocity = lander.getVelocity();
		assertEquals(1.0 * lander.getGravityAcceleration(), velocity.getDy());
		assertEquals(0.0, velocity.getDx());
		assertEquals(0.0, velocity.getDtheta());
		// Position hasn't changed yet, because velocity was 0 at the start of this tick
		assertEquals(100.0, position.getY());
	}
	
	@Test
	void testFreeFallin2() {
		Lander lander = new Lander.Builder().y(100.0).build();
		for (int i=0; i < 2; ++i) {
			lander.clockTick(1.0);
		}
		Position position = lander.getPosition();
		Velocity velocity = lander.getVelocity();
		assertEquals(2.0 * lander.getGravityAcceleration(), velocity.getDy());
		// Position has changed by 0 + 1
		assertEquals(99.0, position.getY());		
	}

	@Test
	void testFreeFallin4() {
		Lander lander = new Lander.Builder().y(100.0).build();
		for (int i=0; i < 4; ++i) {
			lander.clockTick(1.0);
		}
		Position position = lander.getPosition();
		Velocity velocity = lander.getVelocity();
		assertEquals(4.0 * lander.getGravityAcceleration(), velocity.getDy());
		// Position has changed by 0 + 1 + 2 + 3
		assertEquals(94.0, position.getY());		
	}
	@Test
	void testFreeFallin8() {
		Lander lander = new Lander.Builder().y(100.0).build();
		for (int i=0; i < 8; ++i) {
			lander.clockTick(1.0);
		}
		Position position = lander.getPosition();
		Velocity velocity = lander.getVelocity();
		assertEquals(8.0 * lander.getGravityAcceleration(), velocity.getDy());
		// Position has changed by 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7
		assertEquals(72.0, position.getY());
	}
	
	@Test
	void testParabolicTrajectory1() {
		Lander lander = new Lander.Builder().y(0.0).x(0.0).dx(8.0).dy(8.0).build();
		lander.clockTick(1.0);
		Position position = lander.getPosition();
		assertEquals(8.0, position.getX());
		assertEquals(8.0, position.getY());
		Velocity velocity = lander.getVelocity();
		assertEquals(8.0, velocity.getDx());
		assertEquals(7.0, velocity.getDy());		
	}

	@Test
	void testParabolicTrajectory8() {
		Lander lander = new Lander.Builder().y(0.0).x(0.0).dx(8.0).dy(8.0).build();
		for (int i=0; i<8; ++i) {
			lander.clockTick(1.0);
		}
		Position position = lander.getPosition();
		// We moved at a steady 8 m/s in the X direction
		assertEquals(64.0, position.getX());
		
		// In the Y direction, we moved 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 m
		assertEquals(36.0, position.getY());
		Velocity velocity = lander.getVelocity();
		assertEquals(8.0, velocity.getDx());
		assertEquals(0.0, velocity.getDy());		
	}

	@Test
	void testParabolicTrajectory16() {
		Lander lander = new Lander.Builder().y(0.0).x(0.0).dx(8.0).dy(8.0).build();
		for (int i=0; i<16; ++i) {
			lander.clockTick(1.0);
		}
		Position position = lander.getPosition();
		// We moved at a steady 8 m/s in the X direction
		assertEquals(128.0, position.getX());
		
		assertEquals(8.0, position.getY());
		Velocity velocity = lander.getVelocity();
		assertEquals(8.0, velocity.getDx());
		assertEquals(-8.0, velocity.getDy());
	}
	@Test
	void testParabolicTrajectory17() {
		Lander lander = new Lander.Builder().y(0.0).x(0.0).dx(8.0).dy(8.0).build();
		for (int i=0; i<17; ++i) {
			lander.clockTick(1.0);
		}
		Position position = lander.getPosition();
		assertEquals(0.0, position.getY());
	}
	
	@Test
	void testThrustVertical()
	{
		Lander lander = new Lander.Builder().x(0.0).y(100.0).dx(0.0).dy(-10.0).thrusterAcceleration(10.0).build();
		lander.turnOnThruster();
		lander.clockTick(1.0);
		Velocity velocity = lander.getVelocity();
		assertEquals(0.0, velocity.getDx());
		// The thruster counteracted the initial velocity of -10.0 m/s, but gravity accelerates us downwards
		assertEquals(-1.0, velocity.getDy());
	}
	
	@Test
	void testThrustHorizontalRight() {
		Lander lander = new Lander.Builder().x(0.0).y(100.0).theta(Math.PI/2.0).dx(0.0).dy(0.0).thrusterAcceleration(10.0).build();
		lander.turnOnThruster();
		lander.clockTick(1.0);
		Velocity velocity = lander.getVelocity();
		assertEquals(10.0, velocity.getDx());
		// The thruster counteracted the initial velocity of -10.0 m/s, but gravity accelerates us downwards
		assertEquals(-1.0, velocity.getDy(), 0.00001 /*delta*/);		
	}
	
	@Test
	void testThrustHorizontalLeft() {
		Lander lander = new Lander.Builder().x(0.0).y(100.0).theta(-Math.PI/2.0).dx(0.0).dy(0.0).thrusterAcceleration(10.0).build();
		lander.turnOnThruster();
		lander.clockTick(1.0);
		Velocity velocity = lander.getVelocity();
		assertEquals(-10.0, velocity.getDx());
		// The thruster counteracted the initial velocity of -10.0 m/s, but gravity accelerates us downwards
		assertEquals(-1.0, velocity.getDy(), 0.00001 /*delta*/);		
	}
	
	@Test
	void testRotation1() {
		Lander lander = new Lander.Builder().x(0.0).y(100.0).theta(0.0).dtheta(1.0).build();
		lander.clockTick(1.0);
		Position position = lander.getPosition();
		assertEquals(1.0, position.getTheta());
	}
	
	@Test 
	void testRotationFullCircle() {
		Lander lander = new Lander.Builder().x(0.0).y(100.0).theta(0.0).dtheta(Math.PI).build();
		lander.clockTick(1.0);
		lander.clockTick(1.0);
		Position position = lander.getPosition();
		assertEquals(0.0, position.getTheta());
		
	}
	@Test 
	void testRotationFullCircleBackwards() {
		Lander lander = new Lander.Builder().x(0.0).y(100.0).theta(0.0).dtheta(-Math.PI).build();
		lander.clockTick(1.0);
		lander.clockTick(1.0);
		Position position = lander.getPosition();
		assertEquals(0.0, position.getTheta(), 0.000001 /*delta*/);
	}
}
