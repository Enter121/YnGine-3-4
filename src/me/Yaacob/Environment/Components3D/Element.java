package me.Yaacob.Environment.Components3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.newdawn.slick.opengl.Texture;

import com.bulletphysics.dynamics.RigidBody;

public class Element {

public Material m;
public 	int vertnum;
	int id;
	Texture t;
	me.Yaacob.Graphics.Texture nt;
	Texture n;
	me.Yaacob.Graphics.Texture nn;
	
	public 	boolean tang=false;
	public 	boolean tex=false;
	public float[] tangents=new float[0];
	public int[] indices=new int[0];
	public float fpoint;
	
	public int getVertexNum(){
		return indices.length;
	}
	int mode=-1;
	public int getId(){
		return id;
	}
	ModelRenderer mr;
	Model mo;
	public Element(ModelRenderer mr,Model mo){
	this.mr=mr;
	this.mo=mo;
	}
	public void load(){
		if(tex && tang){
			id=	mr.loadVAO(mo.vertices, mo.texcoord, mo.normals, indices, tangents, this);
			mode=0;
			}else if(tex && !tang){
				id=	mr.loadVAO(mo.vertices, mo.texcoord, mo.normals, indices, this);
				mode=1;
			}else{
				id=	mr.loadVAO(mo.vertices, mo.normals, indices, this);
				mode=2;
			}
	}
	
}
