import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 2019136072 손지민
 * 팩토리 메소드 패턴: Asteroid
 * AsteroidPolygonFactory.java: 다각형 도형으로 소행성을 나타냄
 */
public class AsteroidPolygonFactory extends AsteroidFactory {

	@Override
	protected Double[] createPoints(Location centerLoc, int radius) {
		int pointCnt = 10 + ThreadLocalRandom.current().nextInt(6);
		Double[] points = new Double[pointCnt * 2];
		double x = 0;
		double y = 0;
		double angle = 360 / pointCnt;
		for(int i=0; i<pointCnt; i++) {
			x = Math.cos(Math.toRadians(angle * i)) * (radius + ThreadLocalRandom.current().nextInt(radius));
			y = Math.sin(Math.toRadians(angle * i)) * (radius + ThreadLocalRandom.current().nextInt(radius));
			points[i * 2] = Double.valueOf(centerLoc.x() + x);
			points[i * 2 + 1] = Double.valueOf(centerLoc.y() + y);
		}
		
		return points;
	}

}
