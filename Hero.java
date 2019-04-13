package feiJiDaZhan;

import java.awt.image.BufferedImage;
import com.sun.javafx.iio.ImageStorage;
import com.sun.prism.Image;

//我方飞机
public class Hero extends flyObject{
	public BufferedImage[] getImgs() {
		return imgs;
	}
	public void setImgs(BufferedImage[] imgs) {
		this.imgs = imgs;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
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
	private int life;//生命值
	private int x;//x坐标
	private int y;//y坐标
	private int width;//宽度
	private int height;//高度
	private int index = 0;
	private BufferedImage[] imgs = {};
	public Hero() {
		life = 3;
		x = 150;
		y = 400;
		imgs = new BufferedImage[] {Main.hero0,Main.hero1};
		img = Main.hero0;
		width = img.getWidth();
		height = img.getHeight();
		
	}
	public Bullet shoot() {
		Bullet bullet = new Bullet(this.getX()+this.getWidth()/2,this.getY()-this.getHeight());
		return bullet;
	}
	public void moveTo(int x, int y) {
		this.x = x - width/2;
		this.y = y - height/2;
		
	}
	public void step() {
		if(imgs.length > 0) {
			 img = imgs[index++/10%imgs.length];
		}
	}
}
