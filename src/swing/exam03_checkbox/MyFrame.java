package swing.exam03_checkbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	JLabel label1, label2;
	JCheckBox onion, cheese, tomato;
	JButton buttonOK;
	
	JPanel topPanel, MidPanel, ButtomPanel;
	
	
	MyFrame(){
		
		setTitle("체크박스 테스트");
		setSize(270,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		topPanel = new JPanel();
		label1 = new JLabel("행버거에 무엇을 추가하시겠습니까?");
		topPanel.add(label1);
		add(topPanel,BorderLayout.NORTH);
		
		MidPanel = new JPanel();
		onion = new JCheckBox("양파");
		MidPanel.add(onion);
		cheese = new JCheckBox("치즈");
		MidPanel.add(cheese);
		tomato = new JCheckBox("토마토");
		MidPanel.add(tomato);
		
		label2 = new JLabel("선택한 옵션은 다음과 같습니다.");
		MidPanel.add(label2);
		add(MidPanel,BorderLayout.CENTER);
		
		ButtomPanel = new JPanel();
		buttonOK = new JButton("OK");
		ButtomPanel.add(buttonOK);
		add(ButtomPanel,BorderLayout.SOUTH);
		setVisible(true);
		
		MyActionListener listener = new MyActionListener();
		buttonOK.addActionListener(listener);
		
	}
	
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String evt = e.getActionCommand();
			List<String> list = new ArrayList();
			
			if(evt =="OK") {
				if(onion.isSelected()) 	list.add(onion.getText());
				if(cheese.isSelected()) list.add(cheese.getText());
				if(tomato.isSelected())	list.add(tomato.getText());
								
				if(list.size() == 0) {
					label2.setText("선택된 옵션이 없습니다." );	
					label2.setForeground(Color.RED);
				}else {
					label2.setText(list.toString() +"가 선택 되었습니다.");
					label2.setForeground(Color.BLUE);
					
				}
			}
			
		}
		
	}
}
