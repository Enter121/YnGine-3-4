package me.Yaacob.Environment.Components3D;

import java.io.File;

import me.Yaacob.Core.Shader;
import me.Yaacob.Core.UType;

public class ModelShader extends Shader{

	public ModelShader() {
		super(new File("src/shaders/ModelVertex.glsl"), new File("src/shaders/ModelFragment.glsl"));
		// TODO Auto-generated constructor stub

	}

	int transform , tex, norm ,camera , projection , tang , texb ,shineDamper,reflectivity , lcolor,lpos,lighting;
	
	@Override
	public void bindAttributes() {
		// TODO Auto-generated method stub
		this.bindAttribute(0, "position");	
		this.bindAttribute(1, "normal");	
		this.bindAttribute(2, "texture");
		this.bindAttribute(3, "tangents");	
	}

	@Override
	public void UniformLocation() {
		// TODO Auto-generated method stub
	transform=this.getUniformLocation("transform");
	tex=this.getUniformLocation("tex");
	norm=this.getUniformLocation("norm");
	camera=this.getUniformLocation("camera");
	projection=this.getUniformLocation("projection");
	tang=this.getUniformLocation("tang");
	texb=this.getUniformLocation("texb");
	shineDamper=this.getUniformLocation("shineDamper");
	reflectivity=this.getUniformLocation("reflectivity");
	lpos=this.getUniformLocation("lpos");
	lcolor=this.getUniformLocation("lcolor");
	lighting=this.getUniformLocation("lighting");
	}

}
