package org.osll.roboracing.server.game.engine;

import java.util.HashMap;

import org.osll.roboracing.server.game.Game;
import org.osll.roboracing.server.game.Utils;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.Coordinate;
import org.osll.roboracing.world.Map;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.Speed;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Team;

/**
 * Game engine implementation. This class are not thread-safe!
 * @author zan
 */
public class GameImpl implements Game {

	private Map map;
	
	private State state;
	
	private InitialPlacingStrategy placer;
	
	private double time = 0;
	
	private HashMap<String, Robot> robots = new HashMap<String, Robot>();
	
	private PhysicalConstraints constraints;
	
	public GameImpl(PhysicalConstraints constraints) {
		this.constraints = constraints;
		placer = new InitialPlacingStrategy(constraints.getWorldRadius());
	}
	
	@Override
	public void addRobot(String name, Team team)
			throws IllegalStateException, IllegalArgumentException {
		checkStarted();
		if (robots.containsKey(name))
			throw new IllegalArgumentException("Robot alredy exist in game");
		robots.put(name, 
				new Robot(name, team, placer.placeNew(team)));
	}

	@Override
	public State getState() {
		return null;
	}

	@Override
	public double getTime() {
		return time;
	}
	
	@Override
	public PhysicalConstraints getPhysicalConstraints() {
		return constraints;
	}

	@Override
	public void run(double runTime,
			java.util.Map<String,ControlCommand> commands) {
		if (time == 0)
			prepareFirstRun();
		for (Robot robot: robots.values()) {
			ControlCommand command = commands.get(robot.getName());
			calcNewCoord(robot, runTime, command);
		}

		
	}

	/**
	 * Производит рассчёт новой координаты робота.
	 * XXX сделан тупо: аппроксимируем траекторию окружностью, на основании
	 * начальной скорости и считаем перемещение по ней уже с учётом ускорения.
	 * @param robot
	 * @param runTime 
	 * @param command 
	 */
	private void calcNewCoord(Robot robot, double runTime, ControlCommand command) {
		double linearSpeed = Utils.module(robot);
		//Положительный радиус считаем, что окружность расположена справа, и 
		//наоборот, отрицательный - слева.
		//R = V/W, где R - радиус аппроксимирующей окружности, V - начальная скорость
		//на участке траектории, W - угловая скорость
		double radius = linearSpeed/command.getAngularSpeed();
		//s = (a*t^2)/2, где s - путь, пройденный роботом, a - ускорение, заданное коммандой,
		//t - время прохождения участка
		double distance = linearSpeed*runTime + command.getAcceleration()*runTime*runTime/2.0;
		//сектор окружности, пройденный за этот отрезок времени
		double angle = distance/radius;
		
		//строим вектор, ортогональный скорости
		Coordinate normal =  buildNormal(robot);
		if (wrongDirection(robot, normal, radius > 0)) {
			normal.setX(-normal.getX());
			normal.setY(normal.getY());
		}
	}

	private boolean wrongDirection(Speed robot, Coordinate normal, boolean right) {
		// TODO Auto-generated method stub
		return false;
	}

	private Coordinate buildNormal(Robot robot) {
		Coordinate normal = new Coordinate(0, 0);
		if (robot.getVy() == 0) {
			normal.setX(0);
			normal.setY(1);
		} else {
			normal.setX(1);
			normal.setY(-1*robot.getVx()/robot.getVy());
		}
		return normal;
	}

	private void prepareFirstRun() {
		state = new State(map);
		state.setRobots(robots.values());
	}

	@Override
	public void setMap(org.osll.roboracing.world.Map map)
			throws IllegalStateException {
		checkStarted();
		this.map = map;
	}
	
	private void checkStarted() {
		if (time > 0)
			throw new IllegalStateException("Game started");		
	}
}
