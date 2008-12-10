package org.osll.roboracing.world;

/**
 * Container for some physical constraints applied to our robots.
 * 
 * @author zan
 */
public class PhysicalConstraints {
	
	private double maxVelocity;
	
	private double maxAcceleration;
	
	private double maxAngularSpeed;
	
	private double dragCoefficient;
	
	private double visionRadius;
	
	private double worldRadius;

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
