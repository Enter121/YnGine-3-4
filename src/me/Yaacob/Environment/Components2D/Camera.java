package me.Yaacob.Environment.Components2D;

import javax.vecmath.Vector2f;

import org.lwjgl.opengl.Display;

public class Camera{

	float x=0 , y=0;
float ox=0,oy=0;
	
	public Camera(){

	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	public void addX(float x) {
		this.ox += x+ox;
	}

	public float getOx() {
		return ox;
	}

	public void setOx(float ox) {
		this.ox = ox;
	}

	public float getOy() {
		return oy;
	}

	public void setOy(float oy) {
		this.oy = oy;
	}

	public float getY() {
		return y+oy;
	}

	public void setY(float y) {
		this.y = y;
	}
	public void addY(float y) {
		this.oy += oy;
	}
	
public void setPosition(float x , float y){
	this.x=x;
	this.y=y;
}

public void setPosition(Vector2f v){
	this.x=v.x;
	this.y=v.y;
}
public void setPosition(GameObject2D o){
	this.x=Display.getWidth()/2-o.getX();
	this.y=Display.getHeight()/2-o.getY();
}

}
