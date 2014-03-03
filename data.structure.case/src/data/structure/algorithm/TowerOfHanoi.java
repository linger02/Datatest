package data.structure.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

public class TowerOfHanoi {
	private Deque<Integer> startTower = null;
	private Deque<Integer> endTower = null;
	private Deque<Integer> mediateTower = null;
	
	private static int DISK_COUNT = 16;
	public static void main(String[] args){
		TowerOfHanoi sort = new TowerOfHanoi();
		sort.move();
	}
	
	public TowerOfHanoi(){
		startTower = new ArrayDeque<Integer>();
		mediateTower = new ArrayDeque<Integer>();
		endTower = new ArrayDeque<Integer>();
		
		for(int i=DISK_COUNT;i>0;i--){
			startTower.push(i);
		}
	}
	
	public void move(){
		System.out.println(startTower.toString()+ ", and top is "+ startTower.peek());
		System.out.println(endTower.toString()+ ", and top is "+ endTower.peek());
		System.out.println(mediateTower.toString()+ ", and top is "+ mediateTower.peek());
		System.out.println();
		System.out.println("*********************The result towers***********************");
		this.moveDisk(startTower.size(),startTower,endTower,mediateTower);
		System.out.println(startTower.toString()+ ", and top is "+ startTower.peek());
		System.out.println(endTower.toString()+ ", and top is "+ endTower.peek());
		System.out.println(mediateTower.toString()+ ", and top is "+ mediateTower.peek());
	}
	
	private void moveDisk(int n, Deque<Integer> start, Deque<Integer> end, Deque<Integer> mediate){
		if(n>0){
			moveDisk(n-1, start, mediate,end);

			int top = start.pop();
			end.push(top);
			
			moveDisk(n-1, mediate, end,start);
		}
	}
}
