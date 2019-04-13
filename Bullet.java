package feiJiDaZhan;
//子弹
public class Bullet extends flyObject{
	int speed;
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		img = Main.bullet;
		width = img.getWidth();
		height = img.getHeight();		
	}
	//移动（竖直移动）
	public void step(){
		y -= 20;
	}
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}

}
