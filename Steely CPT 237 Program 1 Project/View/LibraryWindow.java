//FILE: LibraryWindow.java
//PROG: Marshall Chase Steely
//PURP: The main ui window for the program.

package edu.tridenttech.CPT237.Steely.Library.View;

import edu.tridenttech.CPT237.Steely.Library.Model.Book;
import edu.tridenttech.CPT237.Steely.Library.Model.HomeLibrary;
import edu.tridenttech.CPT237.Steely.Library.Model.LoanedOrSold;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class LibraryWindow implements EventHandler<ActionEvent> {

	private Stage myStage;
	private ComboBox<Book> books = new ComboBox<>();
	private AddBook aBook;
	private SearchWindow s;
	DropShadow shadow = new DropShadow();

	HomeLibrary h = HomeLibrary.getInstance();
	Button done;
	Button add;
	Button loan;
	Button search;
	Button remove;
	private TextArea ta;
	private TextField name;
	Button clear;

	public LibraryWindow(Stage stage) {

		FlowPane pane = new FlowPane();
		Scene scene = new Scene(pane);
		myStage = stage;
		myStage.setScene(scene);
		myStage.setTitle("Chase's Library");
		aBook = new AddBook(books);
		s = new SearchWindow();

		pane.getChildren().add(books);
		books.setPromptText("Choose a book from the drop-down list to loan or remove. "
				+ "Or click 'Add Book' to add a new book to the library. Or click 'Search' to find a book or author.");
		books.setVisibleRowCount(25);
		books.getItems().setAll(h.getAllBooks());

		add = new Button("Add Book");
		add.setStyle("-fx-base: green");
		pane.getChildren().add(add);
		add.setOnAction((event) -> {
			if (!aBook.isShowing()) {
				aBook.show();
			} else {
				aBook.toFront();
			}
		});

		add.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				add.setEffect(shadow);
			}
		});

		add.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				add.setEffect(null);
			}
		});

		remove = new Button("Remove");
		remove.setStyle("-fx-base: red");
		pane.getChildren().add(remove);
		remove.setOnAction(this);

		remove.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				remove.setEffect(shadow);
			}
		});

		remove.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				remove.setEffect(null);
			}
		});

		search = new Button("Search");
		search.setStyle("-fx-base: blue");
		pane.getChildren().add(search);
		search.setOnAction(this);

		search.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				search.setEffect(shadow);
			}
		});

		search.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				search.setEffect(null);
			}
		});

		done = new Button("Done");
		pane.getChildren().add(done);
		done.setOnAction(this);

		name = new TextField();
		name.setPromptText("Enter the Name of Person the Book is being Loaned to. Click 'Loan'");
		name.setPrefWidth(500);
		pane.getChildren().add(name);
		loan = new Button("Loan");
		loan.setStyle("-fx-base: orange");
		pane.getChildren().add(loan);
		loan.setOnAction(this);

		loan.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				loan.setEffect(shadow);
			}
		});

		loan.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				loan.setEffect(null);
			}
		});
		
		clear = new Button("Clear");
		clear.setStyle("-fx-base: black");
		pane.getChildren().add(clear);
		clear.setOnAction(this);

		ta = new TextArea();
		ta.setPrefWidth(900);
		pane.getChildren().add(ta);

	}

	@Override
	public void handle(ActionEvent event) {
		Button button = (Button) (event.getSource());

		if (button == done) {
			myStage.close();
		}

		if (button == search) {
			if (!s.isShowing()) {
				s.show();
			} else {
				s.toFront();
			}
		}

		if (button == remove) {

			h.sellBook(books.getValue());
			books.getItems().remove(books.getValue());
			updateLoanedBooks();

		}

		if (button == loan) {

			h.loanBook(books.getValue(), name.getText());
			books.getItems().remove(books.getValue());
			updateLoanedBooks();

		}
		if (button == clear) {
			name.clear();
			books.getSelectionModel().clearSelection();
			ta.clear();
		}
	}

	private void updateLoanedBooks() {

		ta.clear();
		for (LoanedOrSold ls : h.getLSlist()) {
			ta.appendText(ls.toString());
			ta.appendText("\n");
		}

	}

	public void show() {
		myStage.show();

	}

}
