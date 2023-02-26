import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Arrays;

public class Stability {
	//主程式
	public static void main(String[] args) throws IOException {
		
		ReadFile ReadFile = new ReadFile();
		Calculate Calculate = new Calculate();
		ReadFile.fileName = "D:/Hydrostatics.txt";
		ReadFile.totalLine = ReadFile.getLineNumber();
		ReadFile.findName = "Trim";
		int dataLocation = ReadFile.findData();
		ReadFile.printAllLine(dataLocation,dataLocation);
		String[] Names = ReadFile.setName(1,dataLocation);
		String[] Unit = ReadFile.setName(2,dataLocation);
		Calculate.Data = ReadFile.setAllData(3,dataLocation);
		double[][] test = Calculate.getMTC();
		System.out.println(Arrays.toString(test[1]));
		System.out.println(Calculate.findMax(test));
	}
	
}

class ReadFile {
	static String fileName;
	static int totalLine;
	static String findName;
	//計算總行數
	public static int getLineNumber() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		LineNumberReader lr = new LineNumberReader(br);
		String s = lr.readLine();
		int lines = 0;
		while(s!=null) {
			lines++;
			s = lr.readLine();
		}
		lr.close();
		br.close();
		return lines;
	}
	//尋找數據位置
	public int findData() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		LineNumberReader lr = new LineNumberReader(br);
		String s = lr.readLine();
		int line = 1;
		while(s.lastIndexOf(findName)<0) {
			line++;
			s = lr.readLine();
		}
		lr.close();
		br.close();
		return line;
	}
	//儲存字串(單行)
	public static String[] setName(int dataFrom,int dataLocation) throws IOException {
		
		String line = getLine(dataFrom,dataLocation);
		String[] name = line.trim().split("\\s+");
		return name;
	}
	//儲存數據(單行)
	public static double[] setDataLine(int dataFrom,int dataLocation) throws IOException {
		
		String[] line = setName(dataFrom,dataLocation);
		double[] DataLine = new double[line.length];
		for(int i=0;i<line.length;i++) {
			DataLine[i] = Double.valueOf(line[i]);
		}
		return DataLine;
	}
	//儲存數據(多行)
	public double[][] setAllData(int dataFrom,int dataLocation) throws IOException{
		
		double[] DataLine = setDataLine(dataFrom,dataLocation);
		double[][] AllData = new double[totalLine-dataLocation-dataFrom+2][DataLine.length];
		for(int i=0;i<totalLine-dataLocation-dataFrom+2;i++) {
			DataLine = setDataLine(dataFrom+i,dataLocation);
			for(int j=0;j<DataLine.length;j++) {
				AllData[i][j] = DataLine[j];
			}
		}
		return AllData;
	}
	//取得單行
	public static String getLine(int dataFrom,int dataLocation) throws IOException {  
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String s = br.readLine();
		String line = "";
		if(dataLocation<=0 || dataLocation+dataFrom-1>getLineNumber())
			System.out.println("行數錯誤");
		else {
			dataLocation=dataLocation+dataFrom-1;
			int num = 1;
			while(s!=null){
				if(dataLocation == num++) {
					line = s;
				}
				s = br.readLine();
			}
			br.close();
		}
			return line;
	}
	//輸出全數據
	public String printAllLine(int dataFrom,int dataLocation) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line = br.readLine();
		if(dataLocation>totalLine) { 
			br.close();
			return "";
		}
		else {
			int num = 1;
			while(line!=null){
				if(dataLocation == num++) {
					System.out.println((dataLocation-dataFrom+1)+") "+line);
				}
				line = br.readLine();
			}
			br.close();
			return printAllLine(dataFrom,dataLocation+1);
		}
	}

}

class Calculate {
	static double[][] Data;
	//計算Cb(方塊係數)=V/LBD
	public double[][] getCb(){
		
		double[][] Cb = new double[2][Data.length];
		for(int i=0;i<Data.length;i++) {
			Cb[0][i] = Data[i][0];
			Cb[1][i] = Data[i][4]/(Data[i][2]*Data[i][3]*Data[i][0]);
		}
		return Cb;
	}
	//計算Cm(舯剖面係數)=Am/BD
	public double[][] getCm(){
			
		double[][] Cm = new double[2][Data.length];
		for(int i=0;i<Data.length;i++) {
			Cm[0][i] = Data[i][0];
			Cm[1][i] = Data[i][9]/(Data[i][3]*Data[i][0]);
		}
		return Cm;
	}
	//計算Cw(水線面係數)=Aw/LB
	public double[][] getCw(){
		
		double[][] Cw = new double[2][Data.length];
		for(int i=0;i<Data.length;i++) {
			Cw[0][i] = Data[i][0];
			Cw[1][i] = Data[i][11]/(Data[i][2]*Data[i][3]);
		}
		return Cw;
	}
	//計算Cp(稜塊係數)=V/AmL=Cb/Cm
	public double[][] getCp(){
		
		double[][] Cb = getCb();
		double[][] Cm = getCm();
		double[][] Cp = new double[2][Data.length];
		for(int i=0;i<Data.length;i++) {
			Cp[0][i] = Data[i][0];
			Cp[1][i] = Cb[1][i]/Cm[1][i];
		}
		return Cp;
	}
	//計算TPC(每公分排水頓)=1.025*Aw/100
	public double[][] getTPC(){
			
		double[][] TPC = new double[2][Data.length];
		for(int i=0;i<Data.length;i++) {
			TPC[0][i] = Data[i][0];
			TPC[1][i] = 1.025*Data[i][11]/100;
		}
		return TPC;
	}
	//計算MTC(俯仰差力矩)=VGM/100*300
	public double[][] getMTC(){
				
		double[][] MTC = new double[2][Data.length];
		for(int i=0;i<Data.length;i++) {
			MTC[0][i] = Data[i][0];
			MTC[1][i] = Data[i][5]*Data[i][17]/(100*300);
		}
		return MTC;
	}
	public double findMax(double data[][]) {
		
		double max=0;
		for(int i=0;i<data[0].length;i++) {
			if(data[1][i]>max)
				max = data[1][i];
		}
		return max;
	}
		
			
}
