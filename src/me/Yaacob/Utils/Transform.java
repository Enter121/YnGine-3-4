package me.Yaacob.Utils;

import org.lwjgl.util.vector.Vector3f;

public abstract class Transform {
	Vector3f pos;
	Vector3f rot;
	Vector3f scale;
	public Transform(Vector3f pos, Vector3f rot, Vector3f scale) {
		super();
		this.pos = pos;
		this.rot = rot;
		this.scale = scale;
	}
	public Vector3f getPosition() {
		return pos;
	}
	public void setPosition(Vector3f pos) {
		this.pos = pos;
	}
	public Vector3f getRotation() {
		return rot;
	}
	public void setRotation(Vector3f rot) {
		this.rot = rot;
	}
	public void setRotation(javax.vecmath.Vector3f rot) {
		this.rot = new Vector3f(rot.x,rot.y,rot.z);
	}
	public Vector3f getScale() {
		return scale;
	}
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	public void addPosition(Vector3f v){
		this.pos.x+=v.x;
		this.pos.y+=v.y;
		this.pos.z+=v.z;
		
	}
	
	public void addRotation(Vector3f v){
		this.rot.x+=v.x;
		this.rot.y+=v.y;
		this.rot.z+=v.z;
		
	}
	
	public static Vector3f X(){
		return new Vector3f(1,0,0);
	}
	public static Vector3f Y(){
		return new Vector3f(0,1,0);
	}
	public static Vector3f Z(){
		return new Vector3f(0,0,1);
	}
	
	
}
