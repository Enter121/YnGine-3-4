package me.Yaacob.Graphics;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import me.Yaacob.Core.Y;

public class Texture {

	int width,height , id;
	
	public String name;
	
	
	public Texture(File file){
		this.name=file.getName();
		BufferedImage image = null;
		try {
			image= ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width=image.getWidth();
		this.height=image.getHeight();
		id=this.load(image, 0, 0, width, height);
		
	}
	public Texture(File f , double x , double y , int width , int height){
		this.name=f.getName();
		BufferedImage image = null;
		try {
			image= ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(x+width>image.getWidth() || y+height>image.getHeight() || x<0 || y<0 || width<0 || height<0){
			System.err.println(Y.prefix+"Texture: out of bounds");
			try {
				this.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return;
		}
		this.width=width;
		this.height=height;
		id=this.load(image, x, y, width, height);
	}
	
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getId() {
		return id;
	}
	public int load(BufferedImage image , double ox , double oy,int width , int height){

        int[] pixels = new int[width * height];
        int[] curr=new int[image.getWidth()*image.getHeight()];  
       image.getRGB(0, 0, image.getWidth(), image.getHeight(), curr, 0, image.getWidth());
      
        for(int x=0;x!=width;x++){
        	 for(int y=0;y!=height;y++){  	
        		pixels[y*width+x]=image.getRGB((int)(x+ox), (int)((height-y-1)+oy));
        	}
        }
    	
          ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);

          for(int y = 0; y < height; y++){
              for(int x = 0; x <width; x++){
            	
                  int pixel = pixels[y * width + x];
                  buffer.put((byte) ((pixel >> 16) & 0xFF)); 
                  buffer.put((byte) ((pixel >> 8) & 0xFF));  
                  buffer.put((byte) (pixel & 0xFF));           
                  buffer.put((byte) ((pixel >> 24) & 0xFF));  
              }
          }

          buffer.flip();

        int textureID = glGenTextures();
          glBindTexture(GL_TEXTURE_2D, textureID);

          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

          glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
          glBindTexture(GL_TEXTURE_2D, 0);
        return textureID;
	}
	
	
	public void bind(){
		  glBindTexture(GL_TEXTURE_2D, this.getId());
	}
	public void unbind(){
		  glBindTexture(GL_TEXTURE_2D, 0);
	}
	public void render(double x , double y , double rotation, double scale){
		   //Color.white.bind();
		    this.bind();
		    
		    GL11.glColor3f(1f,1f,1f);
		GL11.glPushMatrix();
		    GL11.glTranslated(x+this.getWidth()/2, y+this.getHeight()/2, 0);      
		   if(rotation!=0)GL11.glRotatef((float)rotation, (float)0, (float)0, 1f);
		    GL11.glTranslated(-this.getWidth()/2,-this.getHeight()/2, 0);   
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glTexCoord2d(0, 0);
		    GL11.glVertex2d(0, 0);
		    GL11.glTexCoord2f(1, 0);
		    GL11.glVertex2d(this.getWidth()*scale, 0);
		    GL11.glTexCoord2d(1, 1);
		    GL11.glVertex2d(this.getWidth()*scale,this.getHeight()*scale);
		    GL11.glTexCoord2d(0, 1);
		    GL11.glVertex2d(0, this.getHeight()*scale);
		    GL11.glEnd();
		     GL11.glPopMatrix();
		    this.unbind();
		 
	}
}
