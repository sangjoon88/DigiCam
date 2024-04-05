package Login;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import MainPage.UserMainPage;

public class LoginMain extends JFrame {
	
	private UserDataSet users;    
	private UserJdbc userJdbc;
    private JTextField tfId;
    private JPasswordField tfPw;
    private JButton btnLogin;
    private JButton btnJoin;
    private JLabel lblImage;
    

    public LoginMain() {
    
    	users = new UserDataSet();
        userJdbc = new UserJdbc();
        users.addUsers(userJdbc.reload());
        
        lblImage = new JLabel(new ImageIcon("images/kollytalk.jpg"));
        lblImage.setHorizontalAlignment(JLabel.CENTER); // 레이블의 이미지를 가운데 정렬

        // 레이아웃 설정
        setLayout(new BorderLayout());
        add(lblImage, BorderLayout.NORTH); // 이미지 레이블을 상단에 추가

        // 로그인 폼 구성
        JPanel pnlCenter = new JPanel(new GridLayout(2, 2, 5, 5)); // 로그인 폼을 위한 패널
        pnlCenter.add(new JLabel("ID:"));
        tfId = new JTextField();
        pnlCenter.add(tfId);
        pnlCenter.add(new JLabel("Password:"));
        tfPw = new JPasswordField();
        pnlCenter.add(tfPw);

        // 하단의 버튼 추가
        JPanel pnlSouth = new JPanel();
        btnLogin = new JButton("Login");
        pnlSouth.add(btnLogin);
        btnJoin = new JButton("Join");
        pnlSouth.add(btnJoin);

        // 패널들을 프레임에 추가
        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
        
        // 이벤트 리스너 설정
        addListeners();

        // 프레임 설정
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300); // 적절한 크기 설정
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public UserDataSet getUsers() {
    	return users;
    }
    
    public String getTfId() {
		return tfId.getText();
	}

    public void addListeners() {

        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new JoinForm(LoginMain.this);
                tfId.setText("");
                tfPw.setText("");
            }
        });
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 아이디칸이 비었을 경우
                if (tfId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(LoginMain.this,
                            "아이디를 입력하세요.",
                            "로그인폼",
                            JOptionPane.WARNING_MESSAGE);

                    // 존재하는 아이디일 경우
                } else if (users.contains(new User(tfId.getText()))) {

                    // 비밀번호칸이 비었을 경우
                    if(String.valueOf(tfPw.getPassword()).isEmpty()) {
                        JOptionPane.showMessageDialog(
                                LoginMain.this,
                                "비밀번호를 입력하세요.",
                                "로그인폼",
                                JOptionPane.WARNING_MESSAGE);

                        // 비밀번호가 일치하지 않을 경우
                    } else if (!users.getUser(tfId.getText()).getPw().equals(String.valueOf(tfPw.getPassword()))) {
                        JOptionPane.showMessageDialog(
                                LoginMain.this,
                                "비밀번호가 일치하지 않습니다.");

                        // 다 완료될 경우
                    } else {
                        new UserMainPage(new User(tfId.getText()));
                    }
                    // 존재하지 않는 Id일 경우
                } else {
                    JOptionPane.showMessageDialog(
                            LoginMain.this,
                            "존재하지 않는 Id입니다."
                    );
                
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int choice = JOptionPane.showConfirmDialog(
                        LoginMain.this,
                        "로그인 프로그램을 종료합니다.",
                        "종료",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public void showFrame() {
        setTitle("Login");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginMain();
    }
}