package data.structure.algorithm;

import java.util.ArrayList;
import java.util.List;

public class SortAlgorithm{

	public static <E> void mergeSort(List<E> array){
		int size = array.size();
		int half = size/2;
		if(half>1){
			List<E> firstPart = new ArrayList<E>();
			for(int i=0;i<half;i++){
				firstPart.add(array.get(i));
			}
			mergeSort(firstPart);
			
			List<E> lastPart = new ArrayList<E>();			
			for(int i=half;i<array.size();i++){
				lastPart.add(array.get(i));
			}			
			mergeSort(lastPart);
		}else{
			
			
			
		}
		
	}
}
