package MAIN_UI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import DBA.DAO;

public class Select_Options extends JDialog {
	
	private ButtonGroup Options;
	private JRadioButton op1, op2;
	private Font font = new Font("맑은 고딕", Font.ROMAN_BASELINE + Font.PLAIN, 14);
	private JPanel jp;
	private JButton OK_bt, cancle_bt;
	private String id;
	
	public Select_Options(String  user_id) {
		
		id = user_id;
		
		setTitle("탈퇴옵션 선택");
		setSize(300, 150);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        jp = new JPanel();
        jp.setLayout(null);
        setContentPane(jp);        
        
        Select sel = new Select();
        
        op1 = new JRadioButton("작성글 유지 탈퇴");
        op1.setFont(font);
        op1.setToolTipText("작성했던 글을 남기고 계정 정보만 삭제됩니다.");
        op1.setSize(150, 30);
        op1.setLocation((getWidth()-op1.getWidth())/2, 10);
        op1.setFocusable(false);
        
        op2 = new JRadioButton("작성글 삭제 탈퇴");
        op2.setFont(font);
        op2.setToolTipText("작성했던 모든 글을 지우고 탈퇴합니다.");
        op2.setSize(op1.getWidth(), op1.getHeight());
        op2.setLocation(op1.getX(), op1.getHeight()+15);
        op2.setFocusable(false);
        
        Options = new ButtonGroup();
        
        op1.setSelected(true);
        Options.add(op1);
        Options.add(op2);
        
        OK_bt = new JButton("탈퇴"); 
        cancle_bt = new JButton("취소");
        
        OK_bt.setBounds(50, op2.getY()+op2.getHeight()+10, 80, 25);
        cancle_bt.setBounds(OK_bt.getX()+OK_bt.getWidth()+20, OK_bt.getY(), OK_bt.getWidth(), OK_bt.getHeight());
        
        OK_bt.setOpaque(true);
        OK_bt.setBackground(null);
        OK_bt.setFocusable(false);
        OK_bt.addActionListener(sel);
        
        cancle_bt.setOpaque(true);
        cancle_bt.setBackground(null);
        cancle_bt.setFocusable(false);
        cancle_bt.addActionListener(sel);

        jp.add(OK_bt);
        jp.add(cancle_bt);
        
        jp.add(op1);
        jp.add(op2);
		
		setModal(true);
		setVisible(true);
	}
	
	class Select implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cancle_bt) {
				dispose();
			}
			if(e.getSource() == OK_bt) {
				int op = op1.isSelected()? 1:2;
				delete_user(op);
				new yesno_popup("탈퇴 완료했습니다!");
				
				System.exit(0);
			}
			
		}		
	}
	
	
	private void delete_user(int op) {
		DAO dao = new DAO();
		dao.User_Delete(id, op);
		
	}	
	
	public class yesno_popup extends JDialog {

		public yesno_popup(String text) {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			setSize(300, 150);
			setTitle("감사합니다");

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

			JButton jb = new JButton("감사합니다");
			jb.setBorderPainted(false);
			jb.setFocusPainted(false);
			jb.setBackground(new Color(0x7743DB));
			jb.setFont(new Font("맑은 고딕", Font.BOLD | Font.PLAIN, 18));
			jb.setForeground(Color.white);

			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

			add(jb, BorderLayout.SOUTH);
			add(jl, BorderLayout.CENTER);
			
			setModal(true);
			setVisible(true);
		}

	}
	
}
