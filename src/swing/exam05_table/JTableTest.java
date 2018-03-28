package swing.exam05_table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JTableTest extends JFrame implements ActionListener{
		DefaultTableModel dtm;
		JTable table;
		JScrollPane scroll;
		JPanel north, south;
		JLabel label;
		JButton btn1,btn2,btn3,btn4;
		
		
		public JTableTest() {
			setTitle("안녕 테이블~^^");
			
			//setSize(500,500);
			label = new JLabel("< Job Table >");
			north = new JPanel();
			north.add(label);
			add("North",north);
			
			
			String[] columnNames = {"이름","나이","직업"};
			Object[][] rowData = {
					{"홍길동",13,"학생"},
					{"김유신",14,"화랑"},
					{"김덕만",16,"공주"}
			};
			
			dtm = new DefaultTableModel(rowData,columnNames);
			table = new JTable(dtm);
			scroll = new JScrollPane(table);
			add(scroll);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(400,300,300,300);
			
			south = new JPanel();
			btn1 = new JButton("추가");
			btn2 = new JButton("수정");
			btn3 = new JButton("삭제");
			btn4 = new JButton("종료");
			
			south.add(btn1);
			south.add(btn2);
			south.add(btn3);
			south.add(btn4);
			
				
			add("South",south);
			setVisible(true);
			
			btn1.addActionListener(this);
			btn2.addActionListener(this);
			btn3.addActionListener(this);
			btn4.addActionListener(this);
			
		}
	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Object evt = e.getSource();
			String evt = e.getActionCommand();
			
			switch(evt) {
			case "추가":
				new Form().setTitle("입력폼");
				break;
			case "수정":
				new Form().setTitle("수정폼");
				break;
			case "삭제":
				
				break;
			case "종료":
				System.exit(0);
				break;
				
			}
		}
		
		class Form extends JFrame{
			JLabel lb1,lb2,lb3;
			JTextField tf1,tf2,tf3;
			JPanel p1,p2,p3;
			JButton btn1, btn2;
			
			public Form() {
				setTitle("");
				setBounds(300,300,220,300);
				lb1 = new JLabel("이름");
				lb2 = new JLabel("나이");
				lb3 = new JLabel("직업");
				
				tf1 = new JTextField();
				tf2 = new JTextField();
				tf3 = new JTextField();
				
				btn1 = new JButton("입력");
				btn2 = new JButton("취소");
				
				setLayout(null);
				
				
				lb1.setBounds(30,30,30,30);
				lb2.setBounds(30,80,30,30);
				lb3.setBounds(30,130,60,30);
				
				tf1.setBounds(80,30,80,30);
				tf2.setBounds(80,80,80,30);
				tf3.setBounds(80,130,80,30);
				
				btn1.setBounds(30,190,60,30);
				btn2.setBounds(100,190,60,30);
				
				add(lb1);
				add(lb2);
				add(lb3);
				
				add(tf1);
				add(tf2);
				add(tf3);
				
				add(btn1);
				add(btn2);
				
				setBounds(300,300,220,300);
				setResizable(false);
				setVisible(true);
			
				
			}
			
		}

}


