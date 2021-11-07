import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @copyright �ѱ�����������б� ��ǻ�Ͱ��к� ��ü���⼳��׽ǽ�
 * @version 2021�⵵ 2�б�
 * @author 2019136072 ������
 * 0,1,2,3���� ǥ�õǴ� ���� �־���
 * 0: ���
 * 1: ������ġ
 * 2: ����
 * 3: ��
 * ����: ������ġ���� ���������� �ִ� ��� ã�� 
 * �̵��� �����¿�θ� �� �� ����
 * �ʿ��� �׻� ������ �ϳ� �־���
 * ������ ã�� �� ������ �ִ� ����� ���̴� -1�� ����ؾ� 
 * �־��� �ذ�å���� �ڵ� ������ ã�� �����丵�ϼ���.
 * �� �ּ��� ������ �����Ͽ� �ּ���.
 * 
 * 1) �����丵�� ����
 * 	��. �� �Լ�
 * 	��. �����ϱ� ���� �̸�
 * 	��. ������ ���
 * 	��. �ڵ� �ߺ�
 * 	��. �� �Ű����� ���
 * 
 * 2) �� �ڵ� ������ �����ϱ� ���� �����丵�� ���
 * 	��. �� �Լ�: ���� ���� ���� �Լ��� �����Ͽ���. ���� ���� ���� ��ġ�� ã�� findFirtIndex �޼ҵ带 �������, ���Ŀ� �� ���� ��ġ�� �������� �ִ� �Ÿ��� ã�� shortestPath�Լ��� �������. 
 * 		�׸��� shortestPath �Լ� ������ 4���� ������ Ž���ϱ� ���� �ش� ��ġ�� ���� ����� �ʾҴ��� Ȯ���ϴ� isRange �޼ҵ带 �������.
 * 	��. �����ϱ� ���� �̸�: solve��� �̸��� ���ٸ� �� �޼ҵ尡 � å���� �ϴ� �� �� �𸣱� ������ findFirtIndex, shortestPath, isRange�� 
 * 		���� �޼ҵ� �̸��� ������ �� �� �޼ҵ��� å���� ������ �� �ֵ��� ����ߴ�.
 * 	��. ������ ���: �����丵�ϱ� ���� �ڵ忡���� rowQueue,colQueue�� �׻� ���� �����Ѵ�. �̿� ���� Location Ŭ������ ���� row�� col�� �����־���, 
 * 		lengthQueue�� �ᱹ ó�� ��ġ���� �ش� ��ġ������ �Ÿ��� ��Ÿ���Ƿ� Location Ŭ������ length�� �Բ� �����־���.
 * 	��. �ڵ� �ߺ�: 4���� ������ Ž���� �� if���� �ڵ尡 �ߺ��� �ȴ�. �̸� dx, dy ������ �̿��Ͽ� �ߺ��Ǵ� 4���� if���� for������ �ٿ��ش�. 
 * 		���� ��ġ���� 4���� ���⿡ �ش�Ǵ� ��ġ�� ã��, isRange�޼ҵ�� ������ �������� Ȯ���Ѵ�.
 * 	��. �� �Ű����� ���: shortestPath�� isRange �Լ��� �ش� ��ġ ��ǥ�� ���ڷ� �Ѱ��ִ� �޼ҵ��, row�� col�� Location���� �����־��� ������
 *  	Location�� �̿��Ͽ� ���ڸ� 3������ 2���� ���δ�. 
 * 
 */
public class Solution {

	private static boolean isRange(Location next, int[][] map) {
		return next.getRow() >= 0 && next.getRow() < map.length 
				&& next.getCol() >= 0 && next.getCol() < map[0].length;
	}
	
	private static int shortestPath(Location start, int[][] map) {
		// ���� ��ġ���� BFS�� �����Ͽ� ���� ��ġ���� ������ ã�´�.
		int minLength = -1;
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<Location> queue = new ArrayDeque<>();
		queue.add(new Location(start.getRow(), start.getCol(), start.getLength()));
		visited[start.getRow()][start.getCol()] = true;
		
		int[] dy = {1, -1, 0, 0};
		int[] dx = {0, 0, 1, -1};
		
		while(!queue.isEmpty()) {
			Location curr = queue.poll();
			for(int i = 0; i < 4; i++) { // 4�� �������� Ž�� ����
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
		// ���� ��ġ �߰�
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
