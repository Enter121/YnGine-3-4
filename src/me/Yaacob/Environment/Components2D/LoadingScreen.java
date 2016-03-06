package me.Yaacob.Environment.Components2D;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class LoadingScreen {
 Sprite s;
 boolean run=true;
 
 public void setRun(boolean r){
	 this.run=r;
 }
 public LoadingScreen(Sprite s){
	 this.s=s;
	 
 }
 public void setSprite(Sprite s){
	 this.s=s;
 }
 public void loading(){
		GL11.glPushMatrix();
		GL11.glTranslatef(Display.getWidth()/2, Display.getHeight()/2, 0);
		GL11.glCallList(s.id);
		GL11.glPopMatrix();
		Display.update();
 }
 public boolean isRunnable(){
	 return run;
 }
 
}
