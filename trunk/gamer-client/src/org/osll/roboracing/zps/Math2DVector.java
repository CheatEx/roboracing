package org.osll.roboracing.zps;

public class Math2DVector {

	
	private double x = 0;
	private double y = 0;
	
	public Math2DVector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Math2DVector v) {
		x+=v.x;
		y+=v.y;
	}
	
	public Math2DVector sub(Math2DVector v) {
		return new Math2DVector(v.x-x,v.y-y);
	}
	
	public double norm() {
		return Math.sqrt(x*x+y*y);
	}
	
	public double diff(Math2DVector v) {
		return Math.sqrt(Math.pow(v.x-x,2) + Math.pow(v.y-y,2)); 
	}
	/**
	 * Скаляоное произведение
	 * @param v
	 * @return
	 */
	public double mul(Math2DVector v) {
		return x*v.x+y*v.y;
	}
	
	public Math2DVector mul(double c) {
		return new Math2DVector(x*c,y*c);
	}
	
	public double getAlpha() {
		return Math.asin(y/norm());
	}
	
	public double getR() {
		return norm();
	}
}
