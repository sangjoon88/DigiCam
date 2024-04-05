package z_Practice;

class Car {
	String color;
	String gearType;
	int door;
	
	
	Car() {

	}
	
	Car(String color, String gearType) {
		this.color = color;
		this.gearType=gearType;
	}
	
	Car(String color, String gearType, int door) {
		this(color, gearType);
		this.door=door;
	}
	
	
}



public class Car_Test2 {

	public static void main (String [] args) {
		
	Car c1 = new Car("Black", "manual", 4);
	
	System.out.println(c1.color+c1.gearType+c1.door);
		
		
	}
	
	
}
