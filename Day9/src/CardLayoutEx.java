import java.awt.*;

public class CardLayoutEx extends FrameEx1{

	CardLayoutEx card;
	Panel first_panel, second_panel, third_panel;
	
	public CardLayoutEx() {
		super("CardLayout qwer") ; 
		card = new CardLayout();
		
		setLayout(card);
		
		first_panel = new Panel();
		first_panel.add(new Button("1"));
		first_panel.add(new Button("2"));
		
		second_panel = new Panel();
		second_panel.add(new Button("1"));
		second_panel.add(new Button("2"));
		
		third_panel = new Panel();
		third_panel.add(new Button("1"));
		third_panel.add(new Button("2"));
		
		add("1", first_panel);
		add("2", second_panel);
		add("3", third_panel);
	}
	
	
	
	
	public static void main(String[] args) {
		
	}
	
}
