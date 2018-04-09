package swing.exam07_project01;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import swing.exam06_jdbc.ConnectionJDBCExample;

public class Member extends JFrame {
	JPanel northp,northp2;
	JLabel title , name ,age ,gender1,addr ,phone,email ;
	Font font1,font2;
	JTextField tname,tage, taddr, tphone1,tphone2,tphone3,temail1,temail2;
	JComboBox emailcb;
	CheckboxGroup gender;
	Checkbox male, female;
	
	
	@SuppressWarnings("unchecked")
	public Member() {
		setTitle("자바 주소록 관리 프로그램");
		setBounds(50,50,600,650);
		setVisible(true);
		
		northp = new JPanel();
		northp.setLayout(null);
		northp.setBounds(0,0,600,40);
		
		title = new JLabel("컨버젼스 개발과정 주소록 관리 프로그램");
		title.setBounds(70,10,600,25);
		
		font1 = new Font("돋움",Font.BOLD,23);
		font2 = new Font("seif",Font.BOLD,20);
		title.setFont(font1);
		title.setForeground(Color.BLACK);
		//northp.setBackground(Color.BLACK);
		northp.add(title);
		add(northp);
		
		//두번째 패널
			
		name = new JLabel("이름");
		tname = new JTextField(20);
		age = new JLabel("나이");
		tage = new JTextField(20);
		
		gender1 = new JLabel("성별");
		gender = new CheckboxGroup();
		male = new Checkbox("남자",gender,true);
		female = new Checkbox("여자",gender,false);
		
		addr = new JLabel("주소");
		taddr = new JTextField(20);
		
		phone = new JLabel("연락처");
		tphone1 = new JTextField();
		JLabel phone1 = new JLabel("   -");
		JLabel phone2 = new JLabel("   -");
		tphone2 = new JTextField();
		tphone3 = new JTextField();
		
		email = new JLabel("이메일");
		temail1 = new JTextField();
		JLabel email1 = new JLabel(" @");
		temail2 = new JTextField();
		
		emailcb = new JComboBox();
		emailcb.addItem("직접입력");
		emailcb.addItem("naver.com");
		emailcb.addItem("daum.net");
		emailcb.addItem("gmail.com");
		emailcb.addItem("nate.com");
		
		int iHPlus = 30;
		
		name.setBounds(30,20+iHPlus,60,25);
		tname.setBounds(70,20+iHPlus,180,25);
		age.setBounds(260,20+iHPlus,35,25);
		tage.setBounds(300,20+iHPlus,100,25);
		
		gender1.setBounds(410,20+iHPlus,30,25);;
		male.setBounds(450,20+iHPlus,40,25);
		female.setBounds(500,20+iHPlus,80,25);
		
		addr.setBounds(30,55+iHPlus,35,25);
		taddr.setBounds(70,55+iHPlus,470,25);
		
		phone.setBounds(25,90+iHPlus,40,25);
		tphone1.setBounds(70,90+iHPlus,140,25);
		phone1.setBounds(210,90+iHPlus,25,25);
		tphone2.setBounds(235,90+iHPlus,140,25);
		phone2.setBounds(375,90+iHPlus,25,25);
		tphone3.setBounds(400,90+iHPlus,140,25);
		//phone1.setBounds(210,90,25,25);
		
		email.setBounds(25,125+iHPlus,40,25);
		temail1.setBounds(70,125+iHPlus,140,25);
		email1.setBounds(210,125+iHPlus,25,25);
		temail2.setBounds(235,125+iHPlus,160,25);
		
		emailcb.setBounds(410,125+iHPlus,130,25);
		
		//패널up
		northp2 = new JPanel();
		northp2.setLayout(null);
		northp2.setBounds(0,30+iHPlus,600,160);
		northp2.setForeground(Color.BLACK);
		northp2.setBackground(Color.GRAY);
		
		northp2.add(name);
		northp2.add(tname);
		northp2.add(age);
		northp2.add(tage);
		northp2.add(gender1);
		northp2.add(male);
		northp2.add(female);
		northp2.add(addr);
		northp2.add(taddr);
		northp2.add(phone);
		northp2.add(tphone1);
		northp2.add(tphone2);
		northp2.add(tphone3);
		northp2.add(phone1);
		northp2.add(phone2);
		northp2.add(email);
		northp2.add(email1);
		northp2.add(temail1);
		northp2.add(temail2);
		northp2.add(emailcb);
		
		add(northp2);		
				
		
		
	}
	
	public static void main(String[] args) {
		new Member();
	}
}
