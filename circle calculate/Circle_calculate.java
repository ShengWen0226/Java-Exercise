package circle_calculate;

import java.util.Scanner;

public class Circle_calculate {

	public static void main(String[] args) {
		Circle c = new Circle();
		Scanner keyboard = new Scanner(System.in);
		System.out.print("��J�W��:");
		c.name = keyboard.next();
		System.out.print("��J�b�|:");
		c.radius = keyboard.nextInt();
		System.out.print("��J����:");
		c.high = keyboard.nextInt();
		/*
		double girth = c.getGirth();
		double area = c.getArea();
		double volume = c.getVolume();
		*/
		c.showAnswer();
		keyboard.close();
	}

}
