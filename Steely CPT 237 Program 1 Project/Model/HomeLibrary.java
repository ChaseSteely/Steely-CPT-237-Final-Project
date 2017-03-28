//FILE: HomeLibray.java
//PROG: Marshall Chase Steely
//PURP: Creates a Library object by loading csv file and creating books.

package edu.tridenttech.CPT237.Steely.Library.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HomeLibrary {

	private static HomeLibrary instance = new HomeLibrary();

	protected ArrayList<Book> book = new ArrayList<>();
	protected ArrayList<LoanedOrSold> ls = new ArrayList<LoanedOrSold>();

	private HomeLibrary() {

	}

	public static HomeLibrary getInstance() {
		return instance;
	}

	public void loadLibrary(String filePath) throws FileNotFoundException {
		Scanner input;
		input = new Scanner(new File(filePath));

		while (input.hasNext()) {
			String line = input.nextLine();
			String[] fields = line.split(",");
			switch (fields[0].charAt(0)) {
			case 'K': {
				String type = fields[1];
				String author = fields[2];
				String title = fields[3];

				createBook(type, author, title);
			}
				break;
			case 'P': {
				String type = fields[1];
				String author = fields[2];
				String title = fields[3];

				createBook(type, author, title);
			}
				break;

			default: {

			}

			}
		}
		input.close();
	}

	public void createBook(String type, String author, String title) {

		switch (type.charAt(0)) {

		case 'K': {
			createKindle(type, author, title);
		}
			break;

		case 'P': {
			createPrint(type, author, title);
		}
			break;
		}
	}

	public boolean createPrint(String type, String author, String title) {
		if (findBookByTitle(title) != null) {
			return false;
		}

		PrintBook print = new PrintBook(author, title, "Print Book");
		book.add(print);
		return true;
	}

	public boolean createKindle(String type, String author, String title) {
		if (findBookByTitle(title) != null) {
			return false;
		}

		KindleBook kindle = new KindleBook(author, title, "Kindle Book");
		book.add(kindle);
		return true;

	}

	public Book findBookByTitle(String title) {
		Book bk = null;
		Optional<Book> match = book.stream().filter(e -> e.getBookTitle().contains(title)).findAny();
		if (match.isPresent()) {
			bk = match.get();
		}
		return bk;
	
	}

	public List<Book> findBookByAuthor(String author) {
		List<Book> returnList = new ArrayList<>();
		for (Book b : book) {
			if (b.getAuthor().contains(author)) {
				returnList.add(b);
			}
		}
		return returnList;
	}
	
	public List<Book> keywordBooks(String title){
		List<Book> rList = new ArrayList<>();
		for (Book b : book) {
			if (b.getBookTitle().contains(title)) {
				rList.add(b);
			}
		}
		return rList;
	}

	public List<Book> getAllBooks() {
		ArrayList<Book> books = new ArrayList<>();
		books.addAll(book);
		Collections.sort(books, new Book.TitleCompare());

		return books;
	}

	public boolean loanBook(Book book, String name) {

		if (!ls.contains(book)) {
			LoanedOrSold l = new LoanedOrSold("Loaned", book.getAuthor(), book.getBookTitle(), name);
			ls.add(l);
			return true;
		} else
			return false;

	}

	public ArrayList<LoanedOrSold> getLSlist() {
		return new ArrayList<LoanedOrSold>(ls);
	}

	public boolean sellBook(Book book) {

		if (!ls.contains(book)) {
			LoanedOrSold l = new LoanedOrSold("Sold", book.getAuthor(), book.getBookTitle(), " someone or donated.");
			ls.add(l);
			return true;
		} else
			return false;
	}

}// END HomeLibrary Class
