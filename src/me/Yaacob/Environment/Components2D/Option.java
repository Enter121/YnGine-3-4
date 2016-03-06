package me.Yaacob.Environment.Components2D;

public abstract class Option {

	String name;
	
	public Option(String name){
		this.name=name;
	}
	
	public abstract void submit();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
