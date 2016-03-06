package me.Yaacob.Core;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.vector.Vector3f;

import me.Yaacob.Environment.Components2D.Animation;
import me.Yaacob.Environment.Components2D.GameObject2D;
import me.Yaacob.Environment.Components2D.LoadingScreen;
import me.Yaacob.Environment.Components3D.GameObject;
import me.Yaacob.Environment.Components3D.ModelRenderer;
import me.Yaacob.Environment.Components3D.Physics;

public class EngineManager implements Runnable{

	YnGine y;
	Canvas c;

	
	public static Physics p;
	public EngineManager(YnGine y){
		this.y=y;
	}
	long lastFPS;
	int fps , fpstemp;
	long lastFrame;
	int fpslimit=60;
	float mX=0,mY=0;
	GameType gameType=null;
	public static boolean physics=false;
	
	LoadingScreen loading;
	
	
	
	public static ArrayList<GameObject2D> objects2d = new ArrayList<>();
	public static ArrayList<GameObject> objects = new ArrayList<>();
	public static ArrayList<Animation> anims=new ArrayList<>();
	public static ModelRenderer ren;
	
	
	
	
	public void addObject2D(GameObject2D o){
		objects2d.add(o);
	}
	
	
	public void start(String title){
		System.out.println("=== STARTING YnGine 3 CLIENT===");
		System.out.println("Version: "+Y.version);
		y.setTitle(title);
		this.initLWJGL(-1,-1);
		this.initGAME();
		
	}
	
	public void setLoadingScreen(LoadingScreen lo){
		this.loading=lo;
	}
	public void start(String title , int WIDTH , int HEIGHT){
		System.out.println("=== STARTING YnGine 3 CLIENT===");
		System.out.println("Version: "+Y.version);
		y.setTitle(title);
		this.initLWJGL(WIDTH,HEIGHT);
		this.initGAME();
	}
	public void setGameType(GameType type){
		this.gameType=type;
	}
	public ContextAttribs attribs;
	
	public void initLWJGL(int w , int h){
		if(this.gameType==null){
			Y.print("GAMETYPE IS NOT SET");
			System.exit(0);
		}
		if(gameType==GameType.G3D){
		 attribs=new ContextAttribs(4,1).withProfileCore(true).withForwardCompatible(true);
		}
		try {
			if(w==-1 || h==-1){
			DisplayMode[] modes;
			
				modes = Display.getAvailableDisplayModes();
			
	        for (int i = 0; i < modes.length; i++)
	        {

	            DisplayMode current = modes[i];

	            if (current.isFullscreenCapable())
	            {
	                Display.setDisplayMode(current);
	            }
	            else
	                Display.setDisplayMode(new DisplayMode(1024,720));
	        }
			}else{
				Display.setDisplayMode(new DisplayMode(w,h));
			}
			if(gameType==GameType.G3D){
				Display.create(new PixelFormat(), attribs);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glCullFace(GL11.GL_BACK);
			}else{
				Display.create();
			}
			Display.setFullscreen(y.fullscreen);
			Display.setVSyncEnabled(y.vsync);
			Display.setTitle(y.getTitle());
			
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void initGAME(){
		c=y;
		c.preinit();
		lastFrame=0;
		fps=0;
		fpstemp=0;
		 lastFPS = getTime();
		 getDelta();
		
		if(gameType==GameType.G3D){
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		}else{
			  GL11.glEnable(GL11.GL_TEXTURE_2D);
			    GL11.glShadeModel(GL11.GL_SMOOTH);        
			    GL11.glDisable(GL11.GL_DEPTH_TEST);
			    GL11.glDisable(GL11.GL_LIGHTING);                    

			    GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
			    GL11.glClearDepth(1);                                       

			    GL11.glEnable(GL11.GL_BLEND);
			    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			    GL11.glViewport(0,0,Display.getWidth(),Display.getHeight());
			    GL11.glMatrixMode(GL11.GL_MODELVIEW);
			
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 1, -1);
			//GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glMatrixMode(GL11.GL_MODELVIEW);
		}
	if(loading!=null){
		if(loading.isRunnable()){
			loading.loading();
		}
	}
		c.init();
		c.ini=true;
		
		this.run();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread.yield();
		while(!Display.isCloseRequested()){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			c.update();
			c.render();
			System.out.println("lol");
			if(physics)p.update();
			this.updateFPS();
			this.updateKeyboard();
			this.updateMouse();
		Display.sync(fpslimit);
		Display.update();
		}
		exit();
	}
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	         
	    return delta;
	}
	public float getDeltaTime(){
		
		return 1.0f/fps;
	}
	public int getFPS(){
		return fps;
	}
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	    	fps=fpstemp;
					c.supdate();
				
	    	
	        fpstemp = 0;
	        lastFPS += 1000;
	    }
	    fpstemp++;
	}
	
	public void updateMouse(){

		
		 if (Mouse.isButtonDown(0)) {
			 
			 if(mX!=Mouse.getX() || mY!=Mouse.getY() ) {
				 
				 mX=Mouse.getX();
				
			mY=Mouse.getY();
				
				
					c.mouseDragged(mX, mY);
				
			 
			 
		 }else{
	
					c.mousePressed(mX, mY);
				
		 }
			 
			 
			 
		 }
		
		 if(Mouse.next()){
			 
			if(Mouse.getEventButtonState()){
				if (Mouse.getEventButton() == 0) {
	
						c.mouseClicked(mX, mY);
					
				}
			}else if(!Mouse.getEventButtonState()){
				if (Mouse.getEventButton() == 0) {
	
						c.mouseReleased(mX, mY);
					
				}
			}
		 }
		 
		if(mX!=Mouse.getX() || mY!=Mouse.getY() ) {
		 
		 mX=Mouse.getX();
		
		mY=Mouse.getY();
		

			c.mouseMoved(mX, mY);
		}
		
	}
	  public void updateKeyboard(){
			if (Keyboard.isKeyDown(Keyboard.getEventKey())) {
				int e =Keyboard.getEventKey();

					c.keyPressed(e);
				
			
	
		    }
			 while (Keyboard.next()) {
				 
			        if (Keyboard.getEventKeyState()) {
			        	int e =Keyboard.getEventKey();
			 
			        			
			        			
			        					c.keyTyped(e);
			        				
			        			
			        			
			        		
			        		
						
			    		
			        }else{
			        	int e =Keyboard.getEventKey();
			      
							c.keyReleased(e);
						
			    	
			    		
			        }
			 }
			
			
			
		}
	
	public void setCanvas(Canvas c){
		if(c!=null){
			this.c=c;
			if(!this.c.ini){
				if(loading!=null){
					if(loading.isRunnable()){
						loading.loading();
					}
				}
				this.c.ini=true;
				this.c.init();
			}
			this.c.reinit();
		}else{
			this.c.dispose();
			this.c=y;
		}
	}
	
	public void exit(){
		c.end();
		this.clean();
		Display.destroy();
		System.exit(0);
	}
	 public void initBullet(){
		 EngineManager.p=new Physics(this, new Vector3f(0,-9.8f,0));
		 physics=true;
	 }
	public void clean(){
		for(GameObject2D o:objects2d){
			o.dispose();
		}
		if(ren!=null){
			ren.clean();
		}
	}
}





