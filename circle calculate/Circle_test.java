package circle_calculate;

import java.util.Scanner;

public class Circle_test {

	public static void main(String[] args) {
		CircleT c = new CircleT();
		Scanner keyboard = new Scanner(System.in);
		System.out.print("��J�W��:");
		c.name = keyboard.next();
		System.out.print("��J�b�|:");
		c.radius = keyboard.nextInt();
		System.out.print("��J����:");
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
		System.out.println(name+"���P��:"+getGirth());
		System.out.println(name+"�����n:"+getArea());
		System.out.println(name+"����n:"+getVolume());
	}
}
