package mirim_forest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.corba.se.impl.monitoring.MonitoredAttributeInfoFactoryImpl;

class House extends JPanel {
	private Image img;
	JLabel item1, item2, item3, item4, item5, item6;
	boolean Entered, Pressed, chk1, chk2, chk3, chk4, chk5, chk6;
	int rX, rY;
	int x, y;
	public static Connection conn=null;

	public House(Image img, Main_w frame) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
		this.setPreferredSize(new Dimension(1280, 900));

		ImageIcon back_btn = new ImageIcon("back.png");
		JButton back = new JButton(back_btn);
		ImageIcon clothes_btn = new ImageIcon("btn_clothes.png");
		JButton clothes = new JButton(clothes_btn);


		this.add(back);
		back.setBounds(1000, 680, 180, 60);
		this.add(clothes);
		clothes.setBounds(1000, 750, 180, 60);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.change("back");
			}
		});
		
		clothes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.change("closet");
			}
		});

		// 드래그
		setLayout(null); // 레이아웃 널
		
		if(Mainpanel.desk == 1) {
			ImageIcon desk = new ImageIcon("desk.png");
			Image deskk = desk.getImage();
			item1 = new JLabel(desk); // 레이블초기화
			item1.setLocation(Mainpanel.deskX, Mainpanel.deskY); // 위치지정
			item1.setSize(100, 100); // 크기 지정
			add(item1); // 패널에 추가 명령
			//chk1 = true;
			item1.addMouseListener(new PressListener(item1));
			item1.addMouseMotionListener(new DragListener(item1,1));
		}
		if(Mainpanel.bed == 1) {
			ImageIcon bed = new ImageIcon("bed.png");
			Image beed = bed.getImage();
			item2 = new JLabel(bed); // 레이블초기화
			item2.setLocation(Mainpanel.bedX, Mainpanel.bedY); // 위치지정
			item2.setSize(100, 100); // 크기 지정
			add(item2); // 패널에 추가 명령
			chk2 = true;
			item2.addMouseListener(new PressListener(item2));
			item2.addMouseMotionListener(new DragListener(item2,2));
		}
		if(Mainpanel.chair == 1) {
			ImageIcon chair = new ImageIcon("chair.png");
			Image chari = chair.getImage();
			item3 = new JLabel(chair); // 레이블초기화
			item3.setLocation(Mainpanel.chairX, Mainpanel.chairY); // 위치지정
			item3.setSize(100, 100); // 크기 지정
			add(item3); // 패널에 추가 명령
			chk3 = true;
			item3.addMouseListener(new PressListener(item3));
			item3.addMouseMotionListener(new DragListener(item3,3));
		}
		if(Mainpanel.sofa == 1) {
			ImageIcon sofa = new ImageIcon("sofa.png");
			Image soffa = sofa.getImage();
			item4 = new JLabel(sofa); // 레이블초기화
			item4.setLocation(Mainpanel.sofaX, Mainpanel.sofaY); // 위치지정
			item4.setSize(100, 100); // 크기 지정
			add(item4); // 패널에 추가 명령
			chk5 = true;
			item4.addMouseListener(new PressListener(item4));
			item4.addMouseMotionListener(new DragListener(item4,4));
		}
		if(Mainpanel.closet == 1) {
			ImageIcon closet = new ImageIcon("closet.png");
			Image closets = closet.getImage();
			item5 = new JLabel(closet); // 레이블초기화
			item5.setLocation(Mainpanel.closetX, Mainpanel.closetY); // 위치지정
			item5.setSize(100, 100); // 크기 지정
			add(item5); // 패널에 추가 명령
			chk4 = true;
			item5.addMouseListener(new PressListener(item5));
			item5.addMouseMotionListener(new DragListener(item5,5));
		}
		setVisible(true);
		
	}

	class PressListener extends MouseAdapter {
		JLabel label;

		public PressListener(JLabel label) {
			this.label = label;
		}

		public void mousePressed(MouseEvent e) {
			rX = e.getXOnScreen() - label.getX();
			rY = e.getYOnScreen() - label.getY();
		}
	}

	class DragListener extends MouseMotionAdapter {
		JLabel label;
		private int chk1;

		public DragListener(JLabel label, int chk1) {
			this.label = label;
			this.chk1 = chk1;
		}

		public void mouseDragged(MouseEvent e) {
			x = e.getXOnScreen() - rX;
			y = e.getYOnScreen() - rY;
			label.setLocation(x, y);
			if(chk1 == 1) {
				Mainpanel.deskX = x;
				Mainpanel.deskY = y;
				System.out.println("desk"+x+y);
			}
			else if(chk1 == 2){
				Mainpanel.bedX = x;
				Mainpanel.bedY = y;
			}
			else if(chk1 == 3) {
				Mainpanel.chairX = x;
				Mainpanel.chairY = y;
			}
			else if(chk1 == 5) {
				Mainpanel.closetX = x;
				Mainpanel.closetY = y;
			}
			else if(chk1 == 4) {
				Mainpanel.sofaX = x;
				Mainpanel.sofaY = y;
			}
			
			try {
				 TimeDB(Mainpanel.deskX, Mainpanel.deskY, Mainpanel.bedX, Mainpanel.bedY, Mainpanel.chairX,
						 Mainpanel.chairY, Mainpanel.closetX, Mainpanel.closetY, Mainpanel.sofaX, Mainpanel.sofaY);
				 
			 }catch(ClassNotFoundException | SQLException e1) {
				 e1.printStackTrace();
			 }
		}
		
		
	}
	
	public void TimeDB(int p, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9) 
			throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	
			//System.out.println("값을 바꿔줄 거임!");
			PreparedStatement ps1;
			ps1 = connection.prepareStatement("UPDATE user_table SET deskX=?, deskY=?, bedX=?, bedY=?, chairX=?, chairY=?, closetX=?, closetY=?, sofaX=?, sofaY=? WHERE name=?"); 
			ps1.setInt(1, p);
			ps1.setInt(2, p1);
			ps1.setInt(3, p2);
			ps1.setInt(4, p3);
			ps1.setInt(5, p4);
			ps1.setInt(6, p5);
			ps1.setInt(7, p6);
			ps1.setInt(8, p7);
			ps1.setInt(9, p8);
			ps1.setInt(10, p9);
			ps1.setString(11, Mainpanel.name);
			int res = ps1.executeUpdate();
			
			ps1.close();
			
		connection.close();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
