package me.Yaacob.Core;

public abstract class YnGine extends Canvas{

	public static boolean fullscreen=false,vsync=false;
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = "YnGine - "+title;
	}
	
}
