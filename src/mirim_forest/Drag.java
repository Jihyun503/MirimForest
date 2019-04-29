package mirim_forest;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Drag extends JFrame {
 JLabel la;
 public Drag(){
  setContentPane(new MousePanel()); // MousePanel을 붙인다
  setSize(400,400); // 크기조정
  setVisible(true); // 보인다
 }
 class MousePanel extends JPanel{
  
  public MousePanel(){
   setLayout(null); // 레이아웃 널
   ImageIcon fish = new ImageIcon("pic\\fishhh.png");
   Image fishh = fish.getImage();
   la = new JLabel(fish); // 레이블초기화
   la.setLocation(100,100); // 위치지정
   la.setSize(120,60); // 크기 지정
   add(la); // 패널에 추가 명령
   this.addMouseListener(new MyMouseListener()); // 마우스리스너
   this.addMouseMotionListener(new MyMouseListener()); // 모션리스너
  }
 }
 class MyMouseListener extends MouseAdapter implements MouseMotionListener{
  //public void mousePressed(MouseEvent e){ // 눌린순간
   //int x = e.getX();
   //int y = e.getY();
   //la.setLocation(x, y); // 위치 조정
  //}
  public void mouseDragged(MouseEvent e){ // 드래그일시
   int x = e.getX();
   int y = e.getY();
   la.setLocation(x, y); // 위치 조정
  }
 }
 
 public static void main(String[] args) {
  new Drag();
 	}
 }