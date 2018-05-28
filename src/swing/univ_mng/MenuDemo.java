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
	static int auth_type=0; //[0]������[2]����,[1]�л�
	static String auth_code = null;
	String scode,sname,smajor;
	
	
	public MenuDemo() {
		setTitle("�л�����ý���");
		
		//makeMenu();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,200);
		setVisible(true);
	}

	//�л� �޴�
	public void stdMakeMenu(String scode,String sname, String smajor) {
		this.scode = scode;
		this.sname = sname;
		this.smajor = smajor;
		text = new JTextPane();
		
		mb = new JMenuBar();
		
		file = new JMenu("��������");
		file.setMnemonic(KeyEvent.VK_F); //Alt+F
		
		color = new JMenu("��������");
		color.setMnemonic(KeyEvent.VK_C);
		
		
		reg_auth = new JMenu("���ѵ��");
		reg_auth.setMnemonic(KeyEvent.VK_F);
		
		
		//����޴�
		f1 = new JMenuItem("�������");//�л���� //Ctrl + N
		f1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));

		c1 = new JMenuItem("���");
		c1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		
		
		r1 = new JMenuItem("�н����� ����");//
		r1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));
		
	
		
		//�޴��� ����޴� ���
		file.add(f1);
		file.addSeparator(); //���м�
		
		//���� �޴� ����޴� ���
		color.add(c1);
		
		reg_auth.add(r1);
		
		//�޴��ٿ� �޴� ���
		mb.add(file);
		mb.add(color);
		mb.add(reg_auth);
		
		//�����ӿ� �޴��� ���
		setJMenuBar(mb);
		//text pane ���
		add(text);
		
		//������ ���
		c1.addActionListener(this);
		f1.addActionListener(this);
	
		r1.addActionListener(this);
	
		
		
		
	}

	//�����޴�
	public void profMakeMenu() {
		text = new JTextPane();
		
		mb = new JMenuBar();
		
		file = new JMenu("�л�����");
		file.setMnemonic(KeyEvent.VK_F); //Alt+F
		
		color = new JMenu("���������");
		color.setMnemonic(KeyEvent.VK_C);
		
		
		font = new JMenu("��������");
		font.setMnemonic(KeyEvent.VK_F);
		
		reg_auth = new JMenu("���ѵ��");
		reg_auth.setMnemonic(KeyEvent.VK_F);
		
		
		//����޴�
		f1 = new JMenuItem("�л����");//
		f1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));

		c1 = new JMenuItem("���");
		c1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		
		s1 = new JMenuItem("���");
		s1.setAccelerator(KeyStroke.getKeyStroke('R',Event.CTRL_MASK));

		r1 = new JMenuItem("�н����� ����");//
		r1.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));
		
	
		
		//�޴��� ����޴� ���
		file.add(f1);
		file.addSeparator(); //���м�
		
		//���� �޴� ����޴� ���
		color.add(c1);

		//font �޴� ���
		font.add(s1);
		
		reg_auth.add(r1);
		
		//�޴��ٿ� �޴� ���
		mb.add(file);
		mb.add(color);
		mb.add(font);
		mb.add(reg_auth);
		
		//�����ӿ� �޴��� ���
		setJMenuBar(mb);
		//text pane ���
		add(text);
		
		//������ ���
		c1.addActionListener(this);
		f1.addActionListener(this);
		s1.addActionListener(this);
		r1.addActionListener(this);
	
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 JMenuItem mi = (JMenuItem)e.getSource();
		 System.out.println("Ÿ��:"+ auth_type + ":"+ mi.getText() + ":"+ mi.getActionCommand()); //���,���
		 System.out.println("auth_code:"+ auth_code);
		 //���� �޴� ó��..
		 if(auth_type==1) { //�л�
			 
			 if(mi.getText().equals("�������")) {
				 //Course_register course_reg  = new Course_register();
				 Course_register course_reg  = new Course_register(scode,sname,smajor);
					//this.setVisible(true);
			 }else if (mi.getText().equals("�н����� ����")) {
				Modify modify = new Modify();
				modify.majorcode.setText("�л��ڵ�");
				modify.auth_type=1;
				//modify.auth_code = auth_code;
				modify.tmajorcode.setText(auth_code);
				modify.tmajorname.setText(modify.getName(auth_code, "", "�л�"));
				
				
				this.setVisible(true);	
			}
		 }else if(auth_type ==2) {//����
			 if(mi.getText().equals("�л����")) {
				 Student student = new Student();
					this.setVisible(true);
			 }else if (mi.getText().equals("�н����� ����")) {
				Modify modify = new Modify();
				modify.majorcode.setText("�����ڵ�");
				modify.auth_type=2;
				//modify.auth_code = auth_code;
				modify.tmajorcode.setText(auth_code);
				modify.tmajorname.setText(modify.getName(auth_code, "", "����"));
				
				this.setVisible(true);	
			}
		 }else {
			 MessageDialog("������ �����ϴ�.");//�˾��޽���.
		 }
		 
		

		
	}
	
	public static void main(String[] args) {
		new MenuDemo();

	}


	public void MessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message,message, JOptionPane.WARNING_MESSAGE);
	}
}
