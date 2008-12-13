package org.osll.roboracing.server.game.engine;

import static org.osll.roboracing.server.game.Utils.*;
import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.osll.roboracing.server.game.Game;
import org.osll.roboracing.world.Checkpoint;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.Coordinate;
import org.osll.roboracing.world.Hill;
import org.osll.roboracing.world.Map;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Pit;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.Speed;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Team;

/**
 * Game engine implementation. This class are not thread-safe!
 * @author zan
 */
public class GameImpl implements Game {

	private Map map = new Map();
	
	private State state;
	
	private InitialPlacingStrategy placer;
	
	private double time = 0;
	
	private HashMap<String, Robot> robots = new HashMap<String, Robot>();
	
	private PhysicalConstraints constraints;
	
	public GameImpl(PhysicalConstraints constraints) {
		this.constraints = constraints;
		placer = new InitialPlacingStrategy(constraints.getWorldRadius());
		Map m = new Map();
		m.setPits(new ArrayList<Pit>());
		m.getPits().add(new Pit(123., 123., 10.));
		m.setHills(new ArrayList<Hill>());
		m.getHills().add(new Hill(215,215, 30));
		m.setCheckpoints(new ArrayList<Checkpoint>());
		m.getCheckpoints().add(new Checkpoint(160., 160.));
		setMap(m);
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
		return state;
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
			if(command == null)
				continue;
			calcNewCoord(robot, runTime, command);
		}

		
	}

	/**
	 * Производит рассчёт новой координаты робота.
	 * XXX сделан тупо: аппроксимируем траекторию окружностью, на основании
	 * начальной скорости и считаем перемещение по ней уже с учётом ускорения.
	 * XXX вмясо неоптимально, но должно работать.
	 * @param robot
	 * @param runTime 
	 * @param command 
	 */
	private void calcNewCoord(Robot robot, double runTime, ControlCommand command) {
		double linearSpeed = module((Speed)robot);
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
			normal.setY(-normal.getY());
		}
		//масштабируем его на радиус
		double normalLength = module(normal);
		double proportion = normalLength/radius;
		normal.setX(normal.getX()*proportion);
		normal.setY(normal.getY()*proportion);
		//теперь он указывает на центр аппроксимирующей окружности
		
		//находим сам центр
		Coordinate center = plus(robot, normal);
		//минус наша нормаль - это координата робота в СК центра окружности
		Coordinate translatedRobot =
			new Coordinate(-normal.getX(), -normal.getY());
		//поворачиваем робота вокруг центра
		translatedRobot.setX(translatedRobot.getX()*cos(angle));
		translatedRobot.setY(translatedRobot.getY()*sin(angle));
		//возвращаемся в исходную систему коордиинат
		robot.setX(center.getX() + translatedRobot.getX());
		robot.setY(center.getY() + translatedRobot.getY());

		//теперь обновляем скорость
		linearSpeed += command.getAcceleration()*runTime;
		double speedAngle = atan(robot.getVy()/robot.getVx());
		speedAngle += angle;
		robot.setVx(linearSpeed*cos(speedAngle));
		robot.setVy(linearSpeed*sin(speedAngle));
		//во срань получилась...
	}

	private boolean wrongDirection(Speed robot, Coordinate normal, boolean right) {
		double thing = robot.getVx()*normal.getY() - robot.getVy()*normal.getX();
		return thing > 0 == right;
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
		try {
			state = new State(map);
		} catch (Exception ex) {
		   ex.printStackTrace();
		}
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
