package admin;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenuScreen2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenuScreen2 window = new AdminMenuScreen2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminMenuScreen2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("KollyTalk");
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(34, 77, 96));
		panel.setBounds(0, 0, 484, 661);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel mainLogo = new JLabel(new ImageIcon("C:\\images\\kollytalk_logo.jpg"));
		mainLogo.setBounds(0, 10, 476, 165);
		panel.add(mainLogo);

		JButton noticeBtn = new JButton("");
		noticeBtn.setIcon(new ImageIcon("C:\\images\\notice_un.jpg"));
		noticeBtn.setPressedIcon(new ImageIcon("C:\\images\\notice_click.jpg"));
		noticeBtn.setBounds(124, 243, 240, 60);
		noticeBtn.setBorderPainted(false);
		noticeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 공지사항 등록 버튼 눌렀을 때의 동작
				AdminNoticeScreen notice = new AdminNoticeScreen();
				notice.setVisible(true); // 공지사항 등록화면 띄우기
			}
		});
		panel.add(noticeBtn);

		JButton searchUserBtn = new JButton("");
		searchUserBtn.setBackground(new Color(34, 77, 96));
		searchUserBtn.setIcon(new ImageIcon("C:\\images\\searchuser_un.jpg"));
		searchUserBtn.setPressedIcon(new ImageIcon("C:\\images\\searchuser_click.jpg"));
		searchUserBtn.setBounds(124, 313, 240, 60);
		searchUserBtn.setBorderPainted(false);
		searchUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 내 정보 변경 버튼 눌렀을 때의 동작
				SearchUserInfoScreen searchUserInfoScreen = new SearchUserInfoScreen();
				searchUserInfoScreen.setVisible(true);
				panel.add(searchUserBtn);

				JButton searchChatBtn = new JButton("");
				searchChatBtn.setBackground(new Color(34, 77, 96));
				searchChatBtn.setIcon(new ImageIcon("C:\\images\\searchchat_un.jpg"));
				searchChatBtn.setPressedIcon(new ImageIcon("C:\\images\\searchchat_click.jpg"));
				searchChatBtn.setBounds(124, 383, 240, 66);
				searchChatBtn.setBorderPainted(false);
				searchChatBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 채팅조회 버튼 눌렀을 때의 동작
						PrintChatLogScreen printChatLogScreen = new PrintChatLogScreen();
						printChatLogScreen.setVisible(true);
//	            	printChatLogScreen.getMessageFromDatabase(userId,chatdate);

					}
				});
				panel.add(searchChatBtn);

				JButton kickWordBtn = new JButton("");
				kickWordBtn.setIcon(new ImageIcon("C:\\images\\kickoutword_un.jpg"));
				kickWordBtn.setPressedIcon(new ImageIcon("C:\\images\\kickoutword_click.jpg"));
				kickWordBtn.setBackground(new Color(34, 77, 96));
				kickWordBtn.setBounds(124, 459, 240, 60);
				kickWordBtn.setBorderPainted(false);
				kickWordBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 강퇴용어 버튼 눌렀을 때의 동작
						CreateKickOutWordScreen createKickOutWordScreen = new CreateKickOutWordScreen();
						createKickOutWordScreen.createScreen(); // createScreen 메서드 호출
						createKickOutWordScreen.setVisible(true);
						createKickOutWordScreen.getKickOutWordList(); // 강퇴용어 목록 갱신

					}
				});
				panel.add(kickWordBtn);

				JButton deleteUserBtn = new JButton("");
				deleteUserBtn.setIcon(new ImageIcon("C:\\images\\deleteuser_un.jpg"));
				deleteUserBtn.setPressedIcon(new ImageIcon("C:\\images\\deleteuser_click.jpg"));
				deleteUserBtn.setBackground(new Color(34, 77, 96));
				deleteUserBtn.setBounds(124, 529, 240, 60);
				deleteUserBtn.setBorderPainted(false);
				deleteUserBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 회원삭제 버튼 눌렀을 때의 동작
						DeleteUserInfoScreen deleteUserInfoScreen = new DeleteUserInfoScreen();
						deleteUserInfoScreen.setVisible(true);
					}
				});
				panel.add(deleteUserBtn);

				JLabel lblNewLabel = new JLabel("<관리자 화면>");
				lblNewLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 26));
				lblNewLabel.setForeground(new Color(255, 255, 255));
				lblNewLabel.setBounds(163, 177, 185, 32);
				panel.add(lblNewLabel);

			}
		});
	}
}
