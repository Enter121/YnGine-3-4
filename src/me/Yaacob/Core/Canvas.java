package me.Yaacob.Core;

public abstract class Canvas {

	
	boolean ini=false;
	
	public void reinit(){
		
	}
	
	public void disable(){
		
	}
	public void dispose(){
		
	}
	
	public void preinit(){
		
	}
	public abstract void init();
	public abstract void render();
	public abstract void update();
	public abstract void supdate();
	public abstract void end();
	
	public abstract void keyPressed(int k);
	public abstract void keyTyped(int k);
	public abstract void keyReleased(int k);
	
	
	public abstract void mouseMoved(float x,float y);
	public abstract void mouseClicked(float x,float y);
	public abstract void mouseDragged(float x,float y);
	public abstract void mouseReleased(float x,float y);
	public abstract void mousePressed(float x,float y);
	

}
