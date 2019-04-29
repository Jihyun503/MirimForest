package mirim_forest;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Mainpanel extends JPanel{
	ImageIcon bgImgicon = new ImageIcon("MF.jpg");
    Image bgImg=bgImgicon.getImage();
    int onoff = 0;
    public static String name = null;
    int check=0;    
	User uname;
	String gname="", cname = "";
	public static Connection conn=null;
	public static int chk, chk2;
	public static int pointPlus, desk, bed, chair,  closet,  sofa,  potato,  deskX,
			 deskY,  bedX, bedY, chairX, chairY,  closetX,  closetY,  sofaX,  sofaY,  potatoX,  potatoY,  gift,  clothes;
    public static long fitime, stime;
    Mainpanel(Main_w frame){
    
    MainSound main_sound = new MainSound();
    File file = new File("King_Street.wav");
    //main_sound.mainsound(file);
    JFrame ch = new JFrame();
    setVisible(true);
    ImageIcon btn_start = new ImageIcon("btn1.png");
    ImageIcon btn_way = new ImageIcon("btn2.png");
    ImageIcon btn_road = new ImageIcon("btn3.png");
    ImageIcon btn_setting = new ImageIcon("setting.png");
    JButton btn1 = new JButton(btn_start);
    JButton btn2 = new JButton(btn_way);
    JButton btn3 = new JButton(btn_setting);
    JButton btn4 = new JButton(btn_road);
    //��������
    ButtonGroup btnGrp = new ButtonGroup();  
    JRadioButton rdBtn1 = new JRadioButton("��");
    JRadioButton rdBtn2 = new JRadioButton("��");
    JButton chBtn1 = new JButton("Ȯ��");
    JButton chBtn2 = new JButton("Ȯ��");
    //���ǿ¿���
    ButtonGroup btnMusic = new ButtonGroup();
    JRadioButton on = new JRadioButton("music on");
    JRadioButton off = new JRadioButton("music off");
    
    btnMusic.add(on);
    btnMusic.add(off);
    btnMusic.add(chBtn1);
    
    btnGrp.add(rdBtn1);
	btnGrp.add(rdBtn2);
	btnGrp.add(chBtn2);
	
    btn1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub 
				//��ư Ŭ������ �� �׼� �ֱ�!!
			if(name == null) {
				name = JOptionPane.showInputDialog("�÷��̾��� �̸��� �Է����ּ���.");
				while(name.equals("")) {
					name = JOptionPane.showInputDialog("�÷��̾��� �̸��� �Է����ּ���.");
				}
				if(!name.equals("")) {
					JFrame c = new JFrame();
					c.setLayout(new FlowLayout());
					c.setSize(200, 180);
					c.setLocation(860, 450);
					c.add(rdBtn1);
					c.add(rdBtn2);
					c.add(chBtn1);
					c.setVisible(true);
					
					chBtn1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(rdBtn1.isSelected())
							{
								chk = 1;
								c.dispose();
							}

							else if(rdBtn2.isSelected()) 
							{
								chk = 2;
								c.dispose();
							}
							int ggender = 0;
							try {
								conn=DriverManager.getConnection("jdbc:sqlite:sample.db");
								 uDAO.init(conn);
								 List<User> users = uDAO.getLists();
								 
								 System.out.println("DB ���̺� �� �޾ƿ�");
									int idx = 0;
									// ����� �Ϲ����� ResultSet���� �������� ���� �ƴ϶� *��ü(DTO)*�� ����!
									for(User u : users) {
										System.out.println("DB ���̺� �� �д� ��");
										check=1;
										gname=u.getName();
										if(name.equals(gname) == false) {
											if(idx==0) {
												User user = new User(name, chk, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
												uDAO.add(user);
												idx++;
											}
										}
												
										idx++;
									}//for
									if(check==0) {
										User user = new User(name, chk, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
										uDAO.add(user);										
									}
								 
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					});
					
				}
				
				
			}	
			else {
				frame.change("ImagePanel");
			}
		}
    });
    
    
    //////
    btn2.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		new way_window();
    	}
    });
    
    chBtn2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("chk");
			if(on.isSelected())
			{
				onoff = 1;
				System.out.println("on");
				ch.dispose();
				
				main_sound.mainsound(file);
				onoff = 0;
			}
			else if(off.isSelected()) 
			{
				onoff = 2;
				System.out.println("off");
				ch.dispose();
				main_sound.Stop_Sound();
				onoff = 0;
			}
		}
	});
    
    btn3.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		ch.setLayout(new FlowLayout());
    		ch.setSize(200, 180);
    		ch.setLocation(860, 450);
    		ch.add(on);
    		ch.add(off);
    		ch.add(chBtn2);
    		ch.setVisible(true);
			
    	}
    });
    
    btn4.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if(name == null) {
				name = JOptionPane.showInputDialog("�÷��̾��� �̸��� �Է����ּ���.");
				while(name.equals("")) {
					name = JOptionPane.showInputDialog("�÷��̾��� �̸��� �Է����ּ���.");
				}
				if(!name.equals("")) {
							try {
								conn=DriverManager.getConnection("jdbc:sqlite:sample.db");
								 uDAO.init(conn);
								 List<User> users = uDAO.getLists();
								 
								 System.out.println("DB ���̺� �� �޾ƿ�+");
									// ����� �Ϲ����� ResultSet���� �������� ���� �ƴ϶� *��ü(DTO)*�� ����!
									for(User u : users) {
										System.out.println("DB ���̺� �� �д� ��+");
										if(name.equals(u.getName())) {
											if(1 == u.getGender()){
												chk = 1;
												}
											else if(2 == u.getGender())
											{
												chk = 2;
											}
											pointPlus = u.getpoint();
											System.out.println(pointPlus);
											desk = u.getdesk();
											bed = u.getbed();
											chair = u.getchair();
											closet = u.getcloset();
											sofa = u.getsofa();
											potato = u.getpotato();
											deskX = u.getdeskX();
											deskY = u.getdeskY();
											bedX = u.getbedX();
											bedY = u.getbedY();
											chairX = u.getchairX();
											chairY = u.getchairY();
											closetX = u.getclosetX();
											closetY = u.getclosetY();
											sofaX = u.getsofaX();
											sofaY = u.getsofaY();
											potatoX = u.getpotatoX();
											potatoY = u.getpotatoY();
											gift = u.getgift();
											clothes = u.getclothes();
											fitime = u.getfitime();
											stime = u.getstime();
											frame.change("ImagePanel");
										}
										//else {
											//JOptionPane.showMessageDialog(null, "��ϵ��� ���� ������Դϴ�");
										//}
										
									}//for
									
									
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							
						}
						
    		}
    	}
	});
			
    
    add(btn1);
    add(btn2);
    add(btn3);
    add(btn4);
    setLayout(null);
    btn1.setBounds(550, 550, 200, 60);
	btn2.setBounds(550, 620, 200, 60);
	btn4.setBounds(550, 690, 200, 60);
	btn3.setBounds(1150, 0, 65, 63);
	
}
    
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgImg,0,0,getWidth(),getHeight(),this);
    }
}

class way_window extends JFrame {
    public way_window() {
        setTitle("�̸��� ���� ��� �ϴ� �ǰ���? ");
        setSize(500, 700);
        setLocation(700, 100);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel game_way = new JLabel(new ImageIcon("ex_way.png"));
        //setPreferredSize(new Dimension(500, 700));
        add(game_way);
        
        setVisible(true);
    }
}
