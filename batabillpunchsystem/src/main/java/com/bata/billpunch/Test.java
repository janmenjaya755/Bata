package com.bata.billpunch;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String[] args) throws IOException {
		
		List<Integer> list= new ArrayList<>();
		list.addAll(Arrays.asList(12));
		
		
		List<String> list1= new ArrayList<>();
		list1.addAll(Arrays.asList("BILL16092020aaaaaaaa"));
		String test = null;
		for(int i=0; i<list1.size();i++) {
			String s =list1.get(i);
			int size2 =list.get(i);
			StringBuffer buf = new StringBuffer(s);
			if (buf.length() == size2) {
				String output = buf.toString();
				test = output;
			} else {
				if (buf.length() < size2) {

					while (buf.length() < size2) {
						buf.insert(0, '0');
						String output = buf.toString();
						test = output;
						System.out.println("adding zero");
					}
				} else {
					String sx = buf.substring(0,size2);
					test = sx;
					System.out.println("use substring");
				}
			}
			
		}
	
		File f = new File("C:\\report\\testreporttttttttttttt.edp");
		FileWriter writer = new FileWriter(f, true);
		writer.write(test);
		writer.close();
		System.out.println("write successful");
	}
}
