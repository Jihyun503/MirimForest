package mirim_forest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import java.io.File;

public class Circle {
	public static void main(String[] ar){
		game_Frame fms = new game_Frame();
	}
}

class game_Frame extends JFrame implements KeyListener, Runnable{ 
	Image[] appleArray = new Image[5];
	
	int f_width ;
	int f_height ;
	 
	int x, y; 
	 
	int[] cx ={0, 0, 0}; // ��� ��ũ�� �ӵ� ����� ����
	int bx = 0; // ��ü ��� ��ũ�� �� ����
	
	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	
	int cnt; 
	
	int player_Speed; // ������ ĳ���Ͱ� �����̴� �ӵ��� ������ ����
	int enemy_speed; // �� �̵� �ӵ� ����
	int player_Status = 0; 
	// ���� ĳ���� ���� üũ ���� 0 : ����, 1: �̻��Ϲ߻�, 2: �浹
	int game_Score; // ���� ���� ���
	int player_Hitpoint; // �÷��̾� ĳ������ ü��
	
	Thread th;
	 
	Toolkit tk = Toolkit.getDefaultToolkit();
	
	Image[] Player_img;
	//�÷��̾� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
	Image BackGround_img; //���ȭ�� �̹���
	Image[] Cloud_img; //�����̴� ���� �̹����迭
	
	Image Enemy_img; 
	 
	ArrayList Enemy_List = new ArrayList();
	ArrayList Explosion_List = new ArrayList();
	//�ټ��� ���� ����Ʈ�� ó���ϱ� ���� �迭
	
	Image buffImage; 
	Graphics buffg;
	
	Enemy en; 
	
	Explosion ex; //���� ����Ʈ�� Ŭ���� ���� Ű

	game_Frame(){
		 init();
		 start();
		  
		 setTitle("���� ���� �����");
		 setSize(f_width, f_height);
		  
		 Dimension screen = tk.getScreenSize();
		
		 int f_xpos = (int)(screen.getWidth() / 2 - f_width / 2);
		 int f_ypos = (int)(screen.getHeight() / 2 - f_height / 2);
		
		 setLocation(f_xpos, f_ypos);
		 setResizable(false);
		 setVisible(true);
	}
	public void init(){ 
		x = 100;
		y = 100;
		f_width = 1200;
		f_height = 600;
		
		
		for (int i = 0; i < 5; i++) {
		      int n = (int)(Math.random() * 10) + 1;
		      
		      if(n%2 == 0) {//¦��
    	   		ImageIcon icon = new ImageIcon("pic//dd.png");
    	   		Image originImg1 = icon.getImage();
    	   		Image changedImg1= originImg1.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
    	   		ImageIcon Icon1 = new ImageIcon(changedImg1);
    	   		Enemy_img = Icon1.getImage();
    	   		appleArray[i] = Icon1.getImage();
    	   		}
		     else if(n%2==1){
    	   		ImageIcon icon2 = new ImageIcon("pic//app.png");
    	   		Image originImg2 = icon2.getImage();
    	   		Image changedImg2= originImg2.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
   		   		ImageIcon Icon2 = new ImageIcon(changedImg2);
   		   		//Enemy_img = Icon2.getImage();
   		   		appleArray[i] = Icon2.getImage();
   	   		}
		    System.out.println(n);  
		      
		}

		Player_img = new Image[5];
		for(int i = 0 ; i < Player_img.length ; ++i){
			Player_img[i] = new ImageIcon("pic//mong" + i + ".png").getImage();
		}
	//�÷��̾� �ִϸ��̼� ǥ���� ���� �����̸��� 
	//�ѹ����� ���� �迭�� ��´�.
	
		BackGround_img = new ImageIcon("pic//bg.png").getImage();
		//��ü ���ȭ�� �̹����� �޽��ϴ�.
	
		Cloud_img = new Image[3];
		for(int i = 0 ; i <Cloud_img.length ; ++i){
		Cloud_img[i] = new ImageIcon("pic//cake" + i + ".png").getImage();
	}
//������ 3�� ���ÿ� �׸��µ� ���ǻ� �迭�� 3���� ���ÿ� �޴´�.

//�����̸��� �ѹ����� ���� �迭�� ��´�.
//��� �̹����� Swing�� ImageIcon���� �޾� �̹��� ����,���� // ���� �ٷ� ���� �� �ְ� �Ѵ�.

game_Score = 0;//���� ���ھ� �ʱ�ȭ
player_Hitpoint = 3;//���� �÷��̾� ü��
  
player_Speed = 5; //���� ĳ���� �����̴� �ӵ� ����
enemy_speed = 7;//���� ������� �ӵ� ����

}
public void start(){
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
addKeyListener(this);

th = new Thread(this); 
th.start(); 

}

public void run(){ 
try{ 
while(true){
KeyProcess(); 
EnemyProcess();

repaint(); 

Thread.sleep(20);
cnt ++;
}
}catch (Exception e){}
}


public void EnemyProcess(){

for (int i = 0 ; i < Enemy_List.size() ; ++i ){ 
en = (Enemy)(Enemy_List.get(i)); 
en.move(); 
if(en.x < -200){ 
Enemy_List.remove(i); 
}

if(Crash(x, y, en.x, en.y, Player_img[0], Enemy_img)){
//�÷��̾�� ���� �浹�� �����Ͽ�
//boolean���� ���� �޾� true�� �Ʒ��� �����մϴ�. 

//player_Hitpoint --; //�÷��̾� ü���� 1����ϴ�.
Enemy_List.remove(i); //���� �����մϴ�.
game_Score += 100; 
//���ŵ� ������ ���ӽ��ھ 10 ������ŵ�ϴ�.

}
}


if ( cnt % 200 == 0 ){ 
en = new Enemy(f_width + 100, 100, enemy_speed);
Enemy_List.add(en); 
en = new Enemy(f_width + 100, 200, enemy_speed);
Enemy_List.add(en);
en = new Enemy(f_width + 100, 300, enemy_speed);
Enemy_List.add(en);
en = new Enemy(f_width + 100, 400, enemy_speed);
Enemy_List.add(en);
en = new Enemy(f_width + 100, 500, enemy_speed);
Enemy_List.add(en);
//�� ������ �ӵ��� �߰��� �޾� ���� �����Ѵ�.

}
}

public boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2){
//���� �浹 ���� �ҽ��� �����մϴ�.
//���� �̹��� ������ �ٷ� �޾� �ش� �̹����� ����, ���̰���
//�ٷ� ����մϴ�.

boolean check = false;

if ( Math.abs( ( x1 + img1.getWidth(null) / 2 )  
- ( x2 + img2.getWidth(null) / 2 ))  
< ( img2.getWidth(null) / 2 + img1.getWidth(null) / 2 )
 && Math.abs( ( y1 + img1.getHeight(null) / 2 )  
- ( y2 + img2.getHeight(null) / 2 ))  
< ( img2.getHeight(null)/2 + img1.getHeight(null)/2 ) ){
//�̹��� ����, ���̰��� �ٷ� �޾� ����մϴ�.


check = true;//�� ���� true�� check�� true�� �����մϴ�.
}else{ check = false;}

return check; //check�� ���� �޼ҵ忡 ���� ��ŵ�ϴ�.

}


public void paint(Graphics g){
buffImage = createImage(f_width, f_height); 
buffg = buffImage.getGraphics();

update(g);
}

public void update(Graphics g){

Draw_Background(); //��� �̹��� �׸��� �޼ҵ� ����
Draw_Player(); //�÷��̾ �׸��� �޼ҵ� �̸� ����

Draw_Enemy(); 

Draw_StatusText();//���� ǥ�� �ؽ�Ʈ�� �׸��� �޼ҵ� ����

g.drawImage(buffImage, 0, 0, this); 
}

public void Draw_Background(){
//��� �̹����� �׸��� �κ��Դϴ�.

buffg.clearRect(0, 0, f_width, f_height);
//ȭ�� ����� ����� ���� ���⼭ �����մϴ�.

if ( bx > - 3500){
//�⺻ ���� 0�� bx�� -3500 ���� ũ�� ����

buffg.drawImage(BackGround_img, bx, 0, this);
bx -= 1;
//bx�� 0���� -1��ŭ ��� ���̹Ƿ� ����̹����� x��ǥ��
//��� �������� �̵��Ѵ�. �׷��Ƿ� ��ü ����� õõ�� 
//�������� �����̰� �ȴ�.
   
}else { bx = 0; }


for (int i = 0;  i < cx.length ; ++i){

if ( cx[i] < 1400){
cx[i] += 5 + i * 3 ;
} else { cx[i] = 0; }

buffg.drawImage(Cloud_img[i], 1200 - cx[i], 50+i*200,  this);
//3���� ���� �̹����� ���� �ٸ� �ӵ� ������ �������� ������.
}
}

public void Draw_Player(){ 

	switch (player_Status){ 
	
	case 0 : // ����
	if((cnt / 5 %2) == 0){ 
	buffg.drawImage(Player_img[1], x, y, this);
	
	}else { buffg.drawImage(Player_img[2], x, y, this); }
	//�����ʿ��� ���� �մ� �̹����� ������ �׷��ش�.
	
	break;
	
	
	case 1 : // �浹
	break;
	
	}

}


public void Draw_Enemy(){ 
	for (int i = 0 ; i < Enemy_List.size() ; ++i ){
		en = (Enemy)(Enemy_List.get(i));
		for(Image img : appleArray) {
		buffg.drawImage(img, en.x, en.y, this);
		}
	}
}

public void Draw_StatusText(){ //���� üũ��  �ؽ�Ʈ�� �׸��ϴ�.

buffg.setFont(new Font("Defualt", Font.BOLD, 20));
//��Ʈ ������ �մϴ�.  �⺻��Ʈ, ����, ������ 20

buffg.drawString("SCORE : " + game_Score, 1000, 70);
//��ǥ x : 1000, y : 70�� ���ھ ǥ���մϴ�.
}
public void KeyProcess(){
if(KeyUp == true) {
if( y > 20 ) y -= 5;
//ĳ���Ͱ� �������� ȭ�� ���� �� �Ѿ�� �մϴ�.

player_Status = 0;
//�̵�Ű�� �������� �÷��̾� ���¸� 0���� �����ϴ�.
}

if(KeyDown == true) {
if( y+ Player_img[0].getHeight(null) < f_height ) y += 5;
//ĳ���Ͱ� �������� ȭ�� �Ʒ��� �� �Ѿ�� �մϴ�.

player_Status = 0;
//�̵�Ű�� �������� �÷��̾� ���¸� 0���� �����ϴ�.
}

if(KeyLeft == true) {
if ( x > 0 ) x -= 5;
//ĳ���Ͱ� �������� ȭ�� �������� �� �Ѿ�� �մϴ�.

player_Status = 0;
//�̵�Ű�� �������� �÷��̾� ���¸� 0���� �����ϴ�.
}

if(KeyRight == true) {
if ( x + Player_img[0].getWidth(null) < f_width ) x += 5;
//ĳ���Ͱ� �������� ȭ�� ���������� �� �Ѿ�� �մϴ�.

player_Status = 0;
//�̵�Ű�� �������� �÷��̾� ���¸� 0���� �����ϴ�.
}
}

	public void keyPressed(KeyEvent e){
	  
	switch(e.getKeyCode()){
	case KeyEvent.VK_UP :
	KeyUp = true;
	break;
	case KeyEvent.VK_DOWN :
	KeyDown = true;
	break;
	case KeyEvent.VK_LEFT :
	KeyLeft = true;
	break;
	case KeyEvent.VK_RIGHT :
	KeyRight = true;
	break;
	}
	}
	public void keyReleased(KeyEvent e){
	  
	switch(e.getKeyCode()){
	case KeyEvent.VK_UP :
	KeyUp = false;
	break;
	case KeyEvent.VK_DOWN :
	KeyDown = false;
	break;
	case KeyEvent.VK_LEFT :
	KeyLeft = false;
	break;
	case KeyEvent.VK_RIGHT :
	KeyRight = false;
	break;
	}
	}
	public void keyTyped(KeyEvent e){}

}


class Enemy{ 
int x;
int y;

int speed; // �� �̵� �ӵ� ������ �߰�

Enemy(int x, int y, int speed ) {
this.x = x;
this.y = y;

this.speed = speed;
// ��ü ������ �ӵ� ���� �߰��� �޽��ϴ�.

}
public void move(){ 
x -= speed;// ���̵��ӵ���ŭ �̵�
}
}

class Explosion{ 
// �������� ���� �̹����� �׸������� Ŭ������ �߰��Ͽ� ��ü���� 

int x; //�̹����� �׸� x ��ǥ
int y; //�̹����� �׸� y ��ǥ
int ex_cnt; //�̹����� ���������� �׸��� ���� ī����
int damage; //�̹��� ������ �����ϱ� ���� ������

Explosion(int x, int y, int damage){
this.x = x;
this.y = y;
this.damage = damage;
}
}
