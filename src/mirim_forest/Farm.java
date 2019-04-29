package mirim_forest;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Farm extends JPanel {
	private Image img;
	 int x=50, y=50, potato = 0, gPotato;
	 Toolkit tk = Toolkit.getDefaultToolkit();
	 Image img1 = tk.getImage("seed.png");
	 Image img2 = tk.getImage("water.png");
	 Point point;
	 Cursor cursor;
	 ImageIcon seed, water, pest;
	 JLabel lbtn, lbtn2, lpest;
	 Container cPane;
	 Timer timer = new Timer();
	 public static Connection conn=null;
	 String fname;
	 
	 public Farm(Image img, Main_w frame) {
		
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
		this.setPreferredSize(new Dimension(1280, 900));
		
		seed = new ImageIcon("seed.png");
		lbtn = new JLabel(seed);
		
		lbtn.setBounds(10, 500, 200, 150); //ó�� ���� �̹���
		this.add(lbtn);
		
		water = new ImageIcon("water.png");
		lbtn2 = new JLabel(water);
		
		lbtn2.setBounds(10, 400, 200, 150); //�� �̹���
		this.add(lbtn2);
		
		pest = new ImageIcon("pest.png");
		lpest = new JLabel(pest);
		
		lpest.setBounds(1100, 500, 100, 100); //���� �̹���
		this.add(lpest);
		
	    ImageIcon gift = new ImageIcon("gift.png"); //������ ���ư��� ��ư
	    JButton gift_btn = new JButton(gift);
	    
	    this.add(gift_btn);
	    gift_btn.setBounds(1000, 640, 180, 60);
		
	    ImageIcon back = new ImageIcon("back.png"); //������ ���ư��� ��ư
	    JButton back_btn = new JButton(back);
	    
	    this.add(back_btn);
	    back_btn.setBounds(1000, 720, 180, 60);
	    
		setVisible(true);
		
		back_btn.addActionListener(new ActionListener() {
			//�ڷΰ��� ��ư Ŭ�� ��, �׶��� �ð��� ����
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.change("back");
			}
		});
		
		gift_btn.addActionListener(new ActionListener() {
			//�����ϱ� ��ư Ŭ�� ��, �� ����� �۹� ���� Ȯ��
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					 conn=DriverManager.getConnection("jdbc:sqlite:sample.db");
					 uDAO.init(conn);
					 List<User> users = uDAO.getLists();
							 
					 int idx = 0;
					 // ����� �Ϲ����� ResultSet���� �������� ���� �ƴ϶� *��ü(DTO)*�� ����!
					 for(User u : users) {
							 //gPotato=u.getpotato();
							 
							 if(Mainpanel.potato != 0) {
								 fname = JOptionPane.showInputDialog("������ ģ���� �̸��� �Է����ּ���.");
								 
								 try {
									gift(Mainpanel.potato, fname);
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							 }
							 else {
								 JOptionPane.showMessageDialog(null, "������ �۹��� �����ϴ�");
							 }
							 break;
						 //idx++;
					 }
				} catch(SQLException e1){
				
				}
				
			}
		});
		
		lbtn.addMouseListener (new MouseAdapter () {
			public void mouseClicked (MouseEvent e) { 
				System.out.println("������ Ŀ���� ����");
				point = new Point(0,0);
				cursor = tk.createCustomCursor(img1, point, "seed");
				setCursor(cursor);
				
			} 
		});
		
		lbtn2.addMouseListener (new MouseAdapter () {
			public void mouseClicked (MouseEvent e) { 
				System.out.println("���� Ŀ���� ����");
				point = new Point(0,0);
				cursor = tk.createCustomCursor(img2, point, "water");
				setCursor(cursor);
				
			} 
		});
		
		this.addMouseListener (new MouseAdapter () {
			public void mouseClicked (MouseEvent e) {
				if(e.getClickCount() == 2) {
					  System.out.println("Ŭ���� ��ġ�� ������ �ѷ���");
					  x = e.getX();
					  y = e.getY();
					  
					  try {
						lbtn.setIcon(new ImageIcon(ImageIO.read( new File("seed.png")))); //2�� Ŭ������ �� ������ Ŭ�� ��ġ�� ����
					  } catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					  }
					  
					  lbtn.setBounds(x, y, lbtn.getWidth(), lbtn.getHeight());
					  add(lbtn);
					  setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					  
					  //�ð��� �޾ƿͼ� DB�� �����ϱ� ����
					  long m = System.currentTimeMillis(); //������ �ѷ��� ���� �ð��� m�� ����
					  
					  try {
						TimeDB(m);//m�� DB�� update���ֱ� ���� �޼ҵ� ȣ��
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
					lbtn.addMouseListener (new MouseAdapter () {
						public void mouseClicked (MouseEvent e1) {
							if(e1.getClickCount() == 2) {
								TimerTask m_task0 = new TimerTask() {
									@Override
									public void run() {
										try {
										  lpest.setIcon(new ImageIcon(ImageIO.read(new File("pest.png"))));
										} catch (IOException e) {
										// TODO Auto-generated catch block
											e.printStackTrace();
										}
										lpest.setBounds(x+500, y+130, lpest.getWidth(), lpest.getHeight());
										add(lpest);
									}
							    };
							    timer.schedule(m_task0, 2000);
									  
							    TimerTask m_task1 = new TimerTask() {
									@Override
									public void run() {
										try {
											lbtn.setIcon(new ImageIcon(ImageIO.read(new File("sprout.png"))));
										} catch (IOException e) {
										// TODO Auto-generated catch block
											e.printStackTrace();
										}
										lbtn.setBounds(x, y, lbtn.getWidth(), lbtn.getHeight());
										add(lbtn);
											  
									}
								};
								timer.schedule(m_task1, 2000);
									  
								TimerTask m_task2 = new TimerTask() {
									@Override
									public void run() {
										try {
											lpest.setIcon(new ImageIcon(ImageIO.read(new File("pest.png"))));
										} catch (IOException e) {
										// TODO Auto-generated catch block
											e.printStackTrace();
										}
										lpest.setBounds(x+420, y+100, lpest.getWidth(), lpest.getHeight());
										add(lpest);
										}
									};
									timer.schedule(m_task2, 2500);
									  
									TimerTask m_task3 = new TimerTask() {
									@Override
									public void run() {
										try {
											lbtn.setIcon(new ImageIcon(ImageIO.read(new File("grass.png"))));
										} catch (IOException e) {
										// TODO Auto-generated catch block
											e.printStackTrace();
										}
										lbtn.setBounds(x, y, lbtn.getWidth(), lbtn.getHeight());
										add(lbtn);	  
									}
									};
									timer.schedule(m_task3, 4000);
									
									TimerTask m_task4 = new TimerTask() {
										@Override
										 public void run() {
											 try {
												 lpest.setIcon(new ImageIcon(ImageIO.read(new File("pest.png"))));
											  } catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											  lpest.setBounds(x+350, y+80, lpest.getWidth(), lpest.getHeight());
											  add(lpest);
										  }
									  };
									  timer.schedule(m_task4, 4500);
									  
									  TimerTask m_task5 = new TimerTask() {
										  
										  @Override
										  public void run() {
											  try {
												  lpest.setIcon(new ImageIcon(ImageIO.read(new File("pest.png"))));
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											  lpest.setBounds(x+200, y+40, lpest.getWidth(), lpest.getHeight());
											  add(lpest);
										  }
									  };
									  timer.schedule(m_task5, 5800);
								}
							}
						});
					  
				 }
			}
		 
		});
		
		lpest.addMouseListener (new MouseAdapter () {
			public void mouseClicked (MouseEvent e) {
				lpest.setVisible(false);
					
				try {
					lbtn.setIcon(new ImageIcon(ImageIO.read(new File("potato.png"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				lbtn.setBounds(x, y, lbtn.getWidth(), lbtn.getHeight());
				add(lbtn);
				
				
				
				lbtn.addMouseListener (new MouseAdapter () {
					public void mouseClicked (MouseEvent e) {
						Mainpanel.potato++;
						Mainpanel.pointPlus+=100;
						try {
							pAdd(Mainpanel.potato, Mainpanel.pointPlus);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						lbtn.setVisible(false);
					} 
				});
			
			}
		 
		});
		
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	public void TimeDB(long m) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	
			PreparedStatement ps1 = connection.prepareStatement("UPDATE user_table SET fitime=? WHERE name=?"); 
			ps1.setLong(1, m);
			ps1.setString(2, Mainpanel.name);
			int res = ps1.executeUpdate();
			
			ps1.close();
		connection.close();
	}
	
	public void pAdd(int a, int p) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	
			PreparedStatement ps2 = connection.prepareStatement("UPDATE user_table SET potato=?, point=? WHERE name=?"); 
			ps2.setLong(1, a);
			ps2.setLong(2, p);
			ps2.setString(3, Mainpanel.name);
			int res = ps2.executeUpdate();
			
			ps2.close();
		connection.close();
	}
	
	public void gift(int a, String fn) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	
			PreparedStatement ps3 = connection.prepareStatement("UPDATE user_table SET potato=? WHERE name=?"); 
			ps3.setLong(1, a);
			ps3.setString(2, fn);
			ps3.executeUpdate();
			
			PreparedStatement ps4 = connection.prepareStatement("UPDATE user_table SET potato=? WHERE name=?"); 
			ps4.setLong(1, a-1);
			ps4.setString(2, Mainpanel.name);
			ps4.executeUpdate();
			
			ps4.close();
			ps3.close();
		connection.close();
	}
}