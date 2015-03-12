package main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.prefs.*;

import javax.swing.*;
import javax.swing.undo.*;

@SuppressWarnings("serial")
public class Main extends AbstractAction{
	
	String source;
	

	static File fl;
	static JTextArea ta;
	public static JFrame f;
	static UndoManager manager;
	static Preferences pref;
	static String[] path = new String[2];
	
	public Main(String s){
		super(s);
		source = s;
	}
	
	public static void main(String[] args){
		f = new JFrame("Tiago's programming language");
		ta = new JTextArea();
		manager = new UndoManager();
		pref = Preferences.systemRoot();
		ta.getDocument().addUndoableEditListener(manager);
		JScrollPane scroll = new JScrollPane (ta);
		ta.setTabSize(4);
		path[0] = pref.get("p1", null);
		path[1] = pref.get("p2", null);
		
		JMenuBar tb = new JMenuBar();
		
		JMenu menu = new JMenu("Save");
		menu.add(new JButton(new Main("Load")));
		menu.add(new JButton(new Main("Save")));
		tb.add(menu);
		
		menu = new JMenu("Edit");
		menu.add(new JButton(new Main("Undo")));
		menu.add(new JButton(new Main("Redo")));
		tb.add(menu);
		
		tb.add(new JButton(new Main("Run&Save")));
		
		tb.add(new JButton(new Main("Run")));

		
		f.add(scroll);
		f.setSize(600, 600);
		f.add(tb, BorderLayout.PAGE_START);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static void SaveFile(){
		FileDialog fd = new FileDialog(f, "Save file", FileDialog.SAVE);
		if(path[0] != null){
			fd.setDirectory(path[0]);
		}
    	fd.setVisible(true);
    	if(fd.getFile() != null){
    		try {
    			PrintWriter writer = new PrintWriter(fd.getDirectory() + fd.getFile(), "UTF-8");
    			writer.print(ta.getText());
    			writer.close();
    		} catch (Exception e) { e.printStackTrace();}
    	}
	}
	
	static void LoadFile(){
		FileDialog fd = new FileDialog(f, "Open file", FileDialog.LOAD);
		if(path[0] != null){
			fd.setDirectory(path[0]);
			fd.setFile(path[1]);
		}
    	fd.setVisible(true);
    	if(fd.getFile() != null){
    		pref.put("p1", fd.getDirectory());
    		pref.put("p2", fd.getFile());
    		try {
    			fl = new File(fd.getDirectory() + fd.getFile());
    			Scanner s = new Scanner(fl);
    			ta.setText("");
    			while(s.hasNextLine()){ta.append(s.nextLine() + "\n");}
    				s.close();
    		} catch (FileNotFoundException e) { e.printStackTrace(); }
    	}
	}

	public void actionPerformed(ActionEvent ae) {
		switch(source){
		case "Undo":{ if(manager.canUndo()){manager.undo();} break;}
		case "Redo":{ if(manager.canUndo()){manager.redo();} break;}
		case "Save":{ SaveFile(); break;}
		case "Load":{ LoadFile(); break;}
		case "Run":{new CodeSplitter(ta.getText()); break;}
		case "Run&Save":{ SaveFile(); new CodeSplitter(ta.getText()); break;}
		}
		
	}
	
}
