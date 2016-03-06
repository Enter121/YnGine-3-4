package me.Yaacob.Environment.Components2D;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import me.Yaacob.Core.EngineManager;

public class Physics2D {

	EngineManager m;
	
	Animation anim;
	boolean a=false;
	Body body=null;
	
	
	ArrayList<GameObject2D> go = new ArrayList<>();
	World world;
	Vec2  gravity;
	
	float timeStep;
	 int velocityIterations;
	 int positionIterations;
	public Physics2D(EngineManager m , Vec2 gravity , boolean sleep){
		this.m=m;
		  timeStep = 1f/20f;
		  velocityIterations = 8;
		   positionIterations = 3;
		   this.gravity = gravity;
		   world = new World(gravity, sleep);
	
		   
	}
	public Physics2D(EngineManager m,boolean sleep){
		this.m=m;
		  timeStep = 1f/20f;
		  velocityIterations = 8;
		   positionIterations = 3;
		   this.gravity = new Vec2(0,-20f);
		   world = new World(gravity,sleep);
	
		  
		   
		   
	
	
	}
	
	
	public void addObject(GameObject2D go){
		this.go.add(go);
	}
	public void removeObject(GameObject2D go){
		this.go.remove(go);
	}
	
	
	public void update(){
		//timeStep = 1.0f/m.getCurrentFPS();
		//for(int i=0;i!=5;i++){
	
		 world.step(timeStep, velocityIterations, positionIterations);
	       for(GameObject2D go: go){
	    	  
	    	   Body body = go.body;
	    	   
	    	   float angle = body.getAngle();
	    	  go.x=(float) body.getPosition().x;
	    	  go.y=(float) body.getPosition().y;
	    			  
	    	  go.rotation=(float) Math.toDegrees(angle);
	    	   
	       }
	     }
	
	
	
	}


	
	
//}
