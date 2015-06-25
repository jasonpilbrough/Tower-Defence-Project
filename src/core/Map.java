package core;

import java.awt.Graphics;
import java.io.File;
import java.util.Scanner;

public class Map {
	private Block[][] blockArray;
	private int numRows,numCols;
	private Block endBlock;
	
	 public Map() {
	        try {
	            Scanner s = new Scanner(new File("map2.txt"));
	            String line = s.nextLine();
	            String[] splits = line.split(",");
	            numRows = Integer.parseInt(splits[0]);
	            numCols = Integer.parseInt(splits[1]);
	            blockArray = new Block[numRows][numCols];
	            Block.setWidth(30);
	            Block.setHeight(30);
	                
	                for (int r = 0; r < numRows; r++) {
	                         String line2 = s.nextLine();
	                         String[] splits2 = line2.split(",");
	                    for (int c = 0; c < numCols; c++) {                      
	                          
	                          if(splits2[c].equals("1")){
	                        	  blockArray[r][c] = new Block(true, Integer.parseInt(splits2[c]), r, c); 
	                          }else if(splits2[c].equals("2")){
	                        	  blockArray[r][c] = new Block(true, Integer.parseInt(splits2[c]), r, c);
	                             
	                          }else if(splits2[c].equals("3")){
	                        	  blockArray[r][c] = new Block(true, Integer.parseInt(splits2[c]), r, c);
	                        	  endBlock=blockArray[r][c];
	                              
	                          }
	                          else{
	                        	  blockArray[r][c] = new Block(false, Integer.parseInt(splits2[c]), r, c);
	                          }
	                            
	                    }
	                }                     
	        } catch (Exception e) {
	            System.out.println("Something went wrong");
	        }

	        
	        determineBlockOrientation();
	    }
	 
	 public void paint(Graphics g) {
	        for (int r = 0; r < numRows; r++) {
	            for (int c = 0; c < numCols; c++) {
	            	blockArray[r][c].paint(g,getStartDirection(),getFinishDirection());
	            }
	        }
	    }
	 
	 public int getStartDirection(){
	    	Block[] temparr = getPath();
	    	int answer = 0;
	    	int tempCount=UtilityMethods.getArrayLength(temparr);
	    	

	    			
	    	
	    	if(temparr[1].getCol() > temparr[0].getCol()){           
	    		answer=90;  
	        }else if(temparr[1].getCol() < temparr[0].getCol()){           
	        	answer=270;  
	        }
	        else if(temparr[1].getRow() < temparr[0].getRow()){           
	        	answer=180;  
	        }
	        else if(temparr[1].getRow() > temparr[0].getRow()){           
	        	answer=0;  
	        }
      
	        
	    	return answer;
	    }
	 
	 public int getFinishDirection(){
		 Block[] temparr = getPath();
	    	int answer = 0;
	    	int tempCount=UtilityMethods.getArrayLength(temparr);
	    	
	    	if(temparr[tempCount-1].getCol() > temparr[tempCount-2].getCol()){           
	    		answer=270;  
	        }else if(temparr[tempCount-1].getCol() < temparr[tempCount-2].getCol()){           
	        	answer=90;  
	        }
	        else if(temparr[tempCount-1].getRow() < temparr[tempCount-2].getRow()){           
	        	answer=0;  
	        }
	        else if(temparr[tempCount-1].getRow() > temparr[tempCount-2].getRow()){           
	        	answer=180;  
	        }

	    	return answer;
	 }
	 
	 public void determineBlockOrientation(){
	    	Block[] temparr = getPath();
	    	int tempCount=UtilityMethods.getArrayLength(temparr);
	    	boolean[]bb = new boolean[4];
	    	
	    	if(getStartDirection()==0){

	    		bb=new boolean[]{true,false,false,false};
	    	}else if(getStartDirection()==90){

	    		bb=new boolean[]{false,true,false,false};
	    	}else if(getStartDirection()==180){

	    		bb=new boolean[]{false,false,true,false};
	    	}else if(getStartDirection()==270){

	    		bb=new boolean[]{false,false,false,true};
	    	}
	    	temparr[0].setBorderOrientation(bb);
	    	
	    	if(getFinishDirection()==0){
	    		bb=new boolean[]{true,false,false,false};
	    	}else if(getFinishDirection()==90){
	    		
	    		bb=new boolean[]{false,true,false,false};
	    	}else if(getFinishDirection()==180){
	    		
	    		bb=new boolean[]{false,false,true,false};
	    	}else if(getFinishDirection()==270){
	    		
	    		bb=new boolean[]{false,false,false,true};
	    	}
	    	temparr[tempCount-1].setBorderOrientation(bb);
	    	
	    	for (int i = 1; i < tempCount-1; i++) {
	    		boolean[]b = new boolean[4];
	    		if(temparr[i].getCol() > temparr[i-1].getCol()){         
	    			//JOptionPane.showMessageDialog(null, "Working");
	                b[3]=true;
	            }
	    		else if(temparr[i].getCol() < temparr[i-1].getCol()){           
	    			b[1]=true;
	            }
	    		else if(temparr[i].getRow() < temparr[i-1].getRow()){           
	            	b[0]=true;  
	            }
	    		else if(temparr[i].getRow() > temparr[i-1].getRow()){           
	            	b[2]=true;
	            }
	    		
	    		if(temparr[i].getCol() > temparr[i+1].getCol()){           
	                b[3]=true;
	            }
	    		else if(temparr[i].getCol() < temparr[i+1].getCol()){           
	    			b[1]=true;
	            }
	    		else if(temparr[i].getRow() < temparr[i+1].getRow()){           
	            	b[0]=true;  
	            }
	    		else if(temparr[i].getRow() > temparr[i+1].getRow()){           
	            	b[2]=true;
	            }
	    		temparr[i].setBorderOrientation(b);
	    		
			}
	    	
	    }
	 
	    public Block[] getPath(){
	        Block[] temparr = new Block[200];
	        int tempCount=0;
	        for (int r = 0; r < numRows; r++) {
	            for (int c = 0; c < numCols; c++) {
	                if(blockArray[r][c].getTerrain()==2){
	                    temparr[tempCount++]=blockArray[r][c];
	                    break;
	                }
	            }
	        }
	        
	        
	        
	        if(blockArray[temparr[tempCount-1].getRow()-1][temparr[tempCount-1].getCol()].isTraversable()
	                &&!temparr[tempCount-1].equals( blockArray[temparr[tempCount-1].getRow()-1][temparr[tempCount-1].getCol()])
	                ){
	            temparr[tempCount]=blockArray[temparr[tempCount-1].getRow()-1][temparr[tempCount-1].getCol()];  
	          
	            tempCount++; 
	        }
	        
	        
	        else if(blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()+1].isTraversable()
	        		&&!temparr[tempCount-1].equals( blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()+1])
	                        ){
	             temparr[tempCount]=blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()+1];
	         
	             tempCount++;
	        }
	        else if(blockArray[temparr[tempCount-1].getRow()+1][temparr[tempCount-1].getCol()].isTraversable()
	        		&&!temparr[tempCount-1].equals(  blockArray[temparr[tempCount-1].getRow()+1][temparr[tempCount-1].getCol()])
	                        ){
	             temparr[tempCount]=blockArray[temparr[tempCount-1].getRow()+1][temparr[tempCount-1].getCol()];
	            
	             tempCount++;
	        }
	        else if(blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()-1].isTraversable()
	        		&&!temparr[tempCount-1].equals( blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()-1])
	                        ){
	             temparr[tempCount]=blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()-1];
	            
	             tempCount++;           
	        }

	        while(true){       	
	            if(blockArray[temparr[tempCount-1].getRow()-1][temparr[tempCount-1].getCol()].isTraversable()
	            		 &&!temparr[tempCount-2].equals( blockArray[temparr[tempCount-1].getRow()-1][temparr[tempCount-1].getCol()])
	                    ){
	                temparr[tempCount]=blockArray[temparr[tempCount-1].getRow()-1][temparr[tempCount-1].getCol()];
	                if(temparr[tempCount].equals(endBlock)){
	                    break;
	                }
	                tempCount++; 
	            }
	            else if(blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()+1].isTraversable()
	            		&&!temparr[tempCount-2].equals( blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()+1])
	                            ){
	                 temparr[tempCount]=blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()+1];
	                 if(temparr[tempCount].equals(endBlock)){
	                    break;
	                }
	                tempCount++;  
	            }
	            else if(blockArray[temparr[tempCount-1].getRow()+1][temparr[tempCount-1].getCol()].isTraversable()
	            		&&!temparr[tempCount-2].equals(  blockArray[temparr[tempCount-1].getRow()+1][temparr[tempCount-1].getCol()])
	                            ){
	                 temparr[tempCount]=blockArray[temparr[tempCount-1].getRow()+1][temparr[tempCount-1].getCol()];
	                 if(temparr[tempCount].equals(endBlock)){
	                    break;
	                }
	                tempCount++;
	                
	            }
	            else if(blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()-1].isTraversable()
	            		&&!temparr[tempCount-2].equals( blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()-1])
	                            ){
	                 temparr[tempCount]=blockArray[temparr[tempCount-1].getRow()][temparr[tempCount-1].getCol()-1];
	                 if(temparr[tempCount].equals(endBlock)){
	                    break;
	                }
	                tempCount++; 
	            }
	        }
	       // JOptionPane.showMessageDialog(null,temparr);
	        return temparr;
	    }

}
