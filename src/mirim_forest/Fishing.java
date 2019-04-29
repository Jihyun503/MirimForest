package mirim_forest;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mirim_forest.House.PressListener;


public class Fishing extends JPanel {
	
	private Image img;
	int chk1 = 0, chk2 = 0, chk3 = 0, chk4 = 0, ck=0;
	int x, y, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;
	JLabel bug1, bug2, bug3;
	JLabel fish1, fish2, fish3, fish4, fish5;
	JLabel bugzzi;
	Toolkit tk;
	int point = 0;

	Fishing(Image img, Main_w frame) {
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
		
		ImageIcon bugs = new ImageIcon("bug.png");
        bug1 = new JLabel(bugs);
        bug2 = new JLabel(bugs);
        bug3 = new JLabel(bugs);
        ImageIcon zzis = new ImageIcon("zzzi.png");
        JLabel zzi = new JLabel(zzis);
        ImageIcon bugzzis = new ImageIcon("bugzzi.png");
        bugzzi = new JLabel(bugzzis);
        ImageIcon fish_1 = new ImageIcon("fish1.png");
        fish1 = new JLabel(fish_1);
        ImageIcon fish_2 = new ImageIcon("fish2.png");
        fish2 = new JLabel(fish_2);
        ImageIcon fish_3 = new ImageIcon("fish3.png");
        fish3 = new JLabel(fish_3);
        ImageIcon fish_4 = new ImageIcon("fish4.png");
        fish4 = new JLabel(fish_4);
        ImageIcon fish_5 = new ImageIcon("fish5.png");
        fish5 = new JLabel(fish_5);
        bug1.setBounds(30, 50, 35, 50);
        bug2.setBounds(70, 50, 35, 50);
        bug3.setBounds(110, 50, 35, 50);
        zzi.setBounds(500, 50, 80, 80);
        add(bug1);
        add(bug2);
        add(bug3);
        add(zzi);
        tk = Toolkit.getDefaultToolkit();
        
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
		this.setPreferredSize(new Dimension(1280, 900));
		
		bug1.addMouseListener(new MyMouseListener(bug1));
		bug2.addMouseListener(new MyMouseListener(bug2));
		bug3.addMouseListener(new MyMouseListener(bug3));
		
		zzi.addMouseListener(new MouseListener() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 if(chk1 == 1) {
					 remove(zzi);
					 add(bugzzi);
				     bugzzi.setBounds(500, 50, 80, 80);

					 add(fish1);
					 add(fish2);
					 add(fish3);
					 add(fish4);
					 add(fish5);
					 int rX1 = 0, rY1 = 0;
					 for(int i = 0; i < 5; i++) {
					 rX1 = (int)(Math.random() * 1100);
					 rY1 = (int)((Math.random() * 500)+200);
					 if(i == 0) {
						 fish1.setBounds(rX1, rY1, 65, 50);
					 }
					 else if(i == 1) {
						 fish2.setBounds(rX1, rY1, 130, 90);
					 }
					 else if(i == 2) {
						 fish3.setBounds(rX1, rY1, 65, 50);
					 }
					 else if(i == 3) {
						 fish4.setBounds(rX1, rY1, 130, 90);
					 }
					 else if(i == 4) {
						 fish5.setBounds(rX1, rY1, 70, 50);
					 }
				}
				     //fish1.setBounds(rX1, rY1, 100, 100);
					 setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					 //chk1 = 0;
				 }
				 
			  }
			 @Override
			 public void mouseEntered(MouseEvent e) {
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
			 }
			 @Override
			 public void mousePressed(MouseEvent e) {
			 }
			 @Override
			 public void mouseReleased(MouseEvent e) {				 
			 }
			 
		});
		
		bugzzi.addMouseListener(new MouseListener() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
					  Image bugzziImg = tk.getImage("bugzzi.png");
					  Point point = new Point(0,0);
					  Cursor cursor = tk.createCustomCursor(bugzziImg, point, "bugzzi");
					  setCursor(cursor);
					  remove(bugzzi);
					  repaint();
					  chk2 = 1;
					  ck++;
					  
			}
			 @Override
			 public void mouseEntered(MouseEvent e) {
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
			 }
			 @Override
			 public void mousePressed(MouseEvent e) {
			 }
			 @Override
			 public void mouseReleased(MouseEvent e) {				 
			 }
			 
		});
		
		this.addMouseListener(new MouseListener() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 if(chk2 == 1) {
					 x = e.getX();
					 y = e.getY();
					 x1 = fish1.getX();
					 y1 = fish1.getY();
					 x2 = fish2.getX();
					 y2 = fish2.getY();
					 x3 = fish3.getX();
					 y3 = fish3.getY();
					 x4 = fish4.getX();
					 y4 = fish4.getY();
					 x5 = fish5.getX();
					 y5 = fish5.getY();
					 add(bugzzi);
					 bugzzi.setLocation(x, y);
					 add(fish1);
					 add(fish2);
					 add(fish3);
					 add(fish4);
					 add(fish5);
					 chk1 = 0;
					 chk2 = 0;

				 Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						 do {
							 x = bugzzi.getX();
							 y = bugzzi.getY()+5;
							 
								 bugzzi.setLocation(x, y);
								
						if(y > 720) {
							remove(bugzzi);
							add(zzi);
							repaint();
						}
								 try{
									 Thread.sleep(70);
								 }
								 catch(Exception e){
									 bugzzi.setLocation(0, 0);

								 }
								 if(ck == 3) {
									 break;
								 }

							 }while(!(y > 720));

						 }
				 });
						t1.start();
						//t3³¡
						
						//t2½ÃÀÛ
						Thread t2 = new Thread(new Runnable() {
							@Override
							public void run() {
								 do {
									if(hit(fish1.getX(),fish1.getY(),fish1.getWidth(),fish1.getHeight())) {
										JOptionPane.showMessageDialog(null, "500Á¡ È¹µæ!");
										point+=500;
										remove(fish1);
										repaint();
										break;
									}
									else if(hit(fish2.getX(),fish2.getY(),fish2.getWidth(),fish2.getHeight())) {
										JOptionPane.showMessageDialog(null, "700Á¡ È¹µæ!");
										point+=500;
										remove(fish2);
										repaint();
										break;
									}

									else if(hit(fish3.getX(),fish3.getY(),fish3.getWidth(),fish3.getHeight())) {
										JOptionPane.showMessageDialog(null, "500Á¡ È¹µæ!");
										point+=500;
										remove(fish3);
										repaint();
										break;
									}
									else if(hit(fish4.getX(),fish4.getY(),fish4.getWidth(),fish4.getHeight())) {
										JOptionPane.showMessageDialog(null, "1000Á¡ È¹µæ!");
										point+=500;
										remove(fish4);
										repaint();
										break;
									}
									else if(hit(fish5.getX(),fish5.getY(),fish5.getWidth(),fish5.getHeight())) {
										JOptionPane.showMessageDialog(null, "500Á¡ È¹µæ!");
										point+=500;
										remove(fish5);
										repaint();
										break;
									}
									
									x1 = fish1.getX()-5;
									fish1.setLocation(x1, y1);
									x2 = fish2.getX()-8;
									fish2.setLocation(x2, y2);
									x3 = fish3.getX()-5;
									fish3.setLocation(x3, y3);
									x4 = fish4.getX()-8;
									fish4.setLocation(x4, y4);
									x5 = fish5.getX()-5;
									fish5.setLocation(x5, y5);
									
									if(x1 < 0) {
										do{
										x1 = fish1.getX()+5;
										fish1.setLocation(x1, y1);
										
									}while(!(x1 >1200));
								}
									else if(x2 < 0) {
										do {
											x2 = fish2.getX()+8;
											fish2.setLocation(x2, y2);
										}while(!(x2 >1200));
									}
									
									else if(x3 < 0) {
										do {
											x3 = fish3.getX()+5;
											fish3.setLocation(x3, y3);
										}while(!(x3 >1200));
									}
									
									else if(x4 < 0) {
										do {
											x4 = fish4.getX()+8;
											fish4.setLocation(x4, y4);
										}while(!(x4 >1200));
									}
									else if(x5 < 0) {
										do {
											x5 = fish5.getX()+5;
											fish5.setLocation(x5, y5);
										}while(!(x5 >1200));
									}
									 try {
										 Thread.sleep(60);
									 }
									 catch (Exception e) {
										 
									}
									 Mainpanel.pointPlus += point;
									 try {
										 TimeDB5(Mainpanel.pointPlus);
										 
									 }catch(ClassNotFoundException | SQLException e) {
										 e.printStackTrace();
									 }
									 
									 if(ck == 3) {
										 break;
									 }
									 
								 }while(!(x1 < 0));
							}
						 });
						t2.start();
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				 }
			}
			 @Override
			 public void mouseEntered(MouseEvent e) {
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
			 }
			 @Override
			 public void mousePressed(MouseEvent e) {
			 }
			 @Override
			 public void mouseReleased(MouseEvent e) {				 
			 }
			 
		});
	}
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
            if(((bugzzi.getX()<=x)&&(x<bugzzi.getX()+bugzzi.getWidth()))   
                    //Å¸°ÙÀÇ yÁÂÇ¥°¡ ÃÑ¾Ë yÁÂÇ¥º¸´Ù ÀÛ°Å³ª °°À¸¸ç ÃÑ¾Ë yÁÂÇ¥º¸´Ù Å¸°Ù yÁÂÇ¥ + Å¸°ÙÀÇ ¼¼·Î ±æÀÌ°¡ Å©¸é
                    &&((bugzzi.getY()<=y)&&(y<bugzzi.getY()+bugzzi.getHeight())))
                return true;
            
            else
                return false;
        }	
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	class MyMouseListener extends MouseAdapter{
		JLabel label;
		
		public MyMouseListener(JLabel label) {
			this.label = label;
		}
		 @Override
		 public void mouseClicked(MouseEvent e) {
			
			 if(e.getClickCount() == 1) {
				  Image bugImg = tk.getImage("bug.png");
				  Point point = new Point(0,0);
				  Cursor cursor = tk.createCustomCursor(bugImg, point, "bug");
				  setCursor(cursor);
				  chk1 = 1;
				  remove(label);
				  repaint();
			  }
			  else if(e.getClickCount() == 2) {
				  setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				  chk1 = 0;
			  }
			 
		  }

		 @Override
		 public void mouseEntered(MouseEvent e) {
		 }

		 @Override
		 public void mouseExited(MouseEvent e) {
		  
		 }

		 @Override
		 public void mousePressed(MouseEvent e) {
		 }

		 @Override
		 public void mouseReleased(MouseEvent e) {
			 
		 }
		 
	}
	public void TimeDB5(int p1) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	
			System.out.println("°ªÀ» ¹Ù²ãÁÙ °ÅÀÓ!");
			PreparedStatement ps2 = connection.prepareStatement("UPDATE user_table SET point=? WHERE name=?"); 
			ps2.setInt(1, p1);
			ps2.setString(2, Mainpanel.name);
			int res = ps2.executeUpdate();
			
			ps2.close();
		connection.close();
	}
	

		
	}

