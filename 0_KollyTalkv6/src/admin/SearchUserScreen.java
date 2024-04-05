package admin;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;

public class SearchUserScreen {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUserScreen window = new SearchUserScreen();
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
	public SearchUserScreen() {
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
 
		panel.setBounds(0, 0, 484, 661);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
 
		JLabel mainLogo = new JLabel("");
		mainLogo.setBounds(0, 40, 484, 165);
		mainLogo.setIcon(new ImageIcon("C:\\images\\kollytalk_logo.jpg"));
		panel.add(mainLogo);
		
		JLabel lblNewLabel = new JLabel("조회하려는 회원ID를 입력해주세요.");
		lblNewLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(20, 215, 349, 63);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(20, 271, 292, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(20, 322, 57, 15);
		panel.add(lblNewLabel_1);
	}

}
