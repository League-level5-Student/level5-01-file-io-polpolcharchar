package _03_To_Do_List;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 *
	 * When add task is clicked:
	 * 		Create a JOptionPane to ask the user for a task and add it to an ArrayList
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		Create a JOptionPane to prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Create a JOptionPane to Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list. 
	 */
	
	JFrame f;
	JPanel p;
	JButton add;
	JButton view;
	JButton remove;
	JButton save;
	JButton load;
	
	ArrayList<String> list;
	
	
	
	public static void main(String[] args) {
		new ToDoList().setup();
	}
	
	void setup() {
		list = new ArrayList<String>();
		f = new JFrame();
		p = new JPanel();
		add = new JButton("Add Task");
		view = new JButton("View Tasks");
		remove = new JButton("Remove Task");
		save = new JButton("Save List");
		load = new JButton("Load List");
		
		f.add(p);
		p.add(add);
		p.add(view);
		p.add(remove);
		p.add(save);
		p.add(load);
		f.setVisible(true);
		f.pack();
		
		try {
			BufferedReader r = new BufferedReader(new FileReader("src/_03_To_Do_List/list.txt"));
			list = new ArrayList<String>();
			String next = r.readLine();
			do {
				list.add(next);
				next = r.readLine();
			}while(next != null);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		add.addActionListener((e) -> {
			//add task
			String currentList = "";
			try {
				FileWriter w = new FileWriter("src/_03_To_Do_List/list.txt");
				list.add(currentList.length() > 0 ? "\n" : "" + JOptionPane.showInputDialog("Enter a task"));
				String result = "";
				for(int i = 0; i < list.size(); i++) {
					result += list.get(i) + "\n";
				}
				w.write(result);
				w.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		view.addActionListener((e) -> {
			//view tasks
			String result = "";
			for(int i = 0; i < list.size(); i++) {
				result += list.get(i) + "\n";
			}
			JOptionPane.showMessageDialog(null, result);
		});
		remove.addActionListener((e) -> {
			//remove task
			String result = "";
			for(int i = 0; i < list.size(); i++) {
				result += i + ": " + list.get(i) + "\n";
			}
			int index = -1;
			do {
				index = Integer.parseInt(JOptionPane.showInputDialog(result + "\nEnter an index:"));
			}while(index < 0 || index >= list.size());
			list.remove(index);
		});
		save.addActionListener((e) -> {
			//save list
			try {
				FileWriter w = new FileWriter("src/_03_To_Do_List/list.txt");
				String result = "";
				for(int i = 0; i < list.size(); i++) {
					result+=list.get(i) + "\n";
				}
				w.write(result);
				w.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		load.addActionListener((e) -> {
			//load list
			JFileChooser f = new JFileChooser();
			int returnVal = f.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String fileName = f.getSelectedFile().getAbsolutePath();
				try {
					BufferedReader r = new BufferedReader(new FileReader(fileName));
					list = new ArrayList<String>();
					String next = r.readLine();
					do {
						list.add(next);
						next = r.readLine();
					}while(next != null);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
//Property of Jack Fitterer\