import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Calculator extends JFrame {
	JTextField label;
	String num = "";
	ArrayList<String> value = new ArrayList<String>();
	
	public Calculator() {
		this.setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		label(); btn();
		
		this.setTitle("계산기");
		this.setSize(330, 435);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void label() {
		label = new JTextField() {
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
		
		this.add(label);
	}
	
	void btn() {
		JPanel btnPanel = new JPanel(new GridLayout(5, 4, 3, 3));
		btnPanel.setBounds(5, 92, 304, 300);
		
		String[] btnName = {"CE", "C", "←", "÷", "7", "8", "9", "×", "4", "5",
				"6", "−", "1", "2", "3", "+", "+/−", "0", ".", "="};
		JButton[] btn = new JButton[btnName.length];
		
		ActionListener btnActionListener = e -> {
			String operation = e.getActionCommand();
			
			if (operation.equals("C")) {
				num = "";
				label.setText("");
			}
			else if(operation.equals("CE")) {
				label.setText("");
			}
			else if (operation.equals("←")) {
				label.setText(label.getText().substring(0, label.getText().length() - 1));
			}
			else if(operation.equals("=")) {
				String result = Double.toString(calculate(label.getText()));
				label.setText("" + result);
				num = "";
			}
			else if (operation.equals("+/−")) {
				if(label.getText().charAt(0)==('+')){
					label.setText("−" + label.getText().substring(1, label.getText().length()));
				}else if(label.getText().charAt(0)==('−')) {
					label.setText("+" + label.getText().substring(1, label.getText().length()));
				}else{
					label.setText("−" + label.getText());
				}
			}		
			else {
				label.setText(label.getText() + e.getActionCommand());
			}
		};
		
		for(int i = 0; i < btnName.length; i++) {
			btn[i] = new JButton(btnName[i]);
			btn[i].setFont(new Font("Dialog", Font.PLAIN, 17));
			if(btnName[i] == "=") {
				btn[i].setBackground(new Color(70, 130, 180));
				btn[i].setForeground(Color.WHITE);
			}
			else if(btnName[i] == "CE" || btnName[i] == "C" || btnName[i] == "←" || btnName[i] == "÷" || btnName[i] == "×" || btnName[i] == "−" || btnName[i] == "+"){
				btn[i].setBackground(new Color(230, 230, 230));
			}
			else {
				btn[i].setBackground(Color.WHITE);
			}
			btn[i].addActionListener(btnActionListener);
			btn[i].setBorderPainted(false);
			btnPanel.add(btn[i]);
		}
		
		this.add(btnPanel);
	}
	
	void parsing(String inputText) {
		value.clear();
		
		for (int i = 0; i < inputText.length(); i++) {
			char ch = inputText.charAt(i);
			
			if (ch == '−' || ch == '+' || ch == '×' || ch == '÷') {
				value.add(num);
				num = "";
				value.add(ch + "");
			} else {
				num = num + ch;
			}
		}
		value.add(num);
	}
	
	double calculate(String inputText) {
		parsing(inputText);
		
		double prev = 0;
		double current = 0;
		String mode = "";
		
		for (String s : value) {
			if (s.equals("+")) {
				mode = "add";
			} else if (s.equals("−")) {
				mode = "sub";
			}  
			else if (s.equals("×")) {
				mode = "mul";
			}  
			else if (s.equals("÷")) {
				mode = "div";
			}  else {
				current = Double.parseDouble(s);
				
				if (mode.equals("add")) {
					prev += current;
				} else if (mode.equals("sub")) {
					prev -= current;
				} 
				else if (mode.equals("mul")) {
					prev *= current;
				} 
				else if (mode.equals("div")) {
					prev /= current;
				} else {
					prev = current;
				}
			}
			prev = Math.round(prev * 100000) / 100000.0;
		}
		
		return prev;
	}


	public static void main(String[] args) {
		new Calculator();
	}

}
