package me.Yaacob.Core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlFile {
		HashMap<String, Object> data;
	   ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> value = new ArrayList<String>();
	File f;
	Yaml yaml;
	@SuppressWarnings("unchecked")
	public YamlFile(File f){
		this.f=f;
		data = new HashMap<String, Object>();
		  yaml = new Yaml();

		  
		  if(!f.exists()){
			 try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
		  
			Y.writeFile(f, "{}");
		  }else  if( Y.readFile(f).equals("")){
			  Y.writeFile(f, "{}");
		  }
		  
			  data = (HashMap< String, Object>) yaml.load(Y.readFile(f));
	
	}
	
	public Object get(String path){
		return data.get(path);
	}
	 
	public void set(String path ,Object value){
		if(value!=null){
			data.put(path, value);
		}else{
			data.remove(path, value);
		}
	}
	
	public void save(){
		try {
			FileWriter writer=new FileWriter(f);
			
			yaml.dump(data,writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
