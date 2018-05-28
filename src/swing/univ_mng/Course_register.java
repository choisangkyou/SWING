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
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Course_register extends JFrame implements ActionListener,MouseListener{
	JTable table,table2;
	DefaultTableModel dtm,dtm2;
	JScrollPane scroll,scroll2;

	Toolkit tk; // 이미지 아이콘
	Image logo;

	JPanel nnorthp; // 자바 레이블...
	JPanel northp; // 이름 레이블...
	JPanel orthp; // 카테고리
	JPanel tablep; // 테이블
	JPanel southp; // 버튼

	
	JLabel title;
	
	JLabel majorcode,majorname,major;// 전공코드, 학부명, 전공명
	JTextField tmajorcode,tmajorname,tmajor;//각 input box
	

	JComboBox search;
	JTextField tsearch;
	JButton bsearch;
	JButton tbsearch;

	JButton input;
	JButton edit;
	JButton delete;
	JButton exit;

	Font f1, f2;

	  Connection conn=null;
	  Statement stmt =null;
	  PreparedStatement pstmt =null;
	  ResultSet rs = null;
	  
	  static int idx =-1;
	  

	  List<Integer> idlist = new ArrayList<Integer>();
  
	  
	public Course_register(String scode,String sname,String smajor) {
		
		this.setTitle("수강신청");

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

		title = new JLabel("수강신청");
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

		majorcode = new JLabel("학생코드");
		tmajorcode = new JTextField(20);
		
		majorname = new JLabel("학생명");
		tmajorname = new JTextField(20);
		
		
		major = new JLabel("전공명");
		tmajor = new JTextField(20);
		
		tmajorcode.setText(scode);
		tmajorname.setText(sname);
		tmajor.setText(smajor);
		

		majorcode.setBounds(20, 20, 60, 25);
		tmajorcode.setBounds(80, 20, 100, 25);
		
		majorname.setBounds(200, 20, 50, 25);
		tmajorname.setBounds(250, 20, 120, 25);
		
		major.setBounds(390, 20, 50, 25);
		tmajor.setBounds(440, 20, 120, 25);
		
		

		// northp 패널 등록
		northp.add(majorcode);
		northp.add(tmajorcode);
		northp.add(majorname);
		northp.add(tmajorname);
		northp.add(major);
		northp.add(tmajor);
	
		this.add(northp);

		// 세번째 패널 orthp(검색할 ...카테고리)
		orthp = new JPanel();
		orthp.setLayout(null);
		orthp.setBounds(0, 160, 600, 80);
		orthp.setBackground(Color.BLUE);

		search = new JComboBox();
		search.addItem("검색할 카테고리를 선택하세요");
		search.addItem("major_code");
		search.addItem("major_name");
		search.addItem("major");

		tsearch = new JTextField();
		bsearch = new JButton("조회");
		tbsearch = new JButton("전체조회");

		search.setBounds(40, 42, 170, 25);
		tsearch.setBounds(230, 42, 140, 25);
		bsearch.setBounds(380, 42, 74, 25);
		tbsearch.setBounds(470, 42, 90, 25);

		bsearch.setBackground(Color.GREEN);
		bsearch.setForeground(Color.RED);
		tbsearch.setBackground(Color.GREEN);
		tbsearch.setForeground(Color.RED);

		orthp.add(search);
		orthp.add(tsearch);
		orthp.add(bsearch);
		orthp.add(tbsearch);

		this.add(orthp);

		

		// 다섯번째 southp 패널(버튼들)
		southp = new JPanel();
		southp.setLayout(null);
		southp.setBounds(5, 500, 600, 200);

		input = new JButton("등록");
		edit = new JButton("수정");
		delete = new JButton("삭제");
		exit = new JButton("종료");

		input.setBounds(0, 70, 142, 40);
		edit.setBounds(146, 70, 142, 40);
		delete.setBounds(292, 70, 142, 40);
		exit.setBounds(438, 70, 138, 40);

		input.setFont(f2);
		input.setBackground(Color.BLUE);
		input.setForeground(Color.YELLOW);
		edit.setFont(f2);
		edit.setBackground(Color.YELLOW);
		edit.setForeground(Color.BLACK);
		delete.setFont(f2);
		delete.setBackground(Color.GREEN);
		delete.setForeground(Color.RED);
		exit.setFont(f2);
		exit.setBackground(Color.MAGENTA);
		exit.setForeground(Color.WHITE);

		southp.add(input);
		southp.add(edit);
		southp.add(delete);
		southp.add(exit);

		// this.add(southp, "SOUTH");

		//AcitionListener 등록
		input.addActionListener( this); //입력버튼.
		edit.addActionListener(this);
		bsearch.addActionListener(this); //조회
		tbsearch.addActionListener(this); //전체 조회
		exit.addActionListener(this);//수정
		delete.addActionListener(this);//삭제
		
		search.addItemListener(new ItemListener(){
			String category=null;
			@Override
			public void itemStateChanged(ItemEvent e) {
				category = (String)e.getItem();
				tsearch.requestFocus(); //포커스 이동.
				
			}
			
		});
		
		
		
		
		//네번째 패널.테이블 패널
		
		String columnNames[]= {
				
				"<HTML><HEAD><H4><font color=red>교과목코드</font></H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>교과목명</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>개설전공</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>수업시수</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>담당교수</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>학점</H4></HEAD></HTML>"
			 };

		

		//수강신청 목록 테이블
		Object[][] rowData= {};
		dtm=new DefaultTableModel(rowData,columnNames);
		table=new JTable(dtm);
		scroll=new JScrollPane(table);//스코롤메인에 테이블추가
		
		//수강신청 추가 테이블
		Object[][] rowData2= {};
		dtm2=new DefaultTableModel(rowData2,columnNames);
		table2=new JTable(dtm2);
		scroll2=new JScrollPane(table2);//스코롤메인에 테이블추가		
		
		table.addMouseListener(this);
		table2.addMouseListener(this);
	
		
		ConnectionDB(); //DB연결
		getListAll(); //리스트 가져오기.
		getListAll2(); //리스트 가져오기.
		//addList();
		
		scroll.setBounds(0,230,600,250);
		scroll2.setBounds(0,480,600,250);
		
		add("Center",scroll);
		add("Center",scroll2);
				
		
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
	
	//==========멤버 리스트All============
	public void getListAll() {
		dtm.setRowCount(0); // 표시할 행index 지정.
		 String scode = tmajorcode.getText();
		 
		String sql = "select major_code,major_name,major,major_time,prof_name ,major_credit\r\n" + 
				" from  major a\r\n" + 
				" join professor b\r\n" + 
				" on a.major_code = b.prof_majorcode";
			 	sql += " where a.major_code not in \r\n" + 
			 			" (select code from course_register where sid ="+ scode+")";
				
		try {
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
				while(rs.next()) {
					String majorcode = rs.getString("major_code");
					String majorname = rs.getString("major_name");
					String major = rs.getString("major");
					String time = rs.getString("major_time");
					String prof_name = rs.getString("prof_name");
					String major_credit = rs.getString("major_credit");
				
				Object[] list = {majorcode,majorname,major,time,prof_name,major_credit};
				dtm.addRow(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	//==========수강신청 리스트============
		public void getListAll2() {
			dtm2.setRowCount(0); // 표시할 행index 지정.
			 String scode = tmajorcode.getText();
			 
			String sql = "SELECT * FROM course_register where sid ="+ scode+"";
					
			try {
				System.out.println(sql);
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
					while(rs.next()) {
						int majorcode = rs.getInt("code");
						String majorname = rs.getString("name");
						String major = rs.getString("major");
						String time = rs.getString("time");
						String prof_name = rs.getString("professor");
						String major_credit = rs.getString("credit");
					
					Object[] list2 = {majorcode,majorname,major,time,prof_name,major_credit};
					dtm2.addRow(list2);
					System.out.println("===수강신청 내역 출력===");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	//==========[ 부분 검색 ] ============
		public void getListOpt(String arg1, String arg2) { //컬럼명, 값 으로 검색
			dtm.setRowCount(0); // 표시할 행index 지정.
			String sql = "select * from major";
					sql += " where "+ arg1 + " like  '%" + arg2 + "%'";
					
			try {
				
				System.out.println("검색:"+ sql);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
					while(rs.next()) {
						String majorcode = rs.getString("major_code");
						String majorname = rs.getString("major_name");
						String major = rs.getString("major");
				
					Object[] list = {majorcode,majorname,major};
					dtm.addRow(list);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	//============ 멤버 입력하기 ============
	public void addList(String[] data) {
		PreparedStatement ps =null;
		String sql = "insert into major (major_code,major_name,major)";
				sql += "values(?,?,?)";
				
						
				try {
					ps =  conn.prepareStatement(sql);
					for(int i =0 ; i < data.length; i++) {
						ps.setString(i+1, data[i]);	
					}
					
					System.out.println("sql:"+ps.toString());
					
					if(ps.executeUpdate() > 0) {
						System.out.println("insert success");
					}else {
						System.out.println("insert fail..");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("text:"+ majorcode.getText());
				
				
	}
	//============ 멤버 업데이트 ============
		public void UpdateList(String[] data,int idx) {
			PreparedStatement ps =null;
			String sql = "update major ";
					sql += " set major_code = ?, major_name=?, major=? ";
					sql += " where major_code ="+ idx;
					
					
					try {
						ps =  conn.prepareStatement(sql);
						for(int i =0 ; i < data.length; i++) {
							ps.setString(i+1, data[i]);	
						}
						
						System.out.println("sql:"+ps.toString());
						
						if(ps.executeUpdate() > 0) {
							System.out.println("update success");
						}else {
							System.out.println("update fail..");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("text:"+ majorcode.getText());
					
					
		}
		
		
	
	// TextField 초기화 
	public void ItemClean() {
		tmajorcode.setText("");
		tmajorname.setText("");
		tmajor.setText("");

	}
	
	// TextField 초기화 
	public void selectedDataView(String[] values) {
			tmajorcode.setText(values[0]);
			tmajorname.setText(values[1]);
			tmajor.setText(values[2]);//연락처
	}
	
	
	//ActionListner
	public void actionPerformed(ActionEvent e) {
	
		
		String evt = e.getActionCommand();
		if(evt.equals("등록")) {
			String majorcode = tmajorcode.getText();
			String majorname = tmajorname.getText();
			String major = tmajor.getText();
			
			if(majorcode.equals("") || majorname.equals("") || major.equals("") ) {
				MessageDialog("데이터를 입력하세요.");
			}else {
				System.out.println("등록else"+majorcode);
				String[] data = {majorcode,majorname,major};
				addList(data); //각 입력된 textField값을 DB에 Insert하는 함수 호출
				getListAll(); //전체 리스트에 반영.
				ItemClean();//초기화.
			}
			
			
			
		}
		
		Object o = e.getSource();
		
		if(o ==exit) {
			System.exit(0);
		}else if(o == bsearch) {//검색
			
			String item = (String) search.getSelectedItem();
			
			String value = tsearch.getText();
			
			if(item.contains("검색") || value.equals("")) {
				MessageDialog("검색내용을 입력하세요.");//팝업메시지.
			}else {
				getListOpt(item,value);//
			}
			
		}else if(o == tbsearch) {//전체 검색.
			getListAll();
		}else if(o == edit) {
			//System.out.println("수정모드 입니다.");
			String majorcode = tmajorcode.getText();
			String majorname = tmajorname.getText();
			String major = tmajor.getText();
			
			if(!majorcode.equals(null)) {
				String[] data = {majorcode,majorname,major};
				UpdateList(data,Integer.parseInt(majorcode)); //각 입력된 textField값을 DB에 Insert하는 함수 호출
				getListAll(); //전체 리스트에 반영.
				
			}else {
				MessageDialog("삭제할 테이블을 선택하세요.");
			}
			
			ItemClean();//초기화.
			
		}else if (o== delete) {
			if(idx == -1) {
				MessageDialog("삭제할 테이블을 선택하세요.");
			}else {
				deleteMember(idx);
			}
		}
		
	}
		
		
	private void deleteMember(int idx) {
		PreparedStatement ps =null;
		String sql = "delete  from  person ";
				sql += " where idx ="+ idx;
				
				//String[] data = {majorcode,age,addr,phone1+"-"+phone2 +"-"+ phone3, email1 +email2,genderStr};
				
				try {
					ps =  conn.prepareStatement(sql);

					System.out.println("sql:"+ps.toString());
					
					if(ps.executeUpdate() > 0) {
						getListAll(); //전체 리스트에 반영.
						ItemClean();//초기화.
						System.out.println("delete success");
					}else {
						System.out.println("delete fail..");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("text:"+ majorcode.getText());
		
	}

	public static void main(String[] args) {
		//new Course_register();
		
	}

	
	//============ 수강신청  ============
		public void addCourse(String[] data) {
			PreparedStatement ps =null;
			String sql = "insert into course_register (code,name,major,time,professor,credit,sid)"; 
					sql += "values(?,?,?,?,?,?,?)";
					
							
					try {
						ps =  conn.prepareStatement(sql);
						for(int i =0 ; i < data.length; i++) {
							if(i==0 || i==(data.length-1)) //0,6 for integer
							{
								ps.setInt(i+1,Integer.parseInt(data[i]));
							}else {
								ps.setString(i+1, data[i]);
							}
								
						}
						
						System.out.println("sql:"+ps.toString());
						
						if(ps.executeUpdate() > 0) {
							System.out.println("delete  addCourse success");
							getListAll();//리프레쉬.
							getListAll2();//리프레쉬.	
						}else {
							System.out.println("delete addCourse  fail..");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("text:"+ majorcode.getText());
					
					
		}
		

		//============ 수강신청 삭제  ============
				public void deleteCourse(int ival, String sval) {
					PreparedStatement ps =null;
					String sql = "delete from course_register "; 
							sql += " where code = ? and sid =?";
							
									
							try {
								ps =  conn.prepareStatement(sql);
								ps.setInt(1,ival);
								ps.setInt(2,Integer.parseInt(sval));
									
				
								
								System.out.println("sql:"+ps.toString());
								
								if(ps.executeUpdate() > 0) {
									System.out.println("delete  addCourse success");
									getListAll();//리프레쉬.
									getListAll2();//리프레쉬.	
								}else {
									System.out.println("insert addCourse  fail..");
								}
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//System.out.println("text:"+ majorcode.getText());
							
							
				}
				
	@Override
	public void mouseClicked(MouseEvent arg0) {
					
			Object ss = arg0.getSource();
			String scode = tmajorcode.getText();
			
			if(ss.equals(table)) {//수강신청
				System.out.println("table1 selected");
				int iRow =table.getSelectedRow();
				 //교과목코드,교과목명,개설전공, 수업시수,담당교수,학점
				 String val0 = (String) table.getValueAt(iRow,0);//전공코드
				 String val1 = (String) table.getValueAt(iRow,1);//학부명
				 String val2 = (String) table.getValueAt(iRow,2);//전공명
				 String val3 = (String) table.getValueAt(iRow,3);//전공코드
				 String val4 = (String) table.getValueAt(iRow,4);//학부명
				 String val5 = (String) table.getValueAt(iRow,5);//전공명
				 
				 idlist.add(Integer.parseInt(val0)); //선택 추가한 교과목은 제외
						
				Object[] list = {val0,val1,val2,val3,val4,val5};
				dtm2.addRow(list);
				String[] data = {val0,val1,val2,val3,val4,val5,scode};
				addCourse(data);
				
			}else {
				//수강내역 삭제.
				System.out.println("table2 selected");
				int iRow2 =table2.getSelectedRow();
				int val0 = (int) table2.getValueAt(iRow2,0);//전공코드
				// String[] data = {val0,scode};
				 deleteCourse(val0,scode);
			}
			//System.out.println("getSource:"+ ss.equals(table2));
			
			 
		}

	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
		
	public void MessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
}


	
