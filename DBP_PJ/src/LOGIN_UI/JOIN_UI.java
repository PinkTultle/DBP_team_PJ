package LOGIN_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import LOGIN_UI.LOGIN.Login_pane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import DBA.DAO.*;
import LOGIN_UI.LOGIN.*;

public class JOIN_UI extends JDialog {
	
	private JPanel c = (JPanel) getContentPane(),
			Join_pane;
	
	private JLabel logo_l;
	private Text id_l, pw_l, email_l, name_l, call_l, golbang;
	private input_feild id_tf, pw_tf, email_tf, name_tf, call_tf;
	private Font font = new Font("맑은 고딕", Font.ROMAN_BASELINE + Font.PLAIN, 18);
	private JButton id_bt, close_bt, join_bt;
	private JFrame login_UI;
	private JComboBox<String> email_host;
	private String [] email_h = {"naver.com", "daum.net", "gmail.com", "hanmail.net", "nate.com", "yahoo.com"};
	
	private ImageIcon join_logo = new ImageIcon("./image/join_LOGO.png"),
			close_img = new ImageIcon("./image/close.png"),
			close_press_img = new ImageIcon("./image/close_press.png"),
			join_img = new ImageIcon("./image/join_2.png"),
			join_press_img = new ImageIcon("./image/join_2_press.png"),
			id_check = new ImageIcon("./image/id_check.png"),
			id_check_press = new ImageIcon("./image/id_check_press.png");
	
			
	private int width = 60;
	
	private boolean overid = false; // 중복확인 했는지 여부
	
	/*
	public static void main(String[] args) {
		new JOIN_UI();
	}*/
	
	public JOIN_UI() {
		
		//로그인창 객체 저장
		
		setTitle("회원가입");
		c.setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(600, 530);
		
		c.setBorder(new EmptyBorder(5, 5, 5, 5));
		c.setBackground(new Color(0xFFFBF5));
		
		//중앙 패널 설정
		Join_pane = new Join_pane();
		Join_pane.setBackground(new Color(0xF7EFE5));
		Join_pane.setLayout(null);
		
		//중앙 패널이 화면 중앙 오도록 설정
		c.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		c.add(Join_pane, BorderLayout.CENTER);
		
		logo_l = new JLabel(join_logo);
		logo_l.setBounds(100, 0, 350, 90);
				
		//여기서부터 입력창들 세팅
		id_l = new Text("ID ", font);
		id_l.setLocation(40, 100);
		id_tf = new input_feild("입력하세요", font);
		id_tf.setSize(220, 30);
		id_tf.setLocation(id_l.getX() + id_l.getWidth(), id_l.getY()+3);
		
		pw_l = new Text("PW ", font);
		pw_l.setLocation(id_l.getX(), id_l.getY()+width+5);
		pw_tf = new input_feild("입력하세요", font);
		pw_tf.setLocation(pw_l.getX() + pw_l.getWidth(), pw_l.getY()+3);
		
		name_l = new Text("이름", font);
		name_l.setLocation(id_l.getX(), pw_l.getY()+width);
		name_tf = new input_feild("입력하세요", font);
		name_tf.setLocation(name_l.getX() + name_l.getWidth(), name_l.getY()+3);
		
		call_l = new Text("연락처", font);
		call_l.setLocation(id_l.getX(), name_l.getY()+width);
		call_tf = new input_feild("입력하세요", font);
		call_tf.setLocation(call_l.getX() + call_l.getWidth(), call_l.getY()+3);
		
		email_l = new Text("Email", font);
		email_l.setLocation(id_l.getX(), call_l.getY()+width);
		email_tf = new input_feild("입력하세요", font);
		email_tf.setSize(150, 30);
		email_tf.setLocation(email_l.getX() + email_l.getWidth(), email_l.getY()+3);
		
		golbang = new Text("@", font);
		golbang.setLocation(email_tf.getX()+email_tf.getWidth()+5, email_tf.getY());
		golbang.setSize(20,30);
		
		email_host = new JComboBox<String>(email_h);
		email_host.setLocation(golbang.getX()+golbang.getWidth()+5, email_tf.getY());
		email_host.setSize(150, 30);
		//email_host.setBackground(new Color(0xfef9d7));
		
		//버튼 리스너
		BT_Listener lis = new BT_Listener();
		
		
		id_bt = new JButton();
		id_bt.setIcon(id_check);
		id_bt.setPressedIcon(id_check_press);
		id_bt.setBounds(id_tf.getX()+id_tf.getWidth()+5, id_tf.getY(), 70, 30);
		id_bt.setOpaque(false);
		id_bt.setBackground(null);
		id_bt.setBorder(null);
		id_bt.setFocusPainted(false);
		id_bt.setContentAreaFilled(false);
		id_bt.addActionListener(lis);
		
		
		close_bt = new JButton();
		close_bt.setIcon(close_img);
		close_bt.setPressedIcon(close_press_img);
		close_bt.setBounds(180, 400, 100, 30);
		close_bt.setOpaque(false);
		close_bt.setBorder(null);
		close_bt.setBackground(null);
		close_bt.setFocusPainted(false);
		close_bt.setContentAreaFilled(false);
		close_bt.addActionListener(lis);
		
		
		join_bt = new JButton();
		join_bt.setIcon(join_img);
		join_bt.setPressedIcon(join_press_img);
		join_bt.setBounds(close_bt.getX()+close_bt.getWidth()+20, close_bt.getY(), 100, 30);
		join_bt.setOpaque(false);
		join_bt.setBorder(null);
		join_bt.setBackground(null);
		join_bt.setFocusPainted(false);
		join_bt.setContentAreaFilled(false);
		join_bt.addActionListener(lis);
		
		Join_pane.add(logo_l);
		
		
		Join_pane.add(id_bt);
		Join_pane.add(close_bt);
		Join_pane.add(join_bt);
		
		Join_pane.add(id_l);
		Join_pane.add(pw_l);
		Join_pane.add(email_l);
		Join_pane.add(name_l);
		Join_pane.add(call_l);
		Join_pane.add(id_tf);
		Join_pane.add(pw_tf);
		Join_pane.add(name_tf);
		Join_pane.add(call_tf);
		Join_pane.add(email_tf);
		Join_pane.add(email_host);
		
		Join_pane.add(golbang);
		
		
		c.setFocusable(true);
		c.requestFocus();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	class Join_pane extends JPanel{
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			Image TF_back = new ImageIcon("./image/TextFeild.png").getImage();
			
			g.drawImage(TF_back, id_l.getX() + id_l.getWidth(), id_l.getY(), 220, 35, null);
			g.drawImage(TF_back, pw_l.getX() + pw_l.getWidth(), pw_l.getY(), 300, 35, null);
			g.drawImage(TF_back, name_l.getX() + name_l.getWidth(), name_l.getY(), 300, 35, null);
			g.drawImage(TF_back, call_l.getX() + call_l.getWidth(), call_l.getY(), 300, 35, null);
			g.drawImage(TF_back, email_l.getX() + email_l.getWidth(), email_l.getY(), 150, 35, null);

		}
	}
	
	
	class Text extends JLabel{
		public Text(String text,Font font) {
			
			setText(text);
			setFont(font);
			setSize( 100, 30);
			
			setHorizontalAlignment(JLabel.CENTER);
			setVerticalTextPosition(JLabel.CENTER);
			setHorizontalTextPosition(JLabel.CENTER);
		}
	}
	
	class input_feild extends JTextField{
		
		private String init_str;
		
		public input_feild(String text, Font font) {
			
			this.init_str = text;
			
			setText(init_str);
			setFont(font);
			setSize( 300, 30);
			
			setBackground(new Color(0xF7EFE5));
			setHorizontalAlignment(JTextField.CENTER);
			setOpaque(false);
			setBorder(null);
			addFocusListener(new focue_event());			
		}
		
		class focue_event implements FocusListener{

			@Override
			public void focusGained(FocusEvent e) {
				JTextField me = (JTextField) e.getSource();
				me.setText("");
			}
			

			@Override
			public void focusLost(FocusEvent e) {
				JTextField me = (JTextField) e.getSource();
				
				if(me.getText().equals(init_str) || me.getText().equals(""))		
					me.setText(init_str);
			}
			
			
		}
	}
	
	class BT_Listener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String id2 = new String(id_tf.getText());
			String pw2 = new String(pw_tf.getText());
			String email2 = new String(email_tf.getText());
			String name2 = new String(name_tf.getText());
			String call2 = new String(call_tf.getText());
			if(e.getSource() == id_bt) {	
				if(id2.equals("입력하세요")||id2.equals("")) {
					overid = false;
					new yesno_popup("ID를 입력하세요!");
				}
				else {
					DBA.DAO udao = new DBA.DAO();
					if(udao.overlap_check(id2)) {
						overid = true; // true 일때만 가입가능
						id_tf.setFocusable(false);
						id_tf.setEditable(false);
						new yesno_popup("사용가능합니다.");
						
					}
					else if(!udao.overlap_check(id2)){
						overid = false;
						new yesno_popup("중복된 ID가 있습니다.");
					}
				}
			}
			
			if(e.getSource() == join_bt){
				if(id2.equals("입력하세요")||id2.equals("")) {
					new yesno_popup("ID를 입력하세요!");
				}else {
					if(pw2.equals("입력하세요")||pw2.equals("")) {
						new yesno_popup("pw를 입력하세요!");
					}else {
						if(name2.equals("입력하세요")||name2.equals("")) {
							new yesno_popup("이름을 입력하세요!");
						}else {
							if(call2.equals("입력하세요")||call2.equals("")) {
								new yesno_popup("연락처를 입력하세요!");
							}else {
								if(email2.equals("입력하세요")||email2.equals("")) {
									new yesno_popup("이메일을 입력하세요!");
								}else {
									if(!overid) {
										new yesno_popup("ID 중복 체크를 진행해주세요.");
									}else {
										DBA.DAO udao2 = new DBA.DAO();
										email2 += '@'+email_host.getSelectedItem().toString();
										DBA.User_DTO udto = new DBA.User_DTO(id2, pw2, name2, email2, name2, call2, 0);
										if(udao2.Insert_user(udto)) {
											dispose();
											new LOGIN();
											new yesno_popup("가입이 완료되었습니다.");
										}
									} 
								}
							}
						}
					}
				}
				
			}
			
			if(e.getSource() == close_bt) {
				dispose();
				login_UI.setVisible(true);

			}
			
			
		}	
	}
	
	public class yesno_popup extends JDialog {

		public yesno_popup(String text) {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			setSize(300, 150);
			setTitle("확인");

			// 사이즈 조절 off
			setResizable(false);
			// 화면 중앙에 출력
			setLocationRelativeTo(null);

			JPanel jp = (JPanel) getContentPane();
			jp.setLayout(new BorderLayout(10, 10));
			jp.setBackground(new Color(0xF7EFE5));
			setContentPane(jp);

			JLabel jl = new JLabel(text);
			jl.setFont(new Font("맑은 고딕", Font.BOLD | Font.PLAIN, 25));

			jl.setHorizontalAlignment(JLabel.CENTER);

			JButton jb = new JButton("확인");
			jb.setBorderPainted(false);
			jb.setFocusPainted(false);
			jb.setBackground(new Color(0x7743DB));
			jb.setFont(new Font("맑은 고딕", Font.BOLD | Font.PLAIN, 22));
			jb.setForeground(Color.white);

			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

			add(jb, BorderLayout.SOUTH);
			add(jl, BorderLayout.CENTER);

			setVisible(true);
		}

	}

	
	
}
