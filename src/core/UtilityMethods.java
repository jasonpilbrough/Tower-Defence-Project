package core;

import java.util.ArrayList;

public class UtilityMethods {
	
	public static int getArrayLength(ArrayList array){
    	return array.size();
	}
	
	public static int getArrayLength(Object[] array){
		int tempCount=0;
    	for (int i = 0; i < array.length; i++) {
			if(array[i]!=null){
				tempCount++;
			}else{
				break;
			}
		}
    	return tempCount;
	}
	
	

}
