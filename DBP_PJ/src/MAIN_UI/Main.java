package MAIN_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.TableRowSorter;

import DBA.DAO;
import DBA.Recipe_DTO;
import DBA.User_DTO;

public class Main extends JFrame {
	private Main mainframe;
	private JPanel currentCenterPanel;
	private JTextField searchTextField;
	private TableRowSorter<DefaultTableModel> sorter;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private String User_id;
	private JPanel centerPanel;
	

    public Main(String id) {
        // 프레임 설정
        setTitle("MAIN");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        User_id = id;

        mainframe = this;
        
        // 상단 패널 생성
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(800, 100));
        topPanel.setBackground(new Color(0xF7EFE5)); 

        // 중앙 패널 생성
        JLabel userIdLabel = new JLabel(User_id);
        userIdLabel.setForeground(Color.BLACK); 

        // 클릭 가능한 이미지 추가
        ImageIcon clickableImage = new ImageIcon("image/Bronze.png"); 
        JLabel imageLabel = new JLabel(clickableImage);
        imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	 SwingUtilities.invokeLater(() -> {
                     User_Profile userProfile = new User_Profile(mainframe, User_id);
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
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀을 편집 불가능하도록 설정
            }
        };
        tableModel.addColumn("NO.");
        tableModel.addColumn("카테고리");
        tableModel.addColumn("제목");
        tableModel.addColumn("레시피 내용");
        tableModel.addColumn("추천수");
        tableModel.addColumn("난이도");
        
        DefaultTableCellRenderer ren = new DefaultTableCellRenderer();
        ren.setHorizontalAlignment(SwingConstants.CENTER);

        
        // 테이블 생성
        table = new JTable(tableModel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블클릭 이벤트
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Recipe_Detail.java에 정보 전달
                        SwingUtilities.invokeLater(() -> {
                            Recipe_Detail recipeDetail = new Recipe_Detail();
                            recipeDetail.setVisible(true);
                        });
                    }
                }
            }
        });
        
        //각 컬럼 길이 변경
        table.getColumn("NO.").setPreferredWidth(8);
        table.getColumn("카테고리").setPreferredWidth(10);
        table.getColumn("제목").setPreferredWidth(60);
        table.getColumn("레시피 내용").setPreferredWidth(100);
        table.getColumn("추천수").setPreferredWidth(1);
        table.getColumn("난이도").setPreferredWidth(1);
        
        TableColumnModel tm = table.getColumnModel();
        
    	tm.getColumn(0).setCellRenderer(ren);  
    	tm.getColumn(1).setCellRenderer(ren);  
    	tm.getColumn(4).setCellRenderer(ren);  
    	tm.getColumn(5).setCellRenderer(ren);  

     
    	//TODO 레시피 테이블 들어갈 부분
    	DAO dao = new DAO();
    	Vector<Recipe_DTO> list = dao.Search_by_recipe(); 
    	
    	for(Recipe_DTO item : list) {
    		Object[] rowData = {Integer.toString(item.getRECIPE_NUMBER()), 
    							item.getCATEGORY(),
    							item.getTITLE(),
    							item.getDESCRIPTION(),
    							Integer.toString(item.getRECOMMEND_COUNT()),
    							Integer.toString(item.getLEVEL())};
    		
    		tableModel.addRow(rowData);
    	}
    	
            
            
        // 스크롤 패널에 테이블 추가
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setPreferredSize(new Dimension(500, 500));
        centerPanel.add(scrollPane, BorderLayout.WEST);
        scrollPane.setBackground(Color.WHITE);
        
        JPanel searchPanel = new JPanel();
        searchTextField = new JTextField(6);
        JButton searchButton = new JButton("검색");
        
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);

        // 상단 패널에 검색 패널 추가
        centerPanel.add(searchPanel);
        // 버튼을 담을 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 5, 10));
        buttonPanel.setBorder(new EmptyBorder(200, 10, 50, 50)); // 하단과 오른쪽의 여백 조절
        JButton button1 = new JButton("등록");
        JButton button2 = new JButton("삭제");
        JButton button3 = new JButton("레시피 추천");
        JButton button4 = new JButton("추천 레시피");

        // 버튼 크기 조절
        Dimension buttonSize = new Dimension(150, 50);
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        centerPanel.add(buttonPanel, BorderLayout.EAST);
        buttonPanel.setBackground(Color.WHITE);
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // 선택된 행 삭제
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "삭제할 행을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = (int)table.getSelectedRow();
                if (selectedRow != -1) {
                    // 선택된 행의 추천수 증가
                    String currentRecommendation = (String) tableModel.getValueAt(selectedRow, 4);
                    int num = Integer.parseInt(currentRecommendation);
                    num++; 
                    tableModel.setValueAt(Integer.toString(num), selectedRow, 4);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "추천할 행을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        button4.addActionListener(new Recommended_recipe());
        
        
        
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
        DefaultTableModel reviewTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀을 편집 불가능하도록 설정
            }
        };
        reviewTableModel.addColumn("리뷰 번호");
        reviewTableModel.addColumn("레시피 번호");
        reviewTableModel.addColumn("내용");
        reviewTableModel.addColumn("평점");

        // 테이블 생성
        JTable reviewTable = new JTable(reviewTableModel);
        reviewTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블클릭 이벤트
                    int selectedRow = reviewTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Recipe_Detail.java에 정보 전달
                        SwingUtilities.invokeLater(() -> {
                            Recipe_Detail recipeDetail = new Recipe_Detail();
                            recipeDetail.setVisible(true);
                        });
                    }
                }
            }
        });
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
        
        JPanel searchPanel = new JPanel();
        searchTextField = new JTextField(6);
        JButton searchButton = new JButton("검색");
        
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        
        reviewPanel.add(searchPanel);
        // 버튼을 담을 패널 생성
        JPanel reviewButtonPanel = new JPanel();
        reviewButtonPanel.setLayout(new GridLayout(3, 1, 5, 10));
        reviewButtonPanel.setBorder(new EmptyBorder(200, 10, 50, 50)); // 하단과 오른쪽의 여백 조절
        JButton reviewButton1 = new JButton("등록");
        JButton reviewButton2 = new JButton("삭제");

        // 버튼 크기 조절
        Dimension reviewButtonSize = new Dimension(150, 50);
        reviewButton1.setPreferredSize(reviewButtonSize);
        reviewButton2.setPreferredSize(reviewButtonSize);

        reviewButtonPanel.add(reviewButton1);
        reviewButtonPanel.add(reviewButton2);
        reviewPanel.add(reviewButtonPanel, BorderLayout.EAST);
        reviewButtonPanel.setBackground(Color.WHITE);

        reviewButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = reviewTable.getSelectedRow();
                if (selectedRow != -1) {
                    // 선택된 행 삭제
                	reviewTableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "삭제할 행을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
       
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
        DefaultTableModel BestRecipeTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀을 편집 불가능하도록 설정
            }
        };
        BestRecipeTableModel.addColumn("카테고리");
        BestRecipeTableModel.addColumn("레시피 번호");
        BestRecipeTableModel.addColumn("레시피 제목");
        BestRecipeTableModel.addColumn("추천수");

        // 테이블 생성
        JTable BestRecipeTable = new JTable(BestRecipeTableModel);
        BestRecipeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블클릭 이벤트
                    int selectedRow = BestRecipeTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Recipe_Detail.java에 정보 전달
                        SwingUtilities.invokeLater(() -> {
                            Recipe_Detail recipeDetail = new Recipe_Detail();
                            recipeDetail.setVisible(true);
                        });
                    }
                }
            }
        });
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
        JButton BestRecipeButton1 = new JButton("추천");

        // 버튼 크기 조절
        Dimension BestRecipeButtonSize = new Dimension(150, 50);
        BestRecipeButton1.setPreferredSize(BestRecipeButtonSize);

        BestRecipeButtonPanel.add(BestRecipeButton1);
        BestRecipePanel.add(BestRecipeButtonPanel, BorderLayout.EAST);
        BestRecipeButtonPanel.setBackground(Color.WHITE);
        BestRecipeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = BestRecipeTable.getSelectedRow();
                if (selectedRow != -1) {
                    // 선택된 행의 추천수 증가
                    int currentRecommendation = (int) BestRecipeTableModel.getValueAt(selectedRow, 3);
                    BestRecipeTableModel.setValueAt(currentRecommendation + 1, selectedRow, 3);
                } else {
                    JOptionPane.showMessageDialog(null, "추천할 행을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
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
        	Main myGUI = new Main(null);
            myGUI.setVisible(true);
        });
    }
    
    class Recommended_recipe implements ActionListener{
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		
    		DAO dao = new DAO();
    		
    		User_DTO user = dao.Query_user_profile(User_id);
    		    		
    	     // 테이블 모델 생성
            tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // 모든 셀을 편집 불가능하도록 설정
                }
            };
            
            tableModel.addColumn("NO.");
            tableModel.addColumn("카테고리");
            tableModel.addColumn("제목");
            tableModel.addColumn("레시피 내용");
            tableModel.addColumn("추천수");
            tableModel.addColumn("난이도");
            
            DefaultTableCellRenderer ren = new DefaultTableCellRenderer();
            ren.setHorizontalAlignment(SwingConstants.CENTER);

            
            // 테이블 생성
            table = new JTable(tableModel);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) { // 더블클릭 이벤트
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            // Recipe_Detail.java에 정보 전달
                            SwingUtilities.invokeLater(() -> {
                                Recipe_Detail recipeDetail = new Recipe_Detail();
                                recipeDetail.setVisible(true);
                            });
                        }
                    }
                }
            });
            
            //각 컬럼 길이 변경
            table.getColumn("NO.").setPreferredWidth(8);
            table.getColumn("카테고리").setPreferredWidth(10);
            table.getColumn("제목").setPreferredWidth(60);
            table.getColumn("레시피 내용").setPreferredWidth(100);
            table.getColumn("추천수").setPreferredWidth(1);
            table.getColumn("난이도").setPreferredWidth(1);
            
            TableColumnModel tm = table.getColumnModel();
            
        	tm.getColumn(0).setCellRenderer(ren);  
        	tm.getColumn(1).setCellRenderer(ren);  
        	tm.getColumn(4).setCellRenderer(ren);  
        	tm.getColumn(5).setCellRenderer(ren);  

         
    		Vector<Recipe_DTO> list = dao.Recommend_recipe(user.getRATING());
        	
        	for(Recipe_DTO item : list) {
        		Object[] rowData = {Integer.toString(item.getRECIPE_NUMBER()), 
        							item.getCATEGORY(),
        							item.getTITLE(),
        							item.getDESCRIPTION(),
        							Integer.toString(item.getRECOMMEND_COUNT()),
        							Integer.toString(item.getLEVEL())};
        		
        		tableModel.addRow(rowData);
        	}
        	
                
                
            // 스크롤 패널에 테이블 추가
            scrollPane = new JScrollPane(table);
            
            revalidate();
            repaint();
    		
    	}
    }

    
    
}




