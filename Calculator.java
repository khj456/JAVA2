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

/**
 * 사칙연산을 계산하는 계산기 프로그램입니다.
 * @author Kim Hui Jin (khj1382443111@gmail.com)
 * @since 1.0
 * 
 * @created 2024-10-30
 * @lastModified 2024-11-01
 * 
 * @see
 */

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
			String prevOperation = "";
			
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
			else if (operation.equals("+") || operation.equals("-") || operation.equals("×") || operation.equals("÷")) {
				if (label.getText().equals("") && operation.equals("-")) {
					label.setText(label.getText() + e.getActionCommand());
				}
				else if (!label.getText().equals("") && !prevOperation.equals("+") && !prevOperation.equals("−") && !prevOperation.equals("×") && !prevOperation.equals("÷")) {
					label.setText(label.getText() + e.getActionCommand());
				}
			}
			else {
				label.setText(label.getText() + e.getActionCommand());
			}
			prevOperation = e.getActionCommand();
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
	/**
	 * @param inputText
	 */
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
		value.remove("");
	}
	/**
	 * @param inputText
	 * @return 계산한 값
	 */
	double calculate(String inputText) {
		parsing(inputText);
		
		double prev = 0;
		double current = 0;
		String mode = "";
		
		for (int i = 0; i < value.size(); i++) {
			String s = value.get(i);
			
			if (s.equals("+")) {
				mode = "add";
			} else if (s.equals("−")) {
				mode = "sub";
			} else if (s.equals("×")) {
				mode = "mul";
			} else if (s.equals("÷")) {
				mode = "div";
			} else {
				if ((mode.equals("mul") || mode.equals("div")) && !s.equals("+") && !s.equals("−") && !s.equals("×") && !s.equals("÷")) {
					Double one = Double.parseDouble(value.get(i - 2));
					Double two = Double.parseDouble(value.get(i));
					Double result = 0.0;
					
					if (mode.equals("mul")) {
						result = one * two;
					} else if (mode.equals("div")) {
						result = one / two;
					}
					value.add(i + 1, Double.toString(result));
					
					for (int j = 0; j < 3; j++) {
						value.remove(i - 2);
					}
					i -= 2;
				}
			}
		}
		
		for (String s : value) {
			if (s.equals("+")) {
				mode = "add";
			} else if (s.equals("−")) {
				mode = "sub";
			}  
			else {
				current = Double.parseDouble(s);
				
				if (mode.equals("add")) {
					prev += current;
				}
				else if (mode.equals("sub")) {
					prev -= current;
				}
				else {
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
