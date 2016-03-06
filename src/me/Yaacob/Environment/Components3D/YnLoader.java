package me.Yaacob.Environment.Components3D;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.TextureLoader;

import me.Yaacob.Core.TextureType;
import me.Yaacob.Core.YamlFile;
import me.Yaacob.Graphics.Texture;

public class YnLoader {

	
	
	@SuppressWarnings("unchecked")
	public static void loadYn(File f , Model m){
		YamlFile y=new YamlFile(f);
		
		int objects=(int)y.get("Elements");
		m.vertices=toFloatArray((ArrayList<Double>)y.get("vertices"));
		m.texcoord=toFloatArray((ArrayList<Double>)y.get("textureCoords"));
		m.normals=toFloatArray((ArrayList<Double>)y.get("normals"));

		for(int i=0;i!=objects;i++){
		Element e=new Element(m.m,m);
		e.tangents=toFloatArray((ArrayList<Double>)y.get("e."+i+".tangents"));
		e.indices=toIntArray((ArrayList<Integer>)y.get("e."+i+".indices"));
		
		
		
		try {
			

			
			if(!y.get("e."+i+".texture").equals("")){
				if(ModelRenderer.type==TextureType.POT){
					e.t=TextureLoader.getTexture(((String)y.get("e."+i+".texture")).replace(",", ";").split(";")[1].toUpperCase(), new FileInputStream((String) y.get("e."+i+".texture")));
				}else{
					e.nt=new Texture(new File((String) y.get("e."+i+".texture")));
				}
				e.tex=true;
			}
			
		if(!y.get("e."+i+".bump").equals("")){
			
			if(ModelRenderer.type==TextureType.POT){
				e.n=TextureLoader.getTexture(((String)y.get("e."+i+".bump")).replace(",", ";").split(";")[1].toUpperCase(), new FileInputStream((String) y.get("e."+i+".bump")));
			}else{
				e.nn=new Texture(new File((String) y.get("e."+i+".bump")));
			}
			e.tang=true;
		}
		
			
		
			
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		e.load();
		m.e.add(e);
	}
	}
	
	
	public static float[] toFloatArray(ArrayList<Double> f){
		float[] fl=new float[f.size()];
		for(int i=0;i!=f.size();i++){
			fl[i]=f.get(i).floatValue();
		}
		return fl;
	}
	
	
	public static float[] Vec3toFloatArray(ArrayList<Vector3f> f){
		float[] fl=new float[f.size()*3];
		
		for(int i=0;i!=f.size();i++){
			fl[i]=f.get(i).x;
			fl[i+1]=f.get(i).y;
			fl[i+2]=f.get(i).z;
		}
		
		return fl;
	}
	public static float[] Vec2toFloatArray(ArrayList<Vector2f> f){
		float[] fl=new float[f.size()*2];
		
		for(int i=0;i!=f.size();i++){
			fl[i]=f.get(i).x;
			fl[i+1]=f.get(i).y;
		}
		
		return fl;
	}
	public static int[] toIntArray(ArrayList<Integer> f){
		int[] fl=new int[f.size()];
		
		for(int i=0;i!=f.size();i++){
			fl[i]=f.get(i);
		}
		
		return fl;
	}
}
