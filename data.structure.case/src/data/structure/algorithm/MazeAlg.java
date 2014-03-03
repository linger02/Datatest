package data.structure.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MazeAlg {
	public static void main(String[] args)throws IOException{
		getPath(readData());
	}
	

	public static void getPath(int[][] data){
		int[][] largeData = new int[data.length+2][];
		
		int rowLen = data[0].length;
		int[] topRow = new int[rowLen + 2];
		int[] buttonRow = new int[rowLen + 2];
		for(int i=0;i<topRow.length;i++){
			topRow[i] = 1;
			buttonRow[i] = 1;
		}
		largeData[0] = topRow;
		largeData[largeData.length-1] = buttonRow;
		
		for(int i=1;i<largeData.length-1;i++){
			int[] row = new int[rowLen + 2];
			row[0] = 1;
			for(int j=1;j<row.length-1;j++){
				row[j] = data[i-1][j-1];
			}
			row[row.length-1] = 1;
			largeData[i] = row;
		}
		
		int startRow = 1;
		int startCol = 1;
		int endRow = largeData.length-2;
		int endCol = rowLen;
		
		Position[] offsets = new Position[4];
		offsets[0] = new Position(0,1);
		offsets[1] = new Position(1,0);
		offsets[2] = new Position(0,-1);
		offsets[3] = new Position(-1,0);
		
		largeData[startRow][startCol] = 1;
		List<Position> posList = new ArrayList<Position>();
		posList.add(new Position(startRow, endRow));
		int value = 1;
		
		while(true){			
			boolean flag = false;
			Position here = posList.get(0);
			System.out.println("here is:"+here.row + "," + here.col);
			value = largeData[here.row][here.col]+1;
			
			for(int i=0;i<offsets.length-1;i++){
				int row = here.row + offsets[i].row;
				int col = here.col + offsets[i].col;
				
				if((row==endRow) && (col==endCol)){
					flag  = true;
					break;
				}				
								
				if(largeData[row][col]==0){
					largeData[row][col] = value;
					Position pos = new Position(row,col);
					posList.add(pos);
				}
				
			}
			
			if(flag){
				break;
			}
			
			posList.remove(0);
			if(posList.isEmpty()){
				System.out.println("no available path,quit");
				return;
			}
			
		}
		
		Position end = new Position(endRow, endCol);
		int pathLen = value-2;
		List<Position> path = new ArrayList<Position>(pathLen);
		value--;
		
		Position here = end;
		for(int i=0;i<pathLen;i++){
			
			for(int j=0;j<offsets.length;j++){
				int row = here.row + offsets[j].row;
				int col = here.col + offsets[j].col;
				
				if(largeData[row][col]==value){
					Position pos = new Position(row, col);
					value--;
					path.add(pos);				
					here = pos; 
					break;
				}
			}
		}
		
		
		System.out.print("The path is:");
		for(int i=path.size()-1;i>=0;i--){
			System.out.print("(" + (path.get(i).row-1) + "," + (path.get(i).col-1) + "),");
		}
				
	}
	
	public static int[][] readData()throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(MazeAlg.class.getResourceAsStream("/data/structure/algorithm/maze.txt")));
		List<String> lines = new ArrayList<String>();
		String line = null;
		while((line=br.readLine())!=null){
			lines.add(line);
		}
		
		int[][] data = new int[lines.size()][];
		int i = 0;
		for(String ln:lines){
			String[] fields = ln.split(" ");
			int[] dataItem = new int[fields.length];
			int j = 0;
			for(String field: fields){
				dataItem[j++] = Integer.parseInt(field);
			}
			
			data[i++] = dataItem;
		}
		
		return data;		
	}
	
	
}

class Position{
	public int row;
	public int col;
	
	public Position(int row, int col){
		this.row = row;
		this.col = col;
	}
}
