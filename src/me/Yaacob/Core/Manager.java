package me.Yaacob.Core;

import java.io.File;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.bulletphysics.collision.shapes.CapsuleShape;

import me.Yaacob.Environment.Components3D.FPSCamera;
import me.Yaacob.Environment.Components3D.GameObject;
import me.Yaacob.Environment.Components3D.Light;
import me.Yaacob.Environment.Components3D.Model;
import me.Yaacob.Environment.Components3D.ModelRenderer;
import me.Yaacob.Graphics.Texture;
import me.Yaacob.Utils.Transform;

public class Manager extends YnGine{

	static EngineManager m;
	YamlFile y;
	public static void main(String[] args){
		vsync=true;
		m=new EngineManager(new Manager());
		m.setGameType(GameType.G3D);
		m.start("FIRST YnGine FRAME" , 1024 ,720);
	}

	Texture t;
	Model mo , mo1;
	ModelRenderer re;
	FPSCamera c;
	GameObject ga, ga1,ga2;
	Light l;
	//Terrain ter;
	@Override
	public void init() {
		m.initBullet();
		// TODO Auto-generated method stub
		Mouse.setGrabbed(true);
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		ModelRenderer.type=TextureType.NPOT;
t=new Texture(new File("socuwan.png"));
c=new FPSCamera(new Vector3f(0,0,0),new Vector3f(0,0,0),new CapsuleShape(0.5f,4f)){};
re=new ModelRenderer();
l=new Light(new Vector3f(0,1000,50), new Vector3f(1,1,1));
re.setCamera(c);
mo=new Model(re, new File("barrel.yn"));
mo1=new Model(re, new File("fff.yn"));

//ter=new Terrain(re,"grass.png");
//ter.setTransform(new Transform(new Vector3f(0,0,0),new Vector3f(0,0,0), new Vector3f(1,1,1)){});
ga=new GameObject(mo,new Transform(new Vector3f(0,10,0),new Vector3f(0f,0,0) ,new Vector3f(1f,1f,1f)){}, false);
//ga=new GameObject(mo,new Transform(new Vector3f(0,0,0),new Vector3f(0f,0,0) ,new Vector3f(1f,1f,1f)){}, true);
ga1=new GameObject(mo1,new Transform(new Vector3f(0,-5,0),new Vector3f(0f,0,0) ,new Vector3f(1f,1f,1f)){}, true);

ga2=new GameObject(mo,new Transform(new Vector3f(0,20,0.5f),new Vector3f(0f,0,0) ,new Vector3f(1f,1f,1f)){}, false);


re.enableLighting();
	
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

		re.begin();
		re.render(ga);
		re.render(ga1);
		re.render(ga2);
		//ter.render(re);
		re.end();
		//l.setPosition(new Vector3f(c.getPosition().x,c.getPosition().y+25,c.getPosition().z));
		//gf.set("class", c);
		//gf.save();
		
	}
	float i=0;
	@Override

	public void update() {
		i+=0.1f;
		// TODO Auto-generated method stub
		//ga.getTransform().addRotation(new Vector3f(0.0f,0.005f,0f));
		//l.setPosition(new Vector3f(-c.getPosition().x,-c.getPosition().y,-c.getPosition().z));
		l.update(re);
	
		//c.addRotation(new Vector3f(0,0.1f,0));
		c.update();
		
		c.getBody().setLinearVelocity(new javax.vecmath.Vector3f(1,0,0));
	}

	@Override
	public void supdate() {
		// TODO Auto-generated method stub
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
			
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
	switch(k){
	case Keyboard.KEY_W:
		c.setForward(true);
		break;
	case Keyboard.KEY_S:
		c.setBackward(true);
		break;
	case Keyboard.KEY_A:
		c.setLeft(true);
		break;
	case Keyboard.KEY_D:
		c.setRight(true);
		break;
	
	}
	}

	@Override
	public void keyTyped(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		switch(k){
		case Keyboard.KEY_W:
			c.setForward(false);
			break;
		case Keyboard.KEY_S:
			c.setBackward(false);
			break;
		case Keyboard.KEY_A:
			c.setLeft(false);
			break;
		case Keyboard.KEY_D:
			c.setRight(false);
			break;
		
		}
	}

	@Override
	public void mouseMoved(float x, float y) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseClicked(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preinit() {
		// TODO Auto-generated method stub
		
	}

}
