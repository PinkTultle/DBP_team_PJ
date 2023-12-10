package MAIN_UI;



import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User_Profile extends JDialog {
    private Text userId_l, name_l, email_l,
    			phone_l, password_l, posts_l, grade_l;
	private Font font = new Font("맑은 고딕", Font.ROMAN_BASELINE + Font.PLAIN, 14);
	
    private JPanel infoPanel, buttonPanel;
    private GridBagConstraints gbc;
    
    public User_Profile(JFrame master) {
    	super(master, "사용자 정보", true);
    	
        setTitle("사용자 정보");
        setSize(300, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(0, 0, getWidth(), getHeight());
        infoPanel.setBackground(new Color(0xF7EFE5));

        // 버튼 패널
        buttonPanel = new JPanel();
        buttonPanel.setSize(210, 50);
        buttonPanel.setLocation((infoPanel.getWidth()-buttonPanel.getWidth())/2,
        			infoPanel.getHeight()-buttonPanel.getHeight()-50);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(0xF7EFE5));
        ImageIcon Withdrawalicon = new ImageIcon("image/Withdrawal.png");
        ImageIcon Closeicon = new ImageIcon("image/Close.png");
        
        //사용자정보 출력 라벨
        userId_l = new Text("사용자 ID: ", font);
        name_l = new Text("이름: ", font);
        email_l = new Text("이메일: ", font);
        phone_l = new Text("전화번호: ", font);
        password_l = new Text("비밀번호: ", font);
        posts_l = new Text("총 작성 수: ", font);
        grade_l = new Text("등급: ", font);
        
        userId_l.setLocation((infoPanel.getWidth()-userId_l.getWidth())/2,
        		30);
        


        infoPanel.add(userId_l);


        // 탈퇴 버튼
        JButton withdrawButton = new JButton(Withdrawalicon);
        withdrawButton.setBorder(null);
        withdrawButton.setBackground(null);
        withdrawButton.addActionListener(e -> {
            // 탈퇴 기능 추가
        });

        // 닫기 버튼
        JButton closeButton = new JButton(Closeicon);
        closeButton.setBorder(null);
        closeButton.setBackground(null);
        closeButton.addActionListener(e -> {
        	dispose();
        });

        // 버튼을 패널에 추가
        buttonPanel.add(withdrawButton);
        buttonPanel.add(closeButton);

        // 버튼 패널을 프레임에 추가
        add(infoPanel);
        infoPanel.add(buttonPanel);

        // 프레임을 표시
        setVisible(true);
    }

    
    class Text extends JLabel{
		public Text(String text,Font font) {
			
			setText(text);
			setFont(font);
			setSize(200, 30);
			setBackground(Color.yellow);
	        setOpaque(true);
			
	        setBorder(new EmptyBorder(0, 10, 0, 0));
			setHorizontalAlignment(JLabel.LEFT);
			setVerticalTextPosition(JLabel.CENTER);
			setHorizontalTextPosition(JLabel.LEFT);
		}
	}
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new User_Profile(null));
    }
}
