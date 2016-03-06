package me.Yaacob.Environment.Components2D;

import java.io.File;

import org.lwjgl.opengl.GL11;

import me.Yaacob.Graphics.Texture;

public class Animation {
	
double rotation=0,scale=1;;
	boolean end=false;
	
	Texture[] frame;
	
	Texture animation;
	
	public int width;

	int[] id;

	public int height;
	
	int c=0;
	
	int x=0,y=0;
	
	int time;
	boolean repeat=true;
	int frames=0;
	
	int cf=0;
	
	public Animation(File f, int x , int y ,  int width , int height , int time , int frames , boolean repeat){
		this.f=f;
		this.width=width;
		this.height=height;
		this.time=time;
		this.frames=frames;
		this.c=time;
		this.repeat=repeat;
		Texture[] c= new Texture[frames];
		frame=c;
		int[] d=new int[frames];
		id=d;
		Texture t=new Texture(f);
		for(int k=0;k!=frames;k++){
		

			//	frame[f][j*width+i]=animation.getPixel((j+y)*animation.texture.getWidth()+((width)*f)+i+x);
			frame[k]=new Texture(f , width*k+x, y,width ,height);
			id[k]= GL11.glGenLists(1);
			GL11.glNewList(id[k], GL11.GL_COMPILE);
			{
			frame[k].bind();
				
				GL11.glBegin(GL11.GL_QUADS);
				    GL11.glTexCoord2d(0, 0);
				    GL11.glVertex2d(0, 0);
				    GL11.glTexCoord2f(1, 0);
				    GL11.glVertex2d(frame[k].getWidth()*scale, 0);
				    GL11.glTexCoord2d(1, 1);
				    GL11.glVertex2d(frame[k].getWidth()*scale,frame[k].getHeight()*scale);
				    GL11.glTexCoord2d(0, 1);
				    GL11.glVertex2d(0, frame[k].getHeight()*scale);
				    GL11.glEnd();

				     GL11.glPopMatrix();
				    frame[k].unbind();
			}
			GL11.glEndList();
			}
			
		}
		
	
	
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
File f;
	public Animation(File f, int width , int height , int time , int frames , boolean repeat){
		this.f=f;
		this.width=width;
		this.height=height;
		this.time=time;
		this.frames=frames;
		this.c=time;
		this.repeat=repeat;
		Texture[] c= new Texture[frames];
		frame=c;
		int[] d=new int[frames];
		id=d;
		this.repeat=repeat;
		Texture t=new Texture(f);
		for(int k=0;k!=frames;k++){
		

			//	frame[f][j*width+i]=animation.getPixel((j+y)*animation.texture.getWidth()+((width)*f)+i+x);
			frame[k]=new Texture(this.f, width*k+x, y,width ,height);
			id[k]= GL11.glGenLists(1);
			GL11.glNewList(id[k], GL11.GL_COMPILE);
			{
			frame[k].bind();
				   
				GL11.glBegin(GL11.GL_QUADS);
				    GL11.glTexCoord2d(0, 0);
				    GL11.glVertex2d(0, 0);
				    GL11.glTexCoord2f(1, 0);
				    GL11.glVertex2d(frame[k].getWidth()*scale, 0);
				    GL11.glTexCoord2d(1, 1);
				    GL11.glVertex2d(frame[k].getWidth()*scale,frame[k].getHeight()*scale);
				    GL11.glTexCoord2d(0, 1);
				    GL11.glVertex2d(0, frame[k].getHeight()*scale);
				    GL11.glEnd();

				     GL11.glPopMatrix();
				    frame[k].unbind();
			}
			GL11.glEndList();
			}

		
		}
		
	
	
	

	public boolean isAnimEnded(){
		return end;
	}
	
	public void reset(){
		end=false;
		cf=0;
	}
	public void changeFrame(){
		if(cf>=frames-1){
			if(repeat){
				cf=0;
				end=false;
			}else{
				end=true;
			}
		}else{
			cf++;
	}
	}
	
	
			public void update(){
				c--;
				if(c<=0){
					c=time;
					changeFrame();
				}
				
		}

	public Texture getCurrentframe() {
		return frame[cf];
	}

public void render(float x , float y , float z , float rotation , float scale){
	GL11.glPushMatrix();
    GL11.glTranslated(x+frame[cf].getWidth()/2, y+frame[cf].getHeight()/2, 0);      
   if(rotation!=0)GL11.glRotatef((float)rotation, (float)0, (float)0, 1f);
   GL11.glScalef(scale, scale, scale);
    GL11.glTranslated(-frame[cf].getWidth()/2,-frame[cf].getHeight()/2, 0);
	GL11.glCallList(id[cf]);
}


	public Texture getAnimation() {
		return animation;
	}


	public void setAnimation(Texture animation) {
		this.animation = animation;
	}
	
	

}
