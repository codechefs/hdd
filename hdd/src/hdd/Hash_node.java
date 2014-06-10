package hdd;

import java.io.*;

public class Hash_node {
	File file;
	Hash_node next,same;		//holds the reference of object with next same hash code of file
	
	public Hash_node(){
		file=null;
		next=null;
		same=null;
	}
}
