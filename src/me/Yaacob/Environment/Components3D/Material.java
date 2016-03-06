package me.Yaacob.Environment.Components3D;

public class Material {

	public String tex;
	public String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTexture() {
		return tex;
	}
	public void setTexture(String tex) {
		this.tex = tex;
	}
	public String getNormal() {
		return norm;
	}
	public void setNormal(String norm) {
		this.norm = norm;
	}
	public String norm;
	public Material(String name,String tex, String norm) {
		super();
		this.tex = tex;
		this.norm = norm;
		this.name=name;
	}
	
}
