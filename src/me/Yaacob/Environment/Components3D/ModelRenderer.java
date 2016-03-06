package me.Yaacob.Environment.Components3D;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;

import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.ConvexHullShape;
import com.bulletphysics.collision.shapes.TriangleShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.QuaternionUtil;
import com.bulletphysics.util.ObjectArrayList;

import me.Yaacob.Core.EngineManager;
import me.Yaacob.Core.TextureType;
import me.Yaacob.Core.UType;
import me.Yaacob.Utils.Transform;


public class ModelRenderer {

	Camera c;
	ArrayList<Integer> vaos = new ArrayList<>();
	ArrayList<Integer> vbos = new ArrayList<>();
	ModelShader s;
	
	public static TextureType type=TextureType.POT;
	
	public ModelRenderer(){
		EngineManager.ren=this;
		s=new ModelShader();
		s.start();
		s.load(UType.Int, s.tex, 0);
		s.load(UType.Int, s.norm, 1);
		s.stop();
	}
	public void setCamera(Camera c){
		this.c=c;
	}
	public void begin(){
		s.start();
	}
	public void end(){
		s.stop();
	}
	public int loadVAO(float[] pos,float[] texcoords ,float[] normals, int[] in, float[] tangents,Element m){
		int id=createVAO();
		vaos.add(id);
		this.bindIndicesBuffer(in);
		this.storeData(0,3,pos);
		this.storeData(1,3,normals);
		this.storeData(2,2,texcoords);
		this.storeData(3,3,tangents);
		this.unbintVAO();
		return id;
	}
	
	public int loadVAO(float[] pos,float[] texcoords ,float[] normals, int[] in, Element m){
		int id=createVAO();
		vaos.add(id);
		this.bindIndicesBuffer(in);
		this.storeData(0,3,pos);
		this.storeData(1,3,normals);
		this.storeData(2,2,texcoords);
		this.unbintVAO();
		return id;
	}
	public int loadVAO(float[] pos,float[] normals, int[] in,Element m){
		int id=createVAO();
		vaos.add(id);
		this.bindIndicesBuffer(in);
		this.storeData(0,3,pos);
		this.storeData(1,3,normals);
		this.unbintVAO();
		return id;
	}
	
	public int createVAO(){
		
		int id =GL30.glGenVertexArrays();
		GL30.glBindVertexArray(id);
		return id;
	}
	
	
	public void storeData(int att , float[] data){
		int vboid=GL15.glGenBuffers();
		vbos.add(vboid);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboid);
		FloatBuffer b=this.getFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, b, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(att, 3, GL11.GL_FLOAT, false,0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	public void unbintVAO(){
		GL30.glBindVertexArray(0);
	}
	public void storeData(int att ,int s, float[] data){
		int vboid=GL15.glGenBuffers();
		vbos.add(vboid);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboid);
		FloatBuffer b=this.getFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, b, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(att, s, GL11.GL_FLOAT, false,0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	public FloatBuffer getFloatBuffer(float[] data){
	FloatBuffer b=BufferUtils.createFloatBuffer(data.length);
	b.put(data);
	b.flip();
	return b;
		
		
	}
	public IntBuffer getIntBuffer(int[] data){
		IntBuffer b=BufferUtils.createIntBuffer(data.length);
		b.put(data);
		b.flip();
		return b;
	}
	
	public void bindIndicesBuffer(int[] indices){
		int vboid =GL15.glGenBuffers();
		vbos.add(vboid);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboid);
		IntBuffer b=this.getIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, b, GL15.GL_STATIC_DRAW);
	}

	public void clean(){
		for(Integer i: vaos){
			GL30.glDeleteVertexArrays(i);
		}
		for(Integer i: vbos){
			GL15.glDeleteBuffers(i);
		}
		vaos.clear();
		vbos.clear();
	}
	
	public void render(GameObject g){
	
		for(PhysicsElement e:g.e){
		Element m=e.e;
		GL30.glBindVertexArray(m.getId());
		if(m.tex){
			GL13.glActiveTexture(GL13.GL_TEXTURE0);

			if(this.type==TextureType.POT)	GL11.glBindTexture(GL11.GL_TEXTURE_2D, m.t.getTextureID());
			if(this.type==TextureType.NPOT) m.nt.bind();

		}
		if(m.tang){
			GL13.glActiveTexture(GL13.GL_TEXTURE1);

		if(this.type==TextureType.POT)	GL11.glBindTexture(GL11.GL_TEXTURE_2D, m.n.getTextureID());
		if(this.type==TextureType.NPOT) m.nn.bind();
		}
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		if(m.mode==0){
			GL20.glEnableVertexAttribArray(2);
			GL20.glEnableVertexAttribArray(3);
		}
		if(m.mode==1){
		
			GL20.glEnableVertexAttribArray(2);
		}
			
			
		
			s.load(UType.Transform, s.transform, e.r.getWorldTransform(new com.bulletphysics.linearmath.Transform()));
			if(c!=null){
				s.load(UType.mat4, s.camera,getCameraMatrix(c));
				s.load(UType.mat4, s.projection, c.getProjection());
			}
			s.load(UType.bool, s.tang, m.tang);
			s.load(UType.bool, s.texb, m.tex);
			s.load(UType.Float, s.reflectivity,g.reflectivity);
			s.load(UType.Float, s.shineDamper,g.shineDamper);
			
			   
			GL11.glDrawElements(GL11.GL_TRIANGLES, e.e.getVertexNum(), GL11.GL_UNSIGNED_INT, 0);
			
			//GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, m.getVertexNum());
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			if(m.mode==0){
				GL20.glDisableVertexAttribArray(2);
				GL20.glDisableVertexAttribArray(3);
			}
			if(m.mode==1){
				GL20.glDisableVertexAttribArray(2);
			}
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			GL13.glActiveTexture(GL13.GL_TEXTURE1);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
				
			
		
		GL30.glBindVertexArray(0);
		
		}
	}
//	public void render(Terrain g){
//	Model m=g.mo;
//		GL30.glBindVertexArray(m.getId());
//		if(m.tex){
//			GL13.glActiveTexture(GL13.GL_TEXTURE0);
//
//			if(this.type==TextureType.POT)	GL11.glBindTexture(GL11.GL_TEXTURE_2D, m.t.getTextureID());
//			if(this.type==TextureType.NPOT) m.nt.bind();
////			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
////			  GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
////			  GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
////			  GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
//		}
//		if(m.tang){
//			GL13.glActiveTexture(GL13.GL_TEXTURE1);
//
//		if(this.type==TextureType.POT)	GL11.glBindTexture(GL11.GL_TEXTURE_2D, m.n.getTextureID());
//		if(this.type==TextureType.NPOT) m.nn.bind();
//		}
//		GL20.glEnableVertexAttribArray(0);
//		GL20.glEnableVertexAttribArray(1);
//		if(m.mode==0){
//			GL20.glEnableVertexAttribArray(2);
//			GL20.glEnableVertexAttribArray(3);
//		}
//		if(m.mode==1){
//		
//			GL20.glEnableVertexAttribArray(2);
//		}
//			
//			
//		
//			s.load(UType.mat4, s.transform, getModelMatrix(g.t));
//			if(c!=null){
//				s.load(UType.mat4, s.camera,getCameraMatrix(c));
//				s.load(UType.mat4, s.projection, c.getProjection());
//				s.load(UType.Float, s.reflectivity,g.reflectivity);
//				s.load(UType.Float, s.shineDamper,g.shineDamper);
//			}
//			s.load(UType.bool, s.tang, m.tang);
//			s.load(UType.bool, s.texb, m.tex);
//
//			   
//			GL11.glDrawElements(GL11.GL_TRIANGLES,m.getVertexNum(), GL11.GL_UNSIGNED_INT, 0);
//			
//			//GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, m.getVertexNum());
//			GL20.glDisableVertexAttribArray(0);
//			GL20.glDisableVertexAttribArray(1);
//			if(m.mode==0){
//				GL20.glDisableVertexAttribArray(2);
//				GL20.glDisableVertexAttribArray(3);
//			}
//			if(m.mode==1){
//				GL20.glDisableVertexAttribArray(2);
//			}
//			GL13.glActiveTexture(GL13.GL_TEXTURE0);
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
//			GL13.glActiveTexture(GL13.GL_TEXTURE1);
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
//				
//			
//		
//		GL30.glBindVertexArray(0);
//	
//	}
	public Matrix4f getModelMatrix(Transform g){
		Matrix4f m=new Matrix4f();
		m.translate(g.getPosition());
		m.rotate(g.getRotation().x,Transform.X());
		m.rotate(g.getRotation().y,Transform.Y());
		m.rotate(g.getRotation().z,Transform.Z());
		m.scale(g.getScale());
		
		return m;
	}
	
	public Matrix4f getCameraMatrix(Camera g){
		Matrix4f m=new Matrix4f();
		
		m.rotate(g.t.getRotation().x,Transform.X());
		m.rotate(g.t.getRotation().y,Transform.Y());
		m.rotate(g.t.getRotation().z,Transform.Z());
		
		m.scale(g.t.getScale());
		m.translate(new org.lwjgl.util.vector.Vector3f(-g.r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.x,-g.r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.y,-g.r.getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin.z));
		
		
		return m;
	}
	
	public void enableLighting(){
		s.start();
		s.load(UType.bool, s.lighting, true);
		s.stop();
	}
	public void disableLighting(){
		s.start();
		s.load(UType.bool, s.lighting, false);
		s.stop();
	}
	
	
	
	public static RigidBody loadPhysics(PhysicsElement e,boolean STATIC , float mass , Vector3f pos){
		Physics p=EngineManager.p;
		RigidBody body;

		
		
			 javax.vecmath.Vector3f inertia = new javax.vecmath.Vector3f();
			 // shape=new BoxShape(new javax.vecmath.Vector3f(5f,5f,5f));
				
			 DefaultMotionState motionState;
					motionState = new DefaultMotionState(new com.bulletphysics.linearmath.Transform(new javax.vecmath.Matrix4f(new Quat4f(0, 0, 0, 1),new javax.vecmath.Vector3f(pos.x,pos.y,pos.z),1f)));
					CollisionShape shape = ModelRenderer.getCollision(e.e.mo.vertices, e.e.indices);
				
					shape.calculateLocalInertia(mass, inertia);
					
					if(!STATIC){
					 body = new RigidBody(mass, motionState, shape,inertia);
					 
					// body.setDamping(0.5F, 0.8F);
		
				p.world.addRigidBody(body);
				
			return body;
					}else{
					     
		//shape=new BvhTriangleMeshShape();
		//TODO
		
					      body = new RigidBody(0, motionState, shape);
						 body.setRestitution(0.25f);

						 body.setDamping(0.5F, 0.8F);
			
					p.world.addRigidBody(body);
					
						return body;
					}
					
				
		

}
	
	
	public static CollisionShape getCollision(float[] vertices,int[] indices){
		Vector3f[] sh=new Vector3f[indices.length];
		Vector3f[] vecs=new Vector3f[vertices.length];
		int j=0;
		System.out.println(vertices.length);
		for(int i=0;i!=vecs.length/3;i++){
			vecs[i]=new Vector3f(vertices[j],vertices[j+1],vertices[j+2]);
			j+=3;
		}
		
		
		for(int i=0;i!=indices.length;i++){
			sh[i]=vecs[indices[i]];
			
	}
		
		
		
		ObjectArrayList<Vector3f> vec=new ObjectArrayList<>();
		
		for(int i=0;i!=sh.length;i++){
			vec.add(sh[i]);
		}
		
		
		ConvexHullShape con=new ConvexHullShape(vec);
		vec.clear();

//		ArrayList<TriangleShape> triangles=new ArrayList<>();
//		
//		for(int i=0;i!=vec.size()/3;i++){
//			triangles.add(new TriangleShape(vec.get(i),vec.get(i+1),vec.get(i+2)));
//		}
//		Quat4f q=new Quat4f();
//		QuaternionUtil.setEuler(q, m.getRotation().x, m.getRotation().y, m.getRotation().z);
//		com.bulletphysics.linearmath.Transform transform=new com.bulletphysics.linearmath.Transform(new javax.vecmath.Matrix4f(q,new javax.vecmath.Vector3f(m.getPosition().x,m.getPosition().y,m.getPosition().z),m.getScale().x));
//		for(TriangleShape triangle:triangles){
//			
//			
//			shape.addChildShape(transform,new BvhTriangleMeshShape(triangle));
//		}
//		System.out.println(shape.getShapeType());
		
		return con;
	}
	
	
}
