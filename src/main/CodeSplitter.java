package main;

import java.util.*;

public class CodeSplitter {
	
	ArrayList<String> code = new ArrayList<String>();
	
	public CodeSplitter(String c1){
		code = splitCode(c1);
		printCode();
		new Interpreter(code);
	}
	
	private ArrayList<String> splitCode(String c1){
		StringBuilder sb = new StringBuilder(c1);
		int i = 0;
		int j = 1;
		while(i < c1.length()){
			if(c1.toCharArray()[i] == '{' || c1.toCharArray()[i] == '}'){
				sb.insert(i+j, '§');
				j++;
			}
			i++; 
		}
		String c = sb.toString();
		String[] lines = c.split("[;§]");
		for(int h = 0; h<lines.length; h++){
			lines[h] = lines[h].replaceAll("[\\n\\x0B\\f\\r]", "");
		}
		ArrayList<String> ret = new ArrayList<String>();
		for(String s: lines){
			if(!s.isEmpty()){
				ret.add(s.trim());
			}
		}
		return ret;
	}
	
	private void printCode(){
		System.out.println("\n\n\n\n\n------------------------------");
		for(String l : code){
				System.out.println(l + "\n---");
		}
		System.out.println("------------------------------");
	}
	
}
