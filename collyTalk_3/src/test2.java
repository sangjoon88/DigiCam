import java.awt.*;
import javax.swing.*;

public class test2 extends JFrame {

    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, adminLoginButton, exitButton;

    class ImagePanel extends JPanel {
        private Image img;

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }

    public test2() {
        setTitle("로그인");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel(new ImageIcon("images/background.png").getImage());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Add components
        idField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("로그인");
        registerButton = new JButton("회원가입");
        adminLoginButton = new JButton("관리자 로그인");
        exitButton = new JButton("나가기");

        panel.add(new JLabel("아이디:"), gbc);
        panel.add(idField, gbc);
        panel.add(new JLabel("비밀번호:"), gbc);
        panel.add(passwordField, gbc);
        panel.add(loginButton, gbc);
        panel.add(registerButton, gbc);
        panel.add(adminLoginButton, gbc);
        panel.add(exitButton, gbc);

        // Adjust constraints for the panel
        gbc.weighty = 1;
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new test2();
    }
}