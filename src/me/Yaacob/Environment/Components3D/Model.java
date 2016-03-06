package me.Yaacob.Environment.Components3D;

import java.io.File;
import java.util.ArrayList;

public class Model {
	
	public ArrayList<Element> e = new ArrayList<>();
	float[] vertices;
	float[] texcoord;
	float[] normals;
	public Model(){
		
	}
	ModelRenderer m;
	public Model(ModelRenderer m,File f){
		this.m=m;
		//OBJLoader.loadOBJ(f, this);
		YnLoader.loadYn(f, this);
	}
	
}
