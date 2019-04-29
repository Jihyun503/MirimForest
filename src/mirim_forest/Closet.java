package mirim_forest;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mirim_forest.Fishing.MyMouseListener;

public class Closet extends JPanel{
	private Image img;
	JLabel clothes1, clothes2, clothes3;
	int chk = 0;
	
	public Closet(Image img, Main_w frame){
		this.img = img;
		setLayout(null);
		ImageIcon back_btn = new ImageIcon("back.png");
		JButton back = new JButton(back_btn);
		ImageIcon cp_btn = new ImageIcon("complete.png");
		JButton cp = new JButton(cp_btn);
		
		this.add(back);
		back.setBounds(1000, 10, 180, 60);
		this.add(cp);
		cp.setBounds(1000, 80, 180, 60);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.change("back");
			}
		});
		
		cp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chk == 1) {
				Mainpanel.clothes = 1;
				try {
					 TimeDB(Mainpanel.clothes);
					 
				 }catch(ClassNotFoundException | SQLException e1) {
					 e1.printStackTrace();
				 }
				
				}
				else if(chk == 0) {
					Mainpanel.clothes = 0;
					try {
						 TimeDB(Mainpanel.clothes);
						 
					 }catch(ClassNotFoundException | SQLException e1) {
						 e1.printStackTrace();
					 }
				}
		}
	});
		
		if(Mainpanel.chk == 1) {
		ImageIcon closet1 = new ImageIcon("Mset.png");
		clothes1 = new JLabel(closet1);
		clothes1.setBounds(600, 200, 450, 400);
		add(clothes1);
		
		clothes1.addMouseListener(new MouseListener() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount()==1){
					 clothes1.setBounds(0, 390, 450, 400);
					 add(clothes1);
					 chk = 1;
				 }
				 else if(e.getClickCount()==2) {
				 clothes1.setBounds(600, 200, 450, 400);
				 add(clothes1);
				 chk = 0;
				 }
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
				 
		});
	}
		
		if(Mainpanel.chk == 2) {
			ImageIcon closet2 = new ImageIcon("Wset.png");
			clothes2 = new JLabel(closet2);
			clothes2.setBounds(600, 200, 450, 400);
			add(clothes2);
			
			clothes2.addMouseListener(new MouseListener() {
				 @Override
				 public void mouseClicked(MouseEvent e) {
					 if (e.getClickCount()==1){
						 clothes2.setBounds(0, 390, 450, 400);
						 add(clothes2);
						 chk = 1;
					 }
					 else if(e.getClickCount()==2) {
						 clothes2.setBounds(600, 200, 450, 400);
						 add(clothes2);
						 chk = 0;
					 }
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
					 
			});
			
		}
}
		public void TimeDB(int p) 
				throws ClassNotFoundException, SQLException {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
		
				//System.out.println("값을 바꿔줄 거임!");
				PreparedStatement ps1;
				ps1 = connection.prepareStatement("UPDATE user_table SET clothes=? WHERE name=?"); 
				ps1.setInt(1, p);
				ps1.setString(2, Mainpanel.name);
				int res = ps1.executeUpdate();
				
				ps1.close();
				
			connection.close();
		}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
