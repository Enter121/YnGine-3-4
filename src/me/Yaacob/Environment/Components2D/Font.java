package me.Yaacob.Environment.Components2D;

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.bulletphysics.collision.broadphase.Dbvt.sStkCLN;

import me.Yaacob.Core.Y;
import me.Yaacob.Graphics.Texture;

public class Font {
	
	ArrayList<Texture> font = new ArrayList<>();
	double size;
	
	
	public Font(File f, double size){
		this.size=size;
		parse(f);
		
	}
	
	
	ArrayList<Character> characters = new ArrayList<>();
	
	
	
	public void parse(File f){
		String content = Y.readFile(f);
		
		for(String s: content.split("\n")){
			if(s.startsWith("page ")){
				s=s.replace("=", ";").replace("page id", "").replace("file", "").replace(" ", "").replace("\"", "");
				font.add(new Texture(new File(s.split(";")[2])));
			} else if(s.startsWith("char ")){
				s=s.replace("=", ";").replace("char", "").replace("width", "").replace("height", "").replace("xoffset", "").replace("yoffset", "").replace("xadvance", "").replace("id", "").replace("x", "").replace("y", "").replace(" ", "").replace("page", "").replace("chnl", "");
				
				int id=Integer.parseInt(s.split(";")[1]);
				int x=Integer.parseInt(s.split(";")[2]);
				int y=Integer.parseInt(s.split(";")[3]);
				int width=Integer.parseInt(s.split(";")[4]);
				int height=Integer.parseInt(s.split(";")[5]);
				int xoffset=Integer.parseInt(s.split(";")[6]);
				int yoffset=Integer.parseInt(s.split(";")[7]);
				int xadvance=Integer.parseInt(s.split(";")[8]);
				

								
				Texture t=new Texture(new File(font.get(Integer.parseInt(s.split(";")[9])).name), x, y, width, height);
				Character c=new Character(t, id,x, y, width, height, xoffset, yoffset, xadvance);
			characters.add(c);
			}
		}
	}
	public void render(float x , float y , String str){
		char[] s=str.toCharArray();
		float ox=0;
		for(int i=0;i!=s.length;i++){
			
			for(Character c:characters){
				if(c.id==(int)s[i]){
					GL11.glPushMatrix();
					GL11.glTranslatef(c.xoffset+ox+x, c.yoffset+y, 0);
					
					c.render(size);
					ox+=(c.xadvance+c.xoffset)*size;
					GL11.glPopMatrix();
					break;
				}
			
			}
		}
	}
	
	
}