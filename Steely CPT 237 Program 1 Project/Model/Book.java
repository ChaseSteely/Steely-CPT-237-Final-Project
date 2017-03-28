//FILE: Book.java
//PROG: Marshall Chase Steely
//PURP: Book object which is the parent of Kindle and Print.

package edu.tridenttech.CPT237.Steely.Library.Model;

import java.util.Comparator;

public class Book {

	protected String title;
	protected String type;
	protected String author;

	HomeLibrary hl = HomeLibrary.getInstance();

	public Book(String author, String title, String type) {

		this.author = author;
		this.title = title;
		this.type = type;

	}

	public String getBookTitle() {

		return this.title;
	}

	public String getBookType() {
		return this.type;
	}

	public String getAuthor() {
		return this.author;
	}

	
	static public class AuthorCompare implements Comparator<Book> {
		public int compare(Book b1, Book b2) {
			return b1.getAuthor().compareTo(b2.getAuthor());
		}
	}

	static public class TitleCompare implements Comparator<Book> {
		public int compare(Book b1, Book b2) {
			return b1.getBookTitle().compareTo(b2.getBookTitle());
		}
	}

	@Override
	public String toString() {
		return getBookTitle().concat(" by " + getAuthor()).concat(" -" + getBookType());
	}


}
