import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JFrame {
	JButton[] btn;
	String[] str = {"CE", "C", "←", "÷", "7", "8", "9", "×", "4", "5",
					"6", "−", "1", "2", "3", "+", "+/−", "0", ".", "="};
	JLabel label = new JLabel("0");
	
	public Calculator() {
		GUI();
		setEvent();
		
		this.setTitle("계산기");
		this.setSize(300, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void GUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.getContentPane().setBackground(Color.ORANGE);
		
		JPanel topPanel = new JPanel(new BorderLayout(5, 5));
		topPanel.add("North", new Label());
		topPanel.add("West", new Label());
		topPanel.add("East", new Label());
		topPanel.add("South", new Label());
		
		label.setBackground(Color.WHITE);
		topPanel.add("Center", label);
		this.add(topPanel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(5, 5, 3, 3));
		this.add(panel);
		
		btn = new JButton[20];
		for(int i = 0; i < btn.length; i++) {
			btn[i] = new JButton("" + str[i]);
			panel.add(btn[i]);
		}
	}

	
	public void setEvent() {
		
	}
	

	public static void main(String[] args) {
		new Calculator();
	}

}
