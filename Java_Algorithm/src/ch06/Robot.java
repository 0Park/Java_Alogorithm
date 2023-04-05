package ch06;

import java.lang.runtime.SwitchBootstraps;
import java.util.Stack;

public class Robot {
	public static int NUMDIRECTIONS = 4;
	public static int WIDTH = 8;
	public static int HEIGHT = 8;

	Stack<Move> stack = new Stack<Move>();
	Move Move;
	Maze maze = new Maze();
	
	public int[][] DIRECTION_OFFSETS = {
			{0,-1}, // 위쪽으로 이동 
			{1,0}, // 오른쪽으로 이동
			{0,1}, // 아래쪽으로 이동
			{-1,0} // 왼쪽으로이동
	};
	
	public static int NOTVISIT =0;
	public static int WALL =1;
	public static int VISIT = 2;
	int [][] markArray = new int[8][8];
	
	public void findPath(int startX, int startY, int endX, int endY) {
		boolean isEmpty = false;
		boolean isFound = false;
		
		Move start = new Move(startX,startY);
		
		start.direction=0;
		stack.push(start);
// 도착지에 도착하지 않은 상태 + stack이 채워지지 않았을때 + 방향을 전부 돌았을 때
		while(isEmpty == false && isFound == false) {
//			다시 뒤로 백하는 경우
			Move curPos = stack.pop();
			int x =curPos.x;
			int y =curPos.y;
			int direction = curPos.direction;
//			도착지에 도착하지 않은 상태 + 방향이 한 바퀴돌지 않은 상태
			while(isFound == false && direction < NUMDIRECTIONS) {
				int newX= x + DIRECTION_OFFSETS[direction][0];
				int newY= y + DIRECTION_OFFSETS[direction][1];
//				새로운 좌표가 미로 안에 있고,  미로에서 길이고,  방문하지 않은 곳일 때
				if(newX >= 0 && newX < WIDTH && newY >=0 && newY < HEIGHT 
						&& maze.myMaze[newY][newX] == NOTVISIT && markArray[newY][newX]== NOTVISIT) {
//					새로운 위치 업데이트
					Move newPosition = new Move(newX,newY);
					newPosition.direction = direction + 1;
					stack.push(newPosition);
					markArray[y][x] = VISIT;
//					새로운 위치 현재위치로 업데이트
					x = newX;
					y = newY;
					direction=0;
//					목적지 도착
					if(newX== endX && newY == endY) {
						isFound =true;
					}
				}
//				갈곳없어 방향만 전환
				else direction++;
			} // end of while
;			isEmpty = stack.isEmpty();
		} //end of while
	}
	
	public void showPath() {
		int [][] resultArray = new int[8][8];
		boolean isEmpty = false;
		
		for(int i=0; i< HEIGHT;i++) {
			for(int j=0;j<WIDTH;j++) {
				resultArray[i][j] = maze.myMaze[i][j];
			}
		}
		
		for(int i=0; i<HEIGHT;i++) {
			for(int j=0; j < WIDTH ; j++) {
				if(maze.myMaze[i][j] == WALL) {
					System.out.print("*");
				}
				else if(markArray[i][j]==VISIT) {
					System.out.print("|");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
		
		int i=0;
		while(isEmpty==false) {
			Move move = stack.pop();
			int x = move.x;
			int y= move.y;
			resultArray[y][x]=VISIT;
			
			if( i >0) {
				System.out.print("<-");
			}
			System.out.print("("+x+","+y+")");
			i++;
			isEmpty = stack.isEmpty();
		}
		System.out.println();
	}
}
