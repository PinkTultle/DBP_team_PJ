package RECIPE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Recipe_Detail extends JFrame {

    // 레시피 상세 정보 라벨
    private JLabel rtitleLabel = new JLabel("레시피 제목: ");
    private JLabel wnameLabel = new JLabel("작성자: ");
    private JLabel winfoLabel = new JLabel("작성내용: ");
    private JLabel wdateLabel = new JLabel("작성시간: ");
    private JLabel wcontentLabel = new JLabel("레시피 설명: ");
    private JLabel looknumLabel = new JLabel("조회수: ");
    private JLabel goodnumLabel = new JLabel("추천수: ");
    private JLabel difficulty = new JLabel("난이도: ");

    // 작성내용 창
    private JScrollPane infoPane = new JScrollPane();
    // 레시피 설명 창
	private JScrollPane contentPane = new JScrollPane();
	// 리뷰
	private JLabel reviewLabel = new JLabel("리뷰");
	private JTextField reviewField = new JTextField();
	// 댓글
	private JLabel commentLabel = new JLabel("댓글");
	private JTextField commentField = new JTextField();
	// 나가기 버튼
	private JButton exitButton = new JButton("나가기");
	
	private JPanel rdPanel = new JPanel();
	
	//폰트
	Font font = new Font("맑은 고딕", Font.PLAIN,20);
	
    public Recipe_Detail() {
        setTitle("레시피 상세 정보");
        setSize(850, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        rtitleLabel.setFont(font);
        reviewLabel.setSize(80,30);
		reviewLabel.setLocation(0,0);
		this.add(rtitleLabel,BorderLayout.BEFORE_FIRST_LINE);
        
        //rdPanel.setBorder(voteBorder);
		rdPanel.setSize(296, 76);
		rdPanel.setLocation(315, 10);
		rdPanel.setBackground(new Color(0xF7EFE5));
		rdPanel.setLayout(null);
        //JPanel infoPanel = new JPanel(new GridBagLayout());
        //infoPanel.setBackground(new Color(0xF7EFE5));

		// 작성 내용 라벨
		winfoLabel.setSize(80,30);
		winfoLabel.setLocation(10,50);
		this.add(winfoLabel);
		
		// 레시피 설명 라벨
		wcontentLabel.setSize(80,30);
		wcontentLabel.setLocation(10,200);
		this.add(wcontentLabel);
		
		//레시피 정보
		wnameLabel.setSize(80,40);
		wnameLabel.setLocation(600,10);
		wdateLabel.setSize(80,40);
		wdateLabel.setLocation(600,30);
		looknumLabel.setSize(80,40);
		looknumLabel.setLocation(600,50);
		goodnumLabel.setSize(80,40);
		goodnumLabel.setLocation(600,70);
		difficulty.setSize(80,40);
		difficulty.setLocation(600,90);
        this.add(wnameLabel);
        this.add(wdateLabel);
        this.add(looknumLabel);
        this.add(goodnumLabel);
        this.add(difficulty);
        
        
		reviewLabel.setSize(80,30);
		reviewLabel.setLocation(10,500);
		this.add(reviewLabel);
		
		// 작성내용 창
		infoPane.setSize(400,100);
		infoPane.setLocation(10,70);
		setVisible(true);
		rdPanel.add(infoPane);
		
		// 레시피 설명 창
        contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.setSize(500, 250);
        contentPane.setLocation(10, 210);
        
		setVisible(true);
        
		rdPanel.add(contentPane);
				
		
		// 리뷰, 댓글 창
		reviewLabel.setSize(80,30);
		reviewLabel.setLocation(10,500);
		this.add(reviewLabel);
		reviewField.setSize(520, 60);
		reviewField.setLocation(10, 510);
		
		rdPanel.add(reviewField);

		commentLabel.setSize(80,30);
		commentLabel.setLocation(10,600);
		//commentButton.setEnabled(false);
		this.add(commentLabel);
		commentField.setSize(520, 60);
		commentField.setLocation(10, 610);
		rdPanel.add(commentField);
		
	
		// 나가기 버튼
		exitButton.setSize(100, 40);
		exitButton.setLocation(700, 600);
		rdPanel.add(exitButton);
		this.add(rdPanel);
        
		
		// 프레임을 표시
        setVisible(true);
    }

   
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Recipe_Detail());
    }
}
