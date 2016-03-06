//package me.Yaacob.Environment.Components3D;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Random;
//
//import org.newdawn.slick.opengl.TextureLoader;
//
//import me.Yaacob.Core.TextureType;
//import me.Yaacob.Graphics.Texture;
//import me.Yaacob.Utils.Transform;
//
//public class Terrain extends Transformable{
//Model mo;
//private static final float AMPLITUDE = 40f;
//private static final int OCTAVES = 3;
//private static final float ROUGHNESS = 0.3f;
//private int xOffset = 0;
//private int zOffset = 0;
//float reflectivity=0.5f;
//float shineDamper=10f;
//int VERTEX_COUNT=256;
// public Transform getTransform() {
//	return t;
//}
// Random r=new Random();
//int seed;
// 
//public void setTransform(Transform t) {
//	this.t = t;
//}
//float SIZE=800;
//
//int id;
//float[] vertices;
//public Terrain(ModelRenderer m){
//	mo=new Model();
//	mo.mode=2;
//	int count = VERTEX_COUNT * VERTEX_COUNT;
//	vertices = new float[count * 3];
//	float[] normals = new float[count * 3];
//	float[] textureCoords = new float[count*2];
//	int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
//	int vertexPointer = 0;
//	for(int i=0;i<VERTEX_COUNT;i++){
//		for(int j=0;j<VERTEX_COUNT;j++){
//			vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
//			vertices[vertexPointer*3+1] = 0;
//			vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
//			normals[vertexPointer*3] = 0;
//			normals[vertexPointer*3+1] = 1;
//			normals[vertexPointer*3+2] = 0;
//			textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
//			textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
//			vertexPointer++;
//		}
//	}
//	int pointer = 0;
//	for(int gz=0;gz<VERTEX_COUNT-1;gz++){
//		for(int gx=0;gx<VERTEX_COUNT-1;gx++){
//			int topLeft = (gz*VERTEX_COUNT)+gx;
//			int topRight = topLeft + 1;
//			int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
//			int bottomRight = bottomLeft + 1;
//			indices[pointer++] = topLeft;
//			indices[pointer++] = bottomLeft;
//			indices[pointer++] = topRight;
//			indices[pointer++] = topRight;
//			indices[pointer++] = bottomLeft;
//			indices[pointer++] = bottomRight;
//		}
//	}
//	id=m.loadVAO(vertices, normals, indices, mo);
//	mo.id=id;
//}
//private float getSmoothNoise(int x, int z) {
//    float corners = (getNoise(x - 1, z - 1) + getNoise(x + 1, z - 1) + getNoise(x - 1, z + 1)
//            + getNoise(x + 1, z + 1)) / 16f;
//    float sides = (getNoise(x - 1, z) + getNoise(x + 1, z) + getNoise(x, z - 1)
//            + getNoise(x, z + 1)) / 8f;
//    float center = getNoise(x, z) / 4f;
//    return corners + sides + center;
//}
//private float getInterpolatedNoise(float x, float z){
//    int intX = (int) x;
//    int intZ = (int) z;
//    float fracX = x - intX;
//    float fracZ = z - intZ;
//     
//    float v1 = getSmoothNoise(intX, intZ);
//    float v2 = getSmoothNoise(intX + 1, intZ);
//    float v3 = getSmoothNoise(intX, intZ + 1);
//    float v4 = getSmoothNoise(intX + 1, intZ + 1);
//    float i1 = interpolate(v1, v2, fracX);
//    float i2 = interpolate(v3, v4, fracX);
//    return interpolate(i1, i2, fracZ);
//}
//private float interpolate(float a, float b, float blend){
//    double theta = blend * Math.PI;
//    float f = (float)(1f - Math.cos(theta)) * 0.5f;
//    return a * (1f - f) + b * f;
//}
//
//public float generateHeight(float x, float z) {
//    float total = 0;
//    float d = (float) Math.pow(2, OCTAVES-1);
//    for(int i=0;i<OCTAVES;i++){
//        float freq = (float) (Math.pow(2, i) / d);
//        float amp = (float) Math.pow(ROUGHNESS, i) * AMPLITUDE;
//        total += getInterpolatedNoise((x+xOffset)*freq/4, (z + zOffset)*freq) * amp/4;
//    }
//    return total;
//}
// 
//public Terrain(ModelRenderer m , String tex){
//	mo=new Model();
//	//seed=r.nextInt(1000000);
//	mo.tex=true;
//	mo.mode=1;
//	Random r=new Random();
//	int count = VERTEX_COUNT * VERTEX_COUNT;
//	vertices = new float[count * 3];
//	float[] normals = new float[count * 3];
//	float[] textureCoords = new float[count*2];
//	int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
//	int vertexPointer = 0;
//
//	for(int i=0;i<VERTEX_COUNT;i++){
//		for(int j=0;j<VERTEX_COUNT;j++){
//			vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
//			vertices[vertexPointer*3+1] = this.generateHeight((float)j/((float)VERTEX_COUNT - 1) * SIZE,  (float)i/((float)VERTEX_COUNT - 1) * SIZE);
//			vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
//			normals[vertexPointer*3] = 0;
//			normals[vertexPointer*3+1] = 1;
//			normals[vertexPointer*3+2] = 0;
//			textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1)*100;
//			textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1)*100;
//			vertexPointer++;
//		}
//	}
//	int pointer = 0;
//	for(int gz=0;gz<VERTEX_COUNT-1;gz++){
//		for(int gx=0;gx<VERTEX_COUNT-1;gx++){
//			int topLeft = (gz*VERTEX_COUNT)+gx;
//			int topRight = topLeft + 1;
//			int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
//			int bottomRight = bottomLeft + 1;
//			indices[pointer++] = topLeft;
//			indices[pointer++] = bottomLeft;
//			indices[pointer++] = topRight;
//			indices[pointer++] = topRight;
//			indices[pointer++] = bottomLeft;
//			indices[pointer++] = bottomRight;
//		}
//	}
//	
//
//	id=m.loadVAO(vertices,textureCoords, normals, indices, mo);
//	mo.id=id;
//if(ModelRenderer.type==TextureType.POT){
//	try {
//		mo.t=TextureLoader.getTexture("PNG", new FileInputStream(new File(tex)));
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}else{
//	mo.nt=new Texture(new File(tex));
//}
//}
//
//public void render(ModelRenderer r){
//	r.render(this);
//}
//public float getNoise(float x,float z){
//	r.setSeed((long) (x*4324+z*3432+seed));
//	return (r.nextFloat() *2f -1f);
//}
//
//public void setY(float x, float y){
//	for(int i=0;i!=vertices.length;i++){
//		//if(vertices[)
//	}
//}
//
//public Terrain(ModelRenderer m , String tex, String bump){
//	mo=new Model();
//	mo.tex=true;
//	mo.tang=true;
//	mo.mode=0;
//	int count = VERTEX_COUNT * VERTEX_COUNT;
//	vertices = new float[count * 3];
//	float[] normals = new float[count * 3];
//	float[] textureCoords = new float[count*2];
//	int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
//	int vertexPointer = 0;
//	Random r=new Random();
//	for(int i=0;i<VERTEX_COUNT;i++){
//		for(int j=0;j<VERTEX_COUNT;j++){
//			vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
//			vertices[vertexPointer*3+1] = r.nextInt(50)/10;
//			vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
//			normals[vertexPointer*3] = 0;
//			normals[vertexPointer*3+1] = 1;
//			normals[vertexPointer*3+2] = 0;
//			textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1)*100;
//			textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1)*100;
//			vertexPointer++;
//		}
//	}
//	int pointer = 0;
//	for(int gz=0;gz<VERTEX_COUNT-1;gz++){
//		for(int gx=0;gx<VERTEX_COUNT-1;gx++){
//			int topLeft = (gz*VERTEX_COUNT)+gx;
//			int topRight = topLeft + 1;
//			int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
//			int bottomRight = bottomLeft + 1;
//			indices[pointer++] = topLeft;
//			indices[pointer++] = bottomLeft;
//			indices[pointer++] = topRight;
//			indices[pointer++] = topRight;
//			indices[pointer++] = bottomLeft;
//			indices[pointer++] = bottomRight;
//		}
//	}
//	if(ModelRenderer.type==TextureType.POT){
//		try {
//			mo.t=TextureLoader.getTexture("PNG", new FileInputStream(new File(tex)));
//			mo.n=TextureLoader.getTexture("PNG", new FileInputStream(new File(bump)));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}else{
//		mo.nt=new Texture(new File(tex));
//		mo.nn=new Texture(new File(bump));
//	}
//	
//	id=m.loadVAO(vertices,textureCoords, normals, indices,new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f}, mo);
//	mo.id=id;
//}
//
//}
