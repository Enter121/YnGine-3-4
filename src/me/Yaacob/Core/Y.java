package me.Yaacob.Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.lwjgl.util.vector.Vector3f;

import me.Yaacob.Environment.Components3D.Physics;

public final class Y {
	 
	 
	public static String readFile(File f){
		String content="";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			System.err.println(Y.prefix+"FILE NOT FOUND");
		}
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		    while (line != null) {
		        
		    	sb.append(line);
		    	sb.append(System.getProperty("line.separator"));
		    	try {
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    content = sb.toString();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		return content;
	}
	
	public static void writeFile(File f , String content){
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(f);
			writer.print(content);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static final String version="3.2.0";
	public static final String type="Alpha";
	public static final String build="#4";
	public static final String author="Yaacob";
	public static final String prefix="[YnGine] ";
	public static void print(String str){
		System.out.printf(Y.prefix+str+"\n");
	}
	public static void print(float str){
		System.out.printf(Y.prefix+str+"\n");
	}
	
	public String getVersion(){
		return new String("Version: "+version+" "+type+" Build: "+build);
	}
	public String getAuthor(){
		return new String(author);
	}
}
