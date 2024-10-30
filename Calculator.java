import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calculator extends JFrame {
	JButton[] btn;
	String[] str = {"CE", "C", "←", "÷", "7", "8", "9", "×", "4", "5",
					"6", "−", "1", "2", "3", "+", "+/−", "0", ".", "="};

	public Calculator() {
		this.setTitle("계산기");
		this.setSize(300, 400);
		
		TextField text = new TextField("0");
		this.add(text, BorderLayout.NORTH);
		text.setEditable(false);
		
		JPanel panel = new JPanel(new GridLayout(5, 5, 3, 3));
		this.add(panel, BorderLayout.CENTER);
		
		btn = new JButton[20];
		for(int i = 0; i < btn.length; i++) {
			btn[i] = new JButton("" + str[i]);
			panel.add(btn[i]);
		}
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Calculator();
	}

}
