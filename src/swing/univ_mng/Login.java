package swing.univ_mng;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Login extends JFrame implements ActionListener,MouseListener{
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scroll;

	Toolkit tk; // 이미지 아이콘
	Image logo;

	JPanel nnorthp; // 자바 레이블...
	JPanel northp; // 이름 레이블...
	JPanel orthp; // 카테고리
	JPanel tablep; // 테이블
	JPanel southp; // 버튼

	String auth_code=null;
	
	JLabel title;
	
	JLabel majorcode,majorname,jumin,major2,label1,label2;// 전공코드, 학부명, 전공명
	JTextField tmajorcode,tmajorname,tjumin;//각 input box
	

	JComboBox search,majorselect;
	JTextField tsearch;
	JButton bsearch;
	JButton tbsearch;
	JRadioButton r1,r2;
	

/*	JButton input;
	JButton edit;
	JButton delete;*/
	JButton exit;

	Font f1, f2;

	  Connection conn=null;
	  Statement stmt =null;
	  PreparedStatement pstmt =null;
	  ResultSet rs = null;
	  
	  static int idx =-1;
	  String sname ;//이름
	  String smajor;//전공과목
	  
	public Login() { // 생성자
		ConnectionDB(); //DB연결
		this.setTitle("로그인 ");

		Toolkit tk = Toolkit.getDefaultToolkit(); // 이미지 얻어와서 출력
		logo = tk.getImage("c:\\이미지/5.jpg"); // 프레임에 이미지 출력
		this.setIconImage(logo); // JFrame에 올리기

		f1 = new Font("돋움", Font.BOLD, 23);
		f2 = new Font("serif", Font.BOLD, 20);

		this.setLayout(null);
		this.setBounds(50, 50, 600, 650);

		// 첫번째 nnorthp 패널(컨버전스 레이블...)
		nnorthp = new JPanel();
		nnorthp.setLayout(null);
		nnorthp.setBounds(0, 0, 600, 40);

		title = new JLabel("권한 관리");
		title.setBounds(220, 8, 600, 25);
		title.setFont(f1);
		title.setForeground(Color.WHITE);
		nnorthp.setBackground(Color.BLACK);
		// nnorthp 패널 등록
		nnorthp.add(title);
		this.add(nnorthp);

		// 두번째 northp 패널(이름 레이블...)
		northp = new JPanel();
		northp.setLayout(null);
		northp.setBounds(0, 30, 600, 160);

		majorcode = new JLabel("아이디");
		tmajorcode = new JTextField(20);
		
		majorname = new JLabel("비번");
		tmajorname = new JTextField(20);
		
		
		jumin = new JLabel("이름");
		tjumin = new JTextField(20);
		
		label1 = new JLabel("교수");
		label2 = new JLabel("학생");
		
		r1 = new JRadioButton("교수");
		r2 = new JRadioButton("학생");
		
		ButtonGroup group = new ButtonGroup();
		group.add(r1);
		group.add(r2);

		
		majorcode.setBounds(20, 20, 60, 25);
		tmajorcode.setBounds(80, 20, 100, 25);
		
		majorname.setBounds(190, 20, 50, 25);
		tmajorname.setBounds(230, 20, 120, 25);
		
		jumin.setBounds(360, 20, 60, 25);
		tjumin.setBounds(420, 20, 150, 25);
		
		label1.setBounds(20,50,30,25);
		label2.setBounds(80,50,30,25);
		r1.setBounds(50,50,20,25);
		r2.setBounds(110,50,20,25);

		// northp 패널 등록
		northp.add(majorcode);
		northp.add(tmajorcode);
		northp.add(majorname);
		northp.add(tmajorname);
		northp.add(jumin);
		northp.add(tjumin);
		northp.add(label1);
		northp.add(label2);
		northp.add(r1);
		northp.add(r2);
		
	
		this.add(northp);

		// 세번째 패널 orthp(검색할 ...카테고리)
		orthp = new JPanel();
		orthp.setLayout(null);
		orthp.setBounds(0, 160, 600, 80);
		orthp.setBackground(Color.BLUE);

		bsearch = new JButton("확인");
		bsearch.setBounds(250, 45, 74, 25);

		bsearch.setBackground(Color.GRAY);
		bsearch.setForeground(Color.RED);
		orthp.add(bsearch);
		add("Center",orthp);

		

		// 다섯번째 southp 패널(버튼들)
		southp = new JPanel();
		southp.setLayout(null);
		southp.setBounds(5, 500, 600, 200);

		exit = new JButton("종료");

		exit.setBounds(438, 45, 138, 40);
		exit.setFont(f2);
		exit.setBackground(Color.MAGENTA);
		exit.setForeground(Color.WHITE);

		southp.add(exit);
		exit.addActionListener(this);//수정
		bsearch.addActionListener(this);

		
		add("South",southp);
		
		this.setVisible(true);

	} // 생성자 끝

	// 검색창 선택 처리.
	

	//========== DB 연결==========
	public void ConnectionDB() {
		
		
		String driverName ="com.mysql.jdbc.Driver";
		//String url ="jdbc:mysql://localhost:3306/world";
		String url = "jdbc:mysql://127.0.0.1:3306/univ_mng?useSSL=true&verifyServerCertificate=false";//SSL 에러발생시..
		
		String user="root";
		String password ="0000";
		
		try{
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("JDBC Driver Connection Success");
				
		}catch (ClassNotFoundException e){
			System.out.println("mysql jdbc driver can't loading..");
			//e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("커넥션이 연결하지 못하였습니다.");
		} 
	
	}
	
	//학생 전공과목명 가져오기
	public String getMajorName(String scode) {
		String name=null;
		String sql = "SELECT b.major_name";
				sql += " FROM student a ";
				sql += " join major b ";
				sql += " on a.std_major_code = b.major_code ";
				sql += " where a.std_code =?";
				
				
		try {
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scode);
			
			System.out.println("sql:"+ pstmt.toString());
			
			rs = pstmt.executeQuery();
			
				if(rs.next()) {
					
					name = rs.getString("major_name");
					
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	
	//권한 관리자 이름 가져오기 
	public String getName(String id, String password , String type) {
		String name=null;
		String sql =null;
				if(type.equals("학생")) {
					sql ="SELECT * FROM student where std_code =?";
				}else if(type.equals("교수")) {
					sql ="SELECT * FROM professor where prof_code =?";
				}
				
		try {
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			System.out.println("sql:"+ pstmt.toString());
			
			rs = pstmt.executeQuery();
			
				if(rs.next()) {
					if(type.equals("학생")) {
						name = rs.getString("std_name");
					}else {
						name = rs.getString("prof_name");
					}
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
		
	
	
	//==========[ 권한 로그인 ]============
	public boolean getLoginCheck(String id, String password , String type) {
		boolean result = false;
	
		String sql =null;
				sql ="SELECT * FROM access_auth\r\n" + 
						"where auth_id =? and auth_password=? ";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setNString(2, password);
			
			rs = pstmt.executeQuery();
			
				if(rs.next()) {
					
					String auth_id = rs.getString("auth_id");
					String auth_password = rs.getString("auth_password");
					String auth_type = rs.getString("auth_descript");
					String sauth_code = rs.getString("auth_code");
					
					
					result = true;
					auth_code = sauth_code; //학생,교수 코드 지정.
					sname = getName(sauth_code,auth_password,auth_type);
					tjumin.setText(sname); //이름 가져오기.
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//초기화
	public void setClean() {
		if(r1.isSelected()) r1.setSelected(false);
		if(r2.isSelected()) r2.setSelected(false);
		tmajorcode.setText("");
		tmajorname.setText("");
		tjumin.setText("");
	}

	//ActionListner
	public void actionPerformed(ActionEvent e) {
	
		
		String evt = e.getActionCommand();
			
		Object o = e.getSource();
		
		if(o ==exit) {
			System.exit(0);
		}else if(o == bsearch) {//검색
			boolean opt1 = r1.isSelected(); //교수
			boolean opt2 = r2.isSelected(); //학생
			String type=null;
			
			//MessageDialog("옵션:"+ opt1 +":"+ opt2);
			
			if(opt1 || opt2) {
				if(opt1) type="교수";
				if(opt2) type ="학생";
				String id = tmajorcode.getText();
				String password = tmajorname.getText();
				String name = tjumin.getText();
				
				//field check
				
				if(id.equals("") || password.equals("")) {
					MessageDialog("아이디와 비번을 입력하세요.");//팝업메시지.
					tmajorcode.requestFocus();
				}else {
					
					if(getLoginCheck(id, password, type)) {
						MessageDialog(tjumin.getText()+" "+ type+"님 반갑습니다.");//팝업메시지.
						
						setClean();
						MenuDemo menu = new MenuDemo();
						
						if(auth_code!=null)
							menu.auth_code = auth_code; //코드셋
						
						if(opt1) {
							menu.profMakeMenu();
							menu.auth_type=2;//교수
						}
							
						if(opt2) {
							smajor =getMajorName(auth_code);
							menu.stdMakeMenu(auth_code,sname,smajor);
							menu.auth_type = 1;//학생
						}
						
						this.dispose();
						
					}else {
						MessageDialog("아이디 & 비번이 일치하지 않습니다.");//팝업메시지.
						tmajorcode.requestFocus();
					}
				}
				
				
			}else {
				MessageDialog("[학생,교수]옵션을 선택하세요.");//팝업메시지.
			}
			
		
		}
		
	}
		
		
	public void MessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message,message, JOptionPane.WARNING_MESSAGE);
	}
	

	public static void main(String[] args) {
		new Login();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}


	
