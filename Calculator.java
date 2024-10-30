import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Calculator extends JFrame {
	JButton[] btn;
	
	public Calculator() {
		this.setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		JTextField label = new JTextField("0") {
			@Override
			public void setBorder(Border border) {
				// 테두리 없애기
			}
		};
		
		label.setBounds(5, 5, 302, 83);
		label.setHorizontalAlignment(JTextField.RIGHT);
		label.setFont(new Font("Dialog", Font.BOLD, 40));
		label.setBackground(Color.WHITE);
		label.setEditable(false);
		
		JPanel btnPanel = new JPanel(new GridLayout(5, 4, 3, 3));
		btnPanel.setBounds(5, 92, 304, 300);
		
		String[] btnName = {"CE", "C", "←", "÷", "7", "8", "9", "×", "4", "5",
				"6", "−", "1", "2", "3", "+", "+/−", "0", ".", "="};
		JButton[] btn = new JButton[btnName.length];
		for(int i = 0; i < btnName.length; i++) {
			btn[i] = new JButton(btnName[i]);
			btn[i].setFont(new Font("Dialog", Font.PLAIN, 17));
			if(btnName[i] == "=") {
				btn[i].setBackground(Color.BLUE);
				btn[i].setForeground(Color.WHITE);
			}
			else {
				btn[i].setBackground(Color.WHITE);
			}
			btn[i].setBorderPainted(false);
			btnPanel.add(btn[i]);
		}
		
		this.add(label);
		this.add(btnPanel);
		
		this.setTitle("계산기");
		this.setSize(330, 435);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Calculator();
	}

}
