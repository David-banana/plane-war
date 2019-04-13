package feiJiDaZhan;


//敌机
public class Enemy extends flyObject{
	private int width;
	private int height;
	private int blood;
	private int speed;
	//移动（数值固定移动）
	public void step() {
		y += speed;
	}
	public Enemy() {
		x = (int) (Math.random() * Main.WIDTH);
		y = 0;
		img = Main.airplane;
		width = img.getWidth();
		height = img.getHeight();
		speed = (int) ((Math.random() * 5) + 5);
	}
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}
}
