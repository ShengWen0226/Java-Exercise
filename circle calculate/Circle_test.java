package circle_calculate;

import java.util.Scanner;

public class Circle_test {

	public static void main(String[] args) {
		CircleT c = new CircleT();
		Scanner keyboard = new Scanner(System.in);
		System.out.print("輸入名稱:");
		c.name = keyboard.next();
		System.out.print("輸入半徑:");
		c.radius = keyboard.nextInt();
		System.out.print("輸入高度:");
		c.high = keyboard.nextInt();
		c.showAnswer();
		keyboard.close();
	}

}
class CircleT {
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
