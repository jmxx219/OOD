import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 2019136072 손지민
 * 팩토리 메소드 패턴: Asteroid
 * AsteroidRectangleFactory.java: 직사각형 도형으로 소행성을 나타냄
 */
public class AsteroidRectangleFactory extends AsteroidFactory {

	@Override
	protected Double[] createPoints(Location centerLoc, int radius) {
		Double[] points = new Double[8];
		int width = radius;
		int height = radius;
		if(ThreadLocalRandom.current().nextBoolean()) 
			width += ThreadLocalRandom.current().nextInt(radius);
		if(ThreadLocalRandom.current().nextBoolean()) 
			height += ThreadLocalRandom.current().nextInt(radius);
		points[0] = Double.valueOf(centerLoc.x() - width);
		points[1] = Double.valueOf(centerLoc.y() - height);
		points[2] = Double.valueOf(centerLoc.x() + width);
		points[3] = Double.valueOf(centerLoc.y() - height);
		points[4] = Double.valueOf(centerLoc.x() + width);
		points[5] = Double.valueOf(centerLoc.y() + height);
		points[6] = Double.valueOf(centerLoc.x() - width);
		points[7] = Double.valueOf(centerLoc.y() + height);
		return points;
	}

}
