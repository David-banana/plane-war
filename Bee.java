package feiJiDaZhan;

import java.awt.image.BufferedImage;

//小蜜蜂
public class Bee extends flyObject implements Award{
	int width;
	int height;
	int speed = 2;
	private int type;
	//移动（固定）
	public void step() {
		x += speed;
		y ++;	
		if(x > Main.WIDTH - width) {
			speed = -2;
		}
		if(x < 0 ) {
			speed = 2;
		}
	}
	public void type() {
		
	}
	public Bee() {
		 x = (int) (Math.random() * Main.WIDTH);
		 y = 0;
	img = Main.bee;
	width = img.getWidth();
	height = img.getHeight();
	type = (int) (Math.random() * 2);
	}
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}
}
