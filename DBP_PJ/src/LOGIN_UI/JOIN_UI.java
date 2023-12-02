package LOGIN_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LOGIN_UI.LOGIN.Login_pane;

public class JOIN_UI extends JDialog {
	private JPanel c = (JPanel) getContentPane(),
			Join_pane;
	
	
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
		
		c.setFocusable(true);
		c.requestFocus();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	class Join_pane extends JPanel{
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			/*g.setColor(new Color(0x7743DB));
			g.drawLine(ID.getX(), ID.getY()+ID.getHeight(), ID.getX()+ID.getWidth(), ID.getY()+ID.getHeight());
			
			g.drawLine(PW.getX(), PW.getY()+PW.getHeight(), PW.getX()+PW.getWidth(), PW.getY()+PW.getHeight());
		*/
		}
	}
}
