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
import javax.swing.JOptionPane;
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
	static int auth_type=0; //[0]미지정[2]교수,[1]학생
	static String auth_code = null;
	
	public MenuDemo() {
		setTitle("학사관리시스템");
		
		//makeMenu();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,200);
		setVisible(true);
	}

	//학생 메뉴
	public void stdMakeMenu() {
		text = new JTextPane();
		
		mb = new JMenuBar();
		
		file = new JMenu("수강관리");
		file.setMnemonic(KeyEvent.VK_F); //Alt+F
		
		color = new JMenu("성적관리");
		color.setMnemonic(KeyEvent.VK_C);
		
		
		reg_auth = new JMenu("권한등록");
		reg_auth.setMnemonic(KeyEvent.VK_F);
		
		
		//서브메뉴
		f1 = new JMenuItem("수강등록");//학생등록 //Ctrl + N
		f1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));

		c1 = new JMenuItem("등록");
		c1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		
		
		r1 = new JMenuItem("패스워드 수정");//
		r1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));
		
	
		
		//메뉴에 서브메뉴 등록
		file.add(f1);
		file.addSeparator(); //구분선
		
		//색상 메뉴 서브메뉴 등록
		color.add(c1);
		
		reg_auth.add(r1);
		
		//메뉴바에 메뉴 등록
		mb.add(file);
		mb.add(color);
		mb.add(reg_auth);
		
		//프레임에 메뉴바 등록
		setJMenuBar(mb);
		//text pane 등록
		add(text);
		
		//리스너 등록
		c1.addActionListener(this);
		f1.addActionListener(this);
	
		r1.addActionListener(this);
	
		
		
		
	}

	//교수메뉴
	public void profMakeMenu() {
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
		f1 = new JMenuItem("학생등록");//
		f1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));

		c1 = new JMenuItem("등록");
		c1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		
		s1 = new JMenuItem("등록");
		s1.setAccelerator(KeyStroke.getKeyStroke('R',Event.CTRL_MASK));

		r1 = new JMenuItem("패스워드 수정");//
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
		 System.out.println("타입:"+ auth_type + ":"+ mi.getText() + ":"+ mi.getActionCommand()); //등록,등록
		 System.out.println("auth_code:"+ auth_code);
		 //파일 메뉴 처리..
		 if(auth_type==1) { //학생
			 
			 if(mi.getText().equals("수강등록")) {
					Major major  = new Major();
					this.setVisible(true);
			 }else if (mi.getText().equals("패스워드 수정")) {
				Modify modify = new Modify();
				modify.majorcode.setText("학생코드");
				modify.auth_type=1;
				//modify.auth_code = auth_code;
				modify.tmajorcode.setText(auth_code);
				modify.tmajorname.setText(modify.getName(auth_code, "", "학생"));
				
				
				this.setVisible(true);	
			}
		 }else if(auth_type ==2) {//교수
			 if(mi.getText().equals("학생등록")) {
				 Student student = new Student();
					this.setVisible(true);
			 }else if (mi.getText().equals("패스워드 수정")) {
				Modify modify = new Modify();
				modify.majorcode.setText("교수코드");
				modify.auth_type=2;
				//modify.auth_code = auth_code;
				modify.tmajorcode.setText(auth_code);
				modify.tmajorname.setText(modify.getName(auth_code, "", "교수"));
				
				this.setVisible(true);	
			}
		 }else {
			 MessageDialog("권한이 없습니다.");//팝업메시지.
		 }
		 
		

		
	}
	
	public static void main(String[] args) {
		new MenuDemo();

	}


	public void MessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message,message, JOptionPane.WARNING_MESSAGE);
	}
}
