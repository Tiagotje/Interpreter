package main;

import java.util.*;

import javax.swing.*;

public class Interpreter {
	
	String[] CodeTypes = {"byte","char","int","float","long","double","str"};
	Class<?>[] types = {Byte.class, Character.class, Integer.class, Float.class, Long.class, Double.class, String.class};
	
	Map<String,Object> vval = new HashMap<String,Object>();
	Map<String,Class<?>> vtype = new HashMap<String, Class<?>>();  
	
	
	Interpreter(ArrayList<String> c){
		int i = 1;
		for(String line: c){
			String[] la = line.split("\\s");
			
			System.out.println("Processing line: " + line);
			
			//Variable declaration;
			if(Arrays.asList(CodeTypes).contains(la[0])){
				if(la.length != 4){
					Errors.Expected("length to be 4", i);
				}
				else if(Arrays.asList(CodeTypes).contains(la[0])){
					vtype.put(la[1], types[Arrays.asList(CodeTypes).indexOf(la[0])]);
					vval.put(la[1],	( toObject( vtype.get(la[1]), la[3] ) ) );
				}
				continue;
			}
			
			//Variable change
			if(vval.containsKey(la[0])){
				if(la.length != 3){
					Errors.Expected("length to be 3", i);
				}
				else if((vval.containsKey(la[0]) && !Arrays.asList(CodeTypes).contains(la[0])) && la[1] == "="){
					vval.put(la[0], la[2]);
				}
				continue;
			}
			

			
			if(line.startsWith("print(")){
				for(char s: Arrays.copyOfRange(line.toCharArray(), 6, line.length() -1)){
					
				}
				JOptionPane.showMessageDialog(Main.f, new String(Arrays.copyOfRange(la[0].toCharArray(), 6 ,la[0].length()-1)), "print", JOptionPane.PLAIN_MESSAGE);
			}
			
			
			i++;
		}
	}
	
	public static Object toObject( Class<?> clazz, String value ) {
	    if( Boolean.class == clazz ) return Boolean.parseBoolean( value );
	    if( Byte.class == clazz ) return Byte.parseByte( value );
	    if( Short.class == clazz ) return Short.parseShort( value );
	    if( Integer.class == clazz ) return Integer.parseInt( value );
	    if( Long.class == clazz ) return Long.parseLong( value );
	    if( Float.class == clazz ) return Float.parseFloat( value );
	    if( Double.class == clazz ) return Double.parseDouble( value );
	    return value;
	}
	
}
