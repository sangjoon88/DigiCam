import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;

class Exit extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		
		System.exit(0);
	}

}

public class ActionEventEx extends Frame implements ActionListener{   //윈도우 리스너 추가

	Panel p;
	Button input, exit;
	TextArea ta;

	public ActionEventEx() {
		super("ActionEvent Test:");
		p = new Panel();
		input = new Button("input");
		exit = new Button("exit");
		ta = new TextArea();
		
		addWindowListener(new Exit());
		input.addActionListener(this);  //이벤트소스와 이벤트 핸들러 연결
		exit.addActionListener(this);  //이벤트소스와 이벤트 핸들러 연결

		p.add(input);
		p.add(exit);
		add(p, BorderLayout.NORTH);
		add(ta, BorderLayout.CENTER);

		setBounds(300, 300, 300, 200);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		String name;
		name = ae.getActionCommand();

		if (name.equals("input"))
			ta.append("button input.\n");
		else {
			ta.append("exit.\n");
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
			System.exit(0);

		}
	}

	public static void main(String[] args) {
		new ActionEventEx();
	}


}
