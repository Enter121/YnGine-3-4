//package me.Yaacob.Environment.Components2D;
//
//import java.awt.Font;
//
//import org.lwjgl.input.Keyboard;
//import org.lwjgl.input.Mouse;
//import org.lwjgl.opengl.Display;
//
//import me.Yaacob.Core.Canvas;
//import me.Yaacob.Core.EngineManager;
//import me.Yaacob.Core.Y;
//import me.Yaacob.Graphics.Texture;
//import me.Yaacob.Utils.Rectangle;
//
//public abstract class MenuScreen extends Canvas{
//
//	int x=1,y=1;
//	
//	float mX=0,mY=0;
//	
//	Rectangle mouse= new Rectangle(1, 1, 0, 0){};
//	
//	int space=10;
//	
//	public boolean active=false;
//	
//	
//	public int getSpace() {
//		return space;
//	}
//
//	public void setSpace(int space) {
//		this.space = space;
//	}
//
//	public int getX() {
//		return x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public void nextEntry(){
//		current++;
//		if(current>=entries.length)current=0;
//	}
//	public void previousEntry(){
//		if(current<=0)current=entries.length;
//		current--;
//	}
//	
//	public void submit(){
//		entries[current].submit();
//	}
//	
//	public void setY(int y) {
//		this.y = y;
//	}
//
//	int width,height;
//	boolean bg=false;
//	
//	public Texture getBackground() {
//		return background;
//	}
//
//	public void setBackground(Texture background) {
//		this.background = background;
//		if(background!=null)bg=true;
//	}
//
//	Texture background;
//	
//	int current=0;
//	
//	public Option[] getEntries() {
//		return entries;
//		
//	}
//
//	public void setEntries(Option[] entries) {
//		this.entries = entries;
//	}
//
//	public Option[] entries={new Option("OPTION 1"){
//		
//		public void submit(){
//			Y.print("OPTION 1");
//		}} , new Option("OPTION 2"){
//			public void submit(){
//				Y.print("OPTION 2");
//			}
//		}};
//	
//	
//	me.Yaacob.Environment.Components2D.Font f;
//	Texture t;
//	
//	public int time;
//	
//	EngineManager m;
//
//	public MenuScreen(EngineManager m , int size){
//	x=Display.getWidth()/2;
//	y=Display.getHeight()/2;
//	f=new me.Yaacob.Environment.Components2D.Font(new java.awt.Font("Arial" , Font.BOLD,size));
//	space=f.font.getHeight();
//	this.m=m;
//	
//	
//	
//	
//	
//	}
//	
//	public void render(){
//		mX=Mouse.getX();
//		mY=Mouse.getY();
//		mouse.setX(mX);
//		mouse.setY(mY);
//		//System.out.println(mX+" "+mY);
//		
////	if(Mouse.next()){
////		if(Mouse.getEventButtonState()){
////
////			for(int i=0;i!=entries.length;i++){
////			for(int j=0;j!=f.strect.size();j++){
////				if(f.strect.get(j).getName()==entries[i].getName()){
////					if(mouse.overlaps(f.strect.get(j))){
////					submit();
////				}
////				}
////			}
////			}
////		
////		}
////	}
//		
//		
//		
//		orender();
//	if(bg)background.render(Display.getWidth()/2-background.getWidth()/2, Display.getHeight()/2-background.getHeight()/2 , 0 , 1);		
//		int yy=y;
//	for(int i=0;i!=entries.length;i++){
//	
////		for(int j=0;j!=f.strect.size();j++){
////			if(f.strect.get(j).getName()==entries[i].getName()){
////				if(mouse.overlaps(f.strect.get(j))){
////				current=i;
////			}
////			}
////		}
//		if(current==entries.length-i-1)f.render( ">", x-space, yy);
//		
//		
//		f.render(entries[entries.length-i-1].getName(), x,yy);
//		yy+=space;
//	}
//	
//	}
//	public boolean isActive() {
//		return active;
//	}
//
//	public void setActive(boolean active) {
//		this.active = active;
//	}
//
//	public void orender(){
//		
//	}
//	
//	public void keyPressed(int e){
//		
//	}
//	public void keyReleased(int e){
//		
//	}
//	public void keyTyped(int e){
//		if(e==Keyboard.KEY_UP){
//			this.previousEntry();
//		}
//		if(e==Keyboard.KEY_DOWN){
//			this.nextEntry();
//		}
//		if(e==Keyboard.KEY_RETURN){
//			this.submit();
//		}
//	}
//	public void exit(){
//		m.setCanvas(null);
//	}
//
//	@Override
//	public void init() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void update() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void supdate() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void end() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseMoved(float x, float y) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseClicked(float x, float y) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseDragged(float x, float y) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(float x, float y) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(float x, float y) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}
