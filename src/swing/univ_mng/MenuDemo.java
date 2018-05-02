package swing.univ_mng;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

public class MenuDemo extends JFrame implements ActionListener {
	JMenuBar mb;
	JMenu file, color , font ,reg_auth;
	JMenuItem f1, f2,f3,f4;
	JMenuItem c1,c2,c3;
	JMenuItem s1,s2,s3;
	JMenuItem r1,r2,r3;
	
	JTextPane text;
	JFileChooser fc;
	
	public MenuDemo() {
		setTitle("학사관리시스템");
		
		//makeMenu();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,750);
		setVisible(true);
	}

	//학생 메뉴
	public void stdMakeMenu() {
		text = new JTextPane();
		
		mb = new JMenuBar();
		
		file = new JMenu("학생관리");
		file.setMnemonic(KeyEvent.VK_F); //Alt+F
		
		color = new JMenu("교과목관리");
		color.setMnemonic(KeyEvent.VK_C);
		
		
		font = new JMenu("성적관리");
		font.setMnemonic(KeyEvent.VK_F);
		
		reg_auth = new JMenu("권한등록");
		reg_auth.setMnemonic(KeyEvent.VK_F);
		
		
		//서브메뉴
		f1 = new JMenuItem("학생등록");//학생등록 //Ctrl + N
		f1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));

		c1 = new JMenuItem("등록");
		c1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		
		s1 = new JMenuItem("등록");
		s1.setAccelerator(KeyStroke.getKeyStroke('R',Event.CTRL_MASK));

		r1 = new JMenuItem("등록");//학생등록 //Ctrl + N
		r1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));
		
	
		
		//메뉴에 서브메뉴 등록
		file.add(f1);
		file.addSeparator(); //구분선
		
		//색상 메뉴 서브메뉴 등록
		color.add(c1);

		//font 메뉴 등록
		font.add(s1);
		
		reg_auth.add(r1);
		
		//메뉴바에 메뉴 등록
		mb.add(file);
		mb.add(color);
		mb.add(font);
		mb.add(reg_auth);
		
		//프레임에 메뉴바 등록
		setJMenuBar(mb);
		//text pane 등록
		add(text);
		
		//리스너 등록
		c1.addActionListener(this);
		f1.addActionListener(this);
		s1.addActionListener(this);
		r1.addActionListener(this);
	
		
		
		
	}

	//교수메뉴
	public void profMakeMenu() {
		text = new JTextPane();
		
		mb = new JMenuBar();
		
		file = new JMenu("교수관리");
		file.setMnemonic(KeyEvent.VK_F); //Alt+F
		
		color = new JMenu("교과목관리");
		color.setMnemonic(KeyEvent.VK_C);
		
		
		font = new JMenu("성적관리");
		font.setMnemonic(KeyEvent.VK_F);
		
		reg_auth = new JMenu("권한등록");
		reg_auth.setMnemonic(KeyEvent.VK_F);
		
		
		//서브메뉴
		f1 = new JMenuItem("교수등록");//
		f1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));

		c1 = new JMenuItem("등록");
		c1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		
		s1 = new JMenuItem("등록");
		s1.setAccelerator(KeyStroke.getKeyStroke('R',Event.CTRL_MASK));

		r1 = new JMenuItem("등록");//학생등록 //Ctrl + N
		r1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));
		
	
		
		//메뉴에 서브메뉴 등록
		file.add(f1);
		file.addSeparator(); //구분선
		
		//색상 메뉴 서브메뉴 등록
		color.add(c1);

		//font 메뉴 등록
		font.add(s1);
		
		reg_auth.add(r1);
		
		//메뉴바에 메뉴 등록
		mb.add(file);
		mb.add(color);
		mb.add(font);
		mb.add(reg_auth);
		
		//프레임에 메뉴바 등록
		setJMenuBar(mb);
		//text pane 등록
		add(text);
		
		//리스너 등록
		c1.addActionListener(this);
		f1.addActionListener(this);
		s1.addActionListener(this);
		r1.addActionListener(this);
	
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 JMenuItem mi = (JMenuItem)e.getSource();
		 System.out.println(mi.getText());
		 //파일 메뉴 처리..
		 if(mi.getText().equals("학생등록")) {
			Student student = new Student();
			this.setVisible(true);
	
		 }else if (mi.getText().equals("교수등록")) {
			 Professor professor = new Professor();
			 this.setVisible(true);
		 }

		
	}
	
	public static void main(String[] args) {
		new MenuDemo();

	}

	
}
