import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Balance {
	
	private static long Balance;
	private static boolean exist = false;
	private static final String FILE_NAME = "Balance.txt";
	
	public Balance() {
		set(0);
		updateFile();
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
	
	public static int getBalance() {
		return (int) Balance;
	}
	
	public static void add(int input) {
		Balance+= input;
		updateFile();
	}
	
	public static void remove(int input) {
		Balance-= input;
		updateFile();
	}
	
	public static void add(Long input) {
		Balance+= input;
		updateFile();
	}
	
	public static void remove(Long input) {
		Balance-= input;
		updateFile();
	}
	
	public String toString() {
		return String.valueOf(get());
	}
	
	private static void updateFile() {
		try {
			File Data = new File(FILE_NAME);
			BufferedWriter writeFile = new BufferedWriter(new FileWriter(Data, true));
			
			
				Data.createNewFile();
				writeFile.write(getBalance());
				writeFile.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
