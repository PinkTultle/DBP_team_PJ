package MAIN_UI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User_Profile extends JFrame {
    private JLabel userIdLabel, nameLabel, emailLabel, phoneLabel, passwordLabel, postsLabel, gradeLabel;

    public User_Profile() {
        setTitle("사용자 정보");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 사용자정보 라벨
        userIdLabel = new JLabel("사용자 ID: ");
        nameLabel = new JLabel("이름: ");
        emailLabel = new JLabel("이메일: ");
        phoneLabel = new JLabel("전화번호: ");
        passwordLabel = new JLabel("비밀번호: ");
        postsLabel = new JLabel("총 작성 수: ");
        gradeLabel = new JLabel("등급: ");

        // 패널생성
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(0xF7EFE5));

        // GridBagConstraints 설정
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 패널에 라벨과 정보 넣기
        addLabelAndInfo(infoPanel, gbc, userIdLabel, "?");
        addLabelAndInfo(infoPanel, gbc, nameLabel, "?");
        addLabelAndInfo(infoPanel, gbc, emailLabel, "?");
        addLabelAndInfo(infoPanel, gbc, phoneLabel, "?");
        addLabelAndInfo(infoPanel, gbc, passwordLabel, "?");
        addLabelAndInfo(infoPanel, gbc, postsLabel, "?");
        addLabelAndInfo(infoPanel, gbc, gradeLabel, "?");

        // 프레임에 패널 넣기
        add(infoPanel, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(0xF7EFE5));
        ImageIcon Withdrawalicon = new ImageIcon("image/Withdrawal.png");
        ImageIcon Closeicon = new ImageIcon("image/Close.png");

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
        add(buttonPanel, BorderLayout.SOUTH);

        // 프레임을 표시
        setVisible(true);
    }

   
    
    private void addLabelAndInfo(JPanel panel, GridBagConstraints gbc, JLabel label, String info) {
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(info), gbc);
        gbc.gridx = 0;
        gbc.gridy += 2;
    }
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new User_Profile());
    }
}