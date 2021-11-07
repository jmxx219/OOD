import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * 0,1,2,3으로 표시되는 맵이 주어짐
 * 0: 통로
 * 1: 시작위치
 * 2: 보석
 * 3: 벽
 * 목적: 시작위치에서 보석까지의 최단 경로 찾기 
 * 이동은 상하좌우로만 할 수 있음
 * 맵에는 항상 보석이 하나 주어짐
 * 보석을 찾을 수 없으면 최단 경로의 길이는 -1을 출력해야 
 * 주어진 해결책에서 코드 냄세를 찾아 리펙토링하세요.
 * 이 주석에 다음을 포함하여 주세요.
 * 
 * 1) 리펙토링한 순서
 * 	ㄱ. 긴 함수
 * 	ㄴ. 이해하기 힘든 이름
 * 	ㄷ. 데이터 덩어리
 * 	ㄹ. 코드 중복
 * 	ㅁ. 긴 매개변수 목록
 * 
 * 2) 각 코드 냄새를 제거하기 위해 리펙토링한 방법
 * 	ㄱ. 긴 함수: 여러 개의 작은 함수로 분할하였다. 가장 먼저 시작 위치를 찾는 findFirtIndex 메소드를 만들었고, 이후에 이 시작 위치로 보석까지 최단 거리를 찾는 shortestPath함수를 만들었다. 
 * 		그리고 shortestPath 함수 내에서 4개의 방향을 탐색하기 전에 해당 위치가 맵을 벗어나지 않았는지 확인하는 isRange 메소드를 만들었다.
 * 	ㄴ. 이해하기 힘든 이름: solve라는 이름만 본다면 이 메소드가 어떤 책임을 하는 지 잘 모르기 때문에 findFirtIndex, shortestPath, isRange와 
 * 		같이 메소드 이름만 보았을 때 그 메소드의 책임을 이해할 수 있도록 명명했다.
 * 	ㄴ. 데이터 덩어리: 리팩토링하기 전의 코드에서는 rowQueue,colQueue는 항상 같이 등장한다. 이에 따라서 Location 클래스를 만들어서 row와 col을 묶어주었고, 
 * 		lengthQueue도 결국 처음 위치에서 해당 위치까지의 거리를 나타내므로 Location 클래스에 length도 함께 묶어주었다.
 * 	ㄷ. 코드 중복: 4개의 방향을 탐색할 때 if문의 코드가 중복이 된다. 이를 dx, dy 변수를 이용하여 중복되는 4개의 if문을 for문으로 줄여준다. 
 * 		현재 위치에서 4개의 방향에 해당되는 위치를 찾고, isRange메소드로 범위에 들어오는지 확인한다.
 * 	ㄹ. 긴 매개변수 목록: shortestPath나 isRange 함수는 해당 위치 좌표를 인자로 넘겨주는 메소드로, row와 col은 Location으로 묶어주었기 때문에
 *  	Location을 이용하여 인자를 3개에서 2개로 줄인다. 
 * 
 */
public class Solution {

	private static boolean isRange(Location next, int[][] map) {
		return next.getRow() >= 0 && next.getRow() < map.length 
				&& next.getCol() >= 0 && next.getCol() < map[0].length;
	}
	
	private static int shortestPath(Location start, int[][] map) {
		// 시작 위치부터 BFS를 수행하여 시작 위치부터 보석을 찾는다.
		int minLength = -1;
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<Location> queue = new ArrayDeque<>();
		queue.add(new Location(start.getRow(), start.getCol(), start.getLength()));
		visited[start.getRow()][start.getCol()] = true;
		
		int[] dy = {1, -1, 0, 0};
		int[] dx = {0, 0, 1, -1};
		
		while(!queue.isEmpty()) {
			Location curr = queue.poll();
			for(int i = 0; i < 4; i++) { // 4개 방향으로 탐색 진행
				int newR = curr.getRow() + dy[i];
				int newC = curr.getCol() + dx[i];
				int length = curr.getLength();
				if(isRange(new Location(newR, newC, 0), map) && !visited[newR][newC]) {
					if(map[newR][newC]==2) {
						minLength = length + 1;
						break;
					}
					else if(map[newR][newC]==0) {
						visited[newR][newC] = true;
						queue.add(new Location(newR, newC, length + 1));
					}
				}
			}
		}
		
		return minLength;
	}

	private static int findFirtIndex(int[][] map) {
		// 시작 위치 발견
		for(int r=0; r<map.length; ++r)
			for(int c=0; c<map[0].length; ++c) {
				if(map[r][c]==1) {
					return shortestPath(new Location(r, c, 0), map);
				}
			}
		return 0;
	}
	
	
	public static void main(String[] args) {
		int[][] map = new int[][]{
			{3,0,3,0,3,1,3},
			{3,0,0,0,3,0,3},
			{3,0,3,0,0,0,3},
			{3,0,3,3,3,0,3},
			{3,0,0,2,3,0,0},
			{3,3,3,3,3,3,3}
		};
		System.out.println(findFirtIndex(map));
		map = new int[][]{
			{3,3,3,0,3,0,3},
			{3,0,0,0,3,0,3},
			{1,0,3,0,3,0,3},
			{3,0,3,3,3,0,3},
			{3,0,3,2,0,0,0},
			{3,3,3,3,3,3,3}
		};
		System.out.println(findFirtIndex(map));
		map = new int[][]{
			{3,3,3,0,3,3,3},
			{3,0,0,0,3,2,3},
			{1,0,3,0,0,0,3},
			{3,0,3,3,3,0,3}
		};
		System.out.println(findFirtIndex(map));
	}
}
