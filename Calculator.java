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
 * 기본적인 사칙연산을 계산하는 계산기 GUI입니다.
 * 더하기, 빼기, 곱하기, 나누기 연산과 부호 변경, 지우기 등의 기능을 제공합니다.
 * 사용자는 버튼을 클릭하여 숫자와 연산자를 입력하고 결과를 확인할 수 있습니다.
 * 
 * @author Kim Hui Jin (khj1382443111@gmail.com)
 * @version 1.0
 * 
 * @created 2024-10-30
 * @lastModified 2024-11-01
 * @see <a href="https://code-review.tistory.com/entry/%ED%81%B4%EB%A1%A0%EC%BD%94%EB%94%A9-%EC%9E%90%EB%B0%94%EB%A1%9C-%EA%B3%84%EC%82%B0%EA%B8%B0-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0">
 *      자바로 계산기 구현하기</a>
 */

public class Calculator extends JFrame {
	JTextField label;
	String num = "";
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * Calculator 클래스의 기본 생성자.
	 * GUI 컴포넌트를 초기화하고 프레임을 설정.
	 */
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
	
	/**
	 * 계산 결과를 표시할 텍스트 필드를 초기화하고 설정.
	 */
	void label() {
		label = new JTextField() {
			/** 테두기 없애기 */
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
	
	/**
	 * 버튼 패널을 생성하고 버튼의 동작 구성.
	 */
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
			/** 양수이면 음수로, 음수면 양수로 부호 변경 */
			else if (operation.equals("+/−")) {
				if(label.getText().charAt(0)==('+')){
					label.setText("−" + label.getText().substring(1, label.getText().length()));
				}else if(label.getText().charAt(0)==('−')) {
					label.setText("+" + label.getText().substring(1, label.getText().length()));
				}else{
					label.setText("−" + label.getText());
				}
			}
			/** 지금 누른 버튼이 연산자일 때의 조건 */
			else if (operation.equals("+") || operation.equals("-") || operation.equals("×") || operation.equals("÷")) {
				/** 첫 값을 음수로 입력할 수 있음 */
				if (label.getText().equals("") && operation.equals("-")) {
					label.setText(label.getText() + e.getActionCommand());
				}
				/** 이전에 누른 버튼이 연산자가 아니고 && 위의 계산식이 비어있지 않을 때의 조건문 */
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
	 * 입력된 수식 문자열을 파싱하여 숫자와 연산자를 분리.
	 * 분리된 요소들은 value ArrayList에 저장.
	 * 
	 * @param inputText 입력된 수식 문자열
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
		value.add(0,"0");
		value.add(1,"+");
	}
	
	/**
	 * 입력된 수식을 계산하여 결과값을 반환.
	 * 
	 * @param inputText 계산할 수식 문자열
	 * @return 계산한 값
	 */
	double calculate(String inputText) {
		parsing(inputText);
		
		double prev = 0;
		double current = 0;
		String mode = "";
		
		for (int i = 0; i < value.size(); i++) {
			String s = value.get(i);
			
			/** 연산자 우선순위 적용. 곰셈, 나눗셈 먼저 계산 */
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
			/** 소수점 여섯 번째 자리에서 반올림 */
			prev = Math.round(prev * 100000) / 100000.0;
		}
		return prev;
	}
	
	public static void main(String[] args) {
		new Calculator();
	}

}
