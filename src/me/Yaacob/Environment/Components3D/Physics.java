package me.Yaacob.Environment.Components3D;

import java.util.ArrayList;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;

import org.lwjgl.util.vector.Vector3f;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.QuaternionUtil;
import com.bulletphysics.linearmath.Transform;

import me.Yaacob.Core.EngineManager;

public class Physics {

	public DynamicsWorld world;
	
	public static ArrayList<Transformable> objects = new ArrayList<>();
	
	 private final Transform transform = new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new javax.vecmath.Vector3f(0, 0, 0), 1.0f));

	 public Transform getTransform(){
		 return transform;
	 }
	 
	 EngineManager m;
		
	public Physics(EngineManager m , Vector3f gravity){
		this.m=m;
		BroadphaseInterface bp = new DbvtBroadphase();
		CollisionConfiguration cc = new DefaultCollisionConfiguration();
		CollisionDispatcher disp = new CollisionDispatcher(cc);
		 ConstraintSolver solver = new SequentialImpulseConstraintSolver();
		 world = new DiscreteDynamicsWorld(disp,bp,solver,cc);
		 world.setGravity(new javax.vecmath.Vector3f(gravity.x,gravity.y,gravity.z));

	}
	
	public void addObject(Transformable t){
		this.objects.add(t);
	}
	
	public void update(){
		world.stepSimulation(1f/60f,10);

			
		
	}
	
}
