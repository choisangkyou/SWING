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

	Toolkit tk; // �̹��� ������
	Image logo;

	JPanel nnorthp; // �ڹ� ���̺�...
	JPanel northp; // �̸� ���̺�...
	JPanel orthp; // ī�װ�
	JPanel tablep; // ���̺�
	JPanel southp; // ��ư

	
	JLabel title;
	
	JLabel majorcode,majorname,major;// �����ڵ�, �кθ�, ������
	JTextField tmajorcode,tmajorname,tmajor;//�� input box
	

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
		
		this.setTitle("������û");

		Toolkit tk = Toolkit.getDefaultToolkit(); // �̹��� ���ͼ� ���
		logo = tk.getImage("c:\\�̹���/5.jpg"); // �����ӿ� �̹��� ���
		this.setIconImage(logo); // JFrame�� �ø���

		f1 = new Font("����", Font.BOLD, 23);
		f2 = new Font("serif", Font.BOLD, 20);

		this.setLayout(null);
		this.setBounds(50, 50, 600, 650);

		// ù��° nnorthp �г�(�������� ���̺�...)
		nnorthp = new JPanel();
		nnorthp.setLayout(null);
		nnorthp.setBounds(0, 0, 600, 40);

		title = new JLabel("������û");
		title.setBounds(220, 8, 600, 25);
		title.setFont(f1);
		title.setForeground(Color.WHITE);
		nnorthp.setBackground(Color.BLACK);
		// nnorthp �г� ���
		nnorthp.add(title);
		this.add(nnorthp);

		// �ι�° northp �г�(�̸� ���̺�...)
		northp = new JPanel();
		northp.setLayout(null);
		northp.setBounds(0, 30, 600, 160);

		majorcode = new JLabel("�л��ڵ�");
		tmajorcode = new JTextField(20);
		
		majorname = new JLabel("�л���");
		tmajorname = new JTextField(20);
		
		
		major = new JLabel("������");
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
		
		

		// northp �г� ���
		northp.add(majorcode);
		northp.add(tmajorcode);
		northp.add(majorname);
		northp.add(tmajorname);
		northp.add(major);
		northp.add(tmajor);
	
		this.add(northp);

		// ����° �г� orthp(�˻��� ...ī�װ�)
		orthp = new JPanel();
		orthp.setLayout(null);
		orthp.setBounds(0, 160, 600, 80);
		orthp.setBackground(Color.BLUE);

		search = new JComboBox();
		search.addItem("�˻��� ī�װ��� �����ϼ���");
		search.addItem("major_code");
		search.addItem("major_name");
		search.addItem("major");

		tsearch = new JTextField();
		bsearch = new JButton("��ȸ");
		tbsearch = new JButton("��ü��ȸ");

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

		

		// �ټ���° southp �г�(��ư��)
		southp = new JPanel();
		southp.setLayout(null);
		southp.setBounds(5, 500, 600, 200);

		input = new JButton("���");
		edit = new JButton("����");
		delete = new JButton("����");
		exit = new JButton("����");

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

		//AcitionListener ���
		input.addActionListener( this); //�Է¹�ư.
		edit.addActionListener(this);
		bsearch.addActionListener(this); //��ȸ
		tbsearch.addActionListener(this); //��ü ��ȸ
		exit.addActionListener(this);//����
		delete.addActionListener(this);//����
		
		search.addItemListener(new ItemListener(){
			String category=null;
			@Override
			public void itemStateChanged(ItemEvent e) {
				category = (String)e.getItem();
				tsearch.requestFocus(); //��Ŀ�� �̵�.
				
			}
			
		});
		
		
		
		
		//�׹�° �г�.���̺� �г�
		
		String columnNames[]= {
				
				"<HTML><HEAD><H4><font color=red>�������ڵ�</font></H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>�������</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>��������</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>�����ü�</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>��米��</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>����</H4></HEAD></HTML>"
			 };

		

		//������û ��� ���̺�
		Object[][] rowData= {};
		dtm=new DefaultTableModel(rowData,columnNames);
		table=new JTable(dtm);
		scroll=new JScrollPane(table);//���ڷѸ��ο� ���̺��߰�
		
		//������û �߰� ���̺�
		Object[][] rowData2= {};
		dtm2=new DefaultTableModel(rowData2,columnNames);
		table2=new JTable(dtm2);
		scroll2=new JScrollPane(table2);//���ڷѸ��ο� ���̺��߰�		
		
		table.addMouseListener(this);
		table2.addMouseListener(this);
	
		
		ConnectionDB(); //DB����
		getListAll(); //����Ʈ ��������.
		getListAll2(); //����Ʈ ��������.
		//addList();
		
		scroll.setBounds(0,230,600,250);
		scroll2.setBounds(0,480,600,250);
		
		add("Center",scroll);
		add("Center",scroll2);
				
		
		add("South",southp);
		
		this.setVisible(true);

	} // ������ ��

	// �˻�â ���� ó��.
	

	//========== DB ����==========
	public void ConnectionDB() {
		
		
		String driverName ="com.mysql.jdbc.Driver";
		//String url ="jdbc:mysql://localhost:3306/world";
		String url = "jdbc:mysql://127.0.0.1:3306/univ_mng?useSSL=true&verifyServerCertificate=false";//SSL �����߻���..
		
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
			System.out.println("Ŀ�ؼ��� �������� ���Ͽ����ϴ�.");
		} 
	
	}
	
	//==========��� ����ƮAll============
	public void getListAll() {
		dtm.setRowCount(0); // ǥ���� ��index ����.
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
	

	//==========������û ����Ʈ============
		public void getListAll2() {
			dtm2.setRowCount(0); // ǥ���� ��index ����.
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
					System.out.println("===������û ���� ���===");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	//==========[ �κ� �˻� ] ============
		public void getListOpt(String arg1, String arg2) { //�÷���, �� ���� �˻�
			dtm.setRowCount(0); // ǥ���� ��index ����.
			String sql = "select * from major";
					sql += " where "+ arg1 + " like  '%" + arg2 + "%'";
					
			try {
				
				System.out.println("�˻�:"+ sql);
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
		
	//============ ��� �Է��ϱ� ============
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
	//============ ��� ������Ʈ ============
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
		
		
	
	// TextField �ʱ�ȭ 
	public void ItemClean() {
		tmajorcode.setText("");
		tmajorname.setText("");
		tmajor.setText("");

	}
	
	// TextField �ʱ�ȭ 
	public void selectedDataView(String[] values) {
			tmajorcode.setText(values[0]);
			tmajorname.setText(values[1]);
			tmajor.setText(values[2]);//����ó
	}
	
	
	//ActionListner
	public void actionPerformed(ActionEvent e) {
	
		
		String evt = e.getActionCommand();
		if(evt.equals("���")) {
			String majorcode = tmajorcode.getText();
			String majorname = tmajorname.getText();
			String major = tmajor.getText();
			
			if(majorcode.equals("") || majorname.equals("") || major.equals("") ) {
				MessageDialog("�����͸� �Է��ϼ���.");
			}else {
				System.out.println("���else"+majorcode);
				String[] data = {majorcode,majorname,major};
				addList(data); //�� �Էµ� textField���� DB�� Insert�ϴ� �Լ� ȣ��
				getListAll(); //��ü ����Ʈ�� �ݿ�.
				ItemClean();//�ʱ�ȭ.
			}
			
			
			
		}
		
		Object o = e.getSource();
		
		if(o ==exit) {
			System.exit(0);
		}else if(o == bsearch) {//�˻�
			
			String item = (String) search.getSelectedItem();
			
			String value = tsearch.getText();
			
			if(item.contains("�˻�") || value.equals("")) {
				MessageDialog("�˻������� �Է��ϼ���.");//�˾��޽���.
			}else {
				getListOpt(item,value);//
			}
			
		}else if(o == tbsearch) {//��ü �˻�.
			getListAll();
		}else if(o == edit) {
			//System.out.println("������� �Դϴ�.");
			String majorcode = tmajorcode.getText();
			String majorname = tmajorname.getText();
			String major = tmajor.getText();
			
			if(!majorcode.equals(null)) {
				String[] data = {majorcode,majorname,major};
				UpdateList(data,Integer.parseInt(majorcode)); //�� �Էµ� textField���� DB�� Insert�ϴ� �Լ� ȣ��
				getListAll(); //��ü ����Ʈ�� �ݿ�.
				
			}else {
				MessageDialog("������ ���̺��� �����ϼ���.");
			}
			
			ItemClean();//�ʱ�ȭ.
			
		}else if (o== delete) {
			if(idx == -1) {
				MessageDialog("������ ���̺��� �����ϼ���.");
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
						getListAll(); //��ü ����Ʈ�� �ݿ�.
						ItemClean();//�ʱ�ȭ.
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

	
	//============ ������û  ============
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
							getListAll();//��������.
							getListAll2();//��������.	
						}else {
							System.out.println("delete addCourse  fail..");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("text:"+ majorcode.getText());
					
					
		}
		

		//============ ������û ����  ============
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
									getListAll();//��������.
									getListAll2();//��������.	
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
			
			if(ss.equals(table)) {//������û
				System.out.println("table1 selected");
				int iRow =table.getSelectedRow();
				 //�������ڵ�,�������,��������, �����ü�,��米��,����
				 String val0 = (String) table.getValueAt(iRow,0);//�����ڵ�
				 String val1 = (String) table.getValueAt(iRow,1);//�кθ�
				 String val2 = (String) table.getValueAt(iRow,2);//������
				 String val3 = (String) table.getValueAt(iRow,3);//�����ڵ�
				 String val4 = (String) table.getValueAt(iRow,4);//�кθ�
				 String val5 = (String) table.getValueAt(iRow,5);//������
				 
				 idlist.add(Integer.parseInt(val0)); //���� �߰��� �������� ����
						
				Object[] list = {val0,val1,val2,val3,val4,val5};
				dtm2.addRow(list);
				String[] data = {val0,val1,val2,val3,val4,val5,scode};
				addCourse(data);
				
			}else {
				//�������� ����.
				System.out.println("table2 selected");
				int iRow2 =table2.getSelectedRow();
				int val0 = (int) table2.getValueAt(iRow2,0);//�����ڵ�
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


	
