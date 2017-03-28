//FILE: MainClass.java
//PROG: Marshall Chase Steely
//PURP: Tested to make sure file loaded.

package edu.tridenttech.CPT237.Steely.Library.Model;

import java.io.FileNotFoundException;

import java.util.Scanner;

public class MainClass {

	static Scanner console = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		HomeLibrary hl = HomeLibrary.getInstance();
		hl.loadLibrary("C:/Users/Marshall/workspace/Steely CPT 237 Final Project/Books.csv");
		//System.out.printf("%s%n", hl.getAllBooks());
		Input();
	}
	
	private static void Input(){
		
		String title = "";
		System.out.println("Welcome to Chase's Home Library");
		HomeLibrary hl = HomeLibrary.getInstance();
		
		System.out.println("Please enter the Book Title you are looking for.");
		title = console.nextLine();
		
		Book book = hl.findBookByTitle(title);
		
		if (book != null)
		{
			System.out.printf("%s%n%s%n%s",
					book.getBookType(),
					book.getBookTitle(),
					book.getAuthor());
		}
	
    
	}

}
