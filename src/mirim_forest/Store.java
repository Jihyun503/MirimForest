package mirim_forest;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Store extends JPanel{
	
	private Image img;

	Store(Image img, Main_w frame) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
		this.setPreferredSize(new Dimension(1280, 900));
		
		ImageIcon store_btn = new ImageIcon("get.png");
		JButton btn = new JButton(store_btn);

		ImageIcon back_btn = new ImageIcon("back.png");
		JButton back = new JButton(back_btn);
		
		ImageIcon sell_btn = new ImageIcon("sell.png");
		JButton sell = new JButton(sell_btn);
		
		this.add(btn);
		btn.setBounds(1000, 600, 180, 60);
		this.add(back);
		back.setBounds(1000, 760, 180, 60);
		this.add(sell);
		sell.setBounds(1000, 680, 180, 60);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.change("back");
			}
		});
		
		sell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new item_sell();
			}
		});
		
		

        btn.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					new item_store();
				}
			});
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}

class item_store extends JFrame{
 
	//JPanel itemstore = new JPanel();
	ImageIcon bg = new ImageIcon("store_item.png");
    Image bgimg = bg.getImage();
    JLabel itemList;
    int ch1, ch2, ch3, ch4, ch5;
    int point;
	
    JCheckBox cb1 = new JCheckBox("산호초");
    JCheckBox cb2 = new JCheckBox("책상(600)");
    JCheckBox cb3 = new JCheckBox("침대(800)");
    JCheckBox cb4 = new JCheckBox("화장대");
    JCheckBox cb5 = new JCheckBox("호박화장대");
    JCheckBox cb6 = new JCheckBox("토끼의자(500)");
    JCheckBox cb7 = new JCheckBox("옷장(700)");
    JCheckBox cb8 = new JCheckBox("식탁");
    JCheckBox cb9 = new JCheckBox("LP플레이어");
    JCheckBox cb10 = new JCheckBox("당구대");
    JCheckBox cb11 = new JCheckBox("호박스탠드");
    JCheckBox cb12 = new JCheckBox("마법주사위");
    JCheckBox cb13 = new JCheckBox("화분1");
    JCheckBox cb14 = new JCheckBox("케이크");
    JCheckBox cb15 = new JCheckBox("캐리어");
    JCheckBox cb16 = new JCheckBox("다트게임기");
    JCheckBox cb17 = new JCheckBox("복숭아방석");
    JCheckBox cb18 = new JCheckBox("멜로디침대");
    JCheckBox cb19 = new JCheckBox("소파(700)");
    JCheckBox cb20 = new JCheckBox("꽃병");
    JCheckBox cb21 = new JCheckBox("전화기");
    JCheckBox cb22 = new JCheckBox("토끼풍선");
    JCheckBox cb23 = new JCheckBox("고급지구본");
    JCheckBox cb24 = new JCheckBox("토깽이소파");
    
    JButton btn = new JButton("구매하기");
   
   public item_store() {
	   
	   itemList = new JLabel(bg); // 레이블초기화
	   //itemList.setBounds(0, 0, 500, 700); // 위치지정
	   itemList.setSize(500, 700); // 크기 지정
	   add(itemList); // 패널에 추가 명령
	   
	   cb2.setBounds(120, 190, 80, 30);
	   cb3.setBounds(220, 190, 100, 30);
	   cb6.setBounds(350, 190, 130, 30);
	   cb7.setBounds(20, 340, 100, 30);
	   cb19.setBounds(20, 605, 100, 30);
	   btn.setBounds(212, 655, 100, 30);
	   
	   add(cb2);
	   add(cb3);
	   
	   add(cb6);
	   add(cb7);
	   
	   add(cb19);
	   add(btn);
	   
	   
    	 setTitle("상점에 오신 걸 환영합니다~");
         setLayout(null);
         setBounds(300,300,515,745);
         setVisible(true);
         
         btn.addActionListener(new ActionListener() {
        	   	public void actionPerformed(ActionEvent e) {
        	   		
        	   	   if(cb2.isSelected()) {
        	 		   ch1 = 1;
        	 		  point = point + 600;
        	 		 //Mainpanel.desk = 1;
        	 	   }
        	 	   if(cb3.isSelected()) {
        	 		   ch2 = 1;
        	 		  point = point + 800;
        	 		 //Mainpanel.bed = 1;
        	 	   }
        	 	   if(cb6.isSelected()) {
        	 		   ch3 = 1;
        	 		  point = point + 500;
        	 		 //Mainpanel.chair = 1;
        	 	   }
        	 	   if(cb7.isSelected()) {
        	 		   ch4 = 1;
        	 		  point = point + 700;
        	 		 //Mainpanel.closet = 1;
        	 	   }
        	 	   if(cb19.isSelected()) {
        	 		   ch5 = 1;
        	 		  point = point + 700;
        	 		 //Mainpanel.sofa = 1;
        	 	   }
        	 	   if(point > Mainpanel.pointPlus) {
        	 		   JOptionPane.showMessageDialog(null, "포인트가 부족합니다");
        	 		   ch1 = 0;
        	 		   ch2 = 0;
        	 		   ch3 = 0;
        	 		   ch4 = 0;
        	 		   ch5 = 0;
        	 	   }
        	 	   else {
        	 		   Mainpanel.pointPlus-=point;
        	 		  JOptionPane.showMessageDialog(null, "구매가 완료되었습니다");
        	 		  if(ch1 == 1) {
        	 			  Mainpanel.desk = 1;
        	 		  }
        	 		  if(ch2 == 1) {
        	 			  Mainpanel.bed = 1;
       	 		  	  }
        	 		  if(ch3 == 1) {
        	 			  Mainpanel.chair = 1;
      	 		      }
        	 		  if(ch4 == 1) {
        	 			  Mainpanel.closet = 1;
        	 		  }
        	 		  if(ch5 == 1) {
      	 			  	Mainpanel.sofa = 1;
      	 		      }
        	 		  try {
 						 TimeDB5(Mainpanel.pointPlus,ch1,ch2,ch3,ch4,ch5);
 						 
 					 }catch(ClassNotFoundException | SQLException e1) {
 						 e1.printStackTrace();
 					 }
           	 	   }
        	   	}
        	 			
        	});
         
    }
   
   public void TimeDB5(int p, int p1, int p2, int p3, int p4, int p5) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	
			//System.out.println("값을 바꿔줄 거임!");
			PreparedStatement ps1;
			ps1 = connection.prepareStatement("UPDATE user_table SET point=?, desk=?, bed=?, chair=?, closet=?, sofa=? WHERE name=?"); 
			ps1.setInt(1, p);
			if(Mainpanel.desk == 1) {
				ps1.setInt(2, 1);
			}
			else {
				ps1.setInt(2, p1);
			}
			if(Mainpanel.bed==1) {
				ps1.setInt(3, 1);
			}
			else {
				ps1.setInt(3, p2);
			}
			if(Mainpanel.chair==1) {
				ps1.setInt(4, 1);
			}
			else {
				ps1.setInt(4, p3);
			}
			if(Mainpanel.closet==1) {
				ps1.setInt(5, 1);
			}
			else {
				ps1.setInt(5, p4);
			}
			if(Mainpanel.sofa==1) {
				ps1.setInt(6, 1);
			}
			else {
				ps1.setInt(6, p5);
			}
			ps1.setString(7, Mainpanel.name);
			int res = ps1.executeUpdate();
			
			ps1.close();
			
		connection.close();
	}
}

class item_sell extends JFrame{
	ImageIcon bg = new ImageIcon("store_sell.png");
    Image bgimg = bg.getImage();
    JLabel itemList;
    int ch1 = 1, ch2 = 1, ch3 = 1 , ch4 = 1, ch5 = 1;
    int point;
    JCheckBox cb1 = new JCheckBox("책상(600)");
    JCheckBox cb2 = new JCheckBox("침대(800)");
    JCheckBox cb3 = new JCheckBox("의자(500)");
    JCheckBox cb4 = new JCheckBox("옷장(700)");
    JCheckBox cb5 = new JCheckBox("소파(700)");
    JButton btn = new JButton("판매하기");
	public item_sell(){
		   itemList = new JLabel(bg);
		   itemList.setSize(500,700); // 크기 지정
		   add(itemList); // 패널에 추가 명
		   setTitle("상점에 오신 걸 환영합니다~");
	         setLayout(null);
	         setBounds(300,300,515,745);
	         setVisible(true);
		   if(Mainpanel.desk == 1) {
			   add(cb1);
			   cb1.setBounds(50, 190, 100, 30);
		   }
		   if(Mainpanel.bed == 1) {
			   add(cb2);
			   cb2.setBounds(150, 190, 100, 30);
		   }
		   if(Mainpanel.chair == 1) {
			   add(cb3);
			   cb3.setBounds(250, 190, 100, 30);
		   }
		   if(Mainpanel.closet == 1) {
			   add(cb4);
			   cb4.setBounds(50, 300, 100, 30);
		   }
		   if(Mainpanel.sofa == 1) {
			   add(cb5);
			   cb5.setBounds(150, 300, 100, 30);
		   }
		   
		   btn.setBounds(150, 500, 100, 30);
		   add(btn);
		   
		   btn.addActionListener(new ActionListener() {
       	   	public void actionPerformed(ActionEvent e) {
       	   		
       	   	   if(cb1.isSelected()) {
       	 		  ch1 = 0;
       	 		 Mainpanel.pointPlus =  Mainpanel.pointPlus + 600;
       	 		 Mainpanel.desk = 0;
       	 	   }
       	 	   if(cb2.isSelected()) {
       	 		   ch2 = 0;
       	 		 Mainpanel.pointPlus =  Mainpanel.pointPlus + 800;
       	 		 Mainpanel.bed = 0;
       	 	   }
       	 	   if(cb3.isSelected()) {
       	 		   ch3 = 0;
       	 		Mainpanel.pointPlus =  Mainpanel.pointPlus + 500;
       	 		 Mainpanel.chair = 0;
       	 	   }
       	 	   if(cb4.isSelected()) {
       	 		   ch4 = 0;
       	 		Mainpanel.pointPlus =  Mainpanel.pointPlus + 700;
       	 		 Mainpanel.closet = 0;
       	 	   }
       	 	   if(cb5.isSelected()) {
       	 		   ch5 = 0;
       	 		Mainpanel.pointPlus =  Mainpanel.pointPlus + 700;
       	 		 Mainpanel.sofa = 0;
       	 	   }
       	 	   JOptionPane.showMessageDialog(null, "판매가 완료되었습니다");
       	 		  try {
						 TimeDB(Mainpanel.pointPlus,Mainpanel.desk,Mainpanel.bed,Mainpanel.chair,Mainpanel.closet,Mainpanel.sofa);
						 
					 }catch(ClassNotFoundException | SQLException e1) {
						 e1.printStackTrace();
					 }
          	 	   }
       	 			
       	});
	}

public void TimeDB(int p, int p1, int p2, int p3, int p4, int p5) throws ClassNotFoundException, SQLException {
	Class.forName("org.sqlite.JDBC");
	Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

		//System.out.println("값을 바꿔줄 거임!");
		PreparedStatement ps1;
		ps1 = connection.prepareStatement("UPDATE user_table SET point=?, desk=?, bed=?, chair=?, closet=?, sofa=? WHERE name=?"); 
		ps1.setInt(1, p);
		ps1.setInt(2, p1);
		ps1.setInt(3, p2);
		ps1.setInt(4, p3);
		ps1.setInt(5, p4);
		ps1.setInt(6, p5);
		ps1.setString(7, Mainpanel.name);
		int res = ps1.executeUpdate();
		
		ps1.close();
		
	connection.close();
}
}

