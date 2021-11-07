import java.util.Arrays;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 
 * 팩토리 메소드 패턴: Asteroid
 * Asteroid.java: 소행성
 */
public class Asteroid extends Polygon{
	private Direction startDirection;
	private Location startLoc;
	private Location destLoc;
	private int speed;
	
	// 주어진 좌표를 다각형의 꼭짓점으로 사용하여 소행성을 생성함
	public Asteroid(Direction startDirection, Location startLoc, Location destLoc, int speed, Double[] points) {
		this.startDirection = startDirection;
		this.startLoc = startLoc;
		this.destLoc = destLoc;
		this.speed = speed;
		getPoints().addAll(points);
		setStroke(Color.LIGHTGRAY);
		setFill(null);
		setStrokeWidth(3);
	}
	
	public Location getStartLoc() {
		return startLoc;
	}
	public Location getDestLoc() {
		return destLoc;
	}
	public int getSpeed() {
		return speed;
	}
	public void setDestLoc(Location destLoc) {
		this.destLoc = destLoc;
	}
	
	public Asteroid[] explode(double translateX, double translateY) {
		Location centerLoc = new Location(startLoc.x()+(int)translateX,startLoc.y()+(int)translateY);
		Double[] points = new Double[getPoints().size()];
		getPoints().toArray(points);
		translatePoints(points, translateX, translateY);
		
		int leftHalfSize = points.length/2;
		if(leftHalfSize%2==1) ++leftHalfSize;
		int rightHalfSize = points.length-leftHalfSize;
		
		// 사각형이면 4점 중 3점을 이용하여 반을 생성함. 여기에 중점을 끝에 추가. 
		// 사각형, 마름모 등에서는 중점의 추가는 의미가 없지만 꼭지점의 수와 무관하게 처리하기 위함
		Double[] leftHalfPoints = Arrays.copyOf(points, leftHalfSize+4);
		Double[] rightHalfPoints = new Double[rightHalfSize+4];
		int i = leftHalfSize+2;
		leftHalfPoints[i++] = rightHalfPoints[0] = (double)centerLoc.x();
		leftHalfPoints[i] = rightHalfPoints[1] = (double)centerLoc.y();
		System.arraycopy(points, leftHalfSize, rightHalfPoints, 2, rightHalfSize);
		i = rightHalfSize+2;
		rightHalfPoints[i++] = points[0];
		rightHalfPoints[i] = points[1]; 
		
		Location[] destLocs = getNewDestinationLocs();
		Asteroid[] asteroids = new Asteroid[2];
		asteroids[0] = new Asteroid(startDirection, centerLoc, destLocs[0], speed, leftHalfPoints);
		asteroids[1] = new Asteroid(startDirection, centerLoc, destLocs[1], speed, rightHalfPoints);
		return asteroids;
	}
	
	private Location[] getNewDestinationLocs() {
		Location[] destLocs = new Location[2];
		switch(startDirection) {
		case NORTH, SOUTH:
			destLocs[0] = new Location(destLoc.x()+200, destLoc.y());
			destLocs[1] = new Location(destLoc.x()-200, destLoc.y());
			break;
		case EAST:
			destLocs[0] = new Location(startLoc.x(), destLoc.y()+200);
			destLocs[1] = new Location(destLoc.x(), destLoc.y()-200);
			break;
		default: // WEST
			destLocs[0] = new Location(destLoc.x(), destLoc.y()-200);
			destLocs[1] = new Location(startLoc.x(), destLoc.y()+200);
			break;
		}
		return destLocs;
	}
	
	private void translatePoints(Double[] points, double translateX, double translateY) {
		for(int i=0; i<points.length; i++) {
			points[i] += ((i%2==0)? translateX: translateY);
		}
	}
	
	@Override public boolean equals(Object other) {
		if(other==null||getClass()!=other.getClass()) return false;
		if(this==other) return true;
		Asteroid a = (Asteroid)other;
		return startLoc.equals(a.startLoc)&&
			destLoc.equals(a.destLoc)&&
			speed==a.speed;
	}
	
	public String getPoints(Double[] points) {
		String output = "[";
		for(var point: points) {
			output += String.format("%.1f, ", point);
		}
		output += "]";
		return output;
	}
}
