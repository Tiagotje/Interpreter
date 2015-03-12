package main;

import javax.swing.JOptionPane;

public class Errors {
	
	public static void Error(String s, int l){
		System.err.println("ERROR: " + s);
		JOptionPane.showMessageDialog(Main.f, s + "at line: " + l, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void Expected(String s, int l){
		Error("Expected: " + s, l);
	}
	
}
