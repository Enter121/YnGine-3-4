package me.Yaacob.Utils;

import me.Yaacob.Environment.Components2D.Camera;
import me.Yaacob.Environment.Components2D.GameObject2D;


public abstract class Rectangle {

	int width, height;
	public float x;
	public float y;
	
	
	
	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}

	public void addX(double x) {
		this.x += x;
	}
	public void addY(double y) {
		
		this.y += y;
	}
	

	public void setY(float y) {
		this.y = y;
	}


	public Rectangle(int width, int height , float x , float y){
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
	}
	
	
	public boolean overlaps(Rectangle object){
		
		
		if(-this.getY()+this.getHeight()/2>-object.getY()-object.getHeight()/2&& -this.getY()-this.getHeight()/2<-object.getY()+object.getHeight()/2&& this.getX()+this.getWidth()/2>object.getX()-object.getWidth()/2&& this.getX()-this.getWidth()/2<object.getX()+object.getWidth()/2)return true;
		return false;
	}

	
	public void setPosition(float x, float y){
		this.x=x;
		this.y=y;
	}

	
	
}
