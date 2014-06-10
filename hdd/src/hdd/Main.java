package hdd;

import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) {
		String path=null;
		Scanner in=new Scanner(System.in);
		int c;
		ProcessFiles obj=new ProcessFiles();
		System.out.println("Enter path to search for duplicate files:");
		path=in.next();		
		
			obj.AddFiles(new File(path));
			obj.display();
		System.out.println("Do you want to delete the duplicate files??");
		
		while(true){
			System.out.println("1: Yes		0: No");
			c=in.nextInt();
			if(c==1)	{
				obj.delete_duplicate();
				break;
			}
			else if(c==0){
				System.out.println("Ok! Then Good Bye");
				break;
			}
			else	System.out.println("Invalid Entry...");
		}
	}
}
