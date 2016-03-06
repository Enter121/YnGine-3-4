package me.Yaacob.Environment.Components2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.opengl.GL11;

import me.Yaacob.Utils.Rectangle;

public class GameObject2D extends Rectangle{

	public float scale=1;
	public float rotation=0;
	Sprite s;
	
	Body body;
	
	Physics2D ph;
	Animation anim;

	public GameObject2D(Sprite s) {
		super(s.getWidth(), s.getHeight(), 0, 0);
		this.s=s;
		// TODO Auto-generated constructor stub
	}
	public GameObject2D(Sprite s , float x , float y) {
		super(s.getWidth(), s.getHeight(), x, y);
		this.s=s;
		// TODO Auto-generated constructor stub
	}
	
	public GameObject2D(Sprite s , Physics2D ph,boolean dyn) {
		super(s.getWidth(), s.getHeight(), 0, 0);
		this.s=s;
		this.createbody(ph, dyn);
		this.ph=ph;
		ph.addObject(this);
		// TODO Auto-generated constructor stub
	}
	public void setScale(float scale){
		this.scale=scale;
	}
	public GameObject2D(Sprite s , float x , float y, Physics2D ph,boolean dyn) {
		super(s.getWidth(), s.getHeight(), x, y);
		this.s=s;
		this.createbody(ph, dyn);
		this.ph=ph;
		ph.addObject(this);
		// TODO Auto-generated constructor stub
	}
	
	
	public void render(){
		
		if(anim==null){
		GL11.glPushMatrix();
		GL11.glTranslatef(this.x, this.y, 0);
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef((float)rotation, (float)0, (float)0, 1f);
		
		GL11.glCallList(s.id);
		GL11.glPopMatrix();
		}else{
			anim.render(x,y,0,rotation,scale);
		}
	}
	
	
	public void updateGraphics(){
		if(anim!=null){
			anim.update();
		}
	}
	
	public void render(Camera c){
		
		if(anim==null){
		GL11.glPushMatrix();
		GL11.glTranslatef(this.x-c.getX(), this.y-c.getY(), 0);
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef((float)rotation, (float)0, (float)0, 1f);
		GL11.glCallList(s.id);
		GL11.glPopMatrix();
		}else{
			anim.render(x-c.getX(),y-c.getY(),0,rotation,scale);
		}
		
	}
	
	public float getOY(){
		
if(body==null){
			
			return y;
		}else{
			return body.getPosition().y;
		}
		
	}

	
	public float getOX(){
if(body==null){
			
			return x;
		}else{
			return body.getPosition().x;
		}
		
	}

	
	public void addX(float x){
		if(body==null){
			
			this.x+=x;
		}else{
			this.setX(this.getX()+x);	
		}
	}
	public void setVelocity(float x ,float y){
		this.body.setLinearVelocity(new Vec2(x,y));
	}
	public void addY(float y){
		if(body==null){
			
			this.y+=y;
		}else{
			this.setY(this.getY()+y );
		}
	}
	public void setX(float x){
		if(body==null){
			this.x=x;
		}else{
			body.setActive(true);
			this.body.setTransform(new Vec2(x,this.body.getPosition().y), body.getAngle());
			this.body.synchronizeTransform();
		}
	}
	public void setY(float y){
		if(body==null){
			this.y=y;
			
		}else{
			body.setActive(true);
			this.body.setTransform(new Vec2(this.body.getPosition().x,y), body.getAngle());
			this.body.synchronizeTransform();
			
		}
	}
	
	public void setPosition(float x , float y){
		this.setX(x);
		this.setY(y);
	}
	void createbody(Physics2D ph,boolean dynamiobject){
		if(dynamiobject){
			BodyDef bodyDef = new BodyDef();
		    bodyDef.type = BodyType.DYNAMIC;
		    bodyDef.active=true;
		    bodyDef.position.set(x+this.getWidth()/2, y+this.getHeight()/2);
		    Body body = ph.world.createBody(bodyDef);
		    PolygonShape dynamicBox = new PolygonShape();
		    dynamicBox.setAsBox((float)getWidth()/2, (float)getHeight()/2);
		    FixtureDef fixtureDef = new FixtureDef();
		    fixtureDef.shape = dynamicBox;
		    fixtureDef.density = 1f;
		    fixtureDef.restitution=0.25f;
		    fixtureDef.friction = 0.3f;
		    body.createFixture(fixtureDef);
		   body.setBullet(true);
		    this.body=body;
		}else{
			BodyDef bodyDef = new BodyDef();
		    bodyDef.type = BodyType.STATIC;
		    bodyDef.position.set(x+getWidth()/2, y+getHeight()/2);
		    Body body = ph.world.createBody(bodyDef);
		    PolygonShape dynamicBox = new PolygonShape();
		    dynamicBox.setAsBox((float)getWidth()/2, (float)getHeight()/2);
		    
		    FixtureDef fixtureDef = new FixtureDef();
		    fixtureDef.shape = dynamicBox;
		    fixtureDef.density = 1f;
		    fixtureDef.friction = 0.3f;
		    body.createFixture(fixtureDef);
		    this.body=body;
		    
		    
		}
	}
	public void dispose(){
		s.dipose();
		if(ph!=null){
			ph.world.destroyBody(body);
			ph.removeObject(this);
		}
	}
	public void setAnimation(Animation a){
		
		this.anim=a;
	}
//	public boolean overlaps(GameObject2D object){
//		
//		
//		if(-this.getOY()+this.getHeight()/2>-object.getOY()-object.getHeight()/2&& -this.getOY()-this.getHeight()/2<-object.getOY()+object.getHeight()/2&& this.getOX()+this.getWidth()/2>object.getOX()-object.getWidth()/2&& this.getOX()-this.getWidth()/2<object.getOX()+object.getWidth()/2)return true;
//	return false;
//}
}
