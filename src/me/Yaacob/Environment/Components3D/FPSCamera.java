package me.Yaacob.Environment.Components3D;


import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.DefaultMotionState;

import me.Yaacob.Core.EngineManager;

public class FPSCamera extends Camera{
	

	 CollisionShape shape;
	public FPSCamera(Vector3f pos, Vector3f rot, CollisionShape shape) {
		t.setPosition(pos);
		t.setRotation(rot);
		this.shape=shape;
		loadPhysics(false,0.5f);
		// TODO Auto-generated constructor stub
	}
	
	float zoff=0;
	public FPSCamera() {
		// TODO Auto-generated constructor stub
	}
	boolean forw=false,backw=false,up=false,down=false,right=false,left=false;
	
	Vector3f position;
	
	float speed=0.1f;
	RigidBody body;
	
	public float pitch=0 , yaw=0 , roll=0;
	public float sensitivity=0.005f;
	
	
	
	public boolean isForward() {
		return forw;
	}

	public void setForward(boolean forw) {
		this.forw = forw;
	}

	public boolean isBackward() {
		return backw;
	}

	public void setBackward(boolean backw) {
		this.backw = backw;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}



	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	public void addPitch(float pitch) {
		this.pitch += pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void addYaw(float yaw) {
		this.yaw += yaw;
	}

	


public void update(){
	

	body.setLinearVelocity(new javax.vecmath.Vector3f(0,body.getLinearVelocity(new javax.vecmath.Vector3f()).y,0));
			if(backw){
			this.addX(-speed*(float)Math.sin(yaw));
		//	this.addY(-speed*(float)Math.tan(pitch));
			this.addZ(speed*(float)Math.cos(yaw));
			
			
		}
		if(forw){
			
			this.addX(speed*(float)Math.sin(yaw));
		//	this.addY(speed*(float)Math.tan(pitch));
			this.addZ(-speed*(float)Math.cos(yaw));
		}
		
		if(up){
			this.addY(speed);
		}
		if(down){
			this.addY(-speed);
		}
		if(right){
			this.addX(-speed *(float)Math.sin(yaw-Math.toRadians(90)));
			this.addZ(speed *(float)Math.cos(yaw-Math.toRadians(90)));
		}
		if(left){
			this.addX(-speed *(float)Math.sin(yaw+Math.toRadians(90)));
			this.addZ(speed *(float)Math.cos(yaw+Math.toRadians(90)));
		}
		float p=Mouse.getDY() *sensitivity;
		if(Math.toDegrees(pitch)>=80 && p>0){
				p=0;
			
		}


		if(Math.toDegrees(pitch)<=-80 && p<0){
			p=0;
		
	}
	
float ym=Mouse.getDX() *sensitivity;
		this.addYaw(ym);
		this.addPitch(p);
//		if(ym<0){
//			zoff+=10;
//		}
//		if(ym>0){
//			zoff-=10;
//		}
//		
//		if(zoff>0){
//			zoff-=5;
//		}
//		if(zoff<0){
//			zoff+=5;
//		}
		t.setRotation(new Vector3f(-pitch,	yaw , roll));
	}
	
	public void loadPhysics(boolean STATIC, float mass){
		Physics p=EngineManager.p;
			RigidBody body;

			
			
				 javax.vecmath.Vector3f inertia = new javax.vecmath.Vector3f();
				 // shape=new BoxShape(new javax.vecmath.Vector3f(5f,5f,5f));
					
				 DefaultMotionState motionState;
						motionState = new DefaultMotionState(new com.bulletphysics.linearmath.Transform(new Matrix4f(new Quat4f(0, 0, 0, 1),new javax.vecmath.Vector3f(t.getPosition().x,t.getPosition().y,t.getPosition().z),1f)));
						
					
						this.shape.calculateLocalInertia(mass, inertia);
						
						if(!STATIC){
						 body = new RigidBody(mass, motionState, shape,inertia);
						 
						 body.setDamping(0.5F, 0.8F);
			
					p.world.addRigidBody(body);
					
					p.addObject(this);
					
				r=body;
						}else{
						     
			//shape=new BvhTriangleMeshShape();
			//TODO
			
						      body = new RigidBody(0, motionState, shape);
							 body.setRestitution(0.25f);

							 body.setDamping(0.5F, 0.8F);
				
						p.world.addRigidBody(body);
						p.addObject(this);
						
							r=body;
						}
						body.setAngularFactor(0);
						body.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
						this.body=body;
			

		}
	
}
