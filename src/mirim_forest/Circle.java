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
	 
	int[] cx ={0, 0, 0}; // 배경 스크롤 속도 제어용 변수
	int bx = 0; // 전체 배경 스크롤 용 변수
	
	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	
	int cnt; 
	
	int player_Speed; // 유저의 캐릭터가 움직이는 속도를 조절할 변수
	int enemy_speed; // 적 이동 속도 설정
	int player_Status = 0; 
	// 유저 캐릭터 상태 체크 변수 0 : 평상시, 1: 미사일발사, 2: 충돌
	int game_Score; // 게임 점수 계산
	int player_Hitpoint; // 플레이어 캐릭터의 체력
	
	Thread th;
	 
	Toolkit tk = Toolkit.getDefaultToolkit();
	
	Image[] Player_img;
	//플레이어 애니메이션 표현을 위해 이미지를 배열로 받음
	Image BackGround_img; //배경화면 이미지
	Image[] Cloud_img; //움직이는 배경용 이미지배열
	
	Image Enemy_img; 
	 
	ArrayList Enemy_List = new ArrayList();
	ArrayList Explosion_List = new ArrayList();
	//다수의 폭발 이펙트를 처리하기 위한 배열
	
	Image buffImage; 
	Graphics buffg;
	
	Enemy en; 
	
	Explosion ex; //폭발 이펙트용 클래스 접근 키

	game_Frame(){
		 init();
		 start();
		  
		 setTitle("슈팅 게임 만들기");
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
		      
		      if(n%2 == 0) {//짝수
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
	//플레이어 애니메이션 표현을 위해 파일이름을 
	//넘버마다 나눠 배열로 담는다.
	
		BackGround_img = new ImageIcon("pic//bg.png").getImage();
		//전체 배경화면 이미지를 받습니다.
	
		Cloud_img = new Image[3];
		for(int i = 0 ; i <Cloud_img.length ; ++i){
		Cloud_img[i] = new ImageIcon("pic//cake" + i + ".png").getImage();
	}
//구름을 3개 동시에 그리는데 편의상 배열로 3개를 동시에 받는다.

//파일이름을 넘버마다 나눠 배열로 담는다.
//모든 이미지는 Swing의 ImageIcon으로 받아 이미지 넓이,높이 // 값을 바로 얻을 수 있게 한다.

game_Score = 0;//게임 스코어 초기화
player_Hitpoint = 3;//최초 플레이어 체력
  
player_Speed = 5; //유저 캐릭터 움직이는 속도 설정
enemy_speed = 7;//적이 날라오는 속도 설정

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
//플레이어와 적의 충돌을 판정하여
//boolean값을 리턴 받아 true면 아래를 실행합니다. 

//player_Hitpoint --; //플레이어 체력을 1깍습니다.
Enemy_List.remove(i); //적을 제거합니다.
game_Score += 100; 
//제거된 적으로 게임스코어를 10 증가시킵니다.

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
//적 움직임 속도를 추가로 받아 적을 생성한다.

}
}

public boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2){
//기존 충돌 판정 소스를 변경합니다.
//이제 이미지 변수를 바로 받아 해당 이미지의 넓이, 높이값을
//바로 계산합니다.

boolean check = false;

if ( Math.abs( ( x1 + img1.getWidth(null) / 2 )  
- ( x2 + img2.getWidth(null) / 2 ))  
< ( img2.getWidth(null) / 2 + img1.getWidth(null) / 2 )
 && Math.abs( ( y1 + img1.getHeight(null) / 2 )  
- ( y2 + img2.getHeight(null) / 2 ))  
< ( img2.getHeight(null)/2 + img1.getHeight(null)/2 ) ){
//이미지 넓이, 높이값을 바로 받아 계산합니다.


check = true;//위 값이 true면 check에 true를 전달합니다.
}else{ check = false;}

return check; //check의 값을 메소드에 리턴 시킵니다.

}


public void paint(Graphics g){
buffImage = createImage(f_width, f_height); 
buffg = buffImage.getGraphics();

update(g);
}

public void update(Graphics g){

Draw_Background(); //배경 이미지 그리기 메소드 실행
Draw_Player(); //플레이어를 그리는 메소드 이름 변경

Draw_Enemy(); 

Draw_StatusText();//상태 표시 텍스트를 그리는 메소드 실행

g.drawImage(buffImage, 0, 0, this); 
}

public void Draw_Background(){
//배경 이미지를 그리는 부분입니다.

buffg.clearRect(0, 0, f_width, f_height);
//화면 지우기 명령은 이제 여기서 실행합니다.

if ( bx > - 3500){
//기본 값이 0인 bx가 -3500 보다 크면 실행

buffg.drawImage(BackGround_img, bx, 0, this);
bx -= 1;
//bx를 0에서 -1만큼 계속 줄이므로 배경이미지의 x좌표는
//계속 좌측으로 이동한다. 그러므로 전체 배경은 천천히 
//좌측으로 움직이게 된다.
   
}else { bx = 0; }


for (int i = 0;  i < cx.length ; ++i){

if ( cx[i] < 1400){
cx[i] += 5 + i * 3 ;
} else { cx[i] = 0; }

buffg.drawImage(Cloud_img[i], 1200 - cx[i], 50+i*200,  this);
//3개의 구름 이미지를 각기 다른 속도 값으로 좌측으로 움직임.
}
}

public void Draw_Player(){ 

	switch (player_Status){ 
	
	case 0 : // 평상시
	if((cnt / 5 %2) == 0){ 
	buffg.drawImage(Player_img[1], x, y, this);
	
	}else { buffg.drawImage(Player_img[2], x, y, this); }
	//엔진쪽에서 불을 뿜는 이미지를 번갈아 그려준다.
	
	break;
	
	
	case 1 : // 충돌
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

public void Draw_StatusText(){ //상태 체크용  텍스트를 그립니다.

buffg.setFont(new Font("Defualt", Font.BOLD, 20));
//폰트 설정을 합니다.  기본폰트, 굵게, 사이즈 20

buffg.drawString("SCORE : " + game_Score, 1000, 70);
//좌표 x : 1000, y : 70에 스코어를 표시합니다.
}
public void KeyProcess(){
if(KeyUp == true) {
if( y > 20 ) y -= 5;
//캐릭터가 보여지는 화면 위로 못 넘어가게 합니다.

player_Status = 0;
//이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
}

if(KeyDown == true) {
if( y+ Player_img[0].getHeight(null) < f_height ) y += 5;
//캐릭터가 보여지는 화면 아래로 못 넘어가게 합니다.

player_Status = 0;
//이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
}

if(KeyLeft == true) {
if ( x > 0 ) x -= 5;
//캐릭터가 보여지는 화면 왼쪽으로 못 넘어가게 합니다.

player_Status = 0;
//이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
}

if(KeyRight == true) {
if ( x + Player_img[0].getWidth(null) < f_width ) x += 5;
//캐릭터가 보여지는 화면 오른쪽으로 못 넘어가게 합니다.

player_Status = 0;
//이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
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

int speed; // 적 이동 속도 변수를 추가

Enemy(int x, int y, int speed ) {
this.x = x;
this.y = y;

this.speed = speed;
// 객체 생성시 속도 값을 추가로 받습니다.

}
public void move(){ 
x -= speed;// 적이동속도만큼 이동
}
}

class Explosion{ 
// 여러개의 폭발 이미지를 그리기위해 클래스를 추가하여 객체관리 

int x; //이미지를 그릴 x 좌표
int y; //이미지를 그릴 y 좌표
int ex_cnt; //이미지를 순차적으로 그리기 위한 카운터
int damage; //이미지 종류를 구분하기 위한 변수값

Explosion(int x, int y, int damage){
this.x = x;
this.y = y;
this.damage = damage;
}
}
