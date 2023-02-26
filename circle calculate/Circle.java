package circle_calculate;

public class Circle {
	String name;
	int radius;
	int high;
	double getGirth() {
		return radius*2*3.14159;
	}
	double getArea() {
		return radius*radius*3.14159;
	}
	double getVolume() {
		return getArea()*high;
	}
	void showAnswer() {
		System.out.println(name+"的周長:"+getGirth());
		System.out.println(name+"的面積:"+getArea());
		System.out.println(name+"的體積:"+getVolume());
	}
}
