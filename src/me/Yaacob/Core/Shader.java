package me.Yaacob.Core;

import java.io.File;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.bulletphysics.linearmath.Transform;

public abstract class Shader {

	int id;
	int vid;
	int fid;
	private static FloatBuffer mat4f = BufferUtils.createFloatBuffer(16);
	private static FloatBuffer mat3f = BufferUtils.createFloatBuffer(9);
	
	public Shader(File v,File f){
		vid=this.load(v, GL20.GL_VERTEX_SHADER);
		fid=this.load(f, GL20.GL_FRAGMENT_SHADER);
		id=GL20.glCreateProgram();
		GL20.glAttachShader(id, vid);
		GL20.glAttachShader(id, fid);
		this.bindAttributes();
		GL20.glLinkProgram(id);
		GL20.glValidateProgram(id);
		UniformLocation();
	}
	
	public abstract void bindAttributes();
	
	
	protected int getUniformLocation(String name){
		return GL20.glGetUniformLocation(this.id, name);
	}

	
	public void loadFloat(int loc , float val){
		GL20.glUniform1f(loc, val);
	}
	
	public void loadVector3f(int loc , Vector3f v){
		GL20.glUniform3f(loc, v.x, v.y, v.z);
	}
	public void loadVector2f(int loc , Vector2f v){
		GL20.glUniform2f(loc, v.x, v.y);
	}
	
	
	public void loadMatrix4f(int loc , Matrix4f v){
	v.store(mat4f);
	mat4f.flip();
	GL20.glUniformMatrix4(loc, false, mat4f);
	}
	public void loadTransform4f(int loc , Transform v){
		float[] m= new float[16];
		FloatBuffer buf=BufferUtils.createFloatBuffer(16);
		v.getOpenGLMatrix(m);
		buf.clear();
		buf.put(m);
		buf.flip();
		
				GL20.glUniformMatrix4(loc, false, buf);
	}
	public void loadMatrix3f(int loc , Matrix3f v){
		v.store(mat3f);
		mat3f.flip();
		GL20.glUniformMatrix3(loc, false, mat3f);
		}
	public void loadBool(int loc , boolean v){
		int f=0;
		if(v){
			f=1;
		}

	GL20.glUniform1i(loc, f);
	}
	public void loadInt(int loc,int v){
		GL20.glUniform1i(loc, v);
	}

	public abstract void UniformLocation();
	
	
	public void start(){
		GL20.glUseProgram(id);
	}

	public void stop(){
		GL20.glUseProgram(0);
	}
	
	public void clean(){
		GL20.glDetachShader(id, vid);
		GL20.glDetachShader(id, fid);
	GL20.glDeleteShader(vid);
	GL20.glDeleteShader(fid);
		GL20.glDeleteProgram(id);
	}
	public void bindAttribute(int att , String name){
		GL20.glBindAttribLocation(id, att, name);
	}
	public int load(File f, int type){
		if(!f.exists()){
			System.err.println("FILE NOT EXIST");
		
		}
		String content= Y.readFile(f);
		
	int	id=GL20.glCreateShader(type);
		GL20.glShaderSource(id, content);
		GL20.glCompileShader(id);
		if(GL20.glGetShader(id, GL20.GL_COMPILE_STATUS)==GL11.GL_FALSE){
			System.out.println(GL20.glGetShaderInfoLog(id, 500));
			
			if(type==GL20.GL_VERTEX_SHADER){
				System.err.println("Couldn't compile vertex shader");
			}else{
				System.err.println("Couldn't compile fragment shader");
			}
			System.exit(-1);
		}
		return id;
	
		
	}
	public void load(UType u,int loc,Object v){
	switch(u){
	case vec3:
		this.loadVector3f(loc, ((Vector3f)v));
		break;
	case vec2:
		this.loadVector2f(loc, ((Vector2f)v));
		break;
	case mat3:
		this.loadMatrix3f(loc, ((Matrix3f)v));
		break;
	case mat4:
		this.loadMatrix4f(loc, ((Matrix4f)v));
		break;
	case bool:
		this.loadBool(loc, (boolean)v);
		break;
	case Int:
		this.loadInt(loc, (int)v);
		break;
	case Float:
		this.loadFloat(loc, (float)v);
		break;
	case Transform:
		this.loadTransform4f(loc, (Transform)v);
	
	}
	}
}


