import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Balance {
	
	private static long Balance;
	private static boolean exist = false;
	private static final String FILE_NAME = "Balance.txt";
	
	public Balance() {
		File file = new File(FILE_NAME);
		
		if(exist)

		set(0);
	}
	
	public Balance(int newBalance) {
		set(newBalance);
	}
	
	public static void set(int input) {
		Balance = Long.valueOf(input);
	}
	
	public static void set(Long input) {
		Balance = input;
	}
	
	public static Long get() {
		return Balance;
	}
	
	public static void add(int input) {
		Balance+= input;
	}
	
	public static void remove(int input) {
		Balance-= input;
	}
	
	public static void addB(Long input) {
		Balance+= input;
	}
	
	public static void remove(Long input) {
		Balance-= input;
	}
	
	public String toString() {
		return String.valueOf(get());
	}
	
	private void updateFile() throws IOException {
		File file = new File(FILE_NAME);
		
		if(!file.exists()) {
			file.createNewFile();
		}
//		if (file.isFile() && file.canRead()) {
//			PrintWriter pw = new PrintWriter(FILE_NAME);
//			pw.close();
//			FileWriter fw = new FileWriter(file.getAbsoluteFile());
//		    BufferedWriter bw = new BufferedWriter(fw);
//		    bw.write(this.toString());
//		    bw.close();
//			
//		}else {
//			
//		}
		
	}
	
/*
	public static void main(String args[]){
		Balance b = new Balance();
		System.out.println(b.getBalance());
		b.setBalance(25);
		System.out.println(b.getBalance());
		b.addBalance(11);
		System.out.println(b.getBalance());
		b.subtractBalance(9);
		System.out.println(b.getBalance());
		
	}
*/
}
