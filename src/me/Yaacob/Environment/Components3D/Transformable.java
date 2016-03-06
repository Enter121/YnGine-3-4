package me.Yaacob.Environment.Components3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.dynamics.RigidBody;

import me.Yaacob.Utils.Transform;

public abstract class Transformable {
	public RigidBody r;



	public RigidBody getBody() {
		return r;
	}

	public void setBody(RigidBody r) {
		this.r = r;
	}


	public void setX(float x){
		com.bulletphysics.linearmath.Transform t=new com.bulletphysics.linearmath.Transform();
		t.origin.x=x;
		t.origin.y=r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.y;
		t.origin.z=r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.z;
		r.setWorldTransform(t);
	}
	
	public void setY(float y){
		com.bulletphysics.linearmath.Transform t=new com.bulletphysics.linearmath.Transform();
		t.origin.x=r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.x;
		t.origin.y=y;
		t.origin.z=r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.z;
		r.setWorldTransform(t);
	}

	public void setZ(float z){
		com.bulletphysics.linearmath.Transform t=new com.bulletphysics.linearmath.Transform();
		t.origin.x=r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.x;
		t.origin.y=r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.y;
		t.origin.z=z;
		r.setWorldTransform(t);
	}
	public void addX(float x){
		com.bulletphysics.linearmath.Transform t=r.getWorldTransform(new com.bulletphysics.linearmath.Transform());
		t.origin.x=t.origin.x+x;
		t.origin.y=t.origin.y;
		t.origin.z=t.origin.z;
		r.setWorldTransform(t);
	}
	public void addY(float y){
		com.bulletphysics.linearmath.Transform t=r.getWorldTransform(new com.bulletphysics.linearmath.Transform());
		t.origin.x=t.origin.x;
		t.origin.y=t.origin.y+y;
		t.origin.z=t.origin.z;
		r.setWorldTransform(t);
	}
	public void addZ(float z){
		com.bulletphysics.linearmath.Transform t=r.getWorldTransform(new com.bulletphysics.linearmath.Transform());
		t.origin.x=t.origin.x;
		t.origin.y=t.origin.y;
		t.origin.z=t.origin.z+z;
		r.setWorldTransform(t);
	}
	
	public float getX(){
		return r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.x;
	}
	public float getY(){
		return r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.y;
	}
	public float getZ(){
		return r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.z;
	}

	public void setPosition(Vector3f v){
		this.setX(v.x);
		this.setY(v.y);
		this.setZ(v.z);
	}
	public void addPosition(Vector3f v){
		this.addX(v.x);
		this.addY(v.y);
		this.addZ(v.z);
	}
	
	public Vector3f getPosition(){
		return new Vector3f(this.getX(),this.getY(),this.getZ());
	}
	public void setRotation(Quat4f q){
		r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).setRotation(q);
	}
	public javax.vecmath.Vector3f getRotation(){
		Quat4f q= r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).getRotation(new Quat4f());
		return new javax.vecmath.Vector3f(pitch(q.x,q.y,q.z,q.w),yaw(q.x,q.y,q.z,q.w),roll(q.x,q.y,q.z,q.w));
	}
	public float pitch(float x , float y , float z , float w) {
		return (float) Math.atan2(2.0 * (y * z + w * x), w * w - x * x - y * y
		+ z * z);
		}


		public float yaw(float x , float y , float z , float w) {
		return (float) ((float)Math.asin(-2.0 * (x * z - w * y)));
		}


		public float roll(float x , float y , float z , float w) {
		return (float) Math.atan2(2.0 * (x * y + w * z), w * w + x * x - y * y
		- z * z);
		}
}
