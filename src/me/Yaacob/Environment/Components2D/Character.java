package me.Yaacob.Environment.Components2D;

import org.lwjgl.opengl.GL11;

import me.Yaacob.Graphics.Texture;

public class Character {

	int id;
	int x ,y;
	int width,height;
	int xoffset , yoffset;
	int xadvance;
	Texture Char;
	
	int list;
	
	public Character(Texture Char,int id, int x, int y, int width, int height, int xoffset, int yoffset, int xadvance) {
		this.Char=Char;
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.xadvance = xadvance;
		
	}

	public void render(double size){
			    Char.bind();
					GL11.glBegin(GL11.GL_QUADS);
					    GL11.glTexCoord2d(0, 0);
					    GL11.glVertex3d(-Char.getWidth()/2*size, -Char.getHeight()/2*size,0);
					    GL11.glTexCoord2f(1, 0);
					    GL11.glVertex3d((Char.getWidth()/2*size),-Char.getHeight()/2*size,0);
					    GL11.glTexCoord2f(1, 1);
					    GL11.glVertex3d(Char.getWidth()/2*size,Char.getHeight()/2*size,0);
					    GL11.glTexCoord2f(0, 1);
					    GL11.glVertex3d(-Char.getWidth()/2*size, Char.getHeight()/2*size,0);
					    GL11.glEnd();
				Char.unbind();
					 
			
		
	}
	
}
