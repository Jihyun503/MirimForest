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
  setContentPane(new MousePanel()); // MousePanel�� ���δ�
  setSize(400,400); // ũ������
  setVisible(true); // ���δ�
 }
 class MousePanel extends JPanel{
  
  public MousePanel(){
   setLayout(null); // ���̾ƿ� ��
   ImageIcon fish = new ImageIcon("pic\\fishhh.png");
   Image fishh = fish.getImage();
   la = new JLabel(fish); // ���̺��ʱ�ȭ
   la.setLocation(100,100); // ��ġ����
   la.setSize(120,60); // ũ�� ����
   add(la); // �гο� �߰� ���
   this.addMouseListener(new MyMouseListener()); // ���콺������
   this.addMouseMotionListener(new MyMouseListener()); // ��Ǹ�����
  }
 }
 class MyMouseListener extends MouseAdapter implements MouseMotionListener{
  //public void mousePressed(MouseEvent e){ // ��������
   //int x = e.getX();
   //int y = e.getY();
   //la.setLocation(x, y); // ��ġ ����
  //}
  public void mouseDragged(MouseEvent e){ // �巡���Ͻ�
   int x = e.getX();
   int y = e.getY();
   la.setLocation(x, y); // ��ġ ����
  }
 }
 
 public static void main(String[] args) {
  new Drag();
 	}
 }