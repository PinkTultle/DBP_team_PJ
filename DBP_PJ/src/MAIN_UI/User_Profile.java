package MAIN_UI;



import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import DBA.DAO;
import DBA.User_DTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User_Profile extends JDialog {
    private Text userId_l, name_l, email_l,
    			phone_l, password_l, posts_l, grade_l;
	private Font font = new Font("맑은 고딕", Font.ROMAN_BASELINE + Font.PLAIN, 14);
	private User_DTO dto;
	
    private JPanel infoPanel, buttonPanel;
    
    public User_Profile(JFrame master, String user_id) {
    	
    	
    	
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
        
        int gap = 10;
        
        userId_l.setLocation((infoPanel.getWidth()-userId_l.getWidth())/2, 30);
        name_l.setLocation(userId_l.getX(), userId_l.getY()+userId_l.getHeight()+gap);
        email_l.setLocation(userId_l.getX(), name_l.getY()+name_l.getHeight()+gap);
        phone_l.setLocation(userId_l.getX(), email_l.getY()+email_l.getHeight()+gap);
        password_l.setLocation(userId_l.getX(), phone_l.getY()+phone_l.getHeight()+gap);
        posts_l.setLocation(userId_l.getX(), password_l.getY()+password_l.getHeight()+gap);
        grade_l.setLocation(userId_l.getX(), posts_l.getY()+posts_l.getHeight()+gap);

        insert_user_data(user_id);
        
        infoPanel.add(userId_l);
        infoPanel.add(name_l);
        infoPanel.add(email_l);
        infoPanel.add(phone_l);
        infoPanel.add(password_l);
        infoPanel.add(posts_l);
        infoPanel.add(grade_l);


        Withdrawal_bt wd = new Withdrawal_bt();
        
        // 탈퇴 버튼
        JButton withdrawButton = new JButton(Withdrawalicon);
        withdrawButton.setBorder(null);
        withdrawButton.setBackground(null);
        withdrawButton.addActionListener(wd);

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
    	setModal(true);
    }

    
    class Text extends JLabel{
    	private String init;
    	
		public Text(String text,Font font) {
			
			this.init = text;
			setText(text);
			setFont(font);
			setSize(200, 30);
			//setBackground(Color.yellow);
	        //setOpaque(true);
			
	        setBorder(new EmptyBorder(0, 10, 0, 0));
			setHorizontalAlignment(JLabel.LEFT);
			setVerticalTextPosition(JLabel.CENTER);
			setHorizontalTextPosition(JLabel.LEFT);
		}
		
		private void setinit() {
			setText(init);
		}
		
	}
    
    private void insert_user_data(String user_id) {
    	
    	DAO dao = new DAO();
    	dto = dao.Query_user_profile(user_id);
    	
    	userId_l.setText(userId_l.getText()+dto.getID());
        name_l.setText(name_l.getText()+dto.getNAME());
        email_l.setText(email_l.getText()+dto.getEMAIL());
        phone_l.setText(phone_l.getText()+dto.getNUMBER());
        password_l .setText(password_l.getText()+dto.getPW());
        posts_l.setText(posts_l.getText()+dto.getWRITE_COUNT());
        grade_l.setText(grade_l.getText()+dto.getRATING());
    	
    }
    
    class Withdrawal_bt implements ActionListener{
    	
    	public void actionPerformed(ActionEvent e) {
    		new Select_Options(dto.getID());
    		
    		
    	}
    }
}