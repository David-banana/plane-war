package feiJiDaZhan;

import java.awt.image.BufferedImage;

//飞行物-父类
public abstract class flyObject {
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public BufferedImage getImage() {
		return img;
	}
	public void setImage(BufferedImage image) {
		this.img = image;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	protected  int x;
	protected  int y;
	protected  BufferedImage img;
	protected int width;
	protected int height;
	public abstract void step();
	public boolean shootBy(Bullet bullet) {
		int x = bullet.x;
		int y =  bullet.y;
		return this.x<x && x<this.x+width && this.y<y && y<this.y+height; 
	}
}
