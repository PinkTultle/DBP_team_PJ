package LOGIN_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import LOGIN_UI.LOGIN.Login_pane;

public class JOIN_UI extends JDialog {
	
	private JPanel c = (JPanel) getContentPane(),
			Join_pane;
	
	private JLabel logo_l;
	private Text id_l, pw_l, email_l, name_l, call_l;
	private input_feild id_tf, pw_tf, email_tf, name_tf, call_tf;
	private Font font = new Font("맑은 고딕", Font.ROMAN_BASELINE + Font.PLAIN, 18);
	private ImageIcon join_logo = new ImageIcon("./image/join_LOGO.png"),
			close_img = new ImageIcon(),
			join_img = new ImageIcon();
			
	private int width = 60;

	public static void main(String[] args) {
		new JOIN_UI();
	}
	
	
	
	public JOIN_UI() {
		
		setTitle("회원가입");
		c.setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(600, 500);
		
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
		id_tf.setSize(250, 30);
		id_tf.setLocation(id_l.getX() + id_l.getWidth(), id_l.getY()+3);
		
		pw_l = new Text("PW ", font);
		pw_l.setLocation(id_l.getX(), id_l.getY()+width);
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
		
		
		Join_pane.add(logo_l);
		
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

		
		
		
		
		
		
		
		c.setFocusable(true);
		c.requestFocus();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	class Join_pane extends JPanel{
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			Image TF_back = new ImageIcon("./image/TextFeild.png").getImage();
			
			g.drawImage(TF_back, id_l.getX() + id_l.getWidth(), id_l.getY(), 250, 35, null);
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
		
		public input_feild(String text, Font font) {
			
			setText(text);
			setFont(font);
			setSize( 300, 30);
			
			setBackground(new Color(0xF7EFE5));
			setHorizontalAlignment(JTextField.CENTER);
			setOpaque(false);
			setBorder(null);
			
		}
	}
	
	
}
