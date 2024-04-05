package MainPage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Chat.ClientFrame;
import Login.User;


public class UserMainPage extends JFrame {
	
	private JTextField ipField   = new JTextField(13);
	private JTextField portField = new JTextField(5);
	private JPanel top = new JPanel();	
	JButton btnChat = new JButton("채팅시작");
	JButton btnChangeInform = new JButton("정보변경");
    private JPanel buttonPanel;
	
	public UserMainPage(User user) {
		this.setTitle("메인화면");
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		top.setLayout(new FlowLayout());
		top.add(new JLabel("Ip"));
		top.add(ipField);
		top.add(new JLabel("Port"));
		top.add(portField);
	
		this.setLayout(new GridBagLayout());
		buttonPanel = new JPanel();		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
	
        buttonPanel.add(btnChat);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnChangeInform);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill= GridBagConstraints.HORIZONTAL; // 중앙 정렬
        add(top, gbc);
        
        gbc.gridy= 1;
        add(buttonPanel, gbc);
        
        setVisible(true);
        
        btnChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 채팅 시작 버튼 클릭 시 액션
    			String uip = ipField.getText();  //IP를 얻어옴
    			String port = portField.getText();  // PORT를 얻어옴            	
                JOptionPane.showMessageDialog(UserMainPage.this, "채팅을 시작합니다");
    			new ClientFrame(user.getId(),uip,port);
                dispose();

            }
        });

        btnChangeInform.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 정보 변경 버튼 클릭 시 액션
                JOptionPane.showMessageDialog(UserMainPage.this, "정보 변경 화면으로!");
            }
        });

	}
	
}
