package feiJiDaZhan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Main extends JPanel{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 690;//定义窗口的长度和宽度
	Hero hero01 = new Hero();
	Hero hero = new Hero();
	flyObject[] flys = {};
	Bullet[] bullets = {};
	private int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
    private static final int PAUSE = 2;
	private static final int GAME_OVER = 3;
public static BufferedImage hero0;
public static BufferedImage hero1;
public static BufferedImage airplane;
public static BufferedImage bee;
public static BufferedImage bullet;
public static BufferedImage background;
public static BufferedImage start;
public static BufferedImage pause;
public static BufferedImage gameover;
	static {//读取图片资源
		try {
			hero0 = ImageIO.read(new File(Hero.class.getResource("hero1.png").getFile()));
			hero1 = ImageIO.read(new File(Hero.class.getResource("hero0.png").getFile()));
			bee = ImageIO.read(new File(Hero.class.getResource("bee.png").getFile()));
			bullet = ImageIO.read(new File(Hero.class.getResource("bullet.png").getFile()));
		    airplane= ImageIO.read(new File(Hero.class.getResource("airplane.png").getFile()));
		    background = ImageIO.read(new File(Hero.class.getResource("background.png").getFile()));
		    start= ImageIO.read(new File(Hero.class.getResource("start.png").getFile()));
		    pause= ImageIO.read(new File(Hero.class.getResource("pause.png").getFile()));
		    gameover= ImageIO.read(new File(Hero.class.getResource("gameover.png").getFile()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*启动执行代码*/
	
	
	//定义一个定时器
	Timer timer = new Timer();
	int i;
	public void action() {
		 MouseAdapter l = new MouseAdapter() {
	            @Override
	            public void mouseMoved(MouseEvent e) { // 鼠标移动
	                if (state == RUNNING) { // 运行状态下移动英雄机--随鼠标位置
	                    int x = e.getX();
	                    int y = e.getY();
	                    hero.moveTo(x, y);
	                }
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) { // 鼠标进入
	                if (state == PAUSE) { // 暂停状态下运行
	                    state = RUNNING;
	                }
	            }

	            @Override
	            public void mouseExited(MouseEvent e) { // 鼠标退出
	                if (state != GAME_OVER&&state!=START) { // 游戏未结束，则设置其为暂停
	                    state = PAUSE;
	                }
	            }

	            @Override
	            public void mouseClicked(MouseEvent e) { // 鼠标点击
	                switch (state) {
	                case START:
	                    state = RUNNING; // 启动状态下运行
	                    break;
	                case GAME_OVER: // 游戏结束，清理现场
	                    flys = new flyObject[0]; // 清空飞行物
	                    bullets = new Bullet[0]; // 清空子弹
	                    hero = new Hero(); // 重新创建英雄机
	          //          score = 0; // 清空成绩
	                    state = START; // 状态设置为启动
	                    break;
	                }
	            }
	        };
	        this.addMouseListener(l); // 处理鼠标点击操作
	        this.addMouseMotionListener(l); // 处理鼠标滑动操作

		//1000毫秒后，每个1000毫秒就做一次
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(state == RUNNING) { 
				//自动发射子弹
				i++;
				shoot();
				moveBullets();//移动子弹
				moveFlyObject();	//敌机小蜜蜂（飞行物）移动//自动生成敌机和小蜜蜂（飞行物）
				
				if(i % 2 == 0) {
					createFlyObject();
				}
				}
				repaint();
				}
		},1000,30);
}
	
	private void shoot() {
		Bullet b = hero.shoot();
		//将发射的子弹放入子弹中
		bullets = Arrays.copyOf(bullets,bullets.length + 1);
		//将子弹存入数组的最后的位置
		bullets[bullets.length - 1] = b;
	}
	private void moveBullets() {
		for(int i = 0; i < bullets.length; i++)
			bullets[i].step();
	}
	private void createFlyObject() {
		int random = (int) (Math.random() * 20);
		flyObject fo;
		if(random == 0) {
			fo = new Bee();
		}
		else {
			fo = new Enemy();
		}
		flys = Arrays.copyOf(flys, flys.length + 1);
		flys[flys.length - 1] = fo;
	}
	private void moveFlyObject() {
		for(int i = 0;i < flys.length; i++) {
			flys[i].step();
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);//调用父类的paint的方法
		paintBackground(g);
		paintScore(g);
		paintLife(g);
		paintHero(g);
		paintflyObject(g);		
		paintState(g);
	}
	public void paintState(Graphics g) {
		switch(state) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	private void paintScore(Graphics g) {
		
		Font font = new Font("微软雅黑", Font.BOLD, 18);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("分数:10",10,20);
	}
	private void paintLife(Graphics g) {
		
		Font font = new Font("微软雅黑", Font.BOLD, 18);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("生命值:3",10,40);
	}

	private void paintHero(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
	}
	
	private void paintflyObject(Graphics g) {
		flyObject bee = new Bee();
	    flyObject enemy = new Enemy();
		for (int i = 0;i < flys.length ;i ++ ){
			g.drawImage(flys[i].getImage(), flys[i].getX(), flys[i].getY(),null );
			}
		for (int i = 0;i < bullets.length ;i ++ ){
		flyObject bull = new Bullet(hero.getX()+hero.getWidth()/2, hero.getY()+bullet.getHeight());
		g.drawImage(bullets[i].getImage(), bullets[i].getX(),bullets[i].getY()+hero.getHeight(),null );
		}
	}
	Bee bee01 = new Bee();
	private void paintBee(Graphics g) {
		g.drawImage(bee01.img ,bee01.x, bee01.y,null);
	}
	Enemy enemy = new Enemy();
	private void paintEnemy(Graphics g) {
		g.drawImage(enemy.img, enemy.x, enemy.y,null);
	}
	BackGround background01 = new BackGround();
	private void paintBackground(Graphics g) {
		g.drawImage(background01.img, background01.x, background01.y,null);
	}
	Bullet bullet01 = new Bullet(i, i);
	private void paintBullet(Graphics g) {
		
	}
	
	
		public void showMe() {
			//新建窗体->JPanel
			JFrame window = new JFrame();//设置新建窗口
			window.setTitle("飞机大战");
			window.setVisible(true);//设置窗口可见性
			window.setSize(WIDTH, HEIGHT);//设置窗口固定大小
			window.setResizable(false);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setLocationRelativeTo(null);//设置窗口居中
			//Main panel = new Main();//调用JPanel无参构造器
			window.add(this);
			this.action();
	}
		
	public  void bang (Bullet bullet) {
		int index = -1;
		for(int i = 0; i < flys.length; i ++) {
			flyObject obj = flys[i];
			if(obj.shootBy(bullet)) {
				index = i;
				break;
			}
		}
		if(index != -1) {
			flyObject one = flys[index];
			flyObject temp = flys[index];
			flys[index] = flys[flys.length - 1];
			flys[flys.length - 1] = temp;
			flys = Arrays.copyOf(flys, flys.length - 1);
		}
	}
	
	public static void main(String[] args) {
		new Main().showMe();
	}

}
