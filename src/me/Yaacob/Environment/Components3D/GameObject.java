package me.Yaacob.Environment.Components3D;

import java.util.ArrayList;

import me.Yaacob.Core.EngineManager;
import me.Yaacob.Utils.Transform;

public class GameObject {

 float reflectivity=1f;
 float shineDamper=10f;

 public ArrayList<PhysicsElement> e = new ArrayList<>();

public float getReflectivity() {
	return reflectivity;
}
public float getShineDamper() {
	return shineDamper;
}

public Model getModel() {
	return m;
}
Model m;
Transform t;
public GameObject( Model m,Transform t, boolean g) {
	super();
	EngineManager.objects.add(this);
	for(Element ee:m.e){
	PhysicsElement el=new PhysicsElement();
	el.e=ee;
	e.add(el);
	}
	
	this.m = m;
	this.t=t;
	if(EngineManager.physics)this.loadPhysics(g, 10f);
}
public void loadPhysics(boolean STATIC, float mass){
for(PhysicsElement e:e){
	e.r=ModelRenderer.loadPhysics(e, STATIC, mass , new javax.vecmath.Vector3f(t.getPosition().x,t.getPosition().y,t.getPosition().z));
	EngineManager.p.addObject(e);
	
}

}
}
