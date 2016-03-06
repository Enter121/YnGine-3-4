package me.Yaacob.Environment.Components3D;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import me.Yaacob.Utils.Transform;

public abstract class Camera extends Transformable{
	
	
	Transform t;
public Camera() {
		t=new Transform(new Vector3f(0,0,0),new Vector3f(0,0,0),new Vector3f(1,1,1)){};
		// TODO Auto-generated constructor stub
		this.createProjection();
	}
float pitch=0,yaw=0;
Matrix4f pro;
float fov=70;
float NEAR_PLANE= 0.1f;
float FAR_PLANE =1000;

public void createProjection(){
    float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
      float y_scale = (float) ((1f / Math.tan(Math.toRadians(fov/2f))) * aspectRatio);
      float x_scale = y_scale / aspectRatio;
      float frustum_length = FAR_PLANE - NEAR_PLANE;
      pro = new Matrix4f();
      pro.m00 = x_scale;
      pro.m11 = y_scale;
      pro.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
      pro.m23 = -1;
      pro.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
      pro.m33 = 0;
	
}
public Matrix4f getProjection(){
	return pro;
}

}
