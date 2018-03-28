package swing.exam02_event;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InnerClassListener extends JFrame{
	static JButton btn1,btn2;
	JPanel panel = new JPanel ();
	public InnerClassListener() {
		setTitle("이벤트 예제");
		setLayout(new FlowLayout());
		
		btn1 = new JButton("노란색");
		btn2 = new JButton("핑크색");
		
		add(btn1);
		add(btn2);
		
		
		panel.add(btn1);
		panel.add(btn2);
		
		setContentPane(panel);
		setLayout(new FlowLayout()); //정렬
		
		//이벤트 리스너 객체 생성
		MyActionListener listener = new MyActionListener();
		btn1.addActionListener(listener); //리스너 등록.
		btn2.addActionListener(listener); //리스너 등록.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,200);
		setVisible(true);

		
		
	}
	
	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String b = e.getActionCommand();
			if(e.getSource() == btn1) {
				
				panel.setBackground(Color.YELLOW);
				
			}else if(e.getActionCommand() =="핑크색") {
				panel.setBackground(Color.PINK);
			}
			
		}
		
	}
	
}
