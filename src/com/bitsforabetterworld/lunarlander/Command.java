package com.bitsforabetterworld.lunarlander;

/**
 * These are the commands available to the lunar lander.
 * 
 * Thrust: fire the main thrusters, pushing the lander in the direction its nose is facing
 
 * RollClockwise: fire the small control jets that push the lander to roll in the clockwise direction
 * 
 * RollCounterclockwise: fire the small control jets that push the lander to roll in the counterclockwise direction
 * 
 * @author cpdupuis@gmail.com
 *
 */
enum Command {
	None,
	Thrust,
	RollClockwise,
	RollCounterclockwise,
}
