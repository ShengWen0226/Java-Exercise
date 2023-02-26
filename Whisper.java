import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Whisper {

	public static void main(String[] args) {
		Convert cv = new Convert();
		Scanner sc = new Scanner(System.in);
		cv.str = sc.nextLine();
		cv.outputByte();
		cv.num = sc.nextLine();
		cv.outputString();
		sc.close();
	}
}

class Convert {
	String str;
	String num;

	byte[] toByte() {
		byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
		return bytes;
	}

	String getString() {
		String[] numArray = num.split("\s");
		byte[] byArray = new byte[numArray.length];
		for (int i = 0; i < numArray.length; i++)
			byArray[i] = Byte.parseByte(numArray[i]);
		String str = "";
		for (int i = 0; i < byArray.length; i++)
			str += (char) byArray[i];
		return str;
	}

	void outputByte() {
		byte[] bytes = toByte();
		for (byte b : bytes)
			System.out.print(b + "\s");
		System.out.println("\n");
	}

	void outputString() {
		System.out.println(getString());
	}
}
