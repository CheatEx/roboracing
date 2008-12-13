 package org.osll.roboracing.world;

import java.io.Serializable;

/**
 * Container for some physical constraints applied to our robots.
 * 
 */
public class PhysicalConstraints implements Serializable {
	
	private double maxVelocity = 25;
	
	private double maxAcceleration = 10;
	
	private double maxAngularSpeed = 0.8;
	
	private double dragCoefficient = 1;
	
	private double visionRadius = 10;
	
	private double worldRadius = 1000.;

	public double getMaxVelocity() {
		return maxVelocity;
	}

	public void setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	public double getMaxAcceleration() {
		return maxAcceleration;
	}

	public void setMaxAcceleration(double maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	public double getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	public void setMaxAngularSpeed(double maxAngularSpeed) {
		this.maxAngularSpeed = maxAngularSpeed;
	}

	public double getDragCoefficient() {
		return dragCoefficient;
	}

	public void setDragCoefficient(double dragCoefficient) {
		this.dragCoefficient = dragCoefficient;
	}

	public double getVisionRadius() {
		return visionRadius;
	}

	public void setVisionRadius(double visionRadius) {
		this.visionRadius = visionRadius;
	}

	public double getWorldRadius() {
		return worldRadius;
	}

	public void setWorldRadius(double worldRadius) {
		this.worldRadius = worldRadius;
	}
}
