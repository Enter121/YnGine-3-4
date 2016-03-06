package me.Yaacob.Core;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.Drawable;
import org.lwjgl.opengl.Pbuffer;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.opengl.SharedDrawable;

import me.Yaacob.Environment.Components2D.Animation;
import me.Yaacob.Environment.Components2D.Font;
import me.Yaacob.Environment.Components2D.GameObject2D;
import me.Yaacob.Environment.Components2D.LoadingScreen;
import me.Yaacob.Environment.Components2D.Physics2D;
import me.Yaacob.Environment.Components2D.Sprite;
import me.Yaacob.Graphics.Texture;
import me.Yaacob.Utils.Rectangle;

public class Man extends YnGine{

	static EngineManager m;
	
	
	
	public static void main(String[] args){
		m=new EngineManager(new Man());
		vsync=true;
	//	fullscreen=true;
		m.setGameType(GameType.G2D);
		m.start("Test",1280,720);
	}
	
	Sprite o;
	Physics2D ph;
	GameObject2D g,g1;
	Font f;
	Rectangle r;
	Sprite s;
LoadingScreen sc;
Animation anim;
	
	public void preinit(){
		s=new Sprite(m, new Texture(new File("loading.png")));
		sc=new LoadingScreen(s);
		m.setLoadingScreen(sc);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		ph=new Physics2D(m, true);
		o=new Sprite(m, new Texture(new File("player1.png")));
		g=new GameObject2D(o,ph,false);
		g.setScale(4);
		//g1=new GameObject2D(o,ph,true);
		g.setPosition(200, 50);
		//g1.setPosition(230, 300);
		
		f=new Font(new File("g.fnt"),1);
		r=new Rectangle(0,0,1,1){};
		anim=new Animation(new File("player.png"),0,0,11,10,10,4,true);
	
		g.setAnimation(anim);
	}

	@Override
	public void render() {
		
		System.out.println(m.getDeltaTime());
		
		
		// TODO Auto-generated method stub
		g.render();
	//	g1.render();
		//r.render();
//		f.render(100, 700, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 650, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 600, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 550, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 500, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 450, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 400, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 350, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 300, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 250, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		f.render(100, 200, "zzzzzzzzzzzz");
		anim.setTime((int) (m.getDelta()*0.7));
		System.out.println((int) (0.7f*m.getDelta()));
		g.updateGraphics();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	r.setPosition(Mouse.getX(), Mouse.getY());
		ph.update();
	
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
		
	}

	@Override
	public void keyTyped(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(float x, float y) {
		// TODO Auto-generated method stub
		r.setPosition(x, y);
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

}
