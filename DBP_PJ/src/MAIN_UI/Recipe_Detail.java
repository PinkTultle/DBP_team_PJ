package MAIN_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Recipe_Detail extends JFrame {

    // 레시피 상세 정보 라벨
    private JLabel rtitleLabel = new JLabel("레시피 제목: 바비큐");
    private JLabel wnameLabel = new JLabel("작성자: F42Q6I7");
    private JLabel winfoLabel = new JLabel("작성내용 ");
    private JLabel wdateLabel = new JLabel("작성시간: 23/08/28");
    private JLabel wcontentLabel = new JLabel("레시피 설명 ");
    private JLabel looknumLabel = new JLabel("조회수: 1");
    private JLabel goodnumLabel = new JLabel("추천수: 0");
    private JLabel difficulty = new JLabel("난이도: 3");

    // 작성내용 창
    private JTextField infoText;
    private JScrollPane infoPane;
    // 레시피 설명 창
    private JTextField contentText;
	private JScrollPane contentPane;
	// 리뷰
	private JLabel reviewLabel = new JLabel("리뷰");
	private JList reviewList ;
	private DefaultListModel model; // 리뷰리스트에 보이는 데이터
	private JScrollPane rvscrolled;
	// 댓글
	private JLabel commentLabel = new JLabel("댓글");
	private JList commentList = new JList();
	private JScrollPane cmscrolled;
	// 나가기 버튼
	private JButton exitButton = new JButton("나가기");
	
	private JPanel rdPanel = new JPanel();
	
	//제목 폰트
	Font font = new Font("맑은 고딕", Font.PLAIN,20);
	//내용 폰트
	Font cofont = new Font("맑은 고딕", Font.PLAIN,15);
	
    public Recipe_Detail() {
        setTitle("레시피 상세 정보");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
		wnameLabel.setSize(200,40);
		wnameLabel.setLocation(600,20);
		wdateLabel.setSize(200,40);
		wdateLabel.setLocation(600,40);
		looknumLabel.setSize(200,40);
		looknumLabel.setLocation(600,60);
		goodnumLabel.setSize(200,40);
		goodnumLabel.setLocation(600,80);
		difficulty.setSize(200,40);
		difficulty.setLocation(600,100);
        this.add(wnameLabel);
        this.add(wdateLabel);
        this.add(looknumLabel);
        this.add(goodnumLabel);
        this.add(difficulty);
        
        
		reviewLabel.setSize(80,30);
		reviewLabel.setLocation(10,500);
		this.add(reviewLabel);
		
		// 작성내용 창
		infoText = new JTextField("레시피 36");
		infoText.setEnabled(false);
		infoText.setFont(cofont);
		infoPane = new JScrollPane(infoText);
		infoPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        infoPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);;
		infoPane.setSize(400,100);
		infoPane.setLocation(10,70);
		setVisible(true);
		rdPanel.add(infoPane);
		
		// 레시피 설명 창
		
		contentText = new JTextField("맛있는 바비큐 레시피 !");
		contentText.setEnabled(false);
		contentText.setFont(cofont);
		contentPane = new JScrollPane(contentText);
        contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.setSize(500, 250);
        contentPane.setLocation(10, 210);
        
		setVisible(true);
        
		rdPanel.add(contentPane);
				
		
		// 리뷰 라벨 
		reviewLabel.setSize(80,30);
		reviewLabel.setLocation(10,500);
		this.add(reviewLabel);
		
		// 리뷰 리스트 
		/* 하나씩 추가하고 싶을 때
		model = new DefaultListModel();
		model.addElement("abc");
		model.addElement("abcd");
		model.addElement("abcde");
		model.addElement("abcde"); */
		//reviewList = new JList(model);
		String[] reviewstr = {"abc","abcd","abcde","abcdef","abcdefg"};
		reviewList = new JList(reviewstr);
		reviewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		rvscrolled = new JScrollPane(reviewList);
		rvscrolled.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
			
		rvscrolled.setSize(550, 65);
		rvscrolled.setLocation(10, 510);
		
		rdPanel.add(rvscrolled);

		// 댓글 라벨
		commentLabel.setSize(80,30);
		commentLabel.setLocation(10,600);
		//commentButton.setEnabled(false);
		this.add(commentLabel);
		
		// 댓글 리스트
		String[] commentstr = {"너무 어려워요 ㅠㅠ","좋은 레시피 감사합니다","오늘은 이거닷","너무 어려워요 ㅠㅠ"};
		commentList = new JList(commentstr);
		commentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		cmscrolled = new JScrollPane(commentList);
		cmscrolled.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		
		cmscrolled.setSize(550, 65);
		cmscrolled.setLocation(10, 610);
		rdPanel.add(cmscrolled);
		
		// 나가기 버튼 이벤트
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		// 나가기 버튼
		exitButton.setSize(100, 40);
		exitButton.setLocation(600, 600);
		rdPanel.add(exitButton);
		this.add(rdPanel);
        
		
		// 프레임을 표시
        setVisible(true);
    }

   
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Recipe_Detail());
    }
}