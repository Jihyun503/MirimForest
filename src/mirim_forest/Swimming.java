package mirim_forest;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Swimming extends JPanel{
	private Image img;
	JLabel player1, player2, player3;
	JLabel mong;
	int y = 650;
	int x1 = 0, x2 = 0, x3 = 0, x4 = 0;
	int y1 = 0, y2 = 0, y3 = 0, y4 = 0;
	int ck = 0;
	int point = 0;
	
	Swimming(Image img, Main_w frame){
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
		
		ImageIcon players1 = new ImageIcon("swim_player1.png");
		ImageIcon players2 = new ImageIcon("swim_player2.png");
		ImageIcon players3 = new ImageIcon("swim_player3.png");
		ImageIcon doraemong = new ImageIcon("swim_mong.png");
		
		mong = new JLabel(doraemong);
		player1 = new JLabel(players1);
		player2 = new JLabel(players2);
		player3 = new JLabel(players3);
		
		player1.setBounds(50, 650, 110, 150);
		player2.setBounds(400, 650, 95, 150);
		player3.setBounds(1000, 650, 90, 150);
		mong.setBounds(800, 650, 130, 150);
		
		add(player1);
		add(player2);
		add(player3);
		add(mong);
		
		
		frame.setFocusable(true);
	    frame.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				this.KeyReleased(e);
			}

			private void KeyReleased(KeyEvent e) {
				if(e.getKeyCode() == ' ') {
					
					y -= 10;
					mong.setLocation(800, y);
				}
				
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		
	}
	
	public void start() {
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				 do {
					 
					 x1 = player1.getX();
					 y1 = player1.getY()-((int)((Math.random() * 8)+1));
					 x2 = player2.getX();
					 y2 = player2.getY()-((int)((Math.random() * 8)+1));
					 x3 = player3.getX();
					 y3 = player3.getY()-((int)((Math.random() * 8)+1));
					 x4 = mong.getX();
					 y4 = mong.getY();
					 
					 
						 player1.setLocation(x1, y1);
						 player2.setLocation(x2, y2);
						 player3.setLocation(x3, y3);
						 mong.setLocation(x4, y4);
						 
						 if(y4 < 0) {
							 point = 500;
							 Mainpanel.pointPlus += point;
							 try {
								 TimeDB4(Mainpanel.pointPlus);
								 
							 }catch(ClassNotFoundException | SQLException e) {
								 e.printStackTrace();
							 }
							 
							 JOptionPane.showMessageDialog(null, "획득한 포인트는" + point);
							 break;
							 
							 }
						 
						 else if(y1 < 0 || y2 < 0 || y3 < 0) {
							 JOptionPane.showMessageDialog(null, "다음에는 1등을 노려보아요!");
							 break;
						 }
						 
						 
						 
						 try {
							 Thread.sleep(60);
						 }
						 catch (Exception e) {
							 
						}
						 /*
						 remove(pointAdd);
						 point = Integer.toString(pointPlus);
						 pointAdd = new JLabel(point);
						 add(pointAdd);
						 pointAdd.setBounds(0, 0, 100, 100);
						 */
						 
					 }while(!(y1 > 720));
			 }
		 });
		
		t2.start();
				
	}
	
	public void TimeDB4(int p1) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	
			System.out.println("값을 바꿔줄 거임!");
			PreparedStatement ps2 = connection.prepareStatement("UPDATE user_table SET point=? WHERE name=?"); 
			ps2.setInt(1, p1);
			ps2.setString(2, Mainpanel.name);
			int res = ps2.executeUpdate();
			
			ps2.close();
		connection.close();
	}
}
