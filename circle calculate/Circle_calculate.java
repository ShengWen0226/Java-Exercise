package circle_calculate;

import java.util.Scanner;

public class Circle_calculate {

	public static void main(String[] args) {
		Circle c = new Circle();
		Scanner keyboard = new Scanner(System.in);
		System.out.print("輸入名稱:");
		c.name = keyboard.next();
		System.out.print("輸入半徑:");
		c.radius = keyboard.nextInt();
		System.out.print("輸入高度:");
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
