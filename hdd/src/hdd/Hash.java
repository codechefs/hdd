package hdd;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.security.*;

public class Hash{
	final int tab_size=97;
	Hash_node[] table=new Hash_node[tab_size];
	
	public Hash(){
		for(int i=0;i<tab_size;i++)
			table[i]=null;
	}
	
	protected void insert(File file){
		boolean flag=false;
		int index=h(file.getName());
		Hash_node working=table[index];
		Hash_node temp=null;
		MessageDigest md=null;
		DigestInputStream dis1=null;
		DigestInputStream dis2=null;
		byte[] md5Checksum1,md5Checksum2;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}
			
		while(working!=null){
			//System.out.println("Inside Loop: "+working.file.getName()+" "+file.getName());
			if(working.file.getName().compareTo(file.getName())==0){				
				if(working.file.length()==file.length())
				{
					while(working.same!=null)
						working=working.same;
					
					working.same=new Hash_node();
					working.same.file=file;
					flag=true;
					/*System.out.println("Same file found: "+working.file.getPath()+"  "+file.getPath());
					System.out.println("#############################");
					System.out.println(working.file.length());
					System.out.println(file.length());
					
					System.out.println("#############################");*/
					break;
				}
				/*else{
					System.out.println("Same name but different sizes");
					System.out.println(working.file.getPath()+"->"+working.file.length());
					System.out.println(file.getPath()+"->"+file.length());
					break;
				}*/
				
			}
			else
				working=working.next;
		}
		
		if(!flag){
			temp=table[index];
			working=new Hash_node();
			working.file=file;
			if(table[index]==null)	table[index]=working;
			else{
				while(temp.next!=null)
					temp=temp.next;
				temp.next=working;
			}
		}
	}
	
	private int h(String str){
		int hcode=str.hashCode();
		if(hcode<0)
			return(-hcode%tab_size);
		return (hcode%tab_size);
	}
	
	public void display(){
		Hash_node working=null,temp=null;
		int count=0;
		for(int i=0;i<tab_size;i++)
		{
			working=table[i];
			while(working!=null){
				if(working.same!=null){
					count++;
					temp=working;
					while(working!=null)
					{
						System.out.print("("+i+")"+working.file.getName()+"->" );
						working=working.same;
					}
					working=temp;
					System.out.println();
				}
				working=working.next;
			}
		}
		System.out.println(count);
	}
	
	public void delete_duplicate(){		
		Hash_node working=null,temp=null;
		int count=0;
		long size=0;
		for(int i=0;i<tab_size;i++)
		{
			working=table[i];
			while(working!=null){
				if(working.same!=null){
					temp=working;
					while(working.same!=null)
					{
						count++;
						System.out.print("File deleted: "+working.same.file.getPath());
						size+=working.same.file.length();
						System.out.println(working.same.file.delete());
						working=working.same;
					}
					working=temp;
					System.out.println();
				}
				working=working.next;
			}
		}
		System.out.println("Total deleted files: "+count);
		System.out.println("Total size freed: "+size);
	}
}
