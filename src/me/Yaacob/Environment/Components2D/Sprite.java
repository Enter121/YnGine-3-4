package me.Yaacob.Environment.Components2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.opengl.GL11;

import me.Yaacob.Core.EngineManager;
import me.Yaacob.Graphics.Texture;

public class Sprite {

	
	Texture t;
	int id;
	EngineManager m;
	Body body;
	public Sprite(EngineManager m,Texture t) {
	this.t=t;
	this.m=m;
	this.generate();
	}
	public Body getBody() {
		return body;
	}
	

	public void generate(){
		id= GL11.glGenLists(1);
		GL11.glNewList(id, GL11.GL_COMPILE);
		{
		    t.bind();
		   
				GL11.glBegin(GL11.GL_QUADS);
				    GL11.glTexCoord2d(0, 0);
				    GL11.glVertex3f(-t.getWidth()/2, -t.getHeight()/2,0);
				    GL11.glTexCoord2f(1, 0);
				    GL11.glVertex3f((t.getWidth()/2),-t.getHeight()/2,0);
				    GL11.glTexCoord2d(1, 1);
				    GL11.glVertex3f(t.getWidth()/2,t.getHeight()/2,0);
				    GL11.glTexCoord2d(0, 1);
				    GL11.glVertex3f(-t.getWidth()/2, t.getHeight()/2,0);
				    GL11.glEnd();
			t.unbind();
				 
		}
		GL11.glEndList();
	}
	

public int getWidth(){
	return t.getWidth();
}
public int getHeight(){
	return t.getWidth();
}
	public void dipose(){
//		m.objects2d.remove(this);
//		ph.removeObject(this);
//		ph.world.destroyBody(body);
//		body=null;
		GL11.glDeleteLists(id, 1);
	}

}
