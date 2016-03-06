package me.Yaacob.Environment.Components3D;

import org.lwjgl.util.vector.Vector3f;

import me.Yaacob.Core.UType;

public class Light {
	
	Vector3f pos;
	Vector3f colour;
	public Light(Vector3f pos, Vector3f colour) {
		this.pos = pos;
		this.colour = colour;
	}
	public Vector3f getPosition() {
		return pos;
	}
	public void setPosition(Vector3f pos) {
		this.pos = pos;
	}
	public Vector3f getColour() {
		return colour;
	}
	public void setColour(Vector3f colour) {
		this.colour = colour;
	}
	public void update(ModelRenderer r){
		
		r.s.start();
		r.s.load(UType.vec3, r.s.lcolor, colour);
		r.s.load(UType.vec3,r.s.lpos,pos);
		r.s.stop();
	}
	
}
