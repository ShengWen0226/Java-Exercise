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
		System.out.println(name+"���P��:"+getGirth());
		System.out.println(name+"�����n:"+getArea());
		System.out.println(name+"����n:"+getVolume());
	}
}
