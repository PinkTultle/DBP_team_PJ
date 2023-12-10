package MAIN_UI;

import javax.swing.*;

import DBA.Comments_DTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import DBA.*;
import MAIN_UI.*;

public class Recipe_Detail extends JFrame {

    // 레시피 상세 정보 라벨
    private JLabel rtitleLabel = new JLabel("레시피 제목: ");
    private JLabel wnameLabel = new JLabel("작성자: ");
    private JLabel winfoLabel = new JLabel("작성내용 ");
    private JLabel wdateLabel = new JLabel("작성시간: ");
    private JLabel wcontentLabel = new JLabel("레시피 설명 ");
    private JLabel looknumLabel = new JLabel("조회수: ");
    private JLabel goodnumLabel = new JLabel("추천수: ");
    private JLabel difficulty = new JLabel("난이도: ");

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
	
	private JButton ComrmButton = new JButton("댓글 삭제");
	
	private JPanel rdPanel = new JPanel();
	
	static private int recipe_num = 1;
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String id = "RECIPE";
	String pw = "1234";
	private Connection con = null;
	
	//제목 폰트
	Font font = new Font("맑은 고딕", Font.PLAIN,20);
	//내용 폰트
	Font cofont = new Font("맑은 고딕", Font.PLAIN,15);
	
	private void DB_Connect() {
		try {
			con = DriverManager.getConnection(url, id, pw);	
			System.out.println("데이터베이스 연결 성공");
		}
		catch(SQLException e) {
			System.out.println("데이터베이스 연결 실패");
			System.exit(0);
		}
	}
	
    public Recipe_Detail(int recipe_num1) {
    	
    	recipe_num = recipe_num1;
    	
    	DBA.Recipe_DTO rdto = new DBA.Recipe_DTO();
    	DBA.DAO rdao = new DBA.DAO();
    	
    	rdto = rdao.Look_up_recipes(recipe_num1);
    	
    	rtitleLabel = new JLabel("레시피 제목: "+ rdto.getTITLE());
        wnameLabel = new JLabel("작성자: " + rdto.getID());
        winfoLabel = new JLabel("작성내용 ");
        wdateLabel = new JLabel("작성시간: " + rdto.getDATE());
        wcontentLabel = new JLabel("레시피 설명 ");
        looknumLabel = new JLabel("조회수: " + rdto.getVIEW_COUNT());
        goodnumLabel = new JLabel("추천수: " + rdto.getRECOMMEND_COUNT());
        difficulty = new JLabel("난이도: " + rdto.getLEVEL());

        
    	
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
		infoText = new JTextField(rdto.getDESCRIPTION());
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
		
		contentText = new JTextField(rdto.getCONTENT());
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
		Vector<Review_DTO> redto = new Vector<Review_DTO>();
		DBA.DAO redao = new DBA.DAO();
    	
    	redto = redao.Search_for_reviews(recipe_num1);
		
		String[] reviewstr = new String[10];
		for(int index = 0; index < redto.size(); index ++) {
			reviewstr[index] = redto.elementAt(index).getCONTENT();
		}
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
		
		Vector<Comments_DTO> cdto = new Vector<Comments_DTO>();
		DBA.DAO cdao = new DBA.DAO();
    	
    	cdto = cdao.Search_for_comments(recipe_num1);
		
		String[] commentstr = new String[10];
		for(int index = 0; index < cdto.size(); index ++) {
			commentstr[index] = (index+1)+ " " +cdto.elementAt(index).getCONTENT();
		}
		
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
		
		// 댓글 삭제 버튼 이벤트
		ComrmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Vector<Comments_DTO> cdto = new Vector<Comments_DTO>();
				DBA.DAO cdao = new DBA.DAO();
		    	cdto = cdao.Search_for_comments(recipe_num1);
				String cid = cdto.elementAt(commentList.getSelectedIndex()).getID();
				int cnum = cdto.elementAt(commentList.getSelectedIndex()).getCOMMENT_NUMBER();
				System.out.println(MAIN_UI.Main.cid);
				System.out.println(cnum);
				 
				if(cid.contains(MAIN_UI.Main.cid)){ // 불러오는 값이 공백값이 섞여있어서 == 가 아닌 contains로 해당 문자열이 존재하는지로 비교
					DBA.DAO crmdao = new DBA.DAO();
					crmdao.Delete_comment(recipe_num1, cnum);
					dispose();
					MAIN_UI.Recipe_Detail recipeDetail = new MAIN_UI.Recipe_Detail(recipe_num);
					recipeDetail.setVisible(true);
				}else {
					new fail_popup("작성하신 댓글이 아닙니다");
				}
			}
		});
		
		// 나가기 버튼
		ComrmButton.setSize(100, 40);
		ComrmButton.setLocation(450, 680);
		rdPanel.add(ComrmButton);
		this.add(rdPanel);
        
		
		// 프레임을 표시
        setVisible(true);
    }
    
    class fail_popup extends JDialog {

    	public fail_popup(String text) {

    		setSize(500, 150);
    		setTitle("실패");

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
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Recipe_Detail(recipe_num));
    }
}