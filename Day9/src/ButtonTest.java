import java.awt.Frame;
import java.awt.Panel;
import java.awt.*;
public class ButtonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f = new Frame("button test");		
		Panel p = new Panel();	
		Button b1 = new Button();
		Button b2 = new Button("입력");
		Button b3 = new Button("입력3");
		Button b4 = new Button("입력4");
		
		b1.setLable("입력");
		
		p.add(b1); p.add(b2); p.add(b3); p.add(b4); 
		
		f.add(p);
		
		f.setLocation(300,300);
		f.setSize(300,100);
		f.setVisible(true);
	}

}
