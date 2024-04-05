import java.awt.*;
public class CheckBoxEx {
	public static void main(String[] args) {
		

		
		Frame f= new Frame("asfaf");
		Panel p = new Panel();
		
		CheckboxGroup gr	= new CheckboxGroup(); 		//체크박스 그룹이 하나만 트루가 될 수 있게 해준다
		Checkbox ck1 = new Checkbox("asf",gr,false);	//여기에 gr이 더 붙네
		Checkbox ck2 = new Checkbox("asf",gr,false); // 
		Checkbox ck3 = new Checkbox("asf",gr,true);
		
		p.add(ck1);
		p.add(ck2);
		p.add(ck3);
		
		f.add(p);
		
		f.setSize(300,100);
		f.setVisible(true);
	}

}
