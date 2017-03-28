//FILE: LoanedorSold.java
//PROG: Marshall Chase Steely
//PURP: Used to keep track of what books are loaned or removed.

package edu.tridenttech.CPT237.Steely.Library.Model;

public class LoanedOrSold {

	protected String type;

	protected String author;
	protected String title;
	protected String name;

	public LoanedOrSold(String tp, String a, String ttl, String n) {
		type = tp;
		author = a;
		title = ttl;
		name = n;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Status: " + type + " to " + name + " " + title.concat(" by " + getAuthor());
	}

}
