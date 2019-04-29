package mirim_forest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mirim_forest.House.PressListener;

class ImagePanel extends JPanel {
	private Image img;
	private int x = 750;
	private int y = 500;
	JLabel lb, rami;
	int plus = 0;
	String item;
	long m2 = 0;
	public static int chk = 0;
	int ggender;
	public static Connection conn=null;
	ImageIcon icon11, icon22;
	
	public ImagePanel(Image img, Main_w frame) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
		this.setPreferredSize(new Dimension(1280, 900));
		
		
		ImageIcon apple = new ImageIcon("apple.png");
        ImageIcon home = new ImageIcon("home.png");
        ImageIcon fishing = new ImageIcon("fishing.png");
        ImageIcon store = new ImageIcon("store.png");
        ImageIcon stars = new ImageIcon("stars.png");
        ImageIcon swim = new ImageIcon("swim.png");
        ImageIcon farm = new ImageIcon("farm.png");
        JLabel star = new JLabel(stars);
        JButton btn1 = new JButton(apple);
        JButton btn2 = new JButton(home);
        JButton btn3 = new JButton(fishing);
        JButton btn4 = new JButton(store);
        JButton btn5 = new JButton(swim);
        JButton btn6 = new JButton(farm);
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
        this.add(btn6);
        this.setLayout(null);
        btn1.setBounds(162, 330, 200, 60);
		btn2.setBounds(574, 540, 180, 60);
		btn3.setBounds(1000, 330, 180, 60);
		btn4.setBounds(1000, 750, 180, 60);
		btn5.setBounds(902, 500, 180, 60);
		btn6.setBounds(162, 750, 180, 60);
		
		if(plus % 2 == 0) {
		int starX = (int)(Math.random() * 1000);
		int starY = (int)(Math.random() * 1000);
		this.add(star);
		star.setBounds(starX, starY, 50, 50);
		}

		
		star.addMouseListener(new MouseListener() {
		
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 int items = (int)(Math.random() * 12) + 1;
				 switch (items) {
				case 1:
					JOptionPane.showMessageDialog(null, "숨겨진 아이템 !의자!를 획득했습니다");
					Mainpanel.chair = 1;
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "숨겨진 아이템 !책상!를 획득했습니다");
					Mainpanel.desk = 1;
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "숨겨진 아이템 !옷장!을 획득했습니다");
					Mainpanel.closet = 1;
					break;
				case 4:
					JOptionPane.showMessageDialog(null, "단순한 반짝임이었나..");
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "아무것도 아니었다");
					break;
				case 6:
					JOptionPane.showMessageDialog(null, "깨진 유리조각이네...");
					break;
				case 7:
					JOptionPane.showMessageDialog(null, "깨진 유리조각이네...");
					break;
				case 8:
					JOptionPane.showMessageDialog(null, "숨겨진 아이템 !옷장!를 획득했습니다");
					Mainpanel.closet = 1;
					break;
				case 9:
					JOptionPane.showMessageDialog(null, "단순한 반짝임이었나..");
					break;
				case 10:
					JOptionPane.showMessageDialog(null, "숨겨진 아이템 !침대!를 획득했습니다");
					Mainpanel.bed = 1;
					break;

				default:
					JOptionPane.showMessageDialog(null, "아무것도 아니었다");
					break;
				}
				 remove(star);
				 //repaint();
				 
				 try {
						TimeDB(Mainpanel.desk, Mainpanel.bed, Mainpanel.chair, Mainpanel.closet);
					}catch(ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
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
		
		/*
		ImageIcon icon1 = new ImageIcon("mong2.png");
		Image img1 = icon1.getImage();
		Image imgn1 = img1.getScaledInstance(210, 260, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon11 = new ImageIcon(imgn1);
		lb = new JLabel(icon11);
		*/
		
		
		
		this.setVisible(true);
		
		if(Mainpanel.chk == 1) {
			
			if(Mainpanel.clothes == 0) {
				ImageIcon icon1 = new ImageIcon("mong2.png");
				Image img1 = icon1.getImage();
				Image imgn1 = img1.getScaledInstance(210, 260, java.awt.Image.SCALE_SMOOTH);
				icon11 = new ImageIcon(imgn1);
			}
			else if(Mainpanel.clothes == 1) {
				ImageIcon icon1 = new ImageIcon("chMong.png");
				Image img1 = icon1.getImage();
				Image imgn1 = img1.getScaledInstance(210, 260, java.awt.Image.SCALE_SMOOTH);
				icon11 = new ImageIcon(imgn1);
			}
			
			lb = new JLabel(icon11);
			System.out.println("남자로 들어옴");
			lb.setLocation(750, 500);
			lb.setSize(210, 300);
			
			this.add(lb);
			
			lb.setVisible(true);
			
			
			frame.setFocusable(true);
			frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				this.KeyPressed(e);
			}

			private void KeyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					x -= 10;
					lb.setLocation(x, y);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					x += 10;
					lb.setLocation(x, y);
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					y -= 10;
					lb.setLocation(x, y);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {						
					y += 10;
					lb.setLocation(x, y);
				}
				
			}
		});
	}
		
		else if(Mainpanel.chk == 2) {
			if(Mainpanel.clothes == 0) {
			System.out.println("여자로 들어옴");
			ImageIcon icon2 = new ImageIcon("rami.png");
			Image img2 = icon2.getImage();
			Image imgn2 = img2.getScaledInstance(210, 260, java.awt.Image.SCALE_SMOOTH);
			icon22 = new ImageIcon(imgn2);
			}
			else if(Mainpanel.clothes == 1)
			{
				ImageIcon icon2 = new ImageIcon("chRami.png");
				Image img2 = icon2.getImage();
				Image imgn2 = img2.getScaledInstance(210, 260, java.awt.Image.SCALE_SMOOTH);
				icon22 = new ImageIcon(imgn2);
			}
			
			rami = new JLabel(icon22);
			this.add(rami);
			rami.setLocation(750, 500);
			rami.setSize(210, 260);
			rami.setVisible(true);
			frame.setFocusable(true);
		    frame.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					this.KeyPressed(e);
				}

				private void KeyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						x -= 10;
						rami.setLocation(x, y);
					}
					if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						x += 10;
						rami.setLocation(x, y);
					}
					if(e.getKeyCode() == KeyEvent.VK_UP) {
						y -= 10;
						rami.setLocation(x, y);
					}
					if(e.getKeyCode() == KeyEvent.VK_DOWN) {						
						y += 10;
						rami.setLocation(x, y);
					}
					
				}
			});
		}
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.change("apple");
				chk = 1;
				System.out.println(chk);
			}
		});
		add(btn1);
	
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.change("house");
				plus++;
			}
		});
		add(btn2);
		
		btn3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.change("fishing");
			}
		});
		add(btn3);
		
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.change("store");
				plus++;
			}
		});
		add(btn4);
		
		btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.change("swimming");
				plus++;
			}
		});
		add(btn5);
		
		btn6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m2 = System.currentTimeMillis();
				try {
					TimeDB2(m2);
				}catch(ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				frame.change("farm");
				
			}
		});
		add(btn6);
		 
	    
	}
	public void TimeDB2(long m2) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	
			System.out.println("값을 바꿔줄 거임!");
			PreparedStatement ps1 = connection.prepareStatement("UPDATE user_table SET stime=? WHERE name=?");
			ps1.setLong(1, m2);
			ps1.setString(2, Mainpanel.name);
			int res = ps1.executeUpdate();
			
			ps1.close();
		connection.close();
	}
	
	public void TimeDB(int p, int p1, int p2, int p3) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

			//System.out.println("값을 바꿔줄 거임!");
			PreparedStatement ps1;
			ps1 = connection.prepareStatement("UPDATE user_table SET desk=?, bed=?, chair=?, closet=? WHERE name=?"); 
			ps1.setInt(1, p);
			ps1.setInt(2, p1);
			ps1.setInt(3, p2);
			ps1.setInt(4, p3);
			ps1.setString(5, Mainpanel.name);
			int res = ps1.executeUpdate();
			
			ps1.close();
			
		connection.close();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	
}
