package mirim_forest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Apple extends JPanel{

	private Image img;
	JLabel bread1, bread2, bread3, bread4, bread5, bread6;
	JLabel mouse1, mouse2, mouse3, mouse4, mouse5, mouse6, mouse7;
	JLabel mong;
	JLabel pointAdd;
	int point = 0, showPoint = 0;
	String pointS;
	Timer m_timer;
	TimerTask m_task;
	int ck = 0;
	int ck1 = 0, ck2 = 0, ck3 = 0, ck4 = 0, ck5 = 0;
	int x = 700, y = 660;
	int x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0, x11 = 0, x12 = 0;
	int y1 = 0, y2 = 0, y3 = 0, y4 = 0, y5 = 0, y6 = 0, y7 = 0, y8 = 0, y9 = 0, y10 = 0, y11 = 0, y12 = 0;
	
	
	public Apple(Image img, Main_w frame) {
		this.img = img;
		setLayout(null);
		ImageIcon back_btn = new ImageIcon("back.png");
		JButton back = new JButton(back_btn);
		
		this.add(back);
		back.setBounds(1000, 10, 180, 60);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.change("back");
			}
		});
		
		//point = Integer.toString(pointPlus);
		//pointAdd = new JLabel(point);
		//add(pointAdd);
		//pointAdd.setBounds(0, 0, 100, 100);
		
		ImageIcon doraemong = new ImageIcon("mong.png");
		mong = new JLabel(doraemong);
		
		ImageIcon bread = new ImageIcon("bread.png");
		ImageIcon mouse = new ImageIcon("mouse.png");
		bread1 = new JLabel(bread);
		bread2 = new JLabel(bread);
		bread3 = new JLabel(bread);
		bread4 = new JLabel(bread);
		bread5 = new JLabel(bread);
		bread6 = new JLabel(bread);
		
		//mouse1 = new JLabel(mouse);
		mouse2 = new JLabel(mouse);
		//mouse3 = new JLabel(mouse);
		//mouse4 = new JLabel(mouse);
		mouse5 = new JLabel(mouse);
		mouse6 = new JLabel(mouse);
		mouse7 = new JLabel(mouse);
		
		x1 = (int)(Math.random() * 1100);
		x2 = (int)(Math.random() * 1100);
		x3 = (int)(Math.random() * 1100);
		x4 = (int)(Math.random() * 1100);
		x5 = (int)(Math.random() * 1100);
		
		x6 = (int)(Math.random() * 1100);
		x7 = (int)(Math.random() * 1100);
		x8 = (int)(Math.random() * 1100);
		x9 = (int)(Math.random() * 1100);
		x10 = (int)(Math.random() * 1100);
		x11 = (int)(Math.random() * 1100);
		x12 = (int)(Math.random() * 1100);
		
		mong.setLocation(x, y);
		mong.setSize(130, 160);
		
		bread1.setBounds(x1, 0, 70, 53);
		bread2.setBounds(x2, 0, 70, 53);
		bread4.setBounds(x4, 0, 70, 53);
		bread5.setBounds(x5, 0, 70, 53);
		
		mouse2.setBounds(x7, 0, 75, 85);
		mouse5.setBounds(x10, 0, 75, 85);
		mouse6.setBounds(x11, 0, 75, 85);
		mouse7.setBounds(x12, 0, 75, 85);
		
		
		add(mong);
		
		add(bread1);
		add(bread2);
		add(bread4);
		add(bread5);
		
		add(mouse2);
		add(mouse5);
		add(mouse6);
		add(mouse7);
		
		pointAdd = new JLabel("");
		add(pointAdd);
		pointAdd.setBounds(0, 0, 100, 100);

				frame.setFocusable(true);
			    frame.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						this.KeyPressed(e);
					}

					private void KeyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_LEFT) {
							x -= 10;
							mong.setLocation(x, y);
						}
						if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
							x += 10;
							mong.setLocation(x, y);
						}
						
					}
				});
	}
	//Ãæµ¹Ã³¸®
	private boolean hit(int x, int y, int w, int h){
        
        if(targetContains(x,y)
                ||targetContains(x+w-1,y)
                ||targetContains(x+w-1,y+h-1)
                ||targetContains(x,y+h-1))
            return true;
        else
            return false;
    }
	
    private boolean targetContains(int x, int y){
        //Å¸°ÙÀÇ xÁÂÇ¥°¡ ÃÑ¾Ë xÁÂÇ¥º¸´Ù ÀÛ°Å³ª °°À¸¸ç ÃÑ¾Ë xÁÂÇ¥º¸´Ù Å¸°Ù xÁÂÇ¥ + Å¸°ÙÀÇ °¡·Î ±æÀÌ°¡ Å©°í 
        if(((mong.getX()<=x)&&(x<mong.getX()+mong.getWidth()))   
                //Å¸°ÙÀÇ yÁÂÇ¥°¡ ÃÑ¾Ë yÁÂÇ¥º¸´Ù ÀÛ°Å³ª °°À¸¸ç ÃÑ¾Ë yÁÂÇ¥º¸´Ù Å¸°Ù yÁÂÇ¥ + Å¸°ÙÀÇ ¼¼·Î ±æÀÌ°¡ Å©¸é
                &&((mong.getY()-30==y)&&(y<mong.getY()+mong.getHeight())))
            return true;
        
        else
            return false;
    }

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		
	}
	
	public void start() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				 bread1.setVisible(true);
				 bread2.setVisible(true);
				 bread4.setVisible(true);
				 bread5.setVisible(true);
				
				 do {
					 ck1 = 0;
					 ck2 = 0;
					 ck3 = 0;
					 ck4 = 0;
					 ck5 = 0;
					 x1 = bread1.getX();
					 y1 = bread1.getY()+6;
					 x2 = bread2.getX();
					 y2 = bread2.getY()+5;
					 x4 = bread4.getX();
					 y4 = bread4.getY()+3;
					 x5 = bread5.getX();
					 y5 = bread5.getY()+5;
					 
					 x7 = mouse2.getX();
					 y7 = mouse2.getY()+7;
					 x10 = mouse5.getX();
					 y10 = mouse5.getY()+6;
					 x11 = mouse6.getX();
					 y11 = mouse6.getY()+5;
					 x12 = mouse7.getX();
					 y12 = mouse7.getY()+5;
					 
						 bread1.setLocation(x1, y1);
						 bread2.setLocation(x2, y2);
						 bread4.setLocation(x4, y4);
						 bread5.setLocation(x5, y5);
						 
						 mouse2.setLocation(x7, y7);
						 mouse5.setLocation(x10, y10);
						 mouse6.setLocation(x11, y11);
						 mouse7.setLocation(x12, y12);
						 
						 
					
						 if(y1 > 800) {
							 bread1.setLocation(x1, 0);
								do{
									x1 = (int)(Math.random() * 1100);
									y1 = bread1.getY()+((int)((Math.random() * 10)+1));
									bread1.setLocation(x1, y1);
									bread1.setVisible(true);
									
							}while(!(y1 > 0));
						}
						 
						 else if(y2 > 800) {
							 bread2.setLocation(x2, 0);
								do{
									x2 = (int)(Math.random() * 1100);
									y2 = bread2.getY()+((int)((Math.random() * 10)+1));
									bread2.setLocation(x2, y2);
									bread2.setVisible(true);
								
							}while(!(y2 > 0));
						}
						 
						 else if(y4 > 800) {
							 bread4.setLocation(x4, 0);
								do{
									x4 = (int)(Math.random() * 1100);
									y4 = bread4.getY()+((int)((Math.random() * 10)+1));
									bread4.setLocation(x4, y4);
									bread4.setVisible(true);
								
							}while(!(y4 > 0));
						}
						 
						 else if(y5 > 800) {
							 bread5.setLocation(x5, 0);
								do{
									x5 = (int)(Math.random() * 1100);
									y5 = bread5.getY()+((int)((Math.random() * 10)+1));
									bread5.setLocation(x5, y5);
									bread5.setVisible(true);
								
							}while(!(y5 > 0));
						}
						 
						 else if(y7 > 800) {
							 mouse2.setLocation(x7, 0);
								do{
									x7 = (int)(Math.random() * 1100);
									y7 = mouse2.getY()+((int)((Math.random() * 10)+1));
									mouse2.setLocation(x7, y7);
								
							}while(!(y7 > 0));
						}
						 
						 else if(y10 > 800) {
							 mouse5.setLocation(x10, 0);
								do{
									x10 = (int)(Math.random() * 1100);
									y10 = mouse5.getY()+((int)((Math.random() * 10)+1));
									mouse5.setLocation(x10, y10);
								
							}while(!(y10 > 0));
						}
						 
						 else if(y11 > 800) {
							 mouse6.setLocation(x11, 0);
								do{
									x11 = (int)(Math.random() * 1100);
									y11 = mouse6.getY()+((int)((Math.random() * 10)+1));
									mouse6.setLocation(x11, y11);
								
							}while(!(y11 > 0));
						}
						 
						 else if(y12 > 800) {
							 mouse7.setLocation(x12, 0);
								do{
									x12 = (int)(Math.random() * 1100);
									y12 = mouse7.getY()+((int)((Math.random() * 10)+1));
									mouse5.setLocation(x12, y12);
								
							}while(!(y12 > 0));
						}
						 
						 if(hit(mouse2.getX(), mouse2.getY(), mouse2.getWidth(), mouse2.getHeight()))
						 {	
							 System.out.println("¸¶¿ì½º 2");
							 //Mainpanel.pointPlus = Mainpanel.pointPlus + point;
						 	System.out.println(Mainpanel.pointPlus);
						 	/*
							 try {
								 TimeDB3(Mainpanel.pointPlus);
								 
							 }catch(ClassNotFoundException | SQLException e) {
								 e.printStackTrace();
							 }*/
						 	 
							break;
								
						 }
						 else if(hit(mouse5.getX(), mouse5.getY(), mouse5.getWidth(), mouse5.getHeight()))
						 {
							  System.out.println("¸¶¿ì½º 5");
							  //Mainpanel.pointPlus = Mainpanel.pointPlus + point;
							  System.out.println(Mainpanel.pointPlus);
							  /*
								 try {
									 TimeDB3(Mainpanel.pointPlus);
									 
								 }catch(ClassNotFoundException | SQLException e) {
									 e.printStackTrace();
								 }*/
								break;
						 }
						 else if(hit(mouse6.getX(), mouse6.getY(), mouse6.getWidth(), mouse6.getHeight()))
						 {
							 System.out.println("¸¶¿ì½º 6");
							 //Mainpanel.pointPlus = Mainpanel.pointPlus + point;
							 System.out.println(Mainpanel.pointPlus);
							 /*
							 try {
								 TimeDB3(Mainpanel.pointPlus);
								 
							 }catch(ClassNotFoundException | SQLException e) {
								 e.printStackTrace();
							 }*/
								break;
						 }
						 else if(hit(mouse7.getX(), mouse7.getY(), mouse7.getWidth(), mouse7.getHeight()))
						 {
							 System.out.println("¸¶¿ì½º 7");
							 //Mainpanel.pointPlus = Mainpanel.pointPlus + point;
							 System.out.println(Mainpanel.pointPlus);
							 /*
							 try {
								 TimeDB3(Mainpanel.pointPlus);
								 
							 }catch(ClassNotFoundException | SQLException e) {
								 e.printStackTrace();
							 }*/
								break;
						 }
						 else if(hit(bread1.getX(), bread1.getY(), bread1.getWidth(), bread1.getHeight()))
						 {	 point+=100;
						 	 showPoint+=100;
						 	 ck2 = 1;
							 bread1.setVisible(false);
							 System.out.println("1");
							 repaint();
							 
						 }
						 else if(hit(bread2.getX(), bread2.getY(), bread2.getWidth(), bread2.getHeight()))
						 {
							 point+=100;
							 showPoint+=100;
							 ck2 = 1;
							 bread2.setVisible(false);
							 System.out.println("2");
							 repaint();
						 }
						 else if(hit(bread4.getX(), bread4.getY(), bread4.getWidth(), bread4.getHeight()))
						 {
							 point+=100;
							 showPoint+=100;
							 ck2 = 1;
							 bread4.setVisible(false);
							 System.out.println("4");
							 repaint();
						 }
						 else if(hit(bread5.getX(), bread5.getY(), bread5.getWidth(), bread5.getHeight()))
						 {
							 point+=100;
							 showPoint+=100;
							 ck2 = 1;
							 bread5.setVisible(false);
							 System.out.println("5");
							 repaint();
						 }
						 
						 
						 try {
							 Thread.sleep(60);
						 }
						 catch (Exception e) {
							 
						}
						 pointAdd.setText(""+showPoint);
						 
						 
						 if(ck2 == 1) {
							 Mainpanel.pointPlus += point;
							 try {
								 TimeDB3(Mainpanel.pointPlus);
								 
							 }catch(ClassNotFoundException | SQLException e) {
								 e.printStackTrace();
							 }
							 point = 0;
							}
						 ck2 = 0;
						 
						 
						 if(ck1 == 1) {
							 /*
							 Mainpanel.pointPlus += point;
							 try {
								 TimeDB3(Mainpanel.pointPlus);
								 
							 }catch(ClassNotFoundException | SQLException e) {
								 e.printStackTrace();
							 }*/
							 break;
							}
						 
						 
					 }while(!(y > 720));
			 }
		 });
				t1.start();
				Time();
				
				
	}
	
	public void Time() {
		Timer myTimer = new Timer();
		TimerTask myTask = new TimerTask() {

			@Override
			public void run() {
				ck1 = 1;
			}
			
		};
		myTimer.schedule(myTask, 30000);
	}
	
	public void TimeDB3(int p) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	
			//System.out.println("°ªÀ» ¹Ù²ãÁÙ °ÅÀÓ!");
			PreparedStatement ps1 = connection.prepareStatement("UPDATE user_table SET point=? WHERE name=?"); 
			ps1.setInt(1, p);
			ps1.setString(2, Mainpanel.name);
			int res = ps1.executeUpdate();
			
			ps1.close();
		connection.close();
	}

	
}
