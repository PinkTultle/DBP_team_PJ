import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
	private JPanel currentCenterPanel;
    public Main() {
        // 프레임 설정
        setTitle("MAIN");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 상단 패널 생성
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(800, 100));
        topPanel.setBackground(new Color(0xF7EFE5)); 

        // 중앙 패널 생성
        JLabel userIdLabel = new JLabel("사용자 아이디");
        userIdLabel.setForeground(Color.BLACK); 

        // 클릭 가능한 이미지 추가
        ImageIcon clickableImage = new ImageIcon("image/Bronze.png"); 
        JLabel imageLabel = new JLabel(clickableImage);
        imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	 SwingUtilities.invokeLater(() -> {
                     User_Profile userProfile = new User_Profile();
                     userProfile.setVisible(true);
                 });
             }
        });

        // 상단 패널에 컴포넌트 추가
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(userIdLabel);
        topPanel.add(imageLabel);

        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // 가로로 배치하도록 FlowLayout 설정
        leftButtonPanel.setBorder(new EmptyBorder(60, 200, 10, 10)); // 여백 조절
        leftButtonPanel.setBackground(new Color(0xF7EFE5));
        
        // 왼쪽 버튼 패널에 버튼 추가
        JButton leftButton1 = new JButton("레시피");
        
        JButton leftButton2 = new JButton("리뷰");
        
        JButton leftButton3 = new JButton("금주의 레시피");

        // 왼쪽 버튼 크기 조절
        Dimension leftButtonSize = new Dimension(120, 30);
        leftButton1.setPreferredSize(leftButtonSize);
        leftButton2.setPreferredSize(leftButtonSize);
        leftButton3.setPreferredSize(leftButtonSize);

        leftButtonPanel.add(leftButton1);
        leftButtonPanel.add(leftButton2);
        leftButtonPanel.add(leftButton3);

        // topPanel에 왼쪽 버튼 패널 추가
        topPanel.add(leftButtonPanel, BorderLayout.EAST);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE); // 배경색을 원하는 색으로 변경할 수 있습니다.
       
        // 테이블 모델 생성
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("레시피 번호");
        tableModel.addColumn("제목");
        tableModel.addColumn("레시피 내용");
        tableModel.addColumn("추천수");
       

        // 테이블 생성
        JTable table = new JTable(tableModel);

        for (int i = 0; i < 40; i++) {
            Object[] rowData = {i + 1, "제목 " + (i + 1), "레시피 내용 " + (i + 1), (int) (Math.random() * 100)};
            tableModel.addRow(rowData);
        }
        // 스크롤 패널에 테이블 추가
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setPreferredSize(new Dimension(500, 500));
        centerPanel.add(scrollPane, BorderLayout.WEST);
        scrollPane.setBackground(Color.WHITE);

        // 버튼을 담을 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 5, 10));
        buttonPanel.setBorder(new EmptyBorder(200, 10, 50, 50)); // 하단과 오른쪽의 여백 조절
        JButton button1 = new JButton("버튼 1");
        JButton button2 = new JButton("버튼 2");
        JButton button3 = new JButton("버튼 3");

        // 버튼 크기 조절
        Dimension buttonSize = new Dimension(150, 50);
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        centerPanel.add(buttonPanel, BorderLayout.EAST);
        buttonPanel.setBackground(Color.WHITE);
        
        // 레이아웃 설정
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        currentCenterPanel = centerPanel;
        leftButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 리뷰 버튼 클릭 시, 중앙 패널을 리뷰 패널로 변경
            	remove(currentCenterPanel);
                currentCenterPanel = centerPanel ;
                add(currentCenterPanel, BorderLayout.CENTER);

                // 패널을 다시 그리기
                revalidate();
                repaint();
            }
        });
        leftButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 리뷰 버튼 클릭 시, 중앙 패널을 리뷰 패널로 변경
                switchToReviewPanel();
            }
        });
        leftButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 리뷰 버튼 클릭 시, 중앙 패널을 리뷰 패널로 변경
                switchToBestRecipePanel();
            }
        });
    }
   
    // 리뷰 패널로 전환하는 메서드
    private void switchToReviewPanel() {
    	JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BorderLayout());
        reviewPanel.setBackground(Color.WHITE);

        // 테이블 모델 생성
        DefaultTableModel reviewTableModel = new DefaultTableModel();
        reviewTableModel.addColumn("리뷰 번호");
        reviewTableModel.addColumn("레시피 번호");
        reviewTableModel.addColumn("내용");
        reviewTableModel.addColumn("평점");

        // 테이블 생성
        JTable reviewTable = new JTable(reviewTableModel);

        for (int i = 0; i < 40; i++) {
            Object[] rowData = {i + 1, "레시피 번호 " + (i + 1), "리뷰 내용 " + (i + 1), (int) (Math.random() * 5) + 1};
            reviewTableModel.addRow(rowData);
        }

        // 스크롤 패널에 테이블 추가
        JScrollPane reviewScrollPane = new JScrollPane(reviewTable);
        reviewScrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        reviewScrollPane.setPreferredSize(new Dimension(500, 500));
        reviewPanel.add(reviewScrollPane, BorderLayout.WEST);
        reviewScrollPane.setBackground(Color.WHITE);

        // 버튼을 담을 패널 생성
        JPanel reviewButtonPanel = new JPanel();
        reviewButtonPanel.setLayout(new GridLayout(3, 1, 5, 10));
        reviewButtonPanel.setBorder(new EmptyBorder(200, 10, 50, 50)); // 하단과 오른쪽의 여백 조절
        JButton reviewButton1 = new JButton("버튼 1");
        JButton reviewButton2 = new JButton("버튼 2");
        JButton reviewButton3 = new JButton("버튼 3");

        // 버튼 크기 조절
        Dimension reviewButtonSize = new Dimension(150, 50);
        reviewButton1.setPreferredSize(reviewButtonSize);
        reviewButton2.setPreferredSize(reviewButtonSize);
        reviewButton3.setPreferredSize(reviewButtonSize);

        reviewButtonPanel.add(reviewButton1);
        reviewButtonPanel.add(reviewButton2);
        reviewButtonPanel.add(reviewButton3);
        reviewPanel.add(reviewButtonPanel, BorderLayout.EAST);
        reviewButtonPanel.setBackground(Color.WHITE);

        // 기존 중앙 패널을 제거하고 리뷰 패널로 변경
        remove(currentCenterPanel);
        currentCenterPanel = reviewPanel;
        add(currentCenterPanel, BorderLayout.CENTER);

        // 패널을 다시 그리기
        revalidate();
        repaint();
    }
    
    private void switchToBestRecipePanel() {
    	JPanel BestRecipePanel = new JPanel();
    	BestRecipePanel.setLayout(new BorderLayout());
    	BestRecipePanel.setBackground(Color.WHITE);

        // 테이블 모델 생성
        DefaultTableModel BestRecipeTableModel = new DefaultTableModel();
        BestRecipeTableModel.addColumn("카테고리");
        BestRecipeTableModel.addColumn("레시피 번호");
        BestRecipeTableModel.addColumn("레시피 제목");


        // 테이블 생성
        JTable BestRecipeTable = new JTable(BestRecipeTableModel);

        for (int i = 0; i < 1; i++) {
            Object[] rowData = {"카테고리 " + (i + 1), i + 1, "제목 " + (i + 1), (int) (Math.random() * 5) + 1};
            BestRecipeTableModel.addRow(rowData);
        }

        // 스크롤 패널에 테이블 추가
        JScrollPane BestRecipeScrollPane = new JScrollPane(BestRecipeTable);
        BestRecipeScrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        BestRecipeScrollPane.setPreferredSize(new Dimension(500, 500));
        BestRecipePanel.add(BestRecipeScrollPane, BorderLayout.WEST);
        BestRecipeScrollPane.setBackground(Color.WHITE);

        // 버튼을 담을 패널 생성
        JPanel BestRecipeButtonPanel = new JPanel();
        BestRecipeButtonPanel.setLayout(new GridLayout(3, 1, 5, 10));
        BestRecipeButtonPanel.setBorder(new EmptyBorder(200, 10, 50, 50)); // 하단과 오른쪽의 여백 조절
        JButton BestRecipeButton1 = new JButton("버튼 1");
        JButton BestRecipeButton2 = new JButton("버튼 2");
        JButton BestRecipeButton3 = new JButton("버튼 3");

        // 버튼 크기 조절
        Dimension BestRecipeButtonSize = new Dimension(150, 50);
        BestRecipeButton1.setPreferredSize(BestRecipeButtonSize);
        BestRecipeButton2.setPreferredSize(BestRecipeButtonSize);
        BestRecipeButton3.setPreferredSize(BestRecipeButtonSize);

        BestRecipeButtonPanel.add(BestRecipeButton1);
        BestRecipeButtonPanel.add(BestRecipeButton2);
        BestRecipeButtonPanel.add(BestRecipeButton3);
        BestRecipePanel.add(BestRecipeButtonPanel, BorderLayout.EAST);
        BestRecipeButtonPanel.setBackground(Color.WHITE);

        // 기존 중앙 패널을 제거하고 리뷰 패널로 변경
        remove(currentCenterPanel);
        currentCenterPanel = BestRecipePanel;
        add(currentCenterPanel, BorderLayout.CENTER);

        // 패널을 다시 그리기
        revalidate();
        repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	Main myGUI = new Main();
            myGUI.setVisible(true);
        });
    }
}