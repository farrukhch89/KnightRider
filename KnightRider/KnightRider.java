package KnightRider;
import robocode.*;
import java.awt.Color;
import robocode.RateControlRobot;
import robocode.HitWallEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

/**

 * KnightRider - a robot by Team JKL - Farrukh Jahangeer, Khusmanda Ramanjooloo

 */
public class KnightRider extends Robot
{
	/**
	 * run: KnightRider's default behavior
	 */
	 double gunTurnAmt; // How much to turn our gun when searching
	 
	public void run() {
		setBulletColor(Color.red);//Khusmanda
		setGunColor(Color.black);
		
		while(true) {
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		gunTurnAmt = 10; // Initialize gunTurn to 10

		 // Farrukh
			ahead(100);
			turnGunRight(180);
			back(100);
			turnGunRight(180);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) { 

		if (e.getDistance() > 150) { // Khusmanda
			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

			turnGunRight(gunTurnAmt); // Try changing these to setTurnGunRight,
			turnRight(e.getBearing()); 
			
			ahead(e.getDistance() - 140);
			return;
		}

		// Our target is close.
		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		fire(3);
		
		//Khusmanda
		if (getEnergy() > 50 && e.getDistance()< 50){ // if there are robots and robot still have lots of life near then fire 
			fire(3);
			}
		else
			{
			fire(1);
			}
		scan();

	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) { 
		// Replace the next line with any behavior you would like
		back(100);
		turnRight(180);
		fire(1);
		
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) { // Farrukh
		// Replace the next line with any behavior you would like
		ahead(-4);
	}
	public void onHitRobot(HitRobotEvent e) { //Farrukh
	
		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		fire(3);
		back(50);
		
       if (e.getBearing() > -90 && e.getBearing() <= 90) {
           back(100);
       } else {
           ahead(100);
       }
		fire(3);
	}

}
