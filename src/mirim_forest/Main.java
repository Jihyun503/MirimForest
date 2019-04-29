package mirim_forest;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;



//Image를 Label에 넣는 게 아니라 drawImage를 이용해 배경이미지 설정
class Main_w extends JFrame{
	
	
//    ImageIcon bgImgicon = new ImageIcon("pic\\MF.jpg");
//    Image bgImg=bgImgicon.getImage();
    ImageIcon forestbg = new ImageIcon("bgbg.png");
    Image forest_bg = forestbg.getImage();
    
    ImageIcon roombg = new ImageIcon("room.png");
    Image room_bg = roombg.getImage();
    
    ImageIcon storebg = new ImageIcon("store_bg.png");
    Image store_bg = storebg.getImage();
    
    ImageIcon fishingbg = new ImageIcon("fish_bg.png");
    Image fishig_bg = fishingbg.getImage();
    
    ImageIcon applebg = new ImageIcon("apple_bg.jpg");
    Image apple_bg = applebg.getImage();
    
    ImageIcon swimbg = new ImageIcon("swim_bg.png");
    Image swimming_bg = swimbg.getImage();
    
    ImageIcon closetbg = new ImageIcon("closet_bg1.jpg");
    Image closet_bg = closetbg.getImage();
    
    ImageIcon closetbg2 = new ImageIcon("closet_bg2.jpg");
    Image closet_bg2 = closetbg2.getImage();
    
    ImageIcon farmbg = new ImageIcon("farm_bg.png");
    Image farm_bg = farmbg.getImage();
    
    Apple apple;
    Fishing fishing;
    House house;
    Store store;
    ImagePanel imagePanel;
    Mainpanel mainpanel;
    Swimming swimming;
    Closet closet;
    Farm farm;
    
    
    Main_w(){
    	
		setTitle("미림의 숲"); //타이틀바 문자열
		setLocation(0, 0);//시작위치 (x,y)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//종료 버튼 눌렀을때 행동
		
		//MainSound main_sound = new MainSound("pic\\EXID_낮보다는 밤.wav");
    	
        //p_main.setLayout(new FlowLayout());
        //JPanel ImageBtn = new JPanel();
        //p_main.add(btn1);
        
//        this.add(p_main);
//        this.setSize(600,400);
//        this.setVisible(true);
        
		setLocation(300, 0);
	  	setPreferredSize(new Dimension(1280, 870));
	  	
    }
    
    public void change(String PanelName)
    {
    	
    	if(PanelName.equals("ImagePanel")) {
    		this.imagePanel = new ImagePanel(this.forest_bg, this);
    		getContentPane().removeAll();
			getContentPane().add(imagePanel);
			revalidate();
			repaint();
    	}
    	else if(PanelName.equals("house")) {
    		this.house = new House(this.room_bg, this);
    		getContentPane().removeAll();
			getContentPane().add(house);
			revalidate();
			repaint();
    	}
    	else if(PanelName.equals("store")) {
    		this.store = new Store(this.store_bg, this);
    		getContentPane().removeAll();
			getContentPane().add(store);
			revalidate();
			repaint();
    	}
    	else if(PanelName.equals("fishing")) {
    		this.fishing = new Fishing(this.fishig_bg, this);
    		getContentPane().removeAll();
			getContentPane().add(fishing);
			revalidate();
			repaint();
    	}
    	else if(PanelName.equals("back")) {
    		this.imagePanel = new ImagePanel(this.forest_bg, this);
    		getContentPane().removeAll();
			getContentPane().add(imagePanel);
			revalidate();
			repaint();
    	}
    	else if(PanelName.equals("apple")) {
    		this.apple = new Apple(this.apple_bg, this);
    		getContentPane().removeAll();
			getContentPane().add(apple);
			revalidate();
			repaint();
			apple.start();
    	}
    	else if(PanelName.equals("swimming")) {
    		this.swimming = new Swimming(this.swimming_bg, this);
    		getContentPane().removeAll();
			getContentPane().add(swimming);
			revalidate();
			repaint();
			swimming.start();
    	}
    	else if(PanelName.equals("closet")) {
    		if(Mainpanel.chk == 1) {
    			this.closet = new Closet(this.closet_bg, this);
    		}
    		else if(Mainpanel.chk == 2){
    			this.closet = new Closet(this.closet_bg2, this);
    		}
    		getContentPane().removeAll();
			getContentPane().add(closet);
			revalidate();
			repaint();
    	}
    	else if(PanelName.equals("farm")) {
    		this.farm = new Farm(this.farm_bg, this);
    		getContentPane().removeAll();
			getContentPane().add(farm);
			revalidate();
			repaint();
    	}
    	
    }
    
}

public class Main {

	public static void main(String[] args) {
		//public static Connection conn = null;
		//Class.forName("org.sqlite.JDBC");
		//conn = DriverManager.getConnection("jdbc:sqlite:" + JDBCConfig.dbFileName);
		Main_w Frame = new Main_w();
		Frame.mainpanel = new Mainpanel(Frame);
		//Frame.house = new House(Frame.room_bg, Frame);
		Frame.imagePanel = new ImagePanel(Frame.forest_bg, Frame);
		Frame.store = new Store(Frame.store_bg, Frame);
		Frame.fishing = new Fishing(Frame.fishig_bg, Frame);
		Frame.apple = new Apple(Frame.apple_bg, Frame);
		Frame.swimming = new Swimming(Frame.swimming_bg, Frame);
		/*
		if(Mainpanel.chk == 1) {
			Frame.closet = new Closet(Frame.closet_bg, Frame);
		}
		else if(Mainpanel.chk == 2){
			Frame.closet = new Closet(Frame.closet_bg2, Frame);
		}
		*/
		Frame.farm = new Farm(Frame.farm_bg, Frame);
		
		Frame.add(Frame.mainpanel);
		Frame.pack();
	  	Frame.setVisible(true);//창을 보이게 함
	}

}