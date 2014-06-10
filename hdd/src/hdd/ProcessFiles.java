package hdd;
import java.io.*;

public class ProcessFiles extends Hash {
	
	public ProcessFiles(){
		super();										//Call to super class constructor
	}
	
	public void AddFiles(final File folder){
		for (final File fileEntry : folder.listFiles()) {
			//System.out.println(fileEntry.toString());
	        if (fileEntry.isDirectory()) {
	        	
	        	int c=(int)fileEntry.getName().charAt(0);
	        	if((c>=65 && c<=90) || (c>=97 && c<=122) || (c>=48 && c<=57))
	        		AddFiles(fileEntry);					//Recursive Call	
	        }
	        else {
	        	int c=(int)fileEntry.getName().charAt(0);
	        	if((c>=65 && c<=90) || (c>=97 && c<=122) || (c>=48 && c<=57))
	        		insert(fileEntry);	//Inherited function
	        }
	    }
	}
}
